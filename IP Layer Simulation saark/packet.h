#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<stdlib.h>

struct packet
{
	int pid;
	int offset;
	char mf;
	char df;
	int ttl;
	char source_ip[16];
	char dest_ip[16];
	int payload_l;
	char payload[100];
	char source_routing[20];
};


void input(struct packet *p)
{
	char c1;
	char buffer[100];
	p->pid=1000;
	p->offset=-1;
	p->mf='0';
	fflush(stdin);
	printf("enter the DF(don't fragment) bit: 0 or 1\n");
		read(0,&c1,2);
	p->df=c1;
	p->ttl=10;
	printf("enter the Destination Address:\n");
	fflush(stdin);
	read(0,buffer,sizeof(buffer));

	char *c=buffer;
	while(*c++!='\n'){
	}
	*--c='\0';

	strcpy(p->dest_ip,buffer);

	printf("enter the Data to be transmitted:\n");
	read(0,buffer,sizeof(buffer));

	c=buffer;
	while(*c++!='\n'){

	}
	*--c='\0';
	strcpy(p->payload,buffer);
	p->payload_l=strlen(p->payload);
}
	
void itoa(int i, char* buffer){
		 if(i>100){
                        buffer[0]=((i/100)+48);
                        buffer[1]=((i%100)/10+48);
                        buffer[2]=((i%100)%10+48);
                        buffer[3]='\0';
                }else
		 if(i>10){
                        buffer[0]=((i/10)+48);
                        buffer[1]=((i%10)+48);
                        buffer[2]='\0';
                }
                else{
                        buffer[0]=(i+48);
                        buffer[1]='\0';
                }

} 
