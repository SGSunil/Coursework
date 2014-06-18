#include<iostream>
using namespace std;

int f(unsigned int a) { return --a; }
int f(int a) { return ++a; }
int f1(int x)
{
if(x>2)
return x + f1(--x);
else
return 0;
}

int i=5;

int  main(int argc, char **argv){ 
cout<<"*****"<<f1(5)<<endl;
int j=static_cast<int >("sunil");
cout<<j<<endl;
}

