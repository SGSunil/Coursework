#include <iostream>
using namespace std ;
template <class T>
T max(T a, T b)
{
	return a > b ? a : b ;
};

int main()
{
	int max(int, int);
	double  max(double, double);
	char max(char, char);
    cout << "max(10, 15) = " << max(10, 15) << endl ;
    //cout << "max('k', 's') = " << max('k', 's') << endl ;
    //cout << "max(10.1, 15.2) = "<< max(10.1f, 15.2f) << endl ;
//	return 0;
}

