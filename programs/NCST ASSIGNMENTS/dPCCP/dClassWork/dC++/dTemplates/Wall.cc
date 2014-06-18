// A program that plots a picture of a function of two varaibles
// The name 'wallpaper.cc' is from Dewdney's Scientific American
// article on the "Wallpaper Program".  You can have a lot of fun
// with similar programs producing various patterns and pictures

#include <iostream>
using namespace std;
long F(long x, long y, long z)// The function to be displayed
{
	//return (x*x + y*y - z*z) ;

	return (x*x + y*y - (z+5)*(z+5))*(x*x + y*y - z*z) * ( x - y ) * ( x + y) ;
}

int main()
{
	const long W = 51;// width of grid in characters
	const long XH = W/2; // Largest(Highest) x coordinate value
	const long XL = 1+XH-W; // Smallest(Lowest) x cordinate value
	const long H = 23;//height of grid in characters
	const long YH = H/2; // Largest(Highest) y coordinate value
	const long YL = 1+YH-H; // Smallest(Lowest) y cordinate value

	//uses function
	long F(long x, long y, long z);
	  //The main program treats x and y as cordinates and z as a 
	  //constant "level"

	long x, y;
	const long Z =  9;

	for( y = YH;  y >= YL;  y-- )//For each row in turn
	{//a row of picture elements(pixels) with the same y cordinate

		cout << "\t";

		for( x = XL; x <= XH; x++ )//For each column in turn
		{// pixel (x,y)

			if( x == 0 && y == 0 ) 
				cout << "0";
			else if( F(x,y,Z) > 0 ) 
				cout <<"*";
			else // F(x,y,Z) <= 0
				cout<<".";
		}
		//end of the line

		cout << endl;

	}
	//end of the grid

	return EXIT_SUCCESS;

}


