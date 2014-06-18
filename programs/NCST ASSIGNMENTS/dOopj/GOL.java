import ncst.pgdst.*;

public class GOL {
public static void main(String[] arg) throws IOException {

//variable Decl.
SimpleInput sin=new SimpleInput();
SimpleOutput sout=new SimpleOutput();

String input=new String();

int maxxcol=0,maxxrow=0,maxrow=0,maxcol=0,max=0;  //maxxrow, maxxcol=entered size for the original matrix

String s=new String();

maxxrow=sin.readInt();
maxxcol=sin.readInt();

int non=0,non1=0;
int l=0,n=0, N=0;

int count[][]=new int[maxxrow][maxxcol];

maxrow=maxxrow-1; //index up to which assignments will be done.
maxcol=maxxcol-1;

char org[][]=new char[maxxrow+2][maxxcol+2]; //matrix on which manipulation will be done.

char org1[][]=new char[maxxrow][maxxcol]; //original matrix entered.

char org2[][]=new char[maxxrow+2][maxxcol+2]; //temp matrix used for next generation operations.

max=maxrow;

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


//this loop is for counting the neighbours @ and assigning to count[][] array of corresponding position.

while(N>0)
{
	for(int i=1;i<maxxrow+1;i++)
	{
		for(int j=1;j<maxxcol+1;j++)
			{
			non=0;
	
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
			
			count[i-1][j-1]=non;

		
			}//end of inner loop		

		}//end of outer for loop

//this loop is for assigning value

for(int i=1;i<maxxrow+1;i++)
	for(int j=1;j<maxxcol+1;j++)
	{
	if(org[i][j]=='@')
	{	
	if((count[i-1][j-1]==0))
	org2[i][j]='#';

	if((count[i-1][j-1]==1))
	org2[i][j]='#'; 

	if((count[i-1][j-1])>=4)
	org2[i][j]='#'; 

	if((count[i-1][j-1])==2)
	org2[i][j]='@';

	if((count[i-1][j-1])==3)
	org2[i][j]='@';
	}


	if(org[i][j]=='#')
	{
	if((count[i-1][j-1]==3))
	org2[i][j]='@'; 
	else
	org2[i][j]='#';
	}
	}



for(int i=1;i<maxxrow+1;i++)
	for(int j=1;j<maxxcol+1;j++)
	{
	org[i][j]=org2[i][j];
	}


N--;
}


non=0;
for(int i=1;i<maxxrow+1;i++)
	for(int j=1;j<maxxcol+1;j++)
	{
if(org[i][j]=='@')
non++;
	}

System.out.println(non);






}//end of main
}//end of class

