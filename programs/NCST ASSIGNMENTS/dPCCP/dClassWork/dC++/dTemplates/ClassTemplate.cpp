// Generic Safe and emi dynamic Array

#include <iostream>
using namespace std;
template<class T>	//supplied at compile time
class Array{
	T * s; //pointer to storage allocated to array at run time
	int l,h;//bounds chosen at runtime
  void free() { if(s)delete [] s; s=0; }
  void copy( const Array& A)
  {
	l=A.l; h=A.h;
	if(!A.s) { s=0; }
	else {
		s = new T [h-l+1];
		for( int i=0; i< h-l; i++)
			s[i]=A.s[i];
	}
  }

	Array(){s=0; l=0; h=-1;} //forbidden to all others.
public:
	Array(int low, int high)
	{
		if(high < low) { int t=low; low=high; high=t; }

		const int len=high-low+1;

		s= new T[len];

		l=low;
		h=high;
	}

	T& operator[](int i)
	{
		if ( i<l || i>h )
		{
			cerr<<"Index out of bound\n\n";
			exit(1);
		}

		return s[i];
	}
 //Memory management
	~Array(){ free(); }

	Array & operator=(const Array& A)
	{
		if( this != &A ) {free(); copy(A);}

		return *this;
	}

	Array(const Array & A)
	{
		if( this != &A ) { free(); copy(A); }

	}
 
};


int main()
{
	Array <double>a(1,5);

	for (int i=1; i<=5; i++)
		a[i]='a'+i;
	for (int i=1; i<=5; i++)
		cout<<a[i]<<endl;

	return 0;
}


