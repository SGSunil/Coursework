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
};


void input(struct packet *p)
{
	fflush(stdin);
	p->pid=1000;
	p->offset=-1;
	p->mf='0';
	printf("Do u want divide the packet if required \npress 0 for no and 1 for yes\n");
	read(0,&(p->df),2);
	fflush(stdin);
	p->ttl=10;
	printf("\nEnter the destination address\n");
	read(0,p->dest_ip,sizeof(p->dest_ip));
	printf("enter the data to be transferred \n");
	read(0,p->payload,sizeof(p->payload));
	p->payload_l=strlen(p->payload);
}

void display(struct packet *p)
{
	printf("\nThe total information about the packet are : \n");
	printf("\npid -> %d",*p.pid);
	printf("\noffset -> %d",*p.offset);
	printf("\nmf  -> %c",*p.mf);
	printf("\ndf -> %c",*p.df);
	printf("\nttl -> %d",*p.ttl);
	printf("\ndestination ip  -> %s",*p.dest_ip);
	printf("\npaylaod length  -> %d",*p.payload_l);
	printf("\npayload  -> %s",*p.payload);

}


/*int main()
{
	fflush(stdin);
	input();
	display();
	return 0;
}
*/
