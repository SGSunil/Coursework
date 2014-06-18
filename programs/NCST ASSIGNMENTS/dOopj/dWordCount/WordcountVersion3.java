import ncst.pgdst.*;

public class WordcountVersion3{
	static int count=0;
	static SimpleInput sin=new SimpleInput();
	
	public static void main(String args[]) throws IOException{
		//countLogic();
		//showOutput();		
		String s=null;
		char value[]={'a','b','c','d','e','h','k'};
		s=new String(value, 3, 9) ;
		System.out.println(s);
	}

	static void countLogic( )throws IOException{
		while(1>0){
			sin.skipWhite();
			if(readInput( )) count++;
			else break;
		}
	}
	
	static boolean readInput( ) throws IOException{
		if(sin.eof()!=true){
			sin.readWord(); return true;
		}
		else return false;
	}

	static void showOutput( ){
		System.out.println(count);
	}
}
