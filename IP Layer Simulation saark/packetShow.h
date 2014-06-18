#include "RTable.h"
#include<unistd.h>
#ifndef _RTable
#define _RTable
void clear();
void show_packet(struct packet *p){

	printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
	printf("|packet id	|offset		|TTL    |MF  |DF\t\t|\n");
	printf("|%d		|\033[1;35m%d\033[0;30m	        |\033[1;34m%d\033[0;30m      |%c   |%c\t\t\t|\n",p->pid,p->offset,p->ttl,p->mf,p->df);
	printf("|---------------------------------------------------------------|\n");
	printf("|Source IP		|Dest. IP		 		|\n");
	printf("|%15s			|%15s		 				\n",p->source_ip,p->dest_ip);
	printf("|---------------------------------------------------------------|\n");
	printf("|Payload Length					 		|\n");
	printf("|%d					 			|\n",p->payload_l);
	printf("|---------------------------------------------------------------|\n");
	printf("|Payload				 	 		|\n");
	printf("|%s\n",p->payload);
	printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

};


void show_frame(struct frame* p){
	printf("##################################################################\n\n");
	printf("|frame type:%c	|source mac:%s	|dest mac:%s   	 |\n",p->type,p->s_mac,p->d_mac);
	printf("---------------------------------------------------------\n");
	printf("|frame payload below:.........................\n");
	show_packet(&p->pack);		
	printf("###################################################################\n\n\n");
	sleep(1);
	clear();
};

void clear()
{
        //This function uses the vt1010
        printf("\033[2J");//clear the screen
        printf("\033[0;0f");//place the cursor on top left corner of screen

}

#endif
