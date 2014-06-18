import ncst.pgdst.*;

// Node class for the storage of values and links.
class Node {
	int value;
	Node next;
	
	public Node() {
		this(-1,null);
	}
	
	public  Node(int value,Node next) {
		this.value=value;
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
}

public class List {

	Node head, tail;

	public static void main(String str[]) throws IOException {
		int a=0;
		SimpleInput sin=new SimpleInput();
		List list=new List();
		int j=sin.readInt();
		for(int i=0;i<=j;i++) {
			a=sin.readInt();
			list.insertB(a);
		}
		list.insertBefore(100,287);
		list.insertAfter(100,249);
		list.deleteB();
		list.deleteE();
		list.sortList();
		System.out.println("****");
		list.traversal();
		

	}
	
	public void traversal( ) {
	Node curr=null;
	for(curr=head;curr!=null;curr=curr.getNext())
	System.out.println(curr.getValue());

	}	
		
	public void sortList( ) {

	 	Node curr=null,curr2=null,curr1=null;
	 	int min=0,temp=0;
		boolean b=false;
//	 	curr2=head;
	 	for(curr=head;curr!=null;curr=curr.getNext()) {
			min=curr.value;
			System.out.println("***min "+min);
//			if(curr.getNext()!=null) {
			for(curr1=curr.getNext();curr1!=null;curr1=curr1.getNext()) {
				if(min<curr1.value) { }
				else {
					min=curr1.value;
					curr2=curr1;
					b=true;
				}
			}
				if(b==true) {	
				System.out.println("during exchangemin "+min);
				temp=curr.value;
				curr.value=min;
				curr2.value=temp;
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

	public void insertB(int item) {
		Node temp=new Node(item,null);
		if(!isEmpty()) {
			temp.setNext(head);
			head=temp;
		} else {
			head=temp;
			tail=temp;
		
		}
	}

	public void insertE(int item) {
		Node temp=new Node(item,null);
		if(!isEmpty()) {
			tail.setNext(temp);
		} else {
			head=tail=temp;
		}
	}

	
	public void insertAfter(int pValue,int item) {
		Node temp=new Node(item, null);
		Node curr=null,prev=null,curro=null;
		for(curr=head;curr.value!=pValue;){

			prev=curr;
			curro=curr.getNext();
			curr=curr.getNext(); 
		}
		temp.setNext(curro.getNext());
		System.out.println("***"+curro.getNext().value+"@@@"+prev.value);
		curro.setNext(temp);


		if(tail==prev) tail=temp;
	}

	
	public void insertBefore(int pValue,int item) {
		Node temp=new Node(item, null);
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

