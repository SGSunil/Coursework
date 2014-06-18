import ncst.pgdst.*;

// Node class for the storage of values and links.
class Node {
        int value;
        String name;
        Node next;

        public Node() {
                this(-1," ",null);
        }

        public  Node(int value,String str, Node next) {
                this.value=value;
                this.name=str;
                this.next=next;
        }

        public Node getNext( ){
                return next;
        }

        public void setNext(Node next) {
                this.next=next;
        }

        public int getValue( ) {
                return value;
        }

        public String getName( ) {
                return name;
        }
}

public class List3 {

        Node head, tail;
                static String s[]=new String[30];
                static int v[]=new int[30];
                static int k=0;

        public static void main(String str[]) throws IOException {
                int a=0;
                SimpleInput sin=new SimpleInput();
                List3 list=new List3();
                String w=" ",w1=" ", w2=" ";
                int a1=0,a2=0,a3=0;

                sin.skipWhite();

                while(!(w=sin.readWord()).equals("stop")) {
                        if(w.equals("insert")) {
                                sin.skipWhite();
                                w1=sin.readWord();
                                a1=sin.readInt();
                                list.insertB(a1,w1);
                                list.sortList();

                        }
                        if(w.equals("remove")) {
                                if(list.isEmpty()) {
                                continue ;
                                }
                                else {

                                a1=sin.readInt();
                                list.deleteN(a1);
                                }
                        }
                        if(w.equals("print")) {
                                if(list.isEmpty()) {
                                continue ;
                                }else {
                                a1=sin.readInt();
        //                      Node n=new Node(1," ",null);
                                list.printN(a1);
                                //s[i]=n.getName();
                                //v[i]=n.getValue();
                        //      i++;
                                }
                        }
                        sin.skipWhite();
                //      w=sin.readWord();
                }
//		if(list.count()>0)
                	for(int j=0;j<k;j++) {
				if(s[j]!=null)
                        	System.out.println(s[j]+" "+v[j]);
                	}
  //              else
//			System.out.println();

//                      list.traversal();


/*              int j=sin.readInt();
                for(int i=0;i<=j;i++) {
                        a=sin.readInt();
                        list.insertB(a,"onnamahshivay");
                }
                list.insertBefore(100,287,"sunil");
                list.insertAfter(100,249,"kumar");
                list.deleteB();
                list.deleteE();
                list.sortList();
                System.out.println("****");
                list.printN(3);
                System.out.println("****");
                list.deleteN(4);
                System.out.println("****");
                list.traversal();

*/
        }

        public void traversal( ) {
        Node curr=null;
        for(curr=head;curr!=null;curr=curr.getNext())
        System.out.println(curr.getValue()+"***"+curr.getName());

        }



        public Node printN(int N) {
		if(N>count())
			return null;
                int i=1;
                Node temp=new Node(1," ",null);
                Node curr=null;
                for(curr=head;i<=N;i++,curr=curr.getNext()){
                        if(i==N){

                                v[k]=temp.value=curr.getValue();
                                s[k]=temp.name=curr.getName();
                                k++;
                        //      System.out.println(curr.getName()+" "+curr.getValue());
                        //      System.out.println("temp..."+temp.getName()+" "+temp.getValue());
                                break;
                        }
                }
                return temp;
        }

	public int count( ) {
                Node curr=null;
                int i=0;
                for(curr=head;curr!=null;curr=curr.getNext()) {
                        i++;

                }
                return i;
        }





        public void sortList( ) {

                Node curr=null,curr2=null,curr1=null;
                int min=0,temp=0;
                String str=" ",tempS=" ";
                boolean b=false;
//              curr2=head;
                for(curr=head;curr!=null;curr=curr.getNext()) {
                        min=curr.value;
                        str=curr.name;
        //              System.out.println("***min "+min);
//                      if(curr.getNext()!=null) {
                        for(curr1=curr.getNext();curr1!=null;curr1=curr1.getNext()) {
                                if(min<curr1.value) { 
                                        min=curr1.value;
                                        curr2=curr1;
                                        str=curr1.name;
                                        b=true;
				}
                        }
                                if(b==true) {
                                //System.out.println("during exchangemin "+min);
                                tempS=curr.name;
                                temp=curr.value;
                                curr.value=min;
                                curr.name=str;
                                curr2.value=temp;
                                curr2.name=tempS;
                                }
                        b=false;
                }



        }

        public void List( ) {
                head=tail=null;
        }

        public boolean isEmpty( ) {
                return (head==null);
        }

        public void insertB(int item, String str) {
		boolean isEqual=false;
                Node curr=null,prev =null,  temp=new Node(item,str,null);
                if(!isEmpty()) {
			for(curr=head;curr!=null;){
				if(curr.getValue()==item) {
					prev=curr;
					isEqual=true;
				}
				curr=curr.getNext();
			}	

			if(isEqual==true) {
				temp.setNext(prev.getNext());
				prev.setNext(temp);
			}else {
                        temp.setNext(head);
                        head=temp;
			}
                } else {
                        head=temp;
                        tail=temp;

                }
        }

        public void insertE(int item,String str) {
                Node temp=new Node(item,str,null);
                if(!isEmpty()) {
                        tail.setNext(temp);
                } else {
                        head=tail=temp;
                }
        }


        public void insertAfter(int pValue,int item,String str) {
                Node temp=new Node(item,str, null);
                Node curr=null,prev=null,curro=null;

                for(curr=head;curr.value!=pValue;){

                        prev=curr; curro=curr.getNext();curr=curr.getNext(); 
		}

                temp.setNext(curro.getNext());
                curro.setNext(temp);
                
		if(tail==prev) tail=temp;
        }


        public void insertBefore(int pValue,int item,String str) {
                Node temp=new Node(item,str, null);
                Node curr=null, prev=null;

                for(curr=head;curr.value!=pValue;curr=curr.getNext()) {
                        prev=curr;
                }

                temp.setNext(prev.getNext());
                prev.setNext(temp);
        }

        public void delete(Node n) {
                Node curr=null, prev=null;
                for(curr=head;curr!=n;curr=curr.getNext()) {
                        prev=curr;
                }

                prev.setNext(n.getNext());
                if(tail==n) tail=prev;
        }

        public void  deleteN(int N) {
		if(N>count())
			return ;
                int i=1;
                boolean b=false;
                Node curr=null, prev=null;
                if(N!=1) {
                        b=true;
                        for(curr=head;i<=N-1;i++,curr=curr.getNext()) {
                                if(i==N-1){
                                         prev=curr;
                                         break;
                                }

                        }
                } else {

                head=head.getNext();
                return;
                }

                if(b==true) {
                        if(tail==prev.getNext()) {
                                tail=curr;
                                tail.next=null;
                                //System.out.println("reached at the tail");

                        } else {
                                //System.out.println("deleteN "+prev.value);
                                prev.setNext(prev.getNext().getNext());

                        }
                }
        }

        public void deleteB() {

                if(!isEmpty()) {
                head=head.getNext();

                }
        }

        public void deleteE() {
                Node curr=null,prev=null;
                for(curr=head;curr!=tail;curr=curr.getNext()) {
                        prev=curr;
                }

                prev.setNext(null);
                tail=prev;

                }
        }


