#include<iostream>
#include<iomanip>
using namespace std;
//******************************************overloading
int add1(char a);
void add1(int a,int b);

//*****************************************

class String{
	private:
	char *str;
	int len=0;

	public:
	Main(){
		len=0;
		str=new char[len+1];
		str[len]='\0';
	}	
	Main(const String& sObj){
		strcpy(this->str,sObj.str);
		this->len=strlen(sObj.str);
	}
	void printAll( ){
		cout<<"a value is "<<a<<" and b's "<<b;
	
	}
};


 


int main(){
	Main a;
	(&a)->printAll();
	add1(12,13);
	add1(48);
}
//******************************************overloading
void add1(int a,int b){
	printf("%d\n",(a+b));
}

int add1(char a){
	printf("%c\n",(a));
}

//*****************************************
