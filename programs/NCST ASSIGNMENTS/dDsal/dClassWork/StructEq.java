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
                System.out.print("value"+h+" ");
        }

}

class StructEq {
        static boolean isRight=false;
         Node root;
        //static int h,hl,hr;
        StructEq() {

                root=null;
        }
        void readInput() throws IOException {

                SimpleInput sin=new SimpleInput();
                System.out.println("Enter the no of nodes in the tree...");
                int ind=sin.readInt();
                for(int i=0;i<ind;i++) {

                        int m=sin.readInt();
                        insert(m);
                }
          //      System.out.println("in order traversal...");
            //    inOrder(root);
              //  System.out.println("post order traversal...");
                //System.out.println("enter the value u want to search in the tree...");
               // int k=sin.readInt();
               // find(k);
                levelHeight(root);

/*                      if(root.hl>root.hr)
                                root.h= root.hl;
                        else
                                root.h= root.hr; */
                System.out.println("height is..."+root.balance);

        }



        void find(int i) {

                //Node p= search(root,i);
                //if(p==null)
                        System.out.println(i +"Not found");
               // else
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



        boolean search(Node p) {

                if(p!=null) {

                        if((p.balance==2)|(p.balance==-2))
                                return true;

                               boolean flag=  search(p.left);
                               if(!flag && p.right!=null) 
				return search(p.right);
                                    else 
				return flag;
                }
                return false;
        }

	 boolean structEq(Node t,Node t1) {
		
		if(t!=null){
			if(t.balance!=t1.balance)
				return false;
			else {
				boolean flg=structEq(t.left,t1.left);
				if(!flg)
					return structEq(t.right,t1.right);
				else
					return flg;
		

			}
		}		
		return true;
		
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
                //      return root;
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
        //      parent.visit();
        }



         int levelHeight(Node rt) {
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
	
	static void inOrder(Node n){
		if(n!=null){
		inOrder(n.left);
		n.visit();
		inOrder(n.right);
		}
	}

        public static void main(String[] args) throws IOException {
                SimpleInput sin=new SimpleInput();
                //System.out.println("enter the no. of trees...");
		int no=sin.readInt();
                StructEq t[]=new StructEq[no];
		int i=0,j=0;

		boolean tof=false;
		for(int l=0;l<no;l++){
			t[l]=new StructEq();
			while((i=sin.readInt())!=-1){
				t[l].insert(i);			
                		t[l].levelHeight(t[l].root);
			}
		}
	
//		for(int l=0;l<no;l++){
//			t[l].inOrder(t[l].root);
  //              	System.out.println();
//		}
		for(int l=1;l<no;l++){
			tof=t[0].structEq(t[0].root,t[l].root);
			if(tof==true)
                		System.out.println("YES "+Math.abs((t[0].root.h-t[l].root.h)));
			else
                		System.out.println("NO "+Math.abs((t[0].root.h-t[l].root.h)));
		}		
		
/*

                t.readInput();
		t1.readInput();
		tof=t.structEq(t.root,t1.root);
		if(tof==true)
                	System.out.println("YES");
		else
                	System.out.println("NO");
		
		
	//	t.inOrder(root);
*/
//                System.out.println();
        }
}



