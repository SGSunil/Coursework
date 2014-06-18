#include <iostream>
#include <iomanip>
using namespace std;
class  A{
        int i;
        public:
                A() { cout << "A(): \n"; this->i = 20; }
                A(int i) { cout << "A(int): \n"; this->i = i; }
                void print() { cout << i << "\n"; }
                ~A() { cout << "~A()\n"; }
};

int main()
{
        A arr[5] = { A(100), A(200), A(300) };
	char ch[50],c;


	while((c=cin.get())!='a'){
		cout<<flush;
		cout.put(c);
		cout<<flush;
	}
	cout<<flush;
	cin.getline(ch,50,'a');
	cout<<setw(50);
	cout<<setfill('*');
	cout<<ch<<endl;
	cin.putback(10);

	cout<<cin.peek()<<endl;
	cout<<flush;

        for(int i = 0; i < 5; i++){
                arr[i].print();
        }
        cout << "End of Main\n";
	//cerr<<"ons"<<endl;
	clog<<"jho";
        return 0;
}

