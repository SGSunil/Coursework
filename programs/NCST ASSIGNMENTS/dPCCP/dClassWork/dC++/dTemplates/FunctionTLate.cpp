#include <iostream>
using namespace std ;

template <typename T>
T max1(T a, T b)
{      printf("hello\n");
	return a > b ? a : b ;
};


int main( )
{
		cout.precision(10);
		int a=20;
		double d=static_cast<double>(a);
		cout<<d<<endl;
max(10,20);
	//	cout<<max1(10,26)<<endl;
	//	cout<<max1<int>(10.7,26.8)<<endl;
	//	cout<<max1(10,26)<<endl;
		return 0;
}

