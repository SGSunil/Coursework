#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<stdlib.h>
/*	char mtu[10];
//	struct packet sa[100];
	int n;
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
}p,sa[100];
*/

/*void input()
{
	fflush(stdin);
	p.pid=1000;
	p.offset=-1;
	printf("Do u want divide the packet if required \npress 1 for no and 0 for yes\n");
	read(0,&(p.df),2);
	if(p.df=='1')
		p.mf='0';
	else if(p.df=='0')
		p.mf='1';
	fflush(stdin);
	p.ttl=10;
	printf("\nEnter the destination address\n");
	read(0,p.dest_ip,sizeof(p.dest_ip));
	printf("enter the data to be transferred \n");
	read(0,p.payload,sizeof(p.payload));
	p.payload_l=strlen(p.payload)-1;
}
*/

/*void display()
{
	printf("\nThe total information about the packet are : \n");
	printf("==============================================\n");
	printf(" PID  NO_OFFSET    MF     DF    TTL          DEST_ID        PAYLOAD_LENGTH         PAYLOAD\n");
	printf("%d       %d      %c      %c     %d       %s %d %s",p.pid,p.offset,p.mf,p.df,p.ttl,p.dest_ip,p.payload_l,p.payload);
}
*/
/*void display1(struct packet p1)
{
	printf("\nThe total information about the packet are : \n");
	printf("==============================================\n");
	printf(" PID  NO_OFFSET    MF     DF    TTL          DEST_ID        PAYLOAD_LENGTH         PAYLOAD\n");
	printf("%d       %d      %c      %c     %d       %s   %d    %s\n",p1.pid,p1.offset,p1.mf,p1.df,p1.ttl,p1.dest_ip,p1.payload_l,p1.payload);
}

*/

void divide()
{
	fflush(stdin);
	char *e="";
	int r,t,m,i,j=0;
	m=atoi(mtu);
	if(p.df=='0')               //if dont fragment is set false
	{	
		if(m < p.payload_l)
		{
			n=p.payload_l/m;
			r=p.payload_l%m;
			if(r!=0)
				n=n+1;
		printf("\nTotal packet required is : %d\n", n);	
		}
	
for(i=0;i<n;i++,j=j+m)
{
	sa[i].pid=1000;
	sa[i].offset=i+1;
	sa[i].df='1';
	sa[i].ttl=10;
	strcpy(sa[i].dest_ip,p.dest_ip);
	strncpy(sa[i].payload,(p.payload)+j,m);
//	strcat(sa[i].payload,e);
	if(i<n)
		sa[i].mf='0';
	else
		sa[i].mf='1';
	sa[i].payload_l=strlen(sa[i].payload);
}
sa[i-1].mf='1';
	}	
	printf("%s\n",p.payload);
	for(i=0;i<n;i++)
		display1(sa[i]);

} 

void reassembly()
{
	struct packet r;
	int i=-1;
	char *e="";
	char str[100]="";
	fflush(stdin);
	do
	{
		i++;
		strcat(str,sa[i].payload);
	}
	while(sa[i].mf!='1');
	printf("\nTHE LENGTH OF STR IS %d",strlen(str));
	strcat(str,e);
	r.pid=1000;
	r.offset=-1;
	r.df='0';
	r.mf='1';
	r.ttl=10;
	strcpy(r.dest_ip,e);
	strcpy(r.payload,str);
	//r.payload_l=strlen(str);
	r.payload_l=strlen(r.payload);
	printf("\nTHE PACKET AFTER RE-ASSEMBLY IS \n");
	display1(r);
	
}

/*
int main()
{
	fflush(stdin);
	input();
	printf("\nEnter MTU: \n");
	read(0,mtu,sizeof(mtu));
		divide();
	reassembly();
//	display();
	return 0;
}
*/
