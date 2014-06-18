#include"DataStorage.h"
#include<iostream>
#define Main
using namespace std;
#undef Main
// ************************************************** Person class Members
Person::Person(){
	name=new char[2];
	name[0]=' '; name[1]='\0';
	date=new char[2]; date[0]=' '; date[1]='\0';
	gender=' ';
};

Person::Person(char *name, char gender, char *date){
	this->name=new char[strlen(name)+1];
	strcpy(this->name,name);
	this->date=new char[strlen(date)+1];
	strcpy(this->date,date);
	this->gender=gender;
};

void Person::SetName(char* name){
	this->name=new char[strlen(name)+1];
	strcpy(this->name,name);
};

void Person::SetGender(char gender){
	this->gender=gender;
};

void Person::SetDateOfBirth(char* date){
	this->date=new char[strlen(date)+1];
	strcpy(this->date,date);
};

char* Person::GetName( ){
	return name;
};

char Person::GetGender( ){
	return gender;
};

void Person::ShowDetails( ){
	if(strcmp(name," "))
		cout<<name;
	cout<<":";
	if(gender==' ')
		cout<<gender;
	cout<<":";
	if(strcmp(date," "))
		cout<<date;
	cout<<"#"<<endl;
};
// ************************************************** Staff class Member
Staff::Staff():Person(){
	jdate=new char[2];
	strcpy(jdate," ");
	grade=' ';	
};

Staff::Staff(char* jdate, char grade):Person(){
	this->jdate=new char[strlen(jdate)+1];
	strcpy(this->jdate,jdate);	
	this->grade=grade;
};

void Staff::SetJoiningdate(char* jdate){
	this->jdate=new char[strlen(jdate)+1];
	strcpy(this->jdate,jdate);
};
void Staff::SetGrade(char grade){
	this->grade=grade;

};

char Staff::GetGrade(){
	return grade;

}

void Staff::ShowDetails(){
	Person::ShowDetails();
	if(strcmp(jdate," "))
		cout<<jdate;
	cout<<":";
	if(grade==' ')
		cout<<grade;
	cout<<"#"<<endl;	
};
// ************************************************** Student class Member
Student::Student( ):Person(){
	course=new char[2];
	strcpy(course," ");
	rollno=new char[2];
	strcpy(rollno," ");
}

Student::Student(char* course,char* RollNumber):Person(){
	this->course=new char[strlen(course)+1];
	this->rollno=new char[strlen(RollNumber)+1];
	strcpy(this->course,course);
	strcpy(this->rollno,RollNumber);
};

void Student::SetCourse(char* course){
	this->course=new char[strlen(course)+1];
	strcpy(this->course,course);
}

char* Student::RollNumber(){
	return rollno;
};

void Student::ShowDetails(){
	Person::ShowDetails();
	if(strcmp(course," "))
		cout<<course;
	cout<<":";
	if(strcmp(rollno," "))
		cout<<rollno;	
	cout<<"#"<<endl;
};
// ************************************************** RA class Member
RA::RA(const Student& stu):Student(),Staff(){
	course=new char[strlen(stu.course)+1];
	strcpy(course,stu.course);
	rollno=new char[strlen(stu.rollno)+1];
	strcpy(rollno,stu.rollno);
};

void RA::ShowDetails(){
	Person::ShowDetails();
	cout<<":";
	Staff::ShowDetails();
	cout<<":";
	Student::ShowDetails();
	cout<<"#"<<endl;
};
// ************************************************** PeopleContainer class Member
 bool PeopleContainer::Add(Person *){


}

 int PeopleContainer::Remove(char* ){

}

 int PeopleContainer::Remove(Person* ){

}

void PeopleContainer::sort(){

}

void PeopleContainer::ShowPeople(){

}

PeopleContainer* PeopleContainer::GetStudents(){

}

PeopleContainer* PeopleContainer::GetFemales(){


}

PeopleContainer* PeopleContainer::GetStaffByGrade(char){


}

PeopleIterator PeopleContainer::NewPeopleiterator(){


}

// ************************************************** PeopleContainer class Member
 


#ifdef Main

int main(){

	Person *p=new Person();
	//p->ShowDetails();
	Staff *s=new Staff("18-05-2000",'A');
	Person *p1=dynamic_cast<Person*>(s);
	p1->SetName("sunil");
	p1->SetGender('M');
	p1->SetDateOfBirth("18-12-81");
	//p1->ShowDetails();
	Student *s1=new Student("FPGDST-2005","d0557101");
	p1=dynamic_cast<Person*>(s1);
	p1->SetName("sunil_Mandhan");
	p1->SetGender('M');
	p1->SetDateOfBirth("18-12-83");
	p1->ShowDetails();
	return 0;
}
#endif
