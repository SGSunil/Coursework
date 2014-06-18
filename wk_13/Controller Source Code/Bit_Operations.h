#ifndef __BIT_OPERATION
#define __BIT_OPERATION

//This method provides whether bit at a particular poisiton is set or not.
// inputs: 
// 1. operand: for which bit position is to be checked.
// 2. bit_position: bit position to be checked.
// outputs:
// 1 if particular bit is set.
// 0 if particular bit is not set.
int Check_Bit_status(unsigned int operand, unsigned int bit_position)
{
    return (int)((operand >> (bit_position)) & 0x01);

}


#endif
