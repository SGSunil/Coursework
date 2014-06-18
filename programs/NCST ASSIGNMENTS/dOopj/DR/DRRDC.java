import ncst.pgdst.*;
import java.lang.*;

public class DRRDC{
        public static void main(String a[]) throws IOException{

        char M[]={'D','C'};
        int M1[]={500,100};

        char D[]={'C','X'};
        int D1[]={100,10};

        char C[]={'L','X'};
        int C1[]={50,10};

        char X[]={'V','I'};
        int X1[]={5,1};

        char A[]={'M','D','C','L','X','V','I'};
        int A1[]={1000,500,100,50,10,5,1};

        int result=0,N=0;
	String s=new String();


        SimpleInput sin=new SimpleInput();
        SimpleOutput sout=new SimpleOutput();
	int value=0;//value that is operated upon to find Roman representation.

	int ind=0;
	int len=0; //length of the string
	
	System.out.println("enter how many conversion u want...");
	
	N=sin.readInt();	
	System.out.println("is this the no u wanted..."+N);

	int no=0;
	String str[]=new String[N];
	
	while(no<N)
		{
			str[no]=sin.readWord();
			no++;

		}

	no=0;

	while(no<N)
	{

	
//	int intValue=Integer.parseInt(inR); // integer conversion of string.
	int intCheck=str[no].charAt(0); // integer conversion for first character of the word.	
	
	System.out.println("+++"+str[no].charAt(0)); //checking the conversion only

	if((intCheck=='1') | (intCheck=='2')| (intCheck=='3')|(intCheck=='4')|(intCheck=='5')|(intCheck=='6')|(intCheck=='7')|(intCheck=='8')|(intCheck=='9')) 
	{
	value=Integer.parseInt(str[no]);
	
	System.out.println("***"+value);
	
       while(value>0)
        {

        if(value>999)
        {
	int t=value/1000;
	for(int i=0;i<t;i++)
	{
        s+="M";
	value-=1000;
	}
        //value-=1000;
	System.out.println("value in M loop"+value);
        }

        if(value>899)
        {
        s+="CM";
        value-=900;
        } 
	
	if(value>499)
        {
        s+="D";
        value-=500;
        }

        if(value>399)
        {
        s+="CD";
        value-=400;
        }
        
        if(value>99)
        {
	int t=value/100;
	for(int i=0;i<t;i++)
	{
        s+="C";
	value-=100;
	}
       // value-=100;

	System.out.println("value in C  loop"+value);
        }

 /*       if(value>89)
        {
        s+="XC";
        value-=90;
        }
        
	if(value>79)
        {
        s+="LXXX";
        value-=80;
        }
	
        if(value>69)
        {
        s+="LXX";
        value-=70;
        }

        if(value>59)
        {
        s+="LX";
        value-=60;
        }                   */

        if(value>49)
        {
        s+="L";
        value-=50;
        }


        if(value>39)
        {
        s+="XL";
        value-=40;
        }

      /*  if(value>29)
        {
        s+="XXX";
        value-=30;
        }

        if(value>19)
        {
        s+="XX";
        value-=20;
        }               */

	if(value>9)
        {
	int t=value/10;
	for(int i=0;i<t;i++)
	{
        s+="X";
	value-=10;
	}
	System.out.println("outside the loop");
	System.out.println("value in X  loop"+value);
        }


        if(value==9)
        {
        s+="IX";
        value-=9;
        }


        if(value>4)
        {
        s+="V";
        value-=5;
        }

	if(value==4)
	{
	s+="IV";
	value-=4;
	}

        if(value>=1)
        {
        s+="I";
        value-=1;

	System.out.println("value in I  loop"+value);
        }

	if(value==0)
	{
System.out.println("the Roman..."+s);
	break;

	}
}
	}

	else
	{
	str[no]=str[no]+" ";
	len=str[no].length();
	
        for(int i=1;i<len;i++)
        {

              if((i==len-1))
                {
        	        for(int j=0;j<7;j++)
               		 {
                	if(A[j]==str[no].charAt(len-2))
               		 {
        	        result+=A1[j];
			System.out.println("len+1..."+"A1[j] "+A1[j]+"---");

			 }
                	}
                }


                if (str[no].charAt(i)=='M')
                {
			ind=i-1;
       			 if(str[no].charAt(ind)=='C')
               		 {
              		  result-=100;
			  
			}
			if(str[no].charAt(ind)!='C')	
			{
                	for(int k=0;k<7;k++)
			{
                  	if(A[k]==str[no].charAt(ind))
			{
                   	result+=A1[k];
			System.out.println("inside M***"+A[k]);
			break;
			}
      		  	}
                	}
		
		}
			
                if (str[no].charAt(i)=='D')
                {
			ind=i-1;
			for(int j=0;j<2;j++)
			{
                         if(str[no].charAt(ind)==D[j])
                         {
                          result-=D1[j];
			  break;
                         }
			 }
                        if((str[no].charAt(ind)!='C')&(str[no].charAt(ind)!='X')){
			for(int k=0;k<7;k++)
			{
                        if(A[k]==str[no].charAt(ind))
			{
                        result+=A1[k];
			System.out.println("inside D@@@"+A[k]);
			break;

			}
			}
                        }
                }

                if(str[no].charAt(i)=='C')
                {
		   ind=i-1;
		  if( str[no].charAt(ind)=='X')
		  {
		    result-=10;
                   }
                  if( str[no].charAt(ind)!='X')
                  {
                  for(int k=0;k<7;k++)
                 {
                   if(A[k]== str[no].charAt(ind))
                  {
               result+=A1[k];
              System.out.println("inside C  ***"+A[k]);
                 break;
                  }
              }
            }

                }

                if( str[no].charAt(i)=='L')
                {
		 ind=i-1;
		 	if( str[no].charAt(ind)=='X')
                         {
                          result-=10;
				break;
                        }
                        if( str[no].charAt(ind)!='X')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]== str[no].charAt(ind))
                        {
                        result+=A1[k];
                        System.out.println("inside L***"+A[k]);
                        break;
                        }
                        }
                        }


                }

                if( str[no].charAt(i)=='X')
                {
		 ind=i-1;
                         if( str[no].charAt(ind)=='I')
                         {
                          result-=1;
                        }
                        if( str[no].charAt(ind)!='I')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]== str[no].charAt(ind))
                        {
                        result+=A1[k];
                        System.out.println("inside X***"+A[k]);
                        break;
                        }
                        }
                        }


                }

                if(str[no].charAt(i)=='V')
                {
		 ind=i-1;
                         if( str[no].charAt(ind)=='I')
                         {
                          result-=1;
                        }
                        if( str[no].charAt(ind)!='I')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]== str[no].charAt(ind))
                        {
                        result+=A1[k];
                        System.out.println("inside V***"+A[k]);
                        break;
                        }
                        }
                        }

			
        	}

		if( str[no].charAt(i)=='I')
		{	ind=i-1;
			//if( str[no].charAt(ind)!='0')
		 for(int k=0;k<7;k++)
                        {
                        if(A[k]== str[no].charAt(ind))
                        {
                        result+=A1[k];
                        System.out.println("inside V***"+A[k]);
			break;
                        }
                        }

		}ind=0;	
		}
        System.out.println(result);
		}
	no++;
}

	}

	}


