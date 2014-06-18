#include<stdio.h>
#include<string.h>
//stack with integer domain and its top pointer.
int expStack[200];
int *top=NULL;
int size=0;

// stack operations.
void push(int operand);
int pop();
int tel();
int isEmpty();
int isFull();

//variable for poping.
int var1,var2;

//function for evaluation of expression.
void expEval(char ch);

//main function.
int main(){
	int l1;
	char ch='a',ch2;
	int a=0,flag1=0;
	int i=1;
	while((ch!='\n')){
		ch2=getchar(); a=0;
		while((ch2>='0')&&(ch2<='9')){
			flag1=1; a=10*a+(ch2-'0'); ch2=getchar();
		}
		if(flag1==1) {//printf("%d\n",a);
			push(a);
		}
		if((ch2=='+')||(ch2=='-')||(ch2=='*')||(ch2=='^')||(ch2=='%')||(ch2=='/')){
			expEval(ch2);
		}
		a=0; flag1=0; ch=ch2;
	};
		printf("%d\n",expStack[0]);

}
int isFull(){
	if(size==199){ 
		return (1);
	}
	else{ 
		return (0);
	}
}

int isEmpty(){
	if(size==0) return 1;
	else return 0;
}

void push(int operand){

	
	if(!isFull()){
		if(top==NULL){

			top=&expStack[0];
			*top=operand; size++;
	//printf("inside if part of push and value pushed is %d\n",expStack[size-1]);
		}
		else{

			top++;
			*top=operand; 
	//printf("inside else of push and value pushed is %d\n",expStack[size]);
			size++;
		}

	}
}

int pop(){
	if(!isEmpty()){
		size--; 
	//printf("poped value is %d\n",*top);
		return *top--; 
	}
}

int tel(){
	if(!isEmpty()){
		return *top;
	}
}

void expEval(char ch){
	switch(ch){
		case '+':{
			var1=pop();
			var2=pop();
			push(var1+var2); break;
		}
		case '-':{
			var1=pop();
			var2=pop();
			push(var2-var1); break;
		}
		case '%':{
			var1=pop();
			var2=pop();
			push(var2%var1); break;
		}
		case '/':{
			var1=pop();
			var2=pop();
			push(var2/var1); break;
		}
		case '*':{
			var1=pop();
			var2=pop();
			push(var1*var2); break;
		}
		case '^':{
			var1=pop();
			var2=pop();
			push(pow(var2,var1)); break;
		}
	}
}


