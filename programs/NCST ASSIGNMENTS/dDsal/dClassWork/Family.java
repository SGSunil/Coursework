import ncst.pgdst.*;
class Node {
       // int value;
	String value;
        Node left;
        Node right;
//	int hl,hr,h;

        Node() {
		
                value=" ";
                left=null;
                right=null;
//		h=hl=hr=0;
        }
        Node(String i) {
                value=i;
                left=null;
                right=null;
//		h=hl=hr=0;
        }

        void visit() {
                System.out.print(value+" ");
        }

}

class Family {
	static boolean isRight=false;
	static  Node root;
	static int h,hl,hr;
        Family() {
        
                root=null;
        }
       /* void readInput() throws IOException {
       
                SimpleInput sin=new SimpleInput();
		System.out.println("Enter the no of nodes in the tree...");
		int ind=sin.readInt();
                for(int i=0;i<ind;i++) {
                
   			int m=sin.readInt();
                        insert(m);
                }
                System.out.println("in order traversal...");
                inOrder(root);
                System.out.println("post order traversal...");
		postOrder(root);
                System.out.println("enter the value u want to search in the tree...");
                int k=sin.readInt();
                find(k);
		levelHeight(root);
		
			if(root.hl>root.hr)
				root.h= root.hl;
			else 
				root.h= root.hr; 
		System.out.println("height is..."+h);

        }
*/
       /* void find(String i) {
        
                Node p= search(root,i);
                if(p==null)
                        System.out.println(i +"Not found");
                else
                        System.out.println("Found");

        }
	
	*/	
        void insert(String A,String C, String D, int i) {
                        //System.out.println("calling insert");
		
		Node n=search(root,A);
		//if(n!=null)
                        //System.out.println("***"+n.value);
      		if(root==null){
			n=new Node(A); 
			root=n;
		}
		
		if(i==1){
			Node c1=new Node(C);
			n.left=c1;
			n.right=null;
		}
		if(i==2){
			Node c1=new Node(C);
			Node c2=new Node(D);
			n.left=c1;
			n.right=c2;
		}

        }

 	void inOrder(Node p) {
                if(p!=null)
                {
                        inOrder(p.left);
                        p.visit();
                        inOrder(p.right);
                }
        }

/* 	void postOrder(Node p) {
                if(p!=null)
                {
                        postOrder(p.left);
                        postOrder(p.right);
                        p.visit();
                }
        }
*/
        Node search(Node p,String i) {
                        //System.out.println("calling search");
                if(p!=null) {
                
                        if(p.value.equals(i))
                                return p;
                        else {
                        
					Node n= search(p.left,i);
					if(n==null)
						return search(p.right,i);
					else return n;
				}
                 }
                
                return null;
        }

     /*   Node searchParent(Node p,String i) {
		//if(p==root)
		//	return root;
                if(p!=null) {

                                if((i>p.value)){
               		if((p.right.value==i)&&(p.right!=null))
			return p;} 
                                else{
               		if((p.left.value==i)&&(p.left!=null))
                                return p;
			}
			if(i>p.value)
                                 	return searchParent(p.right,i);
                                else
                                	return searchParent(p.left,i);
                       
                }
                return null;
        }
	*/
	
	/*void deleteMerging(String i) {
		Node p=null,prev=null,parent=root;
		if(i!=root.value)
		parent=searchParent(root,i);
		Node toDelete=search(root,i);
		if(toDelete==root){
			if(toDelete.right==null) {
				root=toDelete.left;
			}
			else {
				p=toDelete.right;
				while(p!=null) {
					prev=p;
					p=p.left;
				}
				
				prev.left=root.left;
				root=toDelete.right;

			}
		return;
		}
			
		
		if(isRight==true) {
			if(toDelete.left==null)
				parent.right=toDelete.right;
			else{
				p=toDelete.left;
				while(p!=null) {
					prev=p;
					p=p.right;
				}
				
				parent.right=toDelete.left;
				prev.right=toDelete.right;
			}
		}else {
			if(toDelete.right==null) {
				parent.left=toDelete.left;
			}
			else {
				p=toDelete.right;
				while(p!=null) {
					prev=p;
					p=p.left;
				}
				
				parent.left=toDelete.right;
				prev.left=toDelete.left;
			}
		}
	//	parent.visit();
	} 

*/
	
/*	static int levelHeight(Node rt) {
		if(rt!=null) {

			hl=levelHeight(rt.left);
			hr=levelHeight(rt.right); 
			if(hl>hr)
				h=hl+1;
			else
				h=hr+1;
			return h;

		}

		return 0;

		
	}
*/
	public static void main(String[] args) throws IOException {
        	Family  t=new Family();
		int i=0;
		SimpleInput sin=new SimpleInput();
		String w=" ",parent=" ",child1=" ",child2=" ";
		sin.skipWhite();
		while(!(w=sin.readWord()).equals("END")){
			sin.skipWhite();
			parent=w;

			i=sin.readInt();
			if(i==1) {
				child1=sin.readWord();
				t.insert(parent,child1," ",1);
			}
			
			if(i==2) {
				child1=sin.readWord();
				child2=sin.readWord();
				t.insert(parent,child1,child2,2);
			}
				
		}
		int N=sin.readInt();
		for(i=0;i<N;i++){
			parent=sin.readWord();
			Node n1=t.search(root,parent);
			child1=sin.readWord();
			Node n2=t.search(n1,child1);
			if(n2==null)
                		System.out.println("NO");
			else
                		System.out.println("YES");

		}

		
        /*	t.readInput();
		SimpleInput sin=new SimpleInput();
                System.out.println("enter the node u want to delete(its value)...");
		int toDelete=sin.readInt();	
		t.deleteMerging(toDelete);
                System.out.println("After deletion the in order traversal is...");
	*/
//		t.inOrder(root);

	}
}

