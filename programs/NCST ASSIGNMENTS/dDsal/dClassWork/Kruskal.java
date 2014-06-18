import ncst.pgdst.*;

class Node {

	//int value;
	String value;
	Node parent;//newly added
	Edge head;
	boolean isVisited;//newly added
	int noEdge;

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
			System.out.println("weight of edge... "+e.getWeight()+" value of node is from... "+value+" to "+e.endPoint.value+" parent is "+parent.value);
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
	Node startPoint;//newly added for Kruskal.
	Node endPoint;
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


		
class Kruskal {
	static Node noNodes[];
	static int nOn;
	static int totalEdges=0;
	static String str[];
	static int index,total;
	
	static void totalEdges(Node n[]){
		int total=0;
		for(int i=0;i<n.length;i++) {
			total+=n[i].noEdges(n[i].head);
		}
		totalEdges=total;
	}
	
	static void dFS(Node rt) {
		if(rt!=null) {
			System.out.println(rt.value);
			rt.isVisited=true;
			Edge n=null;
			for(n=rt.head;n!=null;n=n.Next){
				if(n.endPoint.isVisited==false)
					dFS(n.endPoint);
			}
		}
		else
			return;


	}

	static void bFS( ) {
		int i=0;
		Node nodes[]=new Node[nOn];
		//nodes[i]=noNodes[i];  i++;
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
				if(e.endPoint!=null)
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
		//		System.out.println(" call ");
		Edge e=st.head;
		if(st.head==null){
		//	System.out.println("this guy has no further edges"+st.value+" ");
			 return;	
		}
	//	if(total>=nOn)
	//		return;
		
		if(st.isVisited==false){
			total++;
			System.out.println(" in the begining "+st.value+" ");
			st.isVisited=true;
		}/*
		else
			System.out.println("in the begining of loop when found true"+st.value+" ");
		*/	



		while(e!=null) {
			if(e.endPoint.isVisited==false){
				total++;
				System.out.println(" all egde traversal "+e.endPoint.value+" ");
				e.endPoint.isVisited=true;
			}
	/*		else
				System.out.println(" in all egde traversal when found true "+e.endPoint.value+" ");*/
	
			e=e.Next;
		}
		
		e=st.head;

		while(e!=null) {
			BFS(e.endPoint);
	/*		System.out.println(" return from call...before e=e.Next  "+e.endPoint.value);
	*/		
			e=e.Next;
	//		if(e!=null);
	//		System.out.println(" after e=e.Next  "+e.endPoint.value);
		
		}

	//			System.out.println(" return from call ");
	}




	static void setVisited(boolean b) {
		for(int i=0;i<nOn;i++) {
			noNodes[i].isVisited=b;
		}
	}
	
	
	static int searchIndex(String st) {
		//if(noNodes[0]==null) return -1;
		int a=-1;
		for(int i=0;i<nOn;i++) {
			if(noNodes[i]==null) return a;
			if(noNodes[i].value.equals(st)==true)
			{
				//System.out.println("found");

				a= i;
				break;
			}
		}
				//System.out.println("not found");
				return a;
	
	}

	public static void main(String args[]) throws IOException{
		Edge e=null;
		int index1=0,temp=0,temp1=0,noE=0;
		int endP=0,a=0;
		
		for(int j=0;j<nOn;j++)
			str[j]=" ";
		boolean first=true;
		
		SimpleInput sin=new SimpleInput();
		System.out.println("enter no of nodes");
		nOn=sin.readInt();
		String word=" ",word1=" ";
		str=new String[nOn];
		/*for(int j=0;j<nOn;j++){
			System.out.println("enter the String name of the nodes ");
			sin.skipWhite();
			str[j]=sin.readWord();
		}*/
		int b=0;

		noNodes=new Node[nOn];
		System.out.println("enter no of Edges");
		noE=sin.readInt();
		Node dummy=new Node();
		for(int i=0;i<noE;i++) {

			
			System.out.println("enter the start node");
			sin.skipWhite();
			word=sin.readWord();
				if((b=searchIndex(word))==-1) {
					noNodes[index]=new Node();
					if(first)
						noNodes[index].parent=dummy;
					first=false;
					noNodes[index].value=word;
					str[index]=new String();
					str[index]=word;
					//System.out.println("created new node with value "+noNodes[index].value+" and at index "+index);
					temp=index;
					index++;
				}
				else{ 
					temp=searchIndex(word);
					//System.out.println("Already in the list "+noNodes[temp].value);
				}

			System.out.println("enter the end node");
			sin.skipWhite();
			word1=sin.readWord();

				if((b=searchIndex(word1))==-1) {
					noNodes[index]=new Node();
					noNodes[index].value=word1;
					noNodes[index].parent=noNodes[index-1];
					str[index]=new String();
					str[index]=word1;
					//System.out.println("created new node with value "+noNodes[index].value+" and at index "+index);
					temp1=index;
					noNodes[temp1].parent=noNodes[temp];

					index++;
				}else {

				temp1=searchIndex(word1);
					noNodes[temp1].parent=noNodes[temp];
					//System.out.println("Already in the list "+noNodes[temp].value);
				}

					
				System.out.println("enter the wt of this egde");
				a=sin.readInt();
				e=new Edge(noNodes[temp1],a);
				noNodes[temp].addEdge(e);
		}
		//totalEdges(noNodes);
			totalEdges=noE;
		System.out.println("total edges... "+totalEdges);
	
		/*for(int i=0;i<nOn;i++){
			noNodes[i].traverse(noNodes[i].head);
		}*/
		System.out.println("DFS...");
		dFS(noNodes[0]);
		System.out.println("BFS...");
		setVisited(false);
		System.out.println(noNodes[3].isVisited);
		//bFS( );
		BFS(noNodes[0]);
		System.out.println("......");
		
	}
}

