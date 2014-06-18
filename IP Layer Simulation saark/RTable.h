#ifndef _RT
#define _RT
struct r_table{
	char dest_nw[33];
	char sub_no[3];
	char sub_mask[33];
	char router_addr[33];
	char nxt_hop_interface[4];	
	char mtu[3];
};


struct own_mac_table{
	char int_router[4];
	char mac[4];
};

struct arp_cache{
	char int_router[4];
	char mac[4];
};

struct frame{
	char type;
	struct packet pack;
	char s_mac[4];
	char d_mac[4];
};

#endif
