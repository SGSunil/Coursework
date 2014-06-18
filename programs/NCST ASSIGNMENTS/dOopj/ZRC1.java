import ncst.pgdst.*;

public class ZRC1
{

public static void main(String a[]) throws IOException {

int N=0,M=0;//size of matrix

SimpleInput sin=new SimpleInput();// I/O streams
SimpleOutput sout=new SimpleOutput();

N=sin.readInt();
M=sin.readInt();
int value=0;

int m[][]=new int[N][M];
char boolm[][]=new char[N][M];

int rowsCond[]=new int[N];

for(int i=0;i<N;i++)
{
rowsCond[i]=0;
}

for(int i=0;i<N;i++) // initialize the integer matrix m[][]
{
        for(int j=0;j<M;j++)
	        {
		        m[i][j]=sin.readInt();
			        boolm[i][j]='F';
				        }
					}

					for(int i=0;i<N;i++) //initialize the condition matrix
					{
					        for(int j=0;j<M;j++)
						        {
							         if(m[i][j]==0)
								           value+=0;
									            else
										              value+=1;
											              }
												              if(value==0)
													              {
														               for(int j=0;j<M;j++)
															                {
																	          boolm[i][j]='T';
																		          rowsCond[i]=1;
																			           }
																				            }
																					             value=0;
																						     }
																						     value=0; //reset for the columns wise traversal
																						     for(int i=0;i<M;i++)
																						     {
																						             for(int j=0;j<N;j++)
																							             {
																								              if(m[j][i]==0)
																									                value+=0;
																											         else
																												           value+=1;
																													           }
																														           if(value==0)
																															           {
																																            for(int k=0;k<N;k++)
																																	             {
																																		               boolm[k][i]='T';
																																			                }
																																					        }
																																						        value=0;
																																							}

																																							/*for(int i=0;i<N;i++)
																																							{
																																							        for(int j=0;j<M;j++)
																																								                {
																																										                  System.out.print(boolm[i][j]);
																																												                    System.out.print(m[i][j]);
																																														                     }
																																																                      System.out.println();
																																																		      }*/

																																																		      for(int i=0;i<N;i++)
																																																		      {
																																																		              for(int j=0;j<M;j++)
																																																			              {
																																																				               if(boolm[i][j]=='F')
																																																					                {
																																																							          System.out.print(m[i][j]+" ");
																																																								           }

																																																									           }

																																																										                   if(rowsCond[i]==0)
																																																												                   System.out.println();
																																																														   }

																																																														   //System.out.println();
																																																														   }
																																																														   }
																																																														   
