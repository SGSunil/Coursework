#include<iostream>
#include<iomanip>
#define Max 3 
using namespace std;
class String;
//******************************************function overloading
int add1(char a);
void add1(int a,int b);

//*****************************************
//******************************************operator overloading
String& operator+(String& ,String&);
void operator+=(String& ,String&);
String& operator+(String& ,char*);
ostream& operator<<(ostream& ,String& );

//*****************************************
//******************************************overloading

//*****************************************
struct person{
	int id;
	char name[20];
};

typedef struct person emp;

class String{
	public:
	char *str;
	int len;
	int value;
	static int ind;
	emp e[20];
	public:
	//***************************************************functions constructors
	String(){
		cout<<"Called default constructor "<<endl;
		len=50;
		value=0;
		str=new char[len+1];
		str[len]='\0';
	}
	
	String(const char* strc){
		len=strlen(strc);
		cout<<"called constructor for "<<strc<<" len is "<<len<<endl;
		str=new char[len+1];
		strcpy(str,strc);
		str[len]='\0';
	}		

	
	String(const String& sObj){
		cout<<"Called copy constructor "<<endl;
		len=strlen(sObj.str)+1;
		str=new char[len];
		strcpy(this->str,sObj.str);
	}
	
	~String(){
		//cout<<"Called distructor "<<"for String "<<str;
		cout<<"Called distructor "<<"for String "<<str<<endl;
		if(str==NULL)
		cout<<"here "<<endl;
		else
		delete str;
	}

	//***************************************************functions operator overloading 
	void operator=(String& b){
		len=strlen(b.str);
		delete[] str;
		str=new char[len+1];
		strcpy(str,b.str);
	}

	int& operator[](const char* str){
		int i;
		int *in=new int;
		for(i=0;i<Max;i++){
			if(!strcmp(str,e[i].name)){

				cout<<"matched at..."<<i<<endl;
				return e[i].id;
			}
			else{
				cout<<"not matched "<<endl;
				return *in;
			}

		}
	}
	
	emp* operator->( ){
		return &e[1];	
	}

	void* operator new(unsigned int t){
		cout<<"called new operator"<<endl;
		void* p=new char[sizeof(String)/sizeof(char)];
		return p;
	}		
	

	//***************************************************functions others 

	void printAll( ){
		cout<<"String value is "<<str<<"***"<<endl;
	
	}

	void initStruct(const int index, const char* name, const int& id){
		int i;
		strcpy(e[index].name,name);
		e[index].id=id;
		cout<<"name "<<e[index].name<<" id "<<e[index].id<<endl;
	}

	void printStruct(){
		int i;
		for(i=0;i<Max;i++)
		cout<<"name "<<e[i].name<<" id "<<e[i].id<<endl;
	}

	void operator()(int a){
	char res[20]; int fflag=0;
	for(int i=0;i<Max;i++){
		if(e[i].id==a){
			strcpy(res,e[i].name);	
			fflag=1;
			break;
		}
	}	
	if(fflag==1) cout<<"found and it is "<<res<<endl;
	else cout<<"try another query"<<endl;	
	}
 
	//friend String operator+(String&,String&);
	//friend void operator+=(String& ,String&);
	//***************************************************end class 
};


int main(){
	int i;
	String s4=String("OJJD"),s5=s4;
	cout<<s4.str<<" "<<s5.str<<endl;
	String *s1=new String("ONSJMD");
	cout<<s1->str<<endl;
	char *str=new char[20];
	int id1;
	String s=String("KalirMandhan");//=new String("ONS1");
	cout<<"enter values for structures"<<endl;
	for(i=0;i<Max;i++){
		scanf("%s %d",str,&id1);
		s.initStruct(i,str,id1);		
	}
	s(10);

	return 0;
}
//******************************************function overloading
void add1(int a,int b){
	printf("%d\n",(a+b));
}

int add1(char a){
	printf("%c\n",(a));
}

//*****************************************
//******************************************operator overloading
String& operator+(String& a,String& b){
	String *temp=new String();
	temp->len=strlen(a.str)+strlen(b.str)+1;
	temp->str=new char[temp->len];
	strcpy(temp->str,a.str);
	strcat(temp->str,b.str);
	return *temp;
}
void operator+=(String& a,String& b){
	a.len=a.len+b.len;
	char *temp = new char[a.len+1];
	strcpy(temp,a.str);
	strcat(temp,b.str);
	a.str = new char[a.len];
	strcpy(a.str,temp);
}
String& operator+(String& a,char *b){
	a.len=a.len+strlen(b);
	char *temp = new char[a.len+1];
	strcpy(temp,a.str);
	strcat(temp,b);
	a.str = new char[a.len];
	strcpy(a.str,temp);
}

ostream& operator<<(ostream& c,String& a){
	return c<<a.str;
}
//*****************************************

