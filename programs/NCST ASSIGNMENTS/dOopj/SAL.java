import ncst.pgdst.*;

public class SAL
{
	public static void main(String aa[]) throws IOException
		{

			SimpleInput sin=new SimpleInput();
			SimpleOutput sout=new SimpleOutput();

//variable declarations
	int value[]={0,0};//moves by A and B-val[0], val[1];


	int N=0;
	N=sin.readInt();
	System.out.println("..."+value[0]+"the no. of snakes and ladder..."+N);

	int snake[]=new int[N+1];
	int ladder[]=new int[N+1];
	ladder[N]=snake[N]=0;

	int w=0,x=0; //track the next snake and ladder position to input their values pairs.
	int a1 =0,b1=0; //input values for value pair.
	int s=0,l=0; //count the total no snakes pos and ladder pos.
	int bite=0,up=0;

	while(N>0)
		{
			 a1=sin.readInt();
 			b1=sin.readInt();

 			if(a1>b1)
 				{
					snake[w]=a1;
					snake[w+1]=b1;
					 w+=2;
					 System.out.println("a>b");
					 s+=2;
					N--;
 				}

			 if(b1>a1)
 				{
					 ladder[x]=a1;
					 ladder[x+1]=b1;
					 System.out.println("b>a");
					 x+=2;
					 l+=2;
					N--;
 				}

	 System.out.println("W is.."+w+" X is..."+x+" s is..."+s+" l is..."+l);

		}

		boolean reloop=false;

	int D=0; //total no. moves
	D=sin.readInt();
	int p=0,q=0; //
	int a[]=new int[D+1];
	int b[]=new int[D+1];


	while(D>0)
		{
			 a[p]=sin.readInt(); p+=1;
			 b[q]+=sin.readInt(); q+=1;
			 D=D-2;

		}

	for(int i=0;i<p;i++)
		System.out.println("___"+a[i]+"___");

	System.out.println("the elements in arrary A is P..."+p+" elements in B is q..."+q);

// check for A'a value., where it will be at last.
	for(int i=0;i<p;i++)
		{
			 value[0]+=a[i];


			 for(int j=0;j<l-1;j+=2)  //if  idder is present in between, then increment.
				 {
 
					  if(value[0]==ladder[j])
 						 {
 							  up=ladder[j+1]-ladder[j];
 							  value[0]+=up;
	System.out.println("ladder up in A//"+value[0]);
			for(int k=1;k<s;k+=2)
				{
				if(value[0]==snake[k])
				value[0]=0;
				}
	

			 for(int k=0;k<s-1;k+=2)  //if snake is present in between, then decrement.
 				{
 
 					 if(value[0]==snake[k])
  						{
  							 bite=snake[k]-snake[k+1];
  							 value[0]-=bite;
	System.out.println("snake bite after ladderup || "+value[0]);
							 continue;
  						}
 
				  }
						}
						continue;
						
 				}
			 
			 
			 for(int j=0;j<s-1;j+=2)  //if snake is present in between, then decrement.
 				{
 
 					 if(value[0]==snake[j])
  						{
  							 bite=snake[j]-snake[j+1];
  							 value[0]-=bite;
	System.out.println("snake bite after in B <<  "+value[0]);

				}
  						}
						continue;
 
				 } 






// check for B's value., where it will be at last.
	for(int i=0;i<p;i++)
		{
			 value[1]+=b[i];

			
 
			 for(int j=0;j<s-1;j+=2)  //if snake is present in between, then decrement.
 				{
 					 if(value[1]==snake[j])
  						{
						   bite=snake[j]-snake[j+1];
 						  value[1]-=bite;
			 for(int k=0;k<l-1;k+=2)  //if  idder is present in between, then increment.
				 {
 
					  if(value[1]==ladder[k])
 						 {
 							  up=ladder[k+1]-ladder[k];
 							  value[1]+=up;
						  }
						  }
 

  				}
 				}


		   
			 for(int j=0;j<l-1;j+=2)  //if  idder is present in between, then increment.
 				{
 
					  if(value[1]==ladder[j])
  						{
  							 up=ladder[j+1]-ladder[j];

							   value[1]+=up;

			 for(int k=0;k<s-1;k+=2)  //if snake is present in between, then decrement.
 				{
 					 if(value[1]==snake[k])
  						{
						   bite=snake[k]-snake[k+1];
 						  value[1]-=bite;
  						}
 				}

  						}
 				}

 
	}//end of for loop outest

	System.out.println("Value for A..."+value[0]+"value for B..."+value[1]);
				   

	if(value[0]>value[1])
		{
			 System.out.println("A "+value[0]);
		}
	else
		{
			 System.out.println("B "+value[1]);
		}

	}//end of main
	}//end of class


