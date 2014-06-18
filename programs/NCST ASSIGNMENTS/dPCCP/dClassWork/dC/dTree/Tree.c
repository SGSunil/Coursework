#include<stdio.h>
typedef struct node Node;
struct node{
	int value;
	int noc;
	int label,balance,isRight,isLeft;
	Node *left,*right;
};
	Node *root=NULL;
int max=1,tmp,bt=0,acbt=0,cbt=0,i=0;
int balance(Node *rt);
void insert(int val);
int isDivisble2(int number);
int main(){
	int a,i,size,bool=0;
	int nOn=0;
	scanf("%d",&size);
	for(i=0;i<size;i++){
		scanf("%d",&a);
		insert(a);
		nOn++;
	}
	//printf("max label is %d\n",max);
	//balance(root);
	inOrder(root);

//printf("BT=%d ACBT=%d CBT=%d\n",bt,acbt,cbt);
	if(bt==1)
		printf("BT\n");
	else{
		isDivisible2(nOn+1);
		if(cbt==1)
		printf("CBT\n");
		else
		printf("ACBT\n");
	}

}
void insert(int val){
	Node *ptr,*parent=root;
	int flag=0; Node *tmp=(Node* )malloc(sizeof(Node));
	tmp->left=NULL; tmp->right=NULL; tmp->value=val;
	tmp->noc=0; ptr=root;
	if(root==NULL){
		root=tmp; root->label=1;
	}
	else{
		while(ptr!=NULL){
			parent=ptr;
			if(val>parent->value){
				ptr=ptr->right;
			}
			else{
				ptr=ptr->left;
			}
			flag=1;

		}
	}
		if(flag==1){
		if(val>parent->value){
			parent->right=tmp;
			tmp->label=parent->label+1;
			if(tmp->label>max)
				max=tmp->label;
			parent->noc++;
			parent->isRight=1;

				
			
		}
		else{
			parent->left=tmp;
			tmp->label=parent->label+1;
			if(tmp->label>max)
				max=tmp->label;
			parent->isLeft=1;
			parent->noc++;
		}
			if(parent->noc==2){
				parent->isLeft=parent->isRight=0;
			}
		}
	
}
			
int inOrder(Node *rt){
	if(rt!=NULL){


		
		inOrder(rt->left);		
		//printf("value %d chids are %d label is %d isRight=%d isLeft=%d\n",rt->value,rt->noc,rt->label,rt->isRight,rt->isLeft);

		if((rt->label<(max-1))&&((rt->noc==0)||(rt->noc==1))){
			bt=1;cbt=acbt=0; 
		//printf("inside ist cond, value %d chids are %d label is %d and isRight %d and bt=%d cbt=%d acbt=%d\n",rt->value,rt->noc,rt->label,rt->isRight,bt,cbt,acbt);
		}
		
		if((rt->label==max-1)&&(rt->noc==1)&&(rt->isRight==1)){
			bt=1;cbt=acbt=0; 
		//printf("inside 2nd cond, value %d chids are %d label is %d and isRight %d and bt=%d cbt=%d acbt=%d\n",rt->value,rt->noc,rt->label,rt->isRight,bt,cbt,acbt);
			return 0;
		}

		if((rt->label==max-1)&&(rt->noc==0))
			i++;

		if((i>=1)&&(rt->label==max-1)&&((rt->noc==1)||(rt->noc==2))){
			bt=1;cbt=acbt=0;
		//printf("inside 4th cond, value %d chids are %d label is %d and isRight %d and bt=%d cbt=%d acbt=%d\n",rt->value,rt->noc,rt->label,rt->isRight,bt,cbt,acbt);
			return 0;
		
		}

		inOrder(rt->right);		
	}
	else {
		return 1;
	}
}

int balance(Node *rt){
	if(rt!=NULL){
		balance(rt->left);
		balance(rt->right);

	}
}

int isDivisible2(int number){
	int m=number;
	int a=m;
	while(((m)!=1)&&(a!=1)){
		a=m%2;
		m=m/2;
		
	}
	if((m==1)&&(a!=1)){
	//printf("inside isDivisible and found CBT\n");
		bt=0;cbt=1;acbt=0;
		return 0;
	}
	else{ 
		acbt=1;bt=0;cbt=0;
	//printf("inside isDivisible and found ACBT\n");
		return 0;
	}
}
