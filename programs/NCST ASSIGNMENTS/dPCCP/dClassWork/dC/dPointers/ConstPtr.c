#include<stdio.h>

int main(){

	char *str="sunil mandhan";
	printf("%s\n",str);
	int item=100,item1=200;
	int* const ptr1=&item;
	const int* const ptr=&item1;
	printf("*ptr should be 100 and is %d\n",*ptr);

}
