/*
 * Write "n" bytes to a descriptor.
 * Use in place of write() when fd is a stream socket.
 */

#ifndef WRITEN_H_INCLUDED
#define WRITEN_H_INCLUDED

int writen(int fd, char *ptr, int nbytes);

#endif
