#include<stdio.h>
int func(int a,int b){
	return a+b;
}

int func1(int a,int b){
	return a*b;
}
int func3(int a){
	printf("hi\n");
}

int (*om(int a,int (*func2)(int a,int b)))(int c){
	printf(" i am om values %d & %d",a,func2(30,40));
	return func3;
}

int main( ){
	char c;
	int val;
	int (*func2)(int a,int b);
	int (*fun)(int a);
	fun=om(89,func1);
	fun(90);
	func2=func1;
	val=func2(8,40);
	printf("%d\n",val);
	func2=func;
	val=func2(100,23);
	printf("%d\n",val);
}
