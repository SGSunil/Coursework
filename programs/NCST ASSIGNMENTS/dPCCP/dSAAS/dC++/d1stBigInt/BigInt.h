//*****************this is BigInt.h, containing the prototype/declaration for the BigInt program.

#include<iostream>
#include<iomanip>

using namespace std;

//******************************************class BigInt Declaration.
class BigInt{
	//private:
	public:
		char *opd; // operand to be worked on.
		int len;
		int sign;
	
	public:
//******************************************constructors
	BigInt(); //default.
	BigInt(int); // with int parameter.
	BigInt(char *); // with string parameter.
	BigInt(BigInt&); // copy constructor.
//******************************************overloaded operators
	BigInt& operator=(BigInt& ); // assignment operator.

	friend istream& operator>>(istream&,BigInt&); //extraction operator.

	friend ostream& operator<<(ostream&,BigInt&); //insertion operator.
	BigInt& operator+(BigInt& );
	BigInt& operator-(BigInt& );
	BigInt& operator*(BigInt& );
	BigInt& operator/(BigInt& );
	BigInt& operator%(BigInt& );

	int operator>(BigInt&);
	int operator<(BigInt&);
	int operator<=(BigInt&);
	int operator>=(BigInt&);
	int operator==(BigInt&);
//******************************************

};


