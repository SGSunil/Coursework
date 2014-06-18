import ncst.pgdst.*;

public class Triangle{

public static void main(String args[])
throws IOException {

double a=0.0,b=0.0,c=0.0; //three sides of a triangle
SimpleInput sin= new SimpleInput();
SimpleOutput sout= new SimpleOutput();
int i;
//sout.writelnString("sunil"); //Test output
//sout.writelnString("enter "+(i+1)+" no. please, in Decimal notation only");
//try{
a=sin.readDouble();
b=sin.readDouble();
c=sin.readDouble();
//}
//catch(Exception e)
//{
//continue a;
//}

double max,t;

if( ( (a[0]+a[1])>a[2]) & ( ((a[1]+a[2])>a[0]) & ((a[0]+a[2])>a[1]) ))
{

// finding maximum of three


if(((a[0]*a[0])+(a[1]*a[1])==(a[2]*a[2]+0.000001)) | ((a[0]*a[0])+(a[1]*a[1])==(a[2]*a[2]-0.000001))|((a[0]*a[0])+(a[1]*a[1])==(a[2]*a[2])))  { 
sout.writelnString("right-angled");
}else
if((a[0]==a[1])&(a[1]==a[2]))
sout.writelnString("equilateral");
else
if((a[0]==a[1])||(a[1]==a[2]) || (a[0]==a[2]))
sout.writelnString("isosceles");
else
sout.writelnString("notspecial");
}
else
sout.writelnString("invalid"); //Test output
}
}
