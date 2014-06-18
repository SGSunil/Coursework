import ncst.pgdst.*;

class Node {

	//int value;
	String value;
	Node parent;//newly added
	Edge head; //maintain the linked list of Edges from this node.
	boolean isVisited; //for dfs
	boolean num=false,numC=false; //for topological graph
	boolean isChildOfOther=false; 
	int noEdge; //no of edges emerging from this node.

	Node(){
		//value=0;
		value=" ";
		head=null;
		
	}

	void addEdge(Edge e) {
		if(head==null)
			head=e;
		else {
			e.setNext(head);
			head=e;
		}
	}

	void traverse(Edge e) {
		if(e!=null){
			System.out.println("weight of edge... "+e.getWeight()+" value of node is from... "+value+" to "+e.endPoint.value);
			traverse(e.getNext());	
		}
	}

	int noEdges(Edge e) {
		if(e!=null){
			noEdge++;
			noEdges(e.getNext());
		}
		return noEdge;
	}

	void removeEdge(int wt){
		boolean found=false;
		if(head==null)
			System.out.println("no edge to delete");
		else {
			Edge p=null,e=head;
			while(e!=null) {
				p=e;
				if(e.getNext()!=null)
					if(e.getNext().getWeight()==wt){
						found=true;
						break;
					}
				e=e.getNext();
			}
			if(found) {
				p.setNext(e.getNext());
			}
		}
	}

}

class Edge {
	int wt;
	Node startPoint; //start point for the edge.
	Node endPoint; //end Point to make the edge.
	Edge Next;

	Edge(){
		wt=0;
		endPoint=null;
		Next=null;
	}

	Edge(Node endPoint,int wt){
		this.wt=wt;
		this.endPoint=endPoint;
		Next=null;
	}

	int getWeight(){
		return wt;
	}

	Edge getNext(){
		return Next;
	}

	void setNext(Edge e){
		Next=e;
	}

}


		
public class CycleDetect {
	static Node noNodes[]; 
	static int nOn; // total nodes
	static int totalEdges=0; 
	static String str[]; //auxiliry for searching, now not used.
	static int index,total;
	static boolean isCyclePresent=false; // not used
	
	static void totalEdges(Node n[]){
		int total=0;
		for(int i=0;i<n.length;i++) {
			total+=n[i].noEdges(n[i].head);
		}
		totalEdges=total;
	}
	
	static void dFS(Node rt) {
		//System.out.println("hello i am DFS");
		

		if(rt!=null) {
			rt.num=true;

		//	System.out.println(rt.value);
			//rt.isVisited=true;
			Edge n=null;
			for(n=rt.head;n!=null;n=n.Next){
				if(n.endPoint.num==false){
					System.out.println(n.endPoint.value);
					dFS(n.endPoint);
				}
				else{
					if(n.endPoint.numC==false){
						System.out.println("NO");
						System.exit(0);	
					}
				}	

				rt.numC=true;


			}


		}



	}

	static void bFS( ) {
		int i=0;
		Node nodes[]=new Node[nOn];
		Edge e=null,eT=null;
		for(int j=0;j<nOn;j++){
			if(noNodes[j].isVisited==false){
				System.out.print("value of the index in the begining is "+i);
				nodes[i]=noNodes[j];
				noNodes[j].isVisited=true;
				  i++;
			}
			e=noNodes[j].head;
			while(e!=null){
				if(e.endPoint.isVisited==false){
					System.out.print("value of the index is "+i);
					nodes[i]=e.endPoint; i++;
					e.endPoint.isVisited=true;
				}
				e=e.Next;
			}
		}


		for(int j=0;j<i;j++)
			if(nodes[j]!=null)
				System.out.print(nodes[j].value+" ");


	}

	static void BFS(Node st) {
		Edge e=st.head;
		if(st.head==null){
			 return;	
		}
		
		if(st.isVisited==false){
			total++;
			System.out.println(" in the begining "+st.value+" ");
			st.isVisited=true;
		}



		while(e!=null) {
			if(e.endPoint.isVisited==false){
				total++;
				System.out.println(" all egde traversal "+e.endPoint.value+" ");
				e.endPoint.isVisited=true;
			}
			e=e.Next;
		}
		
		e=st.head;

		while(e!=null) {
			BFS(e.endPoint);
			e=e.Next;
			System.out.println(" after e=e.Next  "+e.endPoint.value);
		
		}

	}

	static void setVisited(boolean b) {
		for(int i=0;i<nOn;i++) {
			noNodes[i].isVisited=b;
		}
	}
	
	
	static int searchIndex(String st) {
		int a=-1;
		for(int i=0;i<index;i++) {
			if(noNodes[i]==null) return a;
			if(noNodes[i].value.equals(st)==true)
			{
				//System.out.println("found");

				a= i;
				break;
			}
		}
				return a;
	
	}

	public static void main(String args[]) throws IOException{
		
		Edge e=null;
		int index1=0,temp=0,temp1=0,noE=0;
		
		for(int j=0;j<nOn;j++)
			str[j]=" ";
		boolean first=true;
		
		SimpleInput sin=new SimpleInput();
		
		String word=" ",word1=" ";
		str=new String[nOn];

		noNodes=new Node[50];

		int i=0,j=0,k=0,m=0,p=0,q=0;
		i=sin.readInt();
		String a=" ",b=" ";
		while(j<i) {
			a=sin.readWord();
			q=searchIndex(a);
			if(q==-1){
				noNodes[index]=new Node();
				noNodes[index].value=a;
				noNodes[index].isChildOfOther=true;
				temp1=index;
				index++;
			}
			else{
				temp1=q;
				noNodes[temp1].isChildOfOther=true;
			}

			k=sin.readInt();
			while(m<k) {
				b=sin.readWord();
				p=searchIndex(b);
				if(p==-1){
					noNodes[index]=new Node();
					noNodes[index].value=b;
					temp=index; index++;

				}
				else 
					temp=p;

				e=new Edge(noNodes[temp1],0);
				noNodes[temp].addEdge(e);
				
				m++;
			}
			m=0;

			j++;
		}
		nOn=index;

		for(int j1=0;j1<nOn;j1++){
		if(noNodes[j1].isChildOfOther==false){
				dFS(noNodes[j1]);
			}
		}

		dFS(noNodes[0]);
		System.out.println("BFS...");
		setVisited(false);
		bFS( );
		BFS(noNodes[0]);

		System.out.println("YES");
		
	}
}

