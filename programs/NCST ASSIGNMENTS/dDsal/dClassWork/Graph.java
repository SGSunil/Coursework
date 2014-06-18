import ncst.pgdst.*;

class Node {
	int value;
	Edge head;
	int noEdge;

	Node(){
		value=0; head=null;
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
			System.out.println(e.getWeight());
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


		
class Graph {
	static Node noNodes[];
	static int nOn;
	static int totalEdges=0;
	
	static void totalEdges(Node n[]){
		int total=0;
		for(int i=0;i<n.length;i++) {
			total+=n[i].noEdges(n[i].head);
		}
		totalEdges=total;
	}

	public static void main(String args[]) throws IOException{
		Edge e=null;
		int endP=0,a=0;

		SimpleInput sin=new SimpleInput();
		System.out.println("enter no of nodes");

		nOn=sin.readInt();
		noNodes=new Node[nOn];

		for(int i=0;i<nOn;i++) {
			noNodes[i]=new Node();
			System.out.println("enter neighbour info about this node"+(i+1));
			while((a=sin.readInt())!=-1){
				System.out.println("enter the neighbour of this node");
				endP=sin.readInt();
				e=new Edge(noNodes[endP],a);
				noNodes[i].addEdge(e);
			}
		}
		totalEdges(noNodes);
		System.out.println("total edges... "+totalEdges);
	
		for(int i=0;i<nOn;i++){
			noNodes[i].traverse(noNodes[i].head);
		}
		
	}
}

