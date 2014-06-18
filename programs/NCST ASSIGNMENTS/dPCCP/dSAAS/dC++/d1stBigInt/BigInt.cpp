#include"BigInt.h"
#include<ctype.h>
//#include<string.h>
#include<stdlib.h>

//**************************used to coun the numbers in a numbers and aiding conversion to string.
char *strrev(char *str)
{
      char *p1, *p2;

      if (! str || ! *str)
            return str;
      for (p1 = str, p2 = str + strlen(str) - 1; p2 > p1; ++p1, --p2)
      {
            *p1 ^= *p2;
            *p2 ^= *p1;
            *p1 ^= *p2;
      }
      return str;
}

//to count the number of digits in number.
int countLength(int in){
	if(in==0)
		return 1;
	int rem=abs(in),i=0;
		while(rem>0){
			i++;
			rem=rem/10;
		}
	return i;
}

//**************************implementation of BigInt Constructors.
BigInt::BigInt(){
	len=2;
	sign=0;
	opd=new char[len];
	opd[0]='0';
	opd[1]='\0';	
}

BigInt::BigInt(int in){

	int i=in;
	if(in==0){
		opd=new char[2];
		opd[0]='0';
		opd[1]='\0';
		len=1;
		sign=0;
		cout<<"string rep of integer="<<in<<" is "<<opd<<endl;
		return;
	}	

	if(in<0){
		//cout<<"yes negative"<<endl;
		sign=1;
	}
	else
		sign=0;

	int l;

	in=abs(in);
	char *stri;
	len=l=countLength(in);
	if(sign==1){
		l=len+2;

		opd=new char[l];
		stri=opd+(l-1);
		*opd='-';
		//cout<<"*opd in begin "<<*opd<<endl;
		stri--;
	}
	else{
		l=len+1;
		opd=new char[l];
		stri=opd+(l-2); 
	}


	int rem=in;
	int amp=in%10;
	while(rem>0){
		*stri=amp+48;
		rem=rem/10;
	//cout<<"*stri is "<<*stri<<" rem "<<rem<<" amp "<<amp<<endl;
		stri--;
		amp=rem%10;
	}
	opd[l-1]='\0';
		cout<<"string rep of integer="<<i<<" is "<<opd<<endl;
}

BigInt::BigInt(char *in){// in: input which from BigInt will be constructed.
	len=strlen(in);
	if(in[0]=='-')
		sign=1;
	else
		sign=0;
	opd=new char[len+1];
	strcpy(opd,in);
}

BigInt::BigInt(BigInt& cp){// cp: to be copied from.  ***copy constructor.
	//cout<<"called Copy Constructor operator\n";
	sign=cp.sign;
	len=strlen(cp.opd);
	opd=new char[len+1];
	strcpy(opd,cp.opd);
}
//**************************end of  BigInt Constructors.

//**************************end of  BigInt Constructors.
istream& operator>>(istream& ins,BigInt& ip){		//extraction operator
	ins>>ip.opd;
	return ins;
}

ostream& operator<<(ostream& ons,BigInt& ip){		//insertion operator.
	ons<<ip.opd;
	return ons;
}

BigInt& BigInt::operator=(BigInt& rvalue){
	//cout<<"called assignment operator\n";
	len=rvalue.len;
	opd=new char[len+1];
	strcpy(opd,rvalue.opd);
	return *this;
}


//*****************************************************Operator <=

int BigInt::operator<=(BigInt& rvalue){			//Operator <=.
	int l1=strlen(opd),l2=strlen(rvalue.opd);
	//cout<<"sign of lvalue is "<<sign<<" sign of rvalue "<<rvalue.sign<<endl;

	if(sign==1){
		if(rvalue.sign==1){	//other's sign is also 1, means negative.
			if(l1==l2){
				//cout<<"both signs are -VE and length are also same\n";
				if(strcmp(opd+1,rvalue.opd+1)==0)
					return 1;
				if(strcmp(opd+1,rvalue.opd+1)<0)
					return 0;
				if(strcmp(opd+1,rvalue.opd+1)>0)
					return 1;
			}
			else{
				if(l1>l2)
					return 1;
				else return 0;

			}
		}
		else{
				//cout<<"lvalue is -VE and rvalue is +VE\n";
				return 1;
		}
	}
	else{	//sign==0;
		if(rvalue.sign==1){	//other's sign is also 1, means negative.
				//cout<<"L is -VE and R is +Ve "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				return 0;
		}
		else{	//rvalue.sign==0;
			if(l1==l2){
				//cout<<"L is +VE and R is +Ve and lengths are same "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				if(strcmp(opd,rvalue.opd)==0)
					return 1;
				if(strcmp(opd,rvalue.opd)<0)
					return 1;
				if(strcmp(opd,rvalue.opd)>0)
					return 0;
			}
			else{
				if(l1>l2)
					return 0;
				else
					return 1;
			}
		}
		
	}
}
//*****************************************************End Operator <=

//*****************************************************Operator <
int BigInt::operator<(BigInt& rvalue){
	int l1=strlen(opd),l2=strlen(rvalue.opd);
	//cout<<"sign of lvalue is "<<sign<<" sign of rvalue "<<rvalue.sign<<endl;

	if(sign==1){
		if(rvalue.sign==1){	//other's sign is also 1, means negative.
			if(l1==l2){
				//cout<<"both signs are -VE and length are also same\n";
				if(strcmp(opd+1,rvalue.opd+1)==0)
					return 0;
				if(strcmp(opd+1,rvalue.opd+1)<0)
					return 0;
				if(strcmp(opd+1,rvalue.opd+1)>0)
					return 1;
			}
			else{
				if(l1>l2)
					return 1;
				else return 0;

			}
		}
		else{
				//cout<<"lvalue is -VE and rvalue is +VE\n";
				return 1;
		}
	}
	else{	//sign==0;
		if(rvalue.sign==1){	//other's sign is also 1, means negative.
				//cout<<"L is -VE and R is +Ve "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				return 0;
		}
		else{	//rvalue.sign==0;
			if(l1==l2){
				//cout<<"L is +VE and R is +Ve and lengths are same "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				if(strcmp(opd,rvalue.opd)==0)
					return 0;
				if(strcmp(opd,rvalue.opd)<0)
					return 1;
				if(strcmp(opd,rvalue.opd)>0)
					return 0;
			}
			else{
				if(l1>l2)
					return 0;
				else
					return 1;
			}
		}
	}
}
//*****************************************************End Operator <

//*****************************************************Operator >=
int BigInt::operator>=(BigInt& rvalue){
	int l1=strlen(opd),l2=strlen(rvalue.opd);
	//cout<<"sign of lvalue is "<<sign<<" sign of rvalue "<<rvalue.sign<<endl;

	if(sign==1){
		if(rvalue.sign==1){	//other's sign is also 1, means negative, both negative.
			if(l1==l2){
				//cout<<"both signs are -VE and length are also same\n";
				if(strcmp(opd+1,rvalue.opd+1)==0)
					return 1;
				if(strcmp(opd+1,rvalue.opd+1)<0)
					return 1;
				if(strcmp(opd+1,rvalue.opd+1)>0)
					return 0;
			}
			else{
				if(l1>l2)
					return 0;
				else return 1;

			}
		}
		else{
				//cout<<"lvalue is -VE and rvalue is +VE\n";
				return 0;
		}
	}
	else{	//sign==0;
		if(rvalue.sign==1){	//other's sign is also 1, means negative.
				//cout<<"L is -VE and R is +Ve "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				return 1;
		}
		else{	//rvalue.sign==0;
			if(l1==l2){
				//cout<<"L is +VE and R is +Ve and lengths are same "<<"lvalue "<<opd<<" rvalue "<<rvalue.opd<<"\n";
				if(strcmp(opd,rvalue.opd)==0)
					return 1;
				if(strcmp(opd,rvalue.opd)<0)
					return 0;
				if(strcmp(opd,rvalue.opd)>0)
					return 1;
			}
			else{
				if(l1>l2)
					return 1;
				else
					return 0;
			}
		}
	}

}
//*****************************************************End Operator >= 

//*****************************************************Operator ==
int BigInt::operator==(BigInt& rvalue){

	if((*this<=rvalue)&&(*this>=rvalue)){
		return 1;
	}
	return 0;
}
//*****************************************************End Operator ==

//*****************************************************Operator > 
int BigInt::operator>(BigInt& rvalue){

	if(*this>=rvalue){
		if((*this==rvalue)==0){
			return 1;
		}
		return 0;
	}
		return 0;

}
//*****************************************************End Operator >

//*****************************************************Operator +
BigInt& BigInt::operator+(BigInt& rvalue){
	char *op1,*op2,*result;
	int len1=strlen(opd),len2=strlen(rvalue.opd);
	int i,j,k,m,small,carry=0,sum=0,rem=0,num=0,isNeg=0,isPos=0;
	
	if(!strcmp(opd,"0")&&!strcmp(rvalue.opd,"0")){
		BigInt *b1=new BigInt("0");
		b1->sign=0;
		return *b1;
	}

		

	if(sign==1){
		op1 = new char[len1];
		strcpy(op1,opd+1);
		len1-=1;
	}
	else{
		op1 = new char[len1 + 1];
		strcpy(op1,opd);
		cout<<"first operand for + "<<op1<<" and length is "<<len1<<endl; 
	}
	cout<<"reverse of first "<<strrev(op1)<<endl; 
	if(rvalue.sign==1){
		op2=new char[len2];
		strcpy(op2,rvalue.opd+1);
		len2-=1; 			// to counteract the -VE sign.
	}
	else{
		op2=new char[len2+1];
		strcpy(op2,rvalue.opd);
		cout<<"second operand for + "<<op2<<" and len is "<<len2<<endl; 
	}
	cout<<"reverse of second "<<strrev(op2)<<endl; 
	
	if(len1>len2){
		small=len2;
		num=0;
		result=new char[len1+3];
	}
	else{
		num=1;
		small=len1;
		result=new char[len2+3];
	}
	if(sign==1)
		if(rvalue.sign==1)
			isNeg=1;

	if(sign==0)
		if(rvalue.sign==0)
			isPos=1;


	if((isNeg)||(isPos)){
				cout<<"Hello"<<endl;
			// if both numbers are -VE.
			for(i=0;i<small;i++){
				cout<<"***first loop"<<endl;
				sum=((op1[i]-48)+(op2[i]-48)+carry);
				cout<<"sum at i "<<sum<<endl;
				result[i]=sum%10+48;
				carry=sum/10;
				cout<<"carry at i "<<carry<<endl;
				cout<<"result at i= "<<i<<" "<<result[i]<<endl;
			}
			if(num==1){
				for(i=small;i<len2;i++){
					cout<<"###second loop"<<endl;
					sum=((op2[i]-48)+carry);
					result[i]=sum%10+48;
					carry=sum/10;
					cout<<"result at i= "<<i<<" "<<result[i]<<endl;
				}
			}
			else{
				for(i=small;i<len1;i++){
					cout<<"@@@third loop"<<" small value="<<small<<endl;
					sum=((op1[i]-48)+carry);
					cout<<"sum at i= "<<sum<<endl;
					cout<<"carry at i= "<<i<<" "<<carry<<endl;
					result[i]=sum%10+48;
					carry=sum/10;
					cout<<"result at i= "<<i<<" "<<result[i]<<endl;
				}
			}
			
			if(carry>=1){
				result[i]=carry+48;
				cout<<"******8result at i= "<<i<<" "<<result[i]<<endl;
				i++;
			}
	}		
			cout<<"i value before -Ve appending  is "<<i<<endl;
			if(isNeg==1)
				result[i]='-';
		
			result[i+1]='\0';
			cout<<"result is "<<result<<endl;
			strrev(result);
			cout<<"reverse result is "<<result<<endl;
			BigInt *b=new BigInt(result);
			if(isNeg==1)
				b->sign=1;
			if(isPos==1)
				b->sign=0;
			return *b;
			
}
//*****************************************************End Operator +



//**************************end of  operators.
int main(){
char str[100];
//char in[100];
int in=0;
cout<<"enter the input in string form first and then integer"<<endl;
scanf("%s %d",str,&in);
BigInt b1(str);
BigInt b2(in);
BigInt b3(b1);
/*cout<<(b3<=b2)<<endl;

cout<<(b3<b2)<<endl;
cout<<(b2>=b3)<<endl;
cout<<(b2==b3)<<endl;
cout<<(b2<b3)<<endl;
*/
cout<<b2+b3<<endl;

cout<<b1.opd<<" "<<b2.opd<<" "<<b3.opd<<endl;
return 0;

}
