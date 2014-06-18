#include<iostream>

// ************************************************** 
// class person super base class.
class Person{
	private:
		char *name;
		char gender;
		char *date;
		
	public:
	Person();			//1
        Person(char*,char,char*);	//2
        void SetName(char*);			//3
        void SetGender(char);		//4
	void SetDateOfBirth(char*);		//5
	char* GetName();		//6
	char GetGender();		//7
	virtual void ShowDetails();			//8
	
};// end of Person

// ************************************************** 
class Staff: virtual public Person{
	private:
		char *jdate;
		char grade;

	public:
	Staff();
	Staff(char*,char);
	void SetJoiningdate(char*);
	void SetGrade(char);
	char GetGrade();
	virtual void ShowDetails();
};
// **************************************************  
class Student: virtual public Person{
	public:
		char *course;
		char *rollno;

	public:
	Student();
	Student(char*,char*);
	void SetCourse(char*);
	char* GetRollNumber();
	virtual void ShowDetails();
};
// ************************************************** 
class RA: public Student, public Staff{
	public:
		RA(const Student&);
		virtual void ShowDetails();
};
// ************************************************** 
class PeopleIterator;
class PeopleContainer{
	private:
		Person *head;
		Person *current;

	public:	
	bool Add(Person *);
	int Remove(char* );
	int Remove(Person* );
	void sort();
	void ShowPeople();
	PeopleContainer* GetStudents();
	PeopleContainer* GetFemales();
	PeopleContainer* GetStaffByGrade(char);
	PeopleIterator NewPeopleiterator();
};
// ************************************************** 
class PeopleIterator{

	private:
		PeopleContainer *pc;
		Person *head;
		Person *curr;
	
	public:	
	Person* First();
	Person *Next();
	bool IsOver();
};
// ************************************************** 
