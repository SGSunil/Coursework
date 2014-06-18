import ncst.pgdst.*;
class Node {
	String value;
        //int value;
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

class Tree1 {
	static boolean isRight=false;
	static  Node root;
	static int h,hl,hr;
        Tree1() {
        
                root=null;
        }
        void readInput() throws IOException {
       
                SimpleInput sin=new SimpleInput();
		System.out.println("Enter the no of nodes in the tree...");
		int ind=sin.readInt();
                for(int i=0;i<ind;i++) {
                	sin.skipWhite();
   			String m=sin.readWord();
                        insert(m);
                }
                System.out.println("in order traversal...");
                inOrder(root);
                System.out.println("post order traversal...");
		postOrder(root);
                System.out.println("enter the value u want to search in the tree...");
		sin.skipWhite();
                String k=sin.readWord();
                find(k);
		levelHeight(root);
		
		System.out.println("height is..."+h);

        }

        void find(String i) {
        
                Node p= search(root,i);
                if(p==null)
                        System.out.println(i +" Not found");
                else
                        System.out.println("Found");

        }
        void insert(String i) {
                Node temp=new Node(i);
                if(root==null) {
                        root=temp;
                }
                else {
                        Node p=root;
                        Node prev=root;
                        while(p!=null) {
                        
                                prev=p;
                                //if(i > p.value)
                                p=p.right;
                               // else
                       //         p=p.left;
                        }
                       // if(i>prev.value)
                        	prev.right=temp;
                       // else
                        //	prev.left=temp;
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

        Node search(Node p,String i) {
        
                if(p!=null) {
                
                        if(p.value.equals(i))
                                return p;
                        else {
                        
                                //if(i>p.value){
                                //	isRight=true; 
					return search(p.right,i);
				//}
                                //else {
                                //	isRight=false;
					//return search(p.left,i);
				//}
                        }
                }
                return null;
        }

       /* Node searchParent(Node p,String i) {
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
        }*/
	
/*	void deleteMerging(int i) {
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

	
	static int levelHeight(Node rt) {
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

	public static void main(String[] args) throws IOException {
        	Tree1 t=new Tree1();
		
        	t.readInput();
		SimpleInput sin=new SimpleInput();
                System.out.println("enter the node u want to delete(its value)...");
		int toDelete=sin.readInt();	
		//t.deleteMerging(toDelete);
                System.out.println("After deletion the in order traversal is...");
		t.inOrder(root);
	}
}

