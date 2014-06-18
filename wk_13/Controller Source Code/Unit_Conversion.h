#ifndef __UNIT_CONVERSION
#define __UNIT_CONVERSION

#include <stdio.h>
#include <math.h>

double round(double value, int decimal_places)
{
    return floorf(value * pow(10, decimal_places) + 0.5) / pow(10, decimal_places);
}

//Convert units from voltage to the unit of variable: upper_operation_limit
//inputs:
// 1. voltage: value in volts that needs to be converted to engineering unit.
// 2. lower_operational_limit: lower range in engineering unit.
// 3. upper_operational_limit: upper range in engineering unit.
// 2. lower_operation_voltage_limit: lower range in voltage unit.
// 3. upper_operation_voltage_limit: upper range in voltage unit.
//
//Outputs: value in engineering unit.
double Get_Engineering_Unit_Value(double voltage, double lower_operational_limit, double upper_operational_limit, double lower_operation_voltage_limit, double upper_operation_voltage_limit)
{
    voltage = round(voltage, 2);

    //printf("v:%f, ll:%f, ml:%f, llv:%f, mlv:%f\n", voltage, lower_operational_limit, upper_operational_limit, lower_operation_voltage_limit, upper_operation_voltage_limit);
    double scale_factor = ((upper_operational_limit - lower_operational_limit))/(upper_operation_voltage_limit - lower_operation_voltage_limit);

    //scale_factor * voltage + lower_operational_limit - scale_factor*lower_operation_voltage_limit;
    double engg_value = scale_factor * (voltage - lower_operation_voltage_limit) + lower_operational_limit;

    return round(engg_value, 2);

}

double ADC_Level_Per_Voltage = 204.6; //1023/5 (10 bit ADC levels = 1024, voltage range = 5 - 0 = 5)

double Get_ADC_Level_Per_Voltage()
{
    return ADC_Level_Per_Voltage;
}

//Convert units from voltage to the unit of variable: upper_operation_limit
//inputs:
// 1. voltage: value in volts that needs to be converted to engineering unit.
// 2. lower_operational_limit: lower range in engineering unit.
// 3. upper_operational_limit: upper range in engineering unit.
// 2. lower_operation_voltage_limit: lower range in voltage unit.
// 3. upper_operation_voltage_limit: upper range in voltage unit.
//
//Outputs: value in engineering unit.
double Conver_FROM_ADC(double adc, double adc_level_per_volt) 
{
    return adc*(1/adc_level_per_volt); 

}

//Convert units from voltage to the unit of variable: upper_operation_limit
//inputs:
// 1. voltage: value in volts that needs to be converted to engineering unit.
// 2. lower_operational_limit: lower range in engineering unit.
// 3. upper_operational_limit: upper range in engineering unit.
// 2. lower_operation_voltage_limit: lower range in voltage unit.
// 3. upper_operation_voltage_limit: upper range in voltage unit.
//
//Outputs: value in engineering unit.
unsigned int Conver_TO_ADC(double voltage, double adc_level_per_volt) 
{
    return (unsigned int)(voltage*adc_level_per_volt); 

}

#endif
