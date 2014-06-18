#include <iostream>
using namespace std;
class fof;

class God{
	public:
	static char *presence;
	char *name;
	char *day;
	void getName();
	friend class fof;//friend of God.
	inline static void getP(){
		cout<<presence<<endl;
	}
	friend int fofG(God &g);
	God(){}
	God(char *n){
		name=n;
	}
};
int fofG(God *g){
cout<<g->name<<endl;
return 80;

}

void God::getName( ){
	cout<<name<<endl<<day<<endl;
}
char* God::presence="universe";

class fof{
public: void f1(God *g);	
};

void fof::f1(God *g){
cout<<g->name<<endl<<"fof::f1"<<endl;
}

int main(){
	
	God *g=new God("laxmi");
	g->name="Shiva";
	printf("strlen is %d\n",strlen(g->name));
	//g.day="Monday";
	//g.getName();
	God g1("oss");
	//God::presence="world";
	g1.getP();
	fof fdg;
	fdg.f1(g);
	int val=fofG(g);
	cout << "ons" << "\n" << "osso" << "\n"<<val<<endl;
}
