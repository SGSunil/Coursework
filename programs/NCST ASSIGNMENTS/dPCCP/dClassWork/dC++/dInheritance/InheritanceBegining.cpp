#include<iostream>
#include<iomanip>

#define SIZE 20

using namespace std;

class parent{
	private:
	//***********************************private members/methods
	int id;
	char *city;

	protected:
	//***********************************protected
	char *name;
	int age;

	public:
	//***********************************public
	void getNaAge();
	void getICity();
	
	//***********************************constructors
	parent( );
	parent(const int, const char*,const char*,const int);
	 
};

class parent1:public parent{
	private:
	//***********************************private members/methods
	int id;
	char *city;

	protected:
	//***********************************protected
	char *name;
	int age;

	public:
	//***********************************public
	void getNaAge();
	void getICity();
	
	//***********************************constructors
	parent( );
	parent(const int, const char*,const char*,const int);
	 
};


class base1: protected parent{
	public:	
	//***********************************constructors
	base1():parent(22,"sunil","holland",101){
		cout<<"created base class object"<<endl;

	}

};



int main( ){
	parent p1(40,"sunil","karnal",101);
	base1 b1;
	b1.getICity();

}

parent::parent(){
	cout<<"called default parent constructor "<<endl;
	name=new char[1];
	city=new char[3];
	name[0]='\0';
	strcpy(city,"NO");
	city[2]='\0';
	id=0;
	age=20;
}

parent::parent(const int age,const char* name, const char* city, const int id){
	cout<<"called parameterized parent constructor "<<endl;
	int len1=strlen(name),len2=strlen(city);
	cout<<"len of name is "<<len1<<" len of city is "<<len2<<endl;
	this->name=new char[len1+1];
	this->city=new char[len2+1];
	strcpy(this->name,name);	
	strcpy(this->city,city);
	this->id=id;
	this->age=age;
	cout<<"name is "<<this->name<<"city is "<<this->city<<endl;
}

void parent::getICity(){
	cout<<"id is "<<id<<" city is "<<city<<endl;
}
