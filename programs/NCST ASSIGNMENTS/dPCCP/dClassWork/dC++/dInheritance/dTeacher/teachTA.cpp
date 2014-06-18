#include<iostream>
using namespace std;


	//*************************************************class parent 
class person{
	public:
		int a;

};
	//*************************************************class teacher
class teacher:virtual public person{
	public:
		//int a;
		virtual void show(char);

};

void teacher::show(char f){
	cout<<"teachers show and value is "<<f<<endl;
	
}
	//*************************************************class student

class student:virtual public person{
	public:
		//int a;
		virtual void show(int);

};
void student::show(int i){
	cout<<"student show and value is "<<i<<endl;
}
	//*************************************************class TA

class TA: public teacher, public student{
	public:
	//int a;
	//void show(int);
	void show(char);

};
/*void TA::show(int i){

	cout<<"TA's show and value is "<<i<<endl;

}*/
void TA::show(char ch){

	cout<<"implemented teacher's  show and value is "<<ch<<endl;

}


int main(){
	TA *t=new TA();
	t->student::a=30;
	t->a=20;
	//teacher tc1;
	//int c=tc1.a=20;
	cout<<t->a<<"***"<<t->student::a<<" "<<t->person::a<<" "<<t->student::a<<endl;
	//t.show('a');
	//teacher *st1=&t;
	//st1->show(48);
	//cout<<student::b<<endl;
	return 0;
}

