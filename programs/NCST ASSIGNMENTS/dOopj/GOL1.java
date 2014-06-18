import ncst.pgdst.*;

public class GOL1 {
public static void main(String[] arg) throws IOException {

//variable Decl.
SimpleInput sin=new SimpleInput();
SimpleOutput sout=new SimpleOutput();
String input=new String();
int maxxcol=0,maxxrow=0,maxrow=0,maxcol=0,max=0;
String s=new String();

maxxrow=sin.readInt();
maxxcol=sin.readInt();

int non=0,non1=0;
int l=0,n=0, N=0;
boolean wtb=false,doc=false,diig=false;

maxrow=maxxrow-1;
maxcol=maxxcol-1;

char org[][]=new char[maxxrow+2][maxxcol+2];
char org1[][]=new char[maxxrow][maxxcol];

max=maxrow;
char testA[][]=new char[maxxrow+2][maxxcol+2];
char testH[][]=new char[maxxrow+2][maxxcol+2];

for(int i=0;i<=maxrow;i++)
	{
	sin.skipWhite();
	s=sin.readWord();
	org1[i]=s.toCharArray();
	}
	
for(int i=0;i<maxxrow+2;i++)
	{
	for(int j=0;j<maxxcol+2;j++)
	{ 
	
	if(((i>0)&(i<maxrow+2))&((j>0)&(j<maxxcol+2)))
	{
	int m=i-1,k=j-1;
		if((m<=maxrow)&(k<=maxcol))
	org[i][j]=org1[m][k];
	else
	org[i][j]='0';
	}
	else{
	org[i][j]='0';
	}
	}
	
	}	

N=sin.readInt();


while(N>0)
{
	for(int i=1;i<=maxrow+1;i++)
	{
			
		for(int j=1;j<=maxcol+1;j++)
		{
		non=0;
		
		//if(org[i][j]=='@')
		//{
		
			
	
		if((org[i-1][j-1]=='@')) 
		non++;
		
		if(org[i-1][j]=='@')  
		non++;
		
		if(org[i-1][j+1]=='@')  
		non++;
		
		if(org[i][j-1]=='@')  
		{
		non++;
		}

		if(org[i][j+1]=='@') 
		{
		non++;
		}
		
		if(org[i+1][j-1]=='@')  
		non++;
		
		if(org[i+1][j]=='@')  
		non++;
		
		if((org[i+1][j+1]=='@'))  
		non++;
		
System.out.println(i+" row "+j+" column and non is "+non);


	}
	
	
		 }
								




																	       


	
	N--;
	}


for(int i=0;i<maxxrow+2;i++)
	{
	for(int j=0;j<maxxcol+2;j++)
	{ 
	System.out.print(org[i][j]+" ");
	}
	System.out.println();
	}

if(non<=0)
non=0;
System.out.println(non);
sout.writeln();	






}//end of main
}//end of class
