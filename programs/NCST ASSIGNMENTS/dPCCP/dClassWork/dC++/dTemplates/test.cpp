#include <iostream>
using namespace std;
inline void f (int) { cout << "f (int) called\n"; }
inline void f (unsigned) { cout << "f (unsigned) called\n";}
int main (void) {
f (1); // calls { (int)
f (1U); // calls { (unsigned)
}
