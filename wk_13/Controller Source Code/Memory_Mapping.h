#ifndef __MEMORY_MAPPING_H
#define __MEMORY_MAPPING_H

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <signal.h>
 

//according to the home work assignment, total 40 bytes are from the start address 0xC0000000 are important for us.
//end address is 0xC0000026 for DAC 3 Channel.
#define MAP_SIZE 50 
#define MAP_MASK (MAP_SIZE - 1)
 
void *map_base;
void *virt_addr;

//this is the address of physical memory start that we want to access.
off_t target = 0xC0000000;

int fd;

/*
    This function open the handle to the physical memory.
    The memory is opened for read and write operations.

    Application is exited if memory is not mapped.

*/
int OpenMemory( void )
{

    fd = open("/dev/mem", O_RDWR | O_SYNC);

    if(-1 == fd) 
    {
        printf("/dev/mem could not be opened.\n");
        perror("open");
        return 0;
    } 
    else 
    {
        printf("/dev/mem opened successfully.\n");
    }

    return 1;
}
 

/*
    This function maps the physical memory address to the current process address space. 
    The memory is mapped for read and write operations.

    Application is exited if memory is not mapped.

*/
int MapPhysicalMemoryToVirtualMemory( void )
{

    map_base = mmap(0, MAP_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, target & ~MAP_MASK);

    if(((void *) -1) == map_base) 
    {
        printf("Memory map failed.\n");
	    perror("mmap");
        printf("Exiting Application...\n");
        return 0;
    } 
    else 
    {
        printf("Memory mapped at address %p.\n", map_base);
    }

    return 1;
}


int IntializeMemoryMapping( )
{
    if(0 == OpenMemory( ))
        return 0;
    else
    {
        if(0 == MapPhysicalMemoryToVirtualMemory( ))
            return 0;
    }


    return 1;
}


void* Get_Virtual_Base_Address( )
{
    return map_base;
}


//Clean up method to close the memory mapping.
void CleanUp( )
{

    if(munmap(map_base, MAP_SIZE) == -1) 
    {
        printf("Memory unmap failed.\n");	
    }
 
    close(fd);
    printf("Memory related clean up done successfully...\n");

}



#endif
