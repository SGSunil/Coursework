#include <iostream>
using namespace std;
class part {
        int i;
        public:
                part() { cout << "part() " << endl; this->i = 400; }
                part(int i) { cout << "part(int i) " << i << endl; this->i = i; }
                part & operator = (const part & op) {
                                cout << "operator overloading..." << endl;
                                this->i = op.i;
                                cout << "part operator = " << endl;
                                return *this;
                }
                part(const part & op) {
                                this->i = op.i;
                                cout << "part copy ctor " << i << endl;
                }
                ~part() { cout << "~part " << i << endl; }
};

class whole {
        part p1, p2, p3;
        public:
                whole() { cout << "whole(): " << endl; }
                whole(int i, int j, int k): p1(i), p2(j), p3(k) {
                                                cout << "whole(): " << endl;
                }
                whole(const whole & ow) {
                                cout << "whole Copy Ctor\n";
                }
                ~whole() { cout << "~whole(): " << endl; }
		int abc(){
			printf("sunil\n"); return 1;
		}
};

int main()
{
        whole w(10,20,30);
        cout << "============= w created =============\n";
        whole w1 = w;
	printf("**********%d\n",w1.abc());
}

