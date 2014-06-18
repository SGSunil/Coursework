#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <signal.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>


#include "Memory_Mapping.h"
#include "Unit_Conversion.h"
#include "GPIO_Bits.h"
#include "Bit_Operations.h"
#include "Fault_Detection.h"
#include "File_Operations.h"

char *file_name;

//Memory buffer where the channels values are written before writing down to file.
char contents[10000000] ;

//Tells when to exit the applicaton, after the expiration of that much seconds
int program_life_in_second = 0;

//For tracking the application running time in seconds, to be matched with program life to exit or not the application.
int time_tracker = 0;

void Read_Channels_Values();

//region - TIMER CREATION

#define FIRST_TIMER (SIGRTMAX)
#define SECOND_TIMER (SIGRTMAX-1)

timer_t  first_timer,second_timer;

///////////////////////////////////////////////////////////////////////////////////////
//Initializes the timer with the time period
///////////////////////////////////////////////////////////////////////////////////////
timer_t SetTimer(int signo, int sec)
{
	struct sigevent sigev;
	timer_t timerid;
	struct itimerspec itval;
	struct itimerspec oitval;

	// Create the POSIX timer to generate signo
	sigev.sigev_notify = SIGEV_SIGNAL;
	sigev.sigev_signo = signo;
	sigev.sigev_value.sival_ptr = &timerid;

	//Create the Timer using timer_create signal
	if (timer_create(CLOCK_REALTIME, &sigev, &timerid) == 0)
	{
		itval.it_value.tv_sec = sec/1000;
		itval.it_value.tv_nsec = (long)(sec % 1000) * (1000000L);

        itval.it_interval.tv_sec = itval.it_value.tv_sec;       // here you arm the timer periodically (that's the meaning of it_interval
        itval.it_interval.tv_nsec = itval.it_value.tv_nsec;

		//Set the Timer when to expire through timer_settime
		if (timer_settime(timerid, 0, &itval, &oitval) != 0)
		{
			perror("time_settime error!");
		}
	}
	else
	{
		perror("timer_create error!");
		return (timer_t)-1;
	}

	return timerid;
}


void Print_ADC_AND_GPIO_Channels();
void Prepare_for_file_output();

///////////////////////////////////////////////////////////////////////////////////////
//Signal handler for timers
///////////////////////////////////////////////////////////////////////////////////////
void SignalHandler(int signo, siginfo_t * info, void *context)
{
	if (signo == FIRST_TIMER)
	{
        //when timer ticks, read updated channel values and write them to the file.
	    Read_Channels_Values();	
        Prepare_for_file_output();

	}
	else if (signo == SECOND_TIMER)
	{
        Print_ADC_AND_GPIO_Channels();
        ++time_tracker;

        if(time_tracker >= program_life_in_second)
        {
            kill(getpid(), SIGINT);
        }
	}
}


////////////////////////////////////////////////////////////////////////////////////////
//This method set up two timers
// timer 1:
//  time period = 15 ms
//  Responsible for reading channels values and checking faults
//
// timer 2:
//  time period = 1 sec
//  Responsible for printing values on GUI
///////////////////////////////////////////////////////////////////////////////////////
int Setup_Timer()
{
	struct sigaction sigact;

	sigemptyset(&sigact.sa_mask);
	sigact.sa_flags = SA_SIGINFO;

	//register the Signal Handler
	sigact.sa_sigaction = SignalHandler; 

	// Set up sigaction to catch signal first timer
	if (sigaction(FIRST_TIMER, &sigact, NULL) == -1)
	{
		perror("sigaction failed");
		return -1;
	}

	// Set up sigaction to catch signal first timer
	if (sigaction(SECOND_TIMER, &sigact, NULL) == -1)
	{
		perror("sigaction failed");
		return -1;
	}


	// Create and set the timer when to expire
	first_timer = SetTimer(FIRST_TIMER, 15);
	second_timer = SetTimer(SECOND_TIMER, 1000);

	return 0;
}

//end region - TIMER CREATION

 
///////////////////////////////////////////////////////////////////////////////////////
/*
    This method gets the ADC Channel value for the ADC channel specified by channel_number.

    input:
    virtual_base_address = the address at which memory is mapped.

    output:
    channel value as read from the memory.
*/
///////////////////////////////////////////////////////////////////////////////////////
unsigned short int Get_ADC_Channel_Value(void* virtual_base_address, int channel_number)
{
    off_t adc_offset = 0x0000000;
    return  *(unsigned short int*)(virtual_base_address + channel_number * 2);
    
}


////////////////////////////////////////////////////////////////////////////////////////
/*
    This method gets the GPIO value for the GPIO channel specified by channel_number.

    input:
    virtual_base_address = the address at which memory is mapped.

    output:
    channel value as read from the memory.
*/
///////////////////////////////////////////////////////////////////////////////////////
unsigned int Get_GPIO_Channel_Value(void* virtual_base_address, int channel_number)
{
    off_t gpio_offset = 0x0000018;
    return  *(unsigned int*)(virtual_base_address + gpio_offset + channel_number * 4);
    
}


//Number of total GPIO channels
int number_of_gpio_channels = 2;

//Number of total ADC channels
int number_of_adc_channels = 8;

//Number of implemented ADC channels, rest are spare.
int number_of_adc_channels_implemented = 5;

//Define the minimum operation range in engg. unit for different channels.
double min_operation_limit[8] = {-1, -100, -100, -200, -200, 0 , 0, 0};

//Define the maximum operation range in engg. unit for different channels.
double max_operation_limit[8]= {6, 100, 100, 200, 200, 5, 5, 5};

//Define the minimum operation range in volts for different channels.
double min_operation_limit_volt[8] = {0.2500f, 0.2500f, 0.2500f, 0.2500f, 0.2500f, 0, 0, 0};


//Define the maximum operation range in volts for different channels.
double max_operation_limit_volt[8]= {4.7500f, 4.7500f, 4.7500f, 4.7500f, 4.7500f, 5.00f, 5.00f, 5.00f};

unsigned int gpio_channel_hex_values[2];
unsigned short int adc_channel_hex_values[8];
double adc_channel_volt_value[8];
double adc_channel_engg_value[8];


////////////////////////////////////////////////////////////////////////////////////////
//This method read the channels values and check for different faults.
///////////////////////////////////////////////////////////////////////////////////////
void Read_Channels_Values( void )
{
    for(int ind = 0; ind < number_of_adc_channels; ++ind)
    {
        adc_channel_hex_values[ind] = Get_ADC_Channel_Value(Get_Virtual_Base_Address(), ind);

        if(number_of_adc_channels_implemented > ind)
        {
            adc_channel_volt_value[ind] = Conver_FROM_ADC(adc_channel_hex_values[ind], Get_ADC_Level_Per_Voltage());
            adc_channel_engg_value[ind] = Get_Engineering_Unit_Value(adc_channel_volt_value[ind], min_operation_limit[ind], max_operation_limit[ind], min_operation_limit_volt[ind], max_operation_limit_volt[ind]); 
        }
    }

    for(int ind = 0; ind < number_of_gpio_channels; ++ind)
    {
        gpio_channel_hex_values[ind] = Get_GPIO_Channel_Value(Get_Virtual_Base_Address(), ind);
    }

    //Fault Detection
    Check_Kill_Switch_Pressed_Fault(gpio_channel_hex_values[0]);

    Check_Seat_Occuplied_Fault(gpio_channel_hex_values[0]);
    Check_Seat_Height_Fault(adc_channel_hex_values[ADC_0_SEAT_HEIGHT]);

    Check_LEFT_Motor_Speed_Fault(adc_channel_hex_values[ADC_3_LEFT_MOTOR_SPEED]);
    Check_Right_Motor_Speed_Fault(adc_channel_hex_values[ADC_4_RIGHT_MOTOR_SPEED]);

    Check_Joystick_X_Fault(adc_channel_hex_values[ADC_1_JOYSTICK_X]);
    Check_Joystick_Y_Fault(adc_channel_hex_values[ADC_2_JOYSTICK_Y]);

}


double time_ms = 0;
////////////////////////////////////////////////////////////////////////////////////////
//This method prepares the header for the file output.
///////////////////////////////////////////////////////////////////////////////////////
void prepare_file_header()
{
    sprintf(contents + strlen(contents), "time,");

    for(int ind = 0; ind < number_of_adc_channels; ++ind)
    {
        sprintf(contents + strlen(contents), "ADC_%d HEX, ADC_%d Volt, ADC_%d Engg,", ind, ind, ind); 
    }

    for(int ind = 0; ind < number_of_gpio_channels; ++ind)
    {
        sprintf(contents + strlen(contents), "GPIO_%d HEX,", ind);
    }

    for(int ind = 0; ind < sizeof(gpio_port_0_literals_index)/sizeof(int); ++ind)
    {
        sprintf(contents + strlen(contents), "%s,", gpio_port_0_literals[ind]);
    }

    for(int ind = 0; ind < sizeof(gpio_port_1_literals_index)/sizeof(int); ++ind)
    {
        sprintf(contents + strlen(contents), "%s,", gpio_port_1_literals[ind]);
    }

    sprintf(contents + strlen(contents), "%s,%s", "Fault Flag", "Persistent Flag"); 
    sprintf(contents + strlen(contents), "\n");
}

////////////////////////////////////////////////////////////////////////////////////////
//This method prepares the contents every time they are read for writing in to the file. 
///////////////////////////////////////////////////////////////////////////////////////
void Prepare_for_file_output()
{
        
        sprintf(contents + strlen(contents), "%f,", time_ms);
        time_ms += 0.015;

        for(int ind = 0; ind < number_of_adc_channels; ++ind)
        {
	        sprintf(contents + strlen(contents), "%x,", adc_channel_hex_values[ind]); 
	        sprintf(contents + strlen(contents), "%*.*f,", 8, 3, round(adc_channel_volt_value[ind], 3)); 
	        sprintf(contents + strlen(contents), "%*.*f,", 8, 3, round(adc_channel_engg_value[ind], 3)); 
        }

        for(int ind = 0; ind < number_of_gpio_channels; ++ind)
        {
	        sprintf(contents + strlen(contents), "%x,", gpio_channel_hex_values[ind]); 
        }



        for(int ind = 0; ind < sizeof(gpio_port_0_literals_index)/sizeof(int); ++ind)
        {
	        sprintf(contents + strlen(contents), "%d,", Check_Bit_status(gpio_channel_hex_values[0], gpio_port_0_literals_index[ind]));

        }

        for(int ind = 0; ind < sizeof(gpio_port_1_literals_index)/sizeof(int); ++ind)
        {
	        sprintf(contents + strlen(contents), "%d,", Check_Bit_status(gpio_channel_hex_values[1], gpio_port_1_literals_index[ind]));
        }


	    sprintf(contents + strlen(contents), "%x,%x", Faults, Persistent_Faults); 

        sprintf(contents + strlen(contents), "\n");
}

int number_of_printed_lines = 0;

////////////////////////////////////////////////////////////////////////////////////////
/*  this method fetches and prints the following things
    1. 7 ADC Channels - starting from ADC 0 to ADC 7
    2. 2 GPIO Channels/Ports - Starting from GPIO 0 to GPIO 1

    Note: this function prints the above details once in every one second - so cycle time for printing is 1 second.
*/    
///////////////////////////////////////////////////////////////////////////////////////
void Print_ADC_AND_GPIO_Channels( void )
{

     //this method call, takes the cursor to the last line (\033[A) and delete/overwrite the whole line (\033[2K).
     for(int ind = 0; ind < number_of_printed_lines; ++ind)
            fputs("\033[A\033[2K",stdout);

    //%5s is used for 5 spaces and %2s is used for 2 spaces between adjacent channel headers.
    printf("%5s%s%8s%s%8s%s%8s%s%8s%s%8s%s%8s%s%8s%s%8s%s\n", "ADCs", " ", "ADC_0", "|", "ADC_1", "|", "ADC_2", "|", "ADC_3", "|", "ADC_4", "|", "ADC_5", "|", "ADC_6", "|", "ADC_7", "|");

        number_of_printed_lines = 1;

        printf("%5s%s", "HEX:", " ");
        for(int ind = 0; ind < number_of_adc_channels; ++ind)
        {
	        printf("%8hu%s", adc_channel_hex_values[ind], "|"); 
        }
        ++number_of_printed_lines;

        printf("\n");
        printf("%5s%s", "Volt:", " ");
        for(int ind = 0; ind < number_of_adc_channels; ++ind)
        {
	        printf("%8.3f%s", adc_channel_volt_value[ind], "|"); 
        }
        ++number_of_printed_lines;

        printf("\n");
        printf("%5s%s", "Engg:", " ");
        for(int ind = 0; ind < number_of_adc_channels; ++ind)
        {
	        printf("%8.3f%s", adc_channel_engg_value[ind], "|"); 
        }
        ++number_of_printed_lines;

        printf("\n");
        printf("%8s%2s%8s\n", "GPIO_0", " ", "GPIO_1");
        ++number_of_printed_lines;

        for(int ind = 0; ind < number_of_gpio_channels; ++ind)
        {
	        printf("%8x%2s", gpio_channel_hex_values[ind], " "); 
        }
        ++number_of_printed_lines;

        printf("\n");
        printf("GPIO 0 Boolean flags\n");
        ++number_of_printed_lines;

        for(int ind = 0; ind < sizeof(gpio_port_0_literals_index)/sizeof(int); ++ind)
        {
            printf("%s: %d\n", gpio_port_0_literals[ind], Check_Bit_status(gpio_channel_hex_values[0], gpio_port_0_literals_index[ind]));

        }
        ++number_of_printed_lines;

        printf("\n");
        printf("GPIO 1 Boolean flags\n");
        ++number_of_printed_lines;

        for(int ind = 0; ind < sizeof(gpio_port_1_literals_index)/sizeof(int); ++ind)
        {
            printf("%s: %d\n", gpio_port_1_literals[ind], Check_Bit_status(gpio_channel_hex_values[1], gpio_port_1_literals_index[ind]));
        }

        number_of_printed_lines += 11;

        printf("\n");
        printf("Faults Flag: %d,Persistent_Faults Flag: %d\n", Faults, Persistent_Faults);

        number_of_printed_lines += 2;

}



////////////////////////////////////////////////////////////////////////////////////////
//signal handler for ctrl + c, to terminate the program by freeing all the used resources.
///////////////////////////////////////////////////////////////////////////////////////
void  SIGINT_handler(int signal)
{
    CleanUp();
	timer_delete(first_timer);
	timer_delete(second_timer);

    write_in_file(file_name, contents);
    exit(0);
}
    



////////////////////////////////////////////////////////////////////////////////////////
//main program logic - what our program does.
///////////////////////////////////////////////////////////////////////////////////////
int main(int argc, char** argv) 
{
 
    signal(SIGINT, SIGINT_handler);

    if(!IntializeMemoryMapping())
    {
        printf("Application exiting...\n");
        kill(getpid(), SIGINT);
    }

    if(argc < 3)
    {
        printf("Too few arguments, please enter command line parameters in this format: t file_name.csv\n");
        kill(getpid(), SIGINT);
    }

    program_life_in_second = atoi(argv[1]);
    file_name = (char*)malloc(strlen(argv[2])+1);
    strcpy(file_name, argv[2]);

    if(!Open_File(file_name))
        return 1;

    prepare_file_header();
    Setup_Timer();
    Print_ADC_AND_GPIO_Channels();

    while(1);
    /*

    unsigned int gpio_0 = Get_GPIO_Channel_Value(virt_addr, 0);
    unsigned int gpio_1 = Get_GPIO_Channel_Value(virt_addr, 1);
    int kill_switch_present = Check_Bit_status(gpio_0, (unsigned int)GPIO_PORT_0_KILL_SWITCH_FITTED);

    int kill_switch_present_1 = (gpio_0 >> ((unsigned int)(GPIO_PORT_0_KILL_SWITCH_FITTED) - 1)) & 0x01;
    printf(" 4:%d 4:%d 18:%d\n", kill_switch_present, kill_switch_present_1, Check_Bit_status(gpio_0, (unsigned int)GPIO_PORT_0_SEAT_OCCUPIED_SWITCH)); 

    printf("%ud\n", gpio_0); 
 
    Print_ADC_AND_GPIO_Channels( );

    */

}
