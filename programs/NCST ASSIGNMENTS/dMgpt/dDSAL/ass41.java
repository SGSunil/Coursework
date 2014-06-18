import ncst.pgdst.*;

class node{
	String name;
	int age;
	node nxt;

	node(String name, int age){
		this.name=name;
		this.age=age;
		nxt=null;
	}
};

public class ass41{
	static node head;
	int non;

	ass41(){
		head=null;
		non=0;
	}

	boolean check(int no){
		//System.out.println("inside check and no is: "+(no+1));
		if(no>=0 && no <non)
			return true;
		return false;
	}

	void print_no(int no){
		//System.out.println("inside print and no is:"+(no+1));
		node ptr=head;
		if(check(no)==false){ 
			//System.out.println("inside check false");
			
		}
		else{
			//System.out.println("inside check true");
			for(int i=0;i<non;i++){
				if(no==i && ptr!=null){
					System.out.println(ptr.name+" "+ptr.age);
					break;
				}
				ptr=ptr.nxt;
			}
		}
	}


	void remove(int no){
		//System.out.println("inside remove");
		node ptr=head,prev=head;
		boolean status=false;
		if(false==check(no)){

			//System.out.println("inside remove false");
		}
		else{
			for(int i=0;i<non;i++){
				if(i==no && ptr!=null){
					//System.out.println("inside remove true");
					status=true;
					break;	
				}
				prev=ptr;
				ptr=ptr.nxt;
			}
			if(true==status){
				non--;
				if(ptr==head)
					head=ptr.nxt;	
				else
					prev.nxt=ptr.nxt;
			}
		}


	}
	
	void add_node(String name, int age){
		//System.out.println("inside add_node");
		
		node temp=new node(name,age);
			node ptr=head, prev=head;
		if(head==null){
			head=temp;
			non++;
			//System.out.println("head insertion");
		}
		else{

			if(age<head.age){
				temp.nxt=head;
				head=temp;
				non++;
			}
			else{
				for(ptr=head ; ptr!=null && age>=ptr.age ; prev=ptr ,ptr=ptr.nxt);
			//System.out.println("else insertion");

				temp.nxt=prev.nxt;	
				prev.nxt=temp;
				non++;
			}
		}
	}

	void print_all(){
		node ptr=head;
		for(int i=0;i<non;i++){
			//System.out.println("serial no. "+i+" name:"+ptr.name+" and age is:"+ptr.age);
			ptr=ptr.nxt;

		}

	}
		
	public static void main(String[] args) throws IOException{
		SimpleInput sin=new SimpleInput();

		ass41 list=new ass41();
		String name=new String();
		int age=0;
		char ch=0;
		String input=null;
		while(true){
				sin.skipWhite();
				input=sin.readWord();
			if((input).equals("insert")){
				//System.out.println("inside insert");
				sin.skipWhite();
				name=sin.readWord();
				sin.skipWhite();
				age=sin.readInt();
				list.add_node(name, age);
			}else
			if((input).equals("remove")){
				sin.skipWhite();
				age=sin.readInt();
				list.remove(age-1);
			}else
			if((input).equals("print")){
				sin.skipWhite();
				age=sin.readInt();
				list.print_no(age-1);
			}else
			if((input).equals("stop")){
				break;
			}
		//list.print_all();
		}


	}




};
