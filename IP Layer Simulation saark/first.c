#include <stdio.h>
#include <string.h>
char sourceIPbin[32], destIPbin[32];
void makebin(char *, int );
int main()
{ 
   int j;
   char sourceIP[20], destIP[20];
   scanf("%s%s", sourceIP, destIP);
    makebin(sourceIP, 0);
   makebin(destIP, 1);
   printf(" Source IP binary= ");
   for(j=0; j<32; j++)
         printf("%c",sourceIPbin[j]); 
    printf("\n");
   printf(" Destin IP binary= ");
   for(j=0; j<32; j++)
         printf("%c",destIPbin[j]); 
    printf("\n");
}
void makebin(char *p, int flag)
{
   int n, i=1, j, rem;
   char t[32];
   for(j=0; j<32; j++)
         t[j]='0'; 
    while(p && sscanf(p,"%d", &n))
     {
         p= strpbrk(p,".");
       if(p) p++;
         rem=0; 
         j=0;
       while(n) {
         t[i*8-1-j++] = n%2 +48;
          n/=2;
             }
          i++;
      }
    if(flag==0) 
   for(j=0; j<32; j++)
       sourceIPbin[j]=t[j];
    if(flag==1) 
   for(j=0; j<32; j++)
       destIPbin[j]=t[j];
}
