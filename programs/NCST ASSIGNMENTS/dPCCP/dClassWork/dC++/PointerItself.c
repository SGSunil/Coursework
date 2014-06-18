#include<stdio.h>
void abc(int x,int y){
	printf("%d \n",(x+y));
}

int main(){
	void (*funct)(int x,int y);
	void (*func(int x,int y))(int a,int b);
	funct=func(12,13);
	funct(100,234);
}

	void (*func(int x,int y))(int a,int b){
		printf("hi sum %d\n",(x+y));
		return abc;
}
