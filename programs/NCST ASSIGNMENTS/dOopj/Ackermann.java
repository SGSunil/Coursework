import ncst.pgdst.*;

class Method{

long x,y,result=0,a=0;
SimpleOutput sout=new SimpleOutput();
/*
void setAM(int x,int y)
{
this.x=x;
this.y=y;
}
*/

long AM(long x,long y)
{
sout.writeChar('Y');
if(x==0)
{
result=y+1;
}
else
if(y==0)
{
result=AM(x-1,1);
}
else
{
a=AM(x,y-1);
result=AM(x-1,a);
}
return result;
}
};


public class Ackermann
{
public static void main(String args[])
throws IOException{
SimpleInput sin=new SimpleInput();
SimpleOutput sout=new SimpleOutput();

long res1=0,x,y;


x=sin.readLong();
y=sin.readLong();

Method m=new Method();
//m.setAM(x,y);

res1=m.AM(x,y);
sout.writelnString("result of A("+x+","+y+") is "+res1);
}
}
