import ncst.pgdst.*;

class node{

	static int height;
	int curr_dis;
	edge head;
	int links;

	boolean is_checked;

	node(){
		curr_dis=99999;
		head=null;
		is_checked=false;
		links=0;
	}

	void add_edge(int endp, int time, int depth){
		if(height>depth){
			node ptr=null,prev=head;
			edge temp=new edge(endp,time,depth); 
			if(head==null){
				head=temp;
				links++;
			}
			else{
				for(ptr=head; ptr!=null; prev=ptr, ptr=ptr.nxt);
				prev.nxt=temp;
				links++;
			}
		}
		else
			System.out.println("height is less than depth");
	}
}


class edge{
	int endn;
	int wdepth;
	int time;
	edge nxt;
	
	edge(){
		endn=-1;
		wdepth=-1;
		time=-1;
	}
	
	edge(int endp, int time, int depth){
		endn=endp;
		this.time=time;
		wdepth=depth;
	}
}


class MRX{

	public static void main(String[] args) throws IOException{
		System.out.println("hi");


	}
}
