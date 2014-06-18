/*
 * Read a line from a descriptor.  Read the line one byte at a time,
 * looking for the newline.  We store the newline in the buffer,
 * then follow it with a null (the same as fgets(3)).
 * We return the number of characters up to, but not including,
 * the null (the same as strlen(3)).
*/

#ifndef READLINE_H_INCLUDED
#define READLINE_H_INCLUDED

int readline(int fd, char *ptr, int maxlen);

#endif
