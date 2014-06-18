#include<stdio.h>
#include "packet.h"
#include<unistd.h>
#include<stdlib.h>
#include "packetShow.h"
#include "RTable.h"
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include "first.h"
#include <iostream>
#include<math.h>

//****************************************************************

using namespace std;



//****************************************************************

class router{

	public:
	
	struct packet sa[100],reasm; //packets for storing fragmented packets.
	static char class_based; //whether routing is class based or not.
	static int payload_length;
	static struct packet p;
	static struct frame f;
	int current_in_reasm; //for keeping the current packets in reasm structure.
	int no_of_frag; //no of fragments
	char mac[4]; // source mac to be copied in frame: source mac.
	int mac_tab_entr; // entries in mac table.
	int arp_tab_entr; // entries in arp cache.
	int ip_tab_entr; // entries in routing table.
	char global_ip[16]; // used as source ip when sending packet from this router as host.	
	char ip_name[20]; // name of the routing file for this router.
	char mac_name[20]; //name of the mac file for this router.
	char arp_name[20]; //name of the mac file for this router.
	struct own_mac_table mac_table[40]; // its own interface mapping corresponding to index.
	struct r_table rtable[40]; // its routing table structure.
	struct arp_cache arp[40]; 
	static int index; // static variable to assign the index of the routers.
	int id; //id of the router.
	int packet_seq; //for maintaining the packet sequences to be send to next hop.
	

//****************************************************************
	void reassembly(struct packet* p){
		strcat(reasm.payload,p->payload);
		current_in_reasm+=p->payload_l;	
		reasm.pid=p->pid;
		strcpy(reasm.source_ip,p->source_ip);
		strcpy(reasm.dest_ip,p->dest_ip);
		reasm.mf='0';
		reasm.df=p->df;
		reasm.offset=-1;
	}

//****************************************************************
	void fragmentation(struct packet* p, char* mtu){
		int mtunit=atoi(mtu);
		int len=p->payload_l;
		int no_pack_temp=no_of_frag=(len/mtunit)+(len%mtunit?1:0);

		char buffer[100];
		char buff[50][100];

		strcpy(buffer,p->payload);
		

		char *ptr=buffer;
		int count=0;	
		for(int i=0;i<no_pack_temp;i++){
			strncpy(buff[i],ptr,mtunit);	
			buff[i][mtunit]='\0';
			ptr+=mtunit;
		}

		for(int i=0;i<no_pack_temp;i++){
			strcpy(sa[i].payload,buff[i]);
			sa[i].payload_l=strlen(buff[i]);
			sa[i].pid=p->pid;
			sa[i].df=p->df;
			sa[i].ttl=p->ttl;
			if(i==no_pack_temp-1)
				sa[i].mf='0';
			else
				sa[i].mf='1';
	
			strcpy(sa[i].source_ip,p->source_ip);
			strcpy(sa[i].dest_ip,p->dest_ip);
			sa[i].offset=packet_seq++;
		}

		if(p->df=='1')
			no_of_frag=0;

	}

//****************************************************************

	void anding(char* dest, char* mask, char* res){
		for(int i=0;i<32;i++){
			res[i]=((dest[i]-48)*(mask[i]-48))+48;
		}
	}

//****************************************************************

	void make_frame(struct frame* f, struct packet* p,int s_index, int r_index){
			f->pack=*p;		
			f->pack.df=p->df;
			f->type='1';

			search_mac(mac,r_index);

			strcpy(f->s_mac,mac);
			search_arp(mac,r_index);
			strcpy(f->d_mac,mac); //use from arp_table or request for it.

			//printf("the d_mac is %s and s_mac:%s in id:%d\n",f->d_mac,f->s_mac,id);
	}

//****************************************************************
	void search_mac(char* buffer, int d_index){
		char buff[4];
		itoa(d_index,buff);
		for(int i=0;i<mac_tab_entr;i++){
			if(!strcmp(mac_table[i].int_router,buff)){
				strcpy(buffer,mac_table[i].mac);
				//printf("inside search_mac mac:%s\n",buffer);
				break;
			}
		}
	}

//****************************************************************
	void search_arp(char* buffer, int d_index){
		//printf("calling arp.............for id:%d\n",id);
		char buff[4];
		itoa(d_index,buff);
		for(int i=0;i<arp_tab_entr;i++){
			if(!strcmp(arp[i].int_router,buff)){
				strcpy(buffer,arp[i].mac);
				//printf("inside search_arp dest_mac:%s\n",buffer);
				break;
			}
		}
	}

//****************************************************************
	int class_identify(char *dest_ip){
		char* ptr=dest_ip;
		char buffer[4];
		int i=0;
		while(*ptr!='.')
			buffer[i++]=*ptr++;
		buffer[i]='\0';
		//printf("**** inside identify value is:%s....\n",buffer);
		int decimal_ip_part=atoi(buffer);
		if(decimal_ip_part<=127 && decimal_ip_part>0)
			return 0;
		if(decimal_ip_part<=191 && decimal_ip_part>=128)
			return 1;
		if(decimal_ip_part<=223 && decimal_ip_part>=192)
			return 2;
		return 3;
	}

//****************************************************************
	void update_ip_tab_classbased( ){
		char classA[14],classB[14],classC[14];
		strcpy(classA,"255.0.0.0");
		strcpy(classB,"255.255.0.0");
		strcpy(classC,"255.255.255.0");
		for(int i=0;i<ip_tab_entr;i++){
			if(class_identify(rtable[i].dest_nw)==0)
				strcpy(rtable[i].sub_mask,classA);
			if(class_identify(rtable[i].dest_nw)==1)
				strcpy(rtable[i].sub_mask,classB);
			if(class_identify(rtable[i].dest_nw)==2)
				strcpy(rtable[i].sub_mask,classC);
		}
	}
//****************************************************************
	void anding_cidr(char* buffer, char* nw, int sub_no){
		for(int i=0;i<32;i++)
			buffer[i]='0';
		buffer[32]='\0';
		for(int i=0;i<sub_no;i++){
			if(nw[0]=='0')
				buffer[i]='0';
			else
				buffer[i]='1';//nw[i];
		}

	}

//****************************************************************
	int bin_to_dec(char* s,int j){
		int res=0;
		for(int i=0;i<j;i++){
			if(s[i]=='1')
				res+=(int)pow(2,(double)(j-1-i));	
		}
		return res;
	}

//****************************************************************
	void make_dec_frm_bin(char* source, char* dest){
		char buffer0[9],buffer1[9],buffer2[9],buffer3[9];
		char frag[4];
		int res;
		char* ptr=source;
		int i=0;
		while(i!=8){
			buffer0[i++]=*ptr++;
		}
		buffer0[8]='\0';
		i=0;
		while(i!=8){
			buffer1[i++]=*ptr++;
		}
		buffer1[8]='\0';
		i=0;
		while(i!=8){
			buffer2[i++]=*ptr++;
		}
		buffer2[8]='\0';
		i=0;
		while(i!=8){
			buffer3[i++]=*ptr++;
		}
		buffer3[8]='\0';



		//printf("individuals %s|%s|%s|%s..\n",buffer0,buffer1,buffer2,buffer3);

		res=bin_to_dec(buffer0,8);	
		itoa(res,frag);
		//printf("res:%d for buffer:%s..and str conv:%s..\n",res,buffer0,frag);
		
		strcpy(dest,frag);	

		res=bin_to_dec(buffer1,8);	
		itoa(res,frag);
		//printf("res:%d for buffer:%s..and str conv:%s..\n",res,buffer1,frag);

		strcat(dest,".");	
		strcat(dest,frag);	

		res=bin_to_dec(buffer2,8);	
		itoa(res,frag);
		//printf("res:%d for buffer:%s..and str conv:%s..\n",res,buffer2,frag);

		strcat(dest,".");	
		strcat(dest,frag);	

		res=bin_to_dec(buffer3,8);	
		itoa(res,frag);
		//printf("res:%d for buffer:%s..and str conv:%s..\n",res,buffer3,frag);

		strcat(dest,".");	
		strcat(dest,frag);	
	}

//****************************************************************
	
	void update_ip_tab_cidr( ){
		char buffer[32];

		for(int i=0;i<ip_tab_entr;i++){
			makebin(rtable[i].dest_nw,0);
			//printf("binary form:%s and sub:%s\n",sourceIPbin,rtable[i].sub_no);
			anding_cidr(buffer, sourceIPbin,atoi(rtable[i].sub_no));
			make_dec_frm_bin(buffer,sourceIPbin);
			strcpy(rtable[i].sub_mask,sourceIPbin);
			//sleep(2);
		}

	}

//****************************************************************

	void get_frame(router c[],struct frame* f,int s_index){
		get_packet(c,&f->pack);
	}

//****************************************************************
	int forward(router c[],struct packet* p){

		char dest_mask[33],nw_mask[33],sub_mask[33],res_mask[33];
		dest_mask[32]=nw_mask[32]=sub_mask[32]=res_mask[32]='\0';

		int index=-1,ind;

		makebin(p->dest_ip,0);

		strcpy(dest_mask,sourceIPbin);

		//printf("*** %s and entries:%d for id:%d\n",dest_mask,ip_tab_entr,id);
		
		for(int i=0;i<ip_tab_entr;i++){

				makebin(rtable[i].dest_nw,0);	

				strcpy(nw_mask,sourceIPbin);

		//		printf("1*** %s*\n",sourceIPbin);
				makebin(rtable[i].sub_mask,0);	

				strcpy(sub_mask,sourceIPbin);
		//		printf("2*** %s*\n",sourceIPbin);

				anding(dest_mask,sub_mask,res_mask);
		//		printf("res is %s for id: %d\n",res_mask,id);

				int match=matching(res_mask,nw_mask);

				if(match==1){
					ind=i;
					index=atoi(rtable[i].nxt_hop_interface);
		//			printf("nxt hop: %d for id: %d\n",atoi(rtable[i].nxt_hop_interface),id);
					break;
				}
				
		}	

		//printf("*******in this:%d and mtu:%s\n",id,rtable[ind].mtu);


			if(index!=-1){
				if(!strcmp(rtable[ind].router_addr,"@")){
					reassembly(p);
					if(strlen(reasm.payload)==payload_length){

						update_packet(&reasm,p);
							printf("\n\n\n\n\n\n");
						printf("here matched at last\n");
						printf("packet reached at:%d\n",id);
						show_packet(&reasm);
					}
				}
				else{


					if(p->df=='0'){
						fragmentation(p,rtable[ind].mtu);
						for(int i=0;i<no_of_frag;i++){
							printf("\n\n\n\n\n\n");
							printf("packet going from:%d to router no:%d\n",id,index);
							make_frame(&f,&sa[i],id,index);
							
							show_frame(&f);
							c[index].get_frame(c,&f,id);
						}
					}
					else{
						if(atoi(rtable[ind].mtu)>=p->payload_l){
							make_frame(&f,p,id,index);
							show_frame(&f);
							c[index].get_frame(c,&f,id);
						}
						else{
							printf("packet dropped at index: %d\n",id);
							return 1;

						}
					}
				}
			}
		
	}

//****************************************************************

	void update_packet(struct packet* p,struct packet* p_rec){
		p->payload_l=strlen(p->payload);
		p->ttl=p_rec->ttl;
	}

//****************************************************************


	int matching(char* s1,char* s2){

		for(int i=0;i<32;i++){
			if(*s1++!=*s2++){
				//printf("not matched\n");
				return 0;
			}
		}
		return 1;
	}

//****************************************************************

	void open_arp_table(char *s){

		int desc=open(s,O_RDONLY,007);

		char buffer[30],store[100][4],c;

		int i=0,j=0;

		while(read(desc,&c, 1)){
			
			if(c==' '){
				store[i][j]='\0';
				i++;
				j=0;
			}	
			else
			if(c=='\n'){
				store[i][j]='\0';
				j=0;
				i++;
			}
			else	
			if(c=='*'){
				store[i][j]='\0';
				close(desc);
				break;
			}
			else
				store[i][j++]=c;
				
		}
		
		j=0;

		for(int k=0;k<i;k+=2){
			strcpy(arp[j].int_router,store[k+0]);
			strcpy(arp[j].mac,store[k+1]);
			j++;
		}

		arp_tab_entr=j;

		printf("arp entries in this router: %d is %d\n",id,arp_tab_entr);

}

//****************************************************************
	void open_mac_table(char *s){

		int desc=open(s,O_RDONLY,007);

		char buffer[30],store[100][4],c;

		int i=0,j=0;

		while(read(desc,&c, 1)){
			
			if(c==' '){
				store[i][j]='\0';
				i++;
				j=0;
			}	
			else
			if(c=='\n'){
				store[i][j]='\0';
				j=0;
				i++;
			}
			else	
			if(c=='*'){
				store[i][j]='\0';
				close(desc);
				break;
			}
			else
				store[i][j++]=c;
				
		}
		
		j=0;

		for(int k=0;k<i;k+=2){
			strcpy(mac_table[j].int_router,store[k+0]);
			strcpy(mac_table[j].mac,store[k+1]);
			j++;
		}

		mac_tab_entr=j;

	}

//****************************************************************
	void open_ip_table(char *s){
		int desc=open(s,O_RDONLY,007);

		char buffer[30],store[100][30],c;

		int i=0,j=0;

		while(read(desc,&c, 1)){

			if(c=='\n'||c==' '){
				buffer[j]='\0';
				break;
			}
			buffer[j++]=c;
		}
		strcpy(global_ip,buffer);	
		
		j=0;
		while(read(desc,&c, 1)){
			if(c==' '){
				store[i][j]='\0';
				i++;
				j=0;
			}	
			else
			if(c=='\n'){
				store[i][j]='\0';
				j=0;
				i++;
			}
			else	
			if(c=='*'){
				store[i][j]='\0';
				//close(desc);
				break;
			}
			else
				store[i][j++]=c;
				
		}
		
		j=0;

		for(int k=0;k<i;k+=6){
			strcpy(rtable[j].dest_nw,store[k+0]);
			strcpy(rtable[j].sub_no,store[k+1]);
			strcpy(rtable[j].sub_mask,store[k+2]);
			strcpy(rtable[j].router_addr,store[k+3]);
			strcpy(rtable[j].nxt_hop_interface,store[k+4]);
			strcpy(rtable[j].mtu,store[k+5]);
			j++;
		}

		ip_tab_entr=j;
	
	}

//****************************************************************
	void print_ip_table(){
		printf("****************************************\n\n");
		if(class_based=='0')
		for(int i=0;i<ip_tab_entr;i++){
			printf("dest_nw: %s, sub_mask: %s, nxt_hop: %s, rIP: %s, mtu: %s\n",rtable[i].dest_nw,rtable[i].sub_mask,rtable[i].router_addr,rtable[i].nxt_hop_interface,rtable[i].mtu);

		}
		if(class_based=='1')
		for(int i=0;i<ip_tab_entr;i++){
			printf("dest_nw: %s, sub_no:%s, sub_mask: %s, nxt_hop: %s, rIP: %s, mtu: %s\n",rtable[i].dest_nw,rtable[i].sub_no, rtable[i].sub_mask,rtable[i].router_addr,rtable[i].nxt_hop_interface,rtable[i].mtu);

		}
		printf("****************************************\n\n");
	}



//****************************************************************

	void create_table_name(int id){
		strcpy(ip_name,"router");
		strcpy(mac_name,"mac");
		strcpy(arp_name,"arp");
		char buffer[10];
		if(id>10){
			buffer[0]=((id/10)+48);
			buffer[1]=((id%10)+48);
			buffer[2]='\0';
		}
		else{
			buffer[0]=(id+48);
			buffer[1]='\0';
		}

		strcat(ip_name,buffer);	
		strcat(mac_name,buffer);	
		strcat(arp_name,buffer);
	}

//****************************************************************
	router(){
		id=index++;	
		current_in_reasm=0;
		packet_seq=0;
		arp_tab_entr=0;
		create_table_name(id);
		open_ip_table(ip_name);
		open_mac_table(mac_name);
		open_arp_table(arp_name);
	}

//****************************************************************
	router(const router& r){

		printf("\n***sunil***\n");
	}

//****************************************************************
	void append_source(struct packet *p){
		strcpy(p->source_ip,global_ip);
	}

//****************************************************************
	int get_packet(router c[],struct packet *p){
		p->ttl--;
		if(p->ttl==-1){
			printf("packet dropped: destination not matched\n");
			return 1;
		}
		forward(c,p);
			
	}

};
//****************************************************************

int router::index=0;
char router::class_based; //whether routing is class based or not.
struct packet router::p;
struct frame router::f;
int router::payload_length;

//****************************************************************

int main(){
	char buffer[100];
	int ind;
	int for_another=0;


	
		router r[7];
		int total_routers=7;
	while(1){

		for(int i=0;i<7;i++){
			r[i].packet_seq=0;
			r[i].reasm.payload[0]='\0';	
		}

		
		printf("whether you want class_based routing:press 0 or CIDR:press 1\n");
		read(0,&router::class_based,2);

		if(router::class_based=='0')
		for(int i=0;i<total_routers;i++)
			r[i].update_ip_tab_classbased();
		else
		for(int i=0;i<total_routers;i++)
			r[i].update_ip_tab_cidr();
		
		for(int i=0;i<7;i++){
			printf("router%d...................\n",i);
			r[i].print_ip_table();
		}
			
		if(for_another==0){
			printf("enter the sender index for the first packet or press # to exit below:\n");		
			for_another++;
		}
		else
			printf("Would you like to send another packet:if yes, enter sender index, if no, then press #\n");
		read(0,buffer,2);

		if(buffer[0]=='#')
			exit(0);
		
		ind=atoi(buffer);
		printf("ind is %d\n",ind);

		input(&router::p);
		router::payload_length=strlen(router::p.payload);
		printf("payload length:%d\n",router::payload_length);

		r[ind].append_source(&router::p);
		r[ind].get_packet(r,&router::p);
	}

}
//****************************************************************
