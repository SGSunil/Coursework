/*
 * Write "n" bytes to a descriptor.
 * Use in place of write() when fd is a stream socket.
 */

#include <unistd.h>

int writen(int fd, char *ptr, int nbytes) {
  int nleft, nwritten;
  
  nleft = nbytes;
  while (nleft > 0) {
    nwritten = write(fd, ptr, nleft);
    if (nwritten <= 0)
      return(nwritten);		/* error */
    
    nleft -= nwritten;
    ptr   += nwritten;
  }
  return(nbytes - nleft);
}
