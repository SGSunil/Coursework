import ncst.pgdst.*;

public class Dijkastra {

	static int graphEdges[][];
	static int distance[];
	static boolean isVisited[];
	static int parents[];
	static int num;
	static int numEdges;
	
	static int searchMin( ){
		int min=40000;int index=0; boolean set=false;

		for(int i=1;i<num+1;i++) {
			if((isVisited[i]==false)&&(min>distance[i])){
				min=distance[i];
				index=i;
				set=true;
			}
		}
		
		if(set==true)
			return index;
		else
			return -1;
	}
	
	static void print_path(int i){
		if(parents[i]!=-1){
			print_path(parents[i]);
			System.out.print(parents[i]+" ");
		}

	}	
	
	public static void main(String args[]) throws IOException {

		SimpleInput sin=new SimpleInput();

		int height=0; //Mr. X height

		height=sin.readInt();
	
		numEdges=sin.readInt(); // no. of edges

		num=sin.readInt(); // no. of nodes

		graphEdges=new int[num+1][num+1]; //creation of graph

		for(int i=1;i<num+1;i++) {
			for(int j=1;j<num+1;j++) {
				graphEdges[i][j]=-1;
			}
		}

		int depth=0; //depth of road water	
		int wt=0; // time to reach from one node to another
		int st=0,end=0; // start and ending nodes

		for(int i=0;i<numEdges;i++) {
			st=sin.readInt();
			end=sin.readInt();
			wt=sin.readInt();
			depth=sin.readInt();

			if(height>depth){ // if height is more than the depth of water on part. ege
				graphEdges[st][end]=wt;  // then add it to graph
				graphEdges[end][st]=wt;
			}
		}

		parents= new int[num+1]; // array for parents information
		isVisited =new boolean[num+1]; // array for storing visit status.
		distance =new int[num+1]; // distance info array

		for(int i=1;i<num+1;i++) {
			distance[i]=30000;
			parents[i]=-1;
			isVisited[i]=false;
		}

		
		int start=sin.readInt();
		distance[start]=0;

		int reach=sin.readInt();

		int index=0;
		int visited=0;

		while(visited<=num) {
			index=searchMin( );
			if(index==-1)
				break;
			isVisited[index]=true;
			visited++;

			for(int i=1;i<num+1;i++) {
				if(graphEdges[index][i]!=-1){
					if(distance[i]>distance[index]+graphEdges[index][i]){
						distance[i]=distance[index]+graphEdges[index][i];
						parents[i]=index;
					}
				}
			}
		}

	
		print_path(reach);
		System.out.println(reach);
		
	
	}//end of main.
}
