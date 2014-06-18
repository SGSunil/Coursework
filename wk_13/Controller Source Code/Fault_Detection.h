#ifndef __FAULT_DETECTION_
#define __FAULT_DETECTION_

#include "GPIO_Bits.h"
#include "Bit_Operations.h"
#include "Unit_Conversion.h"

unsigned int variation = 1; // in adc count 1 acd = 0.005 

//different fault types, plan to put the faults in a 32 bit integeron different bit positions.
enum Fault_Types
{
    SEAT_OCCUPLIED_SWITCH_NOT_FITTED_SEAT_OCCUPIED = 1 << 0,
    KILL_SWITCH_NOT_FITTED_KILL_SWITCH_PRESSED = 1 << 1,
    SEAT_HEIGHT_ABOVE_RANGE = 1 << 2,
    SEAT_HEIGHT_BELOW_RANGE = 1 << 3,
    X_POSITION_ABOVE_RANGE = 1 << 4,
    X_POSITION_BELOW_RANGE = 1 << 5,
    Y_POSITION_ABOVE_RANGE = 1 << 6,
    Y_POSITION_BELOW_RANGE = 1 << 7,
    LEFT_MOTOR_SPEED_ABOVE_RANGE = 1 << 8,
    LEFT_MOTOR_SPEED_BELOW_RANGE = 1 << 9,
    RIGHT_MOTOR_SPEED_ABOVE_RANGE = 1 << 10,
    RIGHT_MOTOR_SPEED_BELOW_RANGE = 1 << 11
};

//set this variable if fault occur first time.
unsigned int Faults = 0;

//set this variable if fault persists.
unsigned int Persistent_Faults = 0;

double persistent_time = 2; //2 second
double time_difference = 0.015;//15 milliseconds

double kill_time_ms = 0;
double kill_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Kill_Switch_Pressed_Fault(unsigned int GPIO)
{
    if(!Check_Bit_status(GPIO, (unsigned int)GPIO_PORT_0_KILL_SWITCH_FITTED) && Check_Bit_status(GPIO, (unsigned int)GPIO_PORT_0_KILL_SWITCH_SENSE))
    {
        Faults |= (unsigned int)KILL_SWITCH_NOT_FITTED_KILL_SWITCH_PRESSED;
        kill_time_ms += time_difference;

        if(kill_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)KILL_SWITCH_NOT_FITTED_KILL_SWITCH_PRESSED;
            kill_persisted_time_ms = 0;
        }

        return 1;
    }
    else 
    {
        kill_time_ms = 0;
        kill_persisted_time_ms += time_difference;
        if(kill_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)KILL_SWITCH_NOT_FITTED_KILL_SWITCH_PRESSED);
        }

        Faults = Faults & ~((unsigned int)KILL_SWITCH_NOT_FITTED_KILL_SWITCH_PRESSED) ;
        return 0;
    }
}


double seat_time_ms = 0;
double seat_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Seat_Occuplied_Fault(unsigned int GPIO)
{
    if(Check_Bit_status(GPIO, (unsigned int)GPIO_PORT_0_SEAT_OCCUPIED_SWITCH) && !Check_Bit_status(GPIO, (unsigned int)GPIO_PORT_0_SEAT_FITTED_SWITCH))
    {
        Faults |= (unsigned int)SEAT_OCCUPLIED_SWITCH_NOT_FITTED_SEAT_OCCUPIED;
        seat_time_ms += time_difference; 

        if(seat_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)SEAT_OCCUPLIED_SWITCH_NOT_FITTED_SEAT_OCCUPIED;
            seat_persisted_time_ms = 0;
        }

        return 1;
    }
    else 
    {
        seat_time_ms = 0;
        seat_persisted_time_ms += time_difference;
        if(seat_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)SEAT_OCCUPLIED_SWITCH_NOT_FITTED_SEAT_OCCUPIED);
        }

        Faults = Faults & ~((unsigned int)SEAT_OCCUPLIED_SWITCH_NOT_FITTED_SEAT_OCCUPIED) ;
        return 0;
    }
}

double seat_height_time_ms = 0;
double seat_height_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Seat_Height_Fault(unsigned int seat_height_in_adc)
{
    //printf("+++ %d %d ++++ \n", seat_height_in_adc, Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation);
    if(seat_height_in_adc > Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation)
    {
        Faults |= (unsigned int)SEAT_HEIGHT_ABOVE_RANGE;
        seat_height_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)SEAT_HEIGHT_BELOW_RANGE) ;
        Persistent_Faults &= ~((unsigned int)SEAT_HEIGHT_BELOW_RANGE);

        if(seat_height_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)SEAT_HEIGHT_ABOVE_RANGE;
            seat_height_persisted_time_ms = 0;
        }
    }
    else if(seat_height_in_adc < Conver_TO_ADC(0.25, Get_ADC_Level_Per_Voltage() - variation))
    {
        Faults |= (unsigned int)SEAT_HEIGHT_BELOW_RANGE;
        seat_height_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)SEAT_HEIGHT_ABOVE_RANGE) ;
        Persistent_Faults &= ~((unsigned int)SEAT_HEIGHT_ABOVE_RANGE);

        if(seat_height_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)SEAT_HEIGHT_BELOW_RANGE;
            seat_height_persisted_time_ms = 0;
        }
    }
    else 
    {
        seat_height_time_ms = 0;
        seat_height_persisted_time_ms += time_difference;

        if(seat_height_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)SEAT_HEIGHT_ABOVE_RANGE);
            Persistent_Faults &= ~((unsigned int)SEAT_HEIGHT_BELOW_RANGE);
        }

        Faults = Faults & ~((unsigned int)SEAT_HEIGHT_ABOVE_RANGE) ;
        Faults = Faults & ~((unsigned int)SEAT_HEIGHT_BELOW_RANGE) ;

        return 0;
    }

    return 1;
}

double left_motor_time_ms = 0;
double left_motor_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_LEFT_Motor_Speed_Fault(unsigned int speed_in_adc)
{
    if(speed_in_adc > Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation)
    {
        Faults |= (unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE;
        left_motor_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE) ;
        Persistent_Faults &= ~((unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE);

        if(left_motor_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE;
            left_motor_persisted_time_ms = 0;
        }
    }
    else if(speed_in_adc < Conver_TO_ADC(0.25, Get_ADC_Level_Per_Voltage()) - variation)
    {
        Faults |= (unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE;
        left_motor_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE) ;
        Persistent_Faults &= ~((unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE);
        
        if(left_motor_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE;
            left_motor_persisted_time_ms = 0;
        }
    }
    else 
    {
        left_motor_time_ms = 0;
        left_motor_persisted_time_ms += time_difference;

        if(left_motor_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE);
            Persistent_Faults &= ~((unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE);
        }

        Faults = Faults & ~((unsigned int)LEFT_MOTOR_SPEED_ABOVE_RANGE) ;
        Faults = Faults & ~((unsigned int)LEFT_MOTOR_SPEED_BELOW_RANGE) ;

        return 0;
    }


    return 1;
}


double right_motor_time_ms = 0;
double right_motor_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Right_Motor_Speed_Fault(unsigned int speed_in_adc)
{
    if(speed_in_adc > Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation)
    {
        Faults |= (unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE;
        right_motor_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE) ;
        Persistent_Faults &= ~((unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE);
        
        if(right_motor_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE;
            right_motor_persisted_time_ms = 0;
        }
    }
    else if(speed_in_adc < Conver_TO_ADC(0.25, Get_ADC_Level_Per_Voltage()) - variation)
    {
        Faults |= (unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE;
        right_motor_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE) ;
        Persistent_Faults &= ~((unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE);
        
        if(right_motor_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE;
            right_motor_persisted_time_ms = 0;
        }
    }
    else 
    {
        right_motor_time_ms = 0;
        right_motor_persisted_time_ms += time_difference;

        if(right_motor_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE);
            Persistent_Faults &= ~((unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE);
        }

        Faults = Faults & ~((unsigned int)RIGHT_MOTOR_SPEED_ABOVE_RANGE) ;
        Faults = Faults & ~((unsigned int)RIGHT_MOTOR_SPEED_BELOW_RANGE) ;

        return 0;
    }

    return 1;
}


double joystick_x_time_ms = 0;
double joystick_x_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Joystick_X_Fault(unsigned int joystic_x_in_adc)
{
    if(joystic_x_in_adc > Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation)
    {
        Faults |= (unsigned int)X_POSITION_ABOVE_RANGE;
        joystick_x_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)X_POSITION_BELOW_RANGE);
        Persistent_Faults &= ~((unsigned int)X_POSITION_BELOW_RANGE);
        
        if(joystick_x_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)X_POSITION_ABOVE_RANGE;
            joystick_x_persisted_time_ms = 0;
        }
    }
    else if(joystic_x_in_adc < Conver_TO_ADC(0.25, Get_ADC_Level_Per_Voltage()) - variation)
    {
        Faults |= (unsigned int)X_POSITION_BELOW_RANGE;
        joystick_x_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)X_POSITION_ABOVE_RANGE) ;
        Persistent_Faults &= ~((unsigned int)X_POSITION_ABOVE_RANGE);
        
        if(joystick_x_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)X_POSITION_BELOW_RANGE;
            joystick_x_persisted_time_ms = 0;
        }
    }
    else 
    {
        joystick_x_time_ms = 0;
        joystick_x_persisted_time_ms += time_difference;

        if(joystick_x_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)X_POSITION_ABOVE_RANGE);
            Persistent_Faults &= ~((unsigned int)X_POSITION_BELOW_RANGE);
        }

        Faults = Faults & ~((unsigned int)X_POSITION_ABOVE_RANGE) ;
        Faults = Faults & ~((unsigned int)X_POSITION_BELOW_RANGE) ;

        return 0;
    }

    return 1;
}


double joystick_y_time_ms = 0;
double joystick_y_persisted_time_ms = 0;
////////////////////////////////////////////////////////////
int Check_Joystick_Y_Fault(unsigned int joystic_y_in_adc)
{
    if(joystic_y_in_adc > Conver_TO_ADC(4.75, Get_ADC_Level_Per_Voltage()) + variation)
    {
        Faults |= (unsigned int)Y_POSITION_ABOVE_RANGE;
        joystick_y_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)Y_POSITION_BELOW_RANGE);
        Persistent_Faults &= ~((unsigned int)Y_POSITION_BELOW_RANGE);
        
        if(joystick_y_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)Y_POSITION_ABOVE_RANGE;
            joystick_y_persisted_time_ms = 0;
        }
    }
    else if(joystic_y_in_adc < Conver_TO_ADC(0.25, Get_ADC_Level_Per_Voltage()) - variation)
    {
        Faults |= (unsigned int)Y_POSITION_BELOW_RANGE;
        joystick_y_time_ms += time_difference;
        Faults = Faults & ~((unsigned int)Y_POSITION_ABOVE_RANGE) ;
        Persistent_Faults &= ~((unsigned int)Y_POSITION_ABOVE_RANGE);
        
        if(joystick_y_time_ms >= persistent_time)
        {
            Persistent_Faults |= (unsigned int)Y_POSITION_BELOW_RANGE;
            joystick_y_persisted_time_ms = 0;
        }
    }
    else 
    {
        joystick_y_time_ms = 0;
        joystick_y_persisted_time_ms += time_difference;

        if(joystick_y_persisted_time_ms >= persistent_time)
        {
            Persistent_Faults &= ~((unsigned int)Y_POSITION_ABOVE_RANGE);
            Persistent_Faults &= ~((unsigned int)Y_POSITION_BELOW_RANGE);
        }

        Faults = Faults & ~((unsigned int)Y_POSITION_ABOVE_RANGE) ;
        Faults = Faults & ~((unsigned int)Y_POSITION_BELOW_RANGE) ;

        return 0;
    }

    return 1;
}


#endif
