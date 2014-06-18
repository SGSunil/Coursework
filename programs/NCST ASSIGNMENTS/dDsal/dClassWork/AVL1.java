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
                System.out.println(balance+" <...bal "+value+" <....val"+" height is..."+h);
        }

}

class AVL1 {
        static boolean isRight=false;
        static  Node root;
        //static int h,hl,hr;
        AVL1() {

                root=null;
        }
         void readInput() throws IOException {

                SimpleInput sin=new SimpleInput();
                SimpleOutput sout=new SimpleOutput();
                int j=0,arr[]=new int[100],m=0;

                while(!((m=sin.readInt())==-1)){

                        insert(m);
                        levelHeight(root);


                       // if((root.balance==2)|(root.balance==-2)){
				if(search(root)==true){
                                deleteMerging(m);
                                arr[j]=m;j++;
                        }
                }

   		     /*for(int k=0;k<j;k++){
                	        System.out.print(arr[k]+" ");
                     }*/
			inOrder(root);
                 System.out.println("********");
                 System.out.println(j);
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

	 void inOrder(Node rt){
		if(rt!=null){
		inOrder(rt.left);
		rt.visit();
		inOrder(rt.right);
	}
	}

        public static void main(String[] args) throws IOException {
                AVL1 t=new AVL1();

                t.readInput();
                //System.out.println(root.balance+"####");
                SimpleInput sin=new SimpleInput();
                System.out.println();
        }
}



