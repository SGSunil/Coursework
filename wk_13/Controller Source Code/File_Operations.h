#ifndef __FILE_OPERATIONS_
#define __FILE_OPERATIONS_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *fp = NULL; // fopen returns a file handle of type FILE *

////////////////////////////////////////////////////////////
//Method to open a file to write, it deletes the file 
//before writing it to it and create new one.
///////////////////////////////////////////////////////////
int Open_File(char* file_name)
{
    remove(file_name);
	fp = fopen(file_name, "w+"); // in this case we open the file for reading
	if(!fp)
	{
		perror("file opening");
		return 0;
	}

    return 1;
}

////////////////////////////////////////////////////////////
//Method to write the memory contents to the file. 
///////////////////////////////////////////////////////////
int write_in_file(char* file_name, char* contents)
{
	fwrite(contents, 1, strlen(contents), fp);
	fclose(fp);
}

#endif 
