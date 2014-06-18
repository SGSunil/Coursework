import ncst.pgdst.*;

class Node {
        int value;
        Node left;
        Node right;
	int hl,hr,h,balance;

        Node() {
                value=0;
                left=null;
                right=null;
		h=hl=hr=balance=0;
        }
        Node(int i) {
                value=i;
                left=null;
                right=null;
		h=hl=hr=balance=0;
        }

        void visit() {
                System.out.print(value+" ");
        }

}

class AVL {
	static boolean isRight=false;
	static  Node root;
	//static int h,hl,hr;
        AVL() {
        
                root=null;
        }
        void readInput() throws IOException {
       		
                SimpleInput sin=new SimpleInput();
		//System.out.println("Enter the no of nodes in the tree...");
	//	int ind=sin.readInt();
		int j=0,arr[]=new int[100],m=0;
                while(!((m=sin.readInt())==-1)){
                        insert(m);
			levelHeight(root);
		//System.out.println("height is..."+root.h+"*** "+root.balance);
			if((root.balance==2)||(root.balance==-2)){
				deleteMerging(m);
				arr[j]=m;j++;
			}
                }
                //System.out.println("in order traversal...");
                //inOrder(root);
                //System.out.println("post order traversal...");
		//postOrder(root);
                //System.out.println("enter the value u want to search in the tree...");
                //int l=sin.readInt();
                //find(l);
		//levelHeight(root);
		
/*			if(root.hl>root.hr)
				root.h= root.hl;
			else 
				root.h= root.hr; */
		//System.out.println("height is..."+root.h+"*** "+root.balance);
		for(int k=0;k<j;k++){
			System.out.print(arr[k]+" ");
		}
			System.out.print(j);

        }

        void find(int i) {
        
                Node p= search(root,i);
                if(p==null)
                        System.out.println(i +"Not found");
                else
                        System.out.println("Found");

        }
        void insert(int i) {
                Node temp=new Node(i);
                if(root==null) {
                        root=temp;
                }
                else {
                        Node p=root;
                        Node prev=root;
                        while(p!=null) {
                        
                                prev=p;
                                if(i > p.value)
                                p=p.right;
                                else
                                p=p.left;
                        }
                        if(i>prev.value)
                        	prev.right=temp;
                        else
                        	prev.left=temp;
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

 	void postOrder(Node p) {
                if(p!=null)
                {
                        postOrder(p.left);
                        postOrder(p.right);
                        p.visit();
                }
        }

        Node search(Node p,int i) {
        
                if(p!=null) {
                
                        if(p.value==i)
                                return p;
                        else {
                        
                                if(i>p.value){
                                	isRight=true; 
					return search(p.right,i);
				}
                                else {
                                	isRight=false;
					return search(p.left,i);
				}
                        }
                }
                return null;
        }

        Node searchParent(Node p,int i) {
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
	
	void deleteMerging(int i) {
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


	
	static int levelHeight(Node rt) {
		if(rt!=null) {

			rt.hl=levelHeight(rt.left);
			rt.hr=levelHeight(rt.right); 
			rt.balance=rt.hr-rt.hl;
			if(rt.hl>rt.hr)
				rt.h=rt.hl+1;
			else
				rt.h=rt.hr+1;
			return rt.h;

		}

		return 0;

		
	}

	public static void main(String[] args) throws IOException {
        	AVL t=new AVL();
		
        	t.readInput();
		//SimpleInput sin=new SimpleInput();
                //System.out.println("enter the node u want to delete(its value)...");
		//int toDelete=sin.readInt();	
		//t.deleteMerging(toDelete);
                //System.out.println("After deletion the in order traversal is...");
		//t.inOrder(root);
	}
}

