import ncst.pgdst.*;

class Node {
	char value; 
	Node arrChild[];
	int noC;
	int h,ha[];

	Node( ) { }

	Node(char value){
		this.value=value;
		arrChild=null;
		h=0;ha=null;
		noC=0;
	}

}

public class Org {
	static Node root;

	static Node search(Node rt,char val){

			System.out.println("called Search"+rt.value);
		if(root.arrChild==null){
			System.out.println("value of root from search "+rt.value);
			return root;
		}
		
		if(val==(rt.value)){
			System.out.println("matched for and returning  "+rt.value);
			return rt;
		}
		else 
		if(rt.arrChild!=null){
		for(int j=0;j<rt.noC;j++){
			if(rt.arrChild[j].value==val)
				return rt.arrChild[j];
			System.out.println("Search for inside loop "+rt.arrChild[j].value);
			if(rt.arrChild[j].arrChild!=null)
				return search(rt.arrChild[j],val);
			
		}
		}
		//else  return null;
			
			System.out.println("Return from Search"+rt.value);
		
		return null;
	}

	static int height(Node rt) {
		System.out.println("called Height");
		if(rt.arrChild==null)
			return 1;
		else {
			
			for(int i=0;i<rt.noC;i++) {
				rt.ha[i]=height(rt.arrChild[i]);	
			System.out.println("rt.ha "+"at index "+i+" "+rt.ha[i]+" value "+rt.value);
			}

			rt.h=max(rt.ha)+1;
			System.out.println("height for "+rt.value+" "+rt.h);
			return rt.h;
		}
		


	}

	static int max(int a[]) {
		int max=0;
		for(int i=1;i<a.length;i++){
			if(a[i]>a[max])
				max=i;
		}
			
		System.out.println("max value "+a[max]);
		return a[max];
	}

	static void insert(char value,Node node[]){ };

	static void printAll( ){ };

	public static void main(String args[]) throws IOException {
		int level=1;
		Node arrChild[];
		Node pos1=null,pos2=null;
		char par=' ';
		SimpleInput sin=new SimpleInput();
		System.out.println("enter the root(top level emp)...");
		sin.skipWhite();
		char rt=sin.readChar();
		Node temp=new Node(rt);
		root=temp;
		root.h=level;
		System.out.println("enter the no. of level...");
		int noL=sin.readInt();
		int L=noL-1;
		level++;
	
		Node parent=null;
		int childs=0;
		System.out.println("enter the while...");
		while(L>=0) {
			
			sin.skipWhite();
			par=sin.readChar();

			childs=sin.readInt();
			parent=search(root,par);
			//System.out.println("the searched parent's value is..."+parent.value);
			parent.noC=childs;
			parent.ha=new int[childs];
			parent.arrChild=new Node[childs];
			for(int j=0;j<childs;j++) {
				sin.skipWhite();
				char tmp=sin.readChar();
				temp=new Node(tmp);
				temp.h=level;
				parent.arrChild[j]=temp;
			
			}		
				level++;
		System.out.println("the value of L..."+L);
			L--;
		}
		
		//height(root);
		System.out.println("height of root is..."+root.h);
	
		System.out.println("enter the communications ");
		
		sin.skipWhite();	
		int comm=sin.readInt();	
		int comm1=comm-1;
		while(comm1>=0) {
			sin.skipWhite();
			char mat1=sin.readChar();
			sin.skipWhite();
			char mat2=sin.readChar();
			//sin.skipWhite();
			pos1=search(root,mat1);
				System.out.println("ht at value "+pos1.value+" is "+pos1.h);
			pos2=search(root,mat2);
				System.out.println("ht at value "+pos2.value+" is "+pos2.h);
			if((pos1.h-pos2.h)==-1)
				System.out.println("CCM-1");
			if((pos1.h-pos2.h)==1)
				System.out.println("CCM-2");
			if((pos1.h-pos2.h)==-2)
				System.out.println("CCM-3");
			if((pos1.h-pos2.h)==2)
				System.out.println("CCM-4");
			

			comm1--;
		}
	}
}

