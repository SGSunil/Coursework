import ncst.pgdst.*;

public class Triangle{

public static void main(String args[])
throws IOException {

double a=0.0,b=0.0,c=0.0; //three sides of a triangle
SimpleInput sin= new SimpleInput();
SimpleOutput sout= new SimpleOutput();
int i;
a=sin.readDouble();
b=sin.readDouble();
c=sin.readDouble();
double[] p=new double[3];
p[0]=a;
p[1]=b;
p[2]=c;
double max,t;

// finding maximum of three
max=p[0];
for( i=1; i<3; i++)
{
if(max<p[i])
max=p[i];
else
{
t=p[i];
p[i]=max;
p[i-1]=t;
}
}

a=p[0];
b=p[1];
c=p[2];

if( ((a+b)>c & (b+c)>a & (c+a)>b)) 
{

// finding maximum of three


if((a*a+b*b==c*c)|((a*a+b*b<=c*c+0.000001)& (a*a+b*b>=c*c-0.000001)))
  { 
sout.writelnString("right-angled");
}else
if((a==b)&(b==c))
sout.writelnString("equilateral");
else
if((a==b)|(b==c) | (a==c))
sout.writelnString("isosceles");
else
sout.writelnString("notspecial");
}
else
sout.writelnString("invalid"); //Test output
}
}
