import ncst.pgdst.*;

public class DRRD1{
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

        int result=0;

        String inR=new String();

        SimpleInput sin=new SimpleInput();
        SimpleOutput sout=new SimpleOutput();

        inR=sin.readWord();
        int len=inR.length();

        char word1[]=new char[len];
        char word[]=new char[len+2];

        word1=inR.toCharArray();

	word[0]=word[len+1]='0';
	for(int i=1;i<len+1;i++)
	{
	word[i]=word1[i-1];
	}	

	int ind=0;
	boolean match=false;
        for(int i=2;i<len+2;i++)
        {

              if((i==len+1))
                {
        	        for(int j=0;j<7;j++)
               		 {
                	if(A[j]==word[i-1])
               		 {
        	        result+=A1[j];
			System.out.println("len+1..."+"A1[j] "+A1[j]+"---"+word[i-1]);

			 }
                	}
                }


                if (word[i]=='M')
                {
			ind=i-1;
       			 if(word[ind]=='C')
               		 {
              		  result-=100;
			  
			}
			if(word[ind]!='C')	
			{
                	for(int k=0;k<7;k++)
			{
                  	if(A[k]==word[ind])
			{
                   	result+=A1[k];
			System.out.println("inside M***"+A[k]);
			break;
			}
      		  	}
                	}
		
		}
			
                if (word[i]=='D')
                {
			ind=i-1;
			for(int j=0;j<2;j++)
			{
                         if(word[ind]==D[j])
                         {
                          result-=D1[j];
			  break;
                         }
			 }
                        if((word[ind]!='C')&(word[ind]!='X')){
			for(int k=0;k<7;k++)
			{
                        if(A[k]==word[ind])
			{
                        result+=A1[k];
			System.out.println("inside D@@@"+A[k]);
			break;

			}
			}
                        }
                }

                if(word[i]=='C')
                {
		   ind=i-1;
		  if(word[ind]=='X')
		  {
		    result-=10;
                   }
                  if(word[ind]!='X')
                  {
                  for(int k=0;k<7;k++)
                 {
                   if(A[k]==word[ind])
                  {
               result+=A1[k];
              System.out.println("inside C  ***"+A[k]);
                 break;
                  }
              }
            }

                }

                if(word[i]=='L')
                {
		 ind=i-1;
		 	if(word[ind]=='X')
                         {
                          result-=10;
				break;
                        }
                        if(word[ind]!='X')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]==word[ind])
                        {
                        result+=A1[k];
                        System.out.println("inside L***"+A[k]);
                        break;
                        }
                        }
                        }


                }

                if(word[i]=='X')
                {
		 ind=i-1;
                         if(word[ind]=='I')
                         {
                          result-=1;
                        }
                        if(word[ind]!='I')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]==word[ind])
                        {
                        result+=A1[k];
                        System.out.println("inside X***"+A[k]);
                        break;
                        }
                        }
                        }


                }

                if(word[i]=='V')
                {
		 ind=i-1;
                         if(word[ind]=='I')
                         {
                          result-=1;
                        }
                        if(word[ind]!='I')
                        {
                        for(int k=0;k<7;k++)
                        {
                        if(A[k]==word[ind])
                        {
                        result+=A1[k];
                        System.out.println("inside V***"+A[k]);
                        break;
                        }
                        }
                        }

			
        	}

		if(word[i]=='I')
		{	ind=i-1;
			if(word[ind]!='0')
		 for(int k=0;k<7;k++)
                        {
                        if(A[k]==word[ind])
                        {
                        result+=A1[k];
                        System.out.println("inside V***"+A[k]);
			break;
                        }
                        }

		}ind=0;	
		};


        System.out.println(result);
	}

	}


