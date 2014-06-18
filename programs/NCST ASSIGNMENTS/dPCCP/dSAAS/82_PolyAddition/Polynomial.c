#include<stdio.h>
#include<string.h>
int A[15][2],B[15][2],result[40][2],Result[40][2],matchB[15],matchA[15],i=0, j=0, mA=0,mB=0,p=0, q=
0,a,b,c,t=0,t1=0,min;
int main(){
        int l1=0,l2=0;
        while(1>0){
                scanf("%d",&a); scanf("%d",&b);
                if((a==-1)&&(b==-1))
                        break;
                else{
                        A[i][0]=a; A[i][1]=b;
                        i+=1; matchA[mA]=0; mA++;
                }
        }
        while(1>0){
                scanf("%d",&a); scanf("%d",&b);
                if((a==-1)&&(b==-1))
                        break;
                else{
                        B[j][0]=a; B[j][1]=b;
                        j+=1; matchB[mB]=0; mB++;//match array length
                }
        }
        for(l1=0;l1<i;l1++){
                for(l2=0;l2<j;l2++){
                        if((A[l1][1]==B[l2][1])&&(matchA[l1]==0)&&(matchB[l2]==0)){
                                matchB[l2]=1; matchA[l1]=1; c=(A[l1][0]+B[l2][0]);
                                Result[p][0]=c; Result[p][1]=A[l1][1];
				B[l2][0]=0; 
                                p+=1;
                        }
                }
        }
        for(l1=0;l1<mA;l1++){
                if(matchA[l1]==0){
                        Result[p][0]=A[l1][0]; Result[p][1]=A[l1][1]; p+=1;
                }
        }

        for(l1=0;l1<mB;l1++){
                if(matchB[l1]==0){
                        Result[p][0]=B[l1][0]; Result[p][1]=B[l1][1]; p+=1;
                }
        }
        for(l1=0;l1<p;l1++){
                if(Result[l1][0]!=0){
                        result[q][0]=Result[l1][0]; result[q][1]=Result[l1][1];
                        q+=1;
                }
        }
	
        //Sorting
        for(l1=0;l1<q;l1++){
                min=l1;
                for(l2=l1+1;l2<q;l2++){
                        if(result[l2][1]>result[min][1]){
                                min=l2; mA=-200;
                        }
                }
                if(mA==-200){
                        //exchanging process
                        t=result[l1][0];
                        t1=result[l1][1];
                        result[l1][0]=result[min][0];
                        result[l1][1]=result[min][1];
                        result[min][0]=t;
                        result[min][1]=t1;
                }
        }
	for(l1=0;l1<q;l1++){
		for(l2=l1+1;l2<q;l2++){
			if(result[l1][1]==result[l2][1]){
				result[l1][0]+=result[l2][0];
				result[l2][0]=0;
			}
		}			
	}

        l1=0;
        while(l1<q){
		if(result[l1][0]!=0){
                	printf("%d",result[l1][0]);
                	printf(" %d ",result[l1][1]);
		}
                ++l1;
        }
        printf("\n");
}
