import ncst.pgdst.*;

public class Stack {
	static Object stack[];
	static int top, size;
	
	public static void main(String str[]) throws IOException{

		SimpleInput sin=new SimpleInput();

		sin.skipWhite();
		String brackets=sin.readWord();
//		int s=sin.readInt();
		char[] chb=brackets.toCharArray();
//		for(int i=0;i<chb.length;i++)
//			System.out.println(chb[i]);

		Character c;
		Character c1=new Character(chb[0]);
		int j=0;
		Stack st=new Stack(chb.length+5);
		boolean valid=true;
		while((j<chb.length)&(valid)){
			
			
			if((chb[j]=='(')|(chb[j]=='{')|(chb[j]=='[')){	
				c=new Character(chb[j]);				
				st.push(c);
				if(j==chb.length-1)
					valid=false;
			}
			else {
				if((chb[j]==')')|(chb[j]=='}')|(chb[j]==']')) {
					if(!st.isEmpty())
						c1=(Character)st.pop();
					else
						valid=false;

				if(((c1.charValue()=='(')&chb[j]==')')| ((c1.charValue()=='{')&chb[j]=='}')| ((c1.charValue()=='[')&chb[j]==']')) 
					valid=true;
				else {
					valid=false;	
				}
				}
			}
			j++;
		}		

		if(valid==false)
			System.out.println("Not matching");
		else
			System.out.println("matching");
			



	}//end of main function.


	Stack(int size) {
	top=-1;
	this.size=size;
	stack=new Object[size];
	}
	
	static boolean isFull( ) {
		return (top==(size-1));
	}
	
	static boolean isEmpty( ) {
		return (top==-1);
	}

	static void  push(Object obj) {
		if(!isFull()){
			top++;
			stack[top]=obj;
		}
		else
		System.out.println("stack is full, can't insert");
	}	

	static Object pop( ) {
		if(!isEmpty()){
			Object temp=new Object();
			temp=stack[top];
			top--;
			return (temp);
		}
	return (null);
	}
}	
