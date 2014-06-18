import ncst.pgdst.*;

public class DRRDC{
	public static void main(String a[]) throws IOException{
	
	char M[]={'D','C'};
	int M1[]={500,100};
	
	char D[]={'C','L','X'};
	int D1[]={100,50,10};
	
	char C[]={'L','X'};
	int C1[]={50,10};
	
	char X[]={'V','I'};
	int X1[]={5,1};

	char A[]={'M','D','C','L','X','V','I'};
	int A1[]={1000,500,100,50,10,5,1};

	int result=0;

	String inR=new String();

	SimpleInput sin=new SimpleInput();
	SimpleOutput sout=new SimpleOutput();

	inR=sin.readWord();
	int len=inR.length();
	
	char word[]=new char[len];
	word=inR.toCharArray();

	int ind=0;

	if(len>1)
	{
	a:
	for(int i=1;i<len;i++)
	{	

		if((i==len-1))
		{
		for(int j=0;j<7;j++)
		{
		if(word[len-1]==A[j])
		{
		result+=A1[j];
		break a;
		}
		}
		}

		if((i==0))
		{
		for(int j=0;j<7;j++)
		{
		if(word[len-1]==A[j])
		{
		result+=A1[j];
		}
		}
		}
		else	
		{
		if (word[i]=='M')
		{
		ind=i-1;
		result=result+value('M',ind,M,M1,2,A,A1,word);
		ind=0;
		}

		if (word[i]=='D')
		{
		ind=i-1;
		result+=value('D',ind,D,D1,3,A,A1,word);
		ind=0-1;
		}

		if(word[i]=='C')
		{
		ind=i-1;
		result+=value('C',ind,C,C1,2,A,A1,word);
		ind=0;
		}
		
		if(word[i]=='L')
		{
		//ind=i-1;
		
		}
		
		if(word[i]=='X')
		{
		ind=i-1;
		result+=value('X',ind,X,X1,2,A,A1,word);
		ind=0;
		}
			
		if(word[i]=='V')
		{
		//ind=i-1;
		
		}

		
		}
	};
	}//end of if

	       if(len==1)
		{
		 for(int j=0;j<7;j++)
		 {
		  if(word[ind]==A[j])
		  {
		  result+=A1[j];
		 
		  }
		 }
		}		 
	
	System.out.println(result);	
}

static int value(char s,int ind, char a[],int a1[],int arl,char a2[],int a22[],char w[])
{
	int res=0;
	for(int i=0;i<arl;i++)
	{
	if(i>1)
	{
	if(w[ind]==s)
		{
		res-=a1[i];
		System.out.println("****"+w[ind]);
		}
	else
	{
		for(int k=0;k<7;k++)
			if(a2[k]==s)
				res+=a22[k];
	}
	}
	else
	  for(int k=0;k<7;k++)
	       if(a2[k]==s)
	        res+=a22[k];


	
	System.out.println("..."+w[i]+"..."+a1[i]+"..."+a[i]+"...");
	}
	return res;	
}
}
