#include<iostream>
using namespace std;

void reverse( const char * const sPtr ) 
{ 
if ( sPtr[ 0 ] == '\0' ) 
return; 
else { 
reverse( &sPtr[ 1 ] ); 
putchar( sPtr[ 0 ] ); 
} 
} 

int main()
{
int a=100;
reverse("sunil");
void* ptr=&a;
int *c=(int*)ptr;
cout<<*c;
}
