//*****************************************************************************
//P R O G R A M  H E A D E R
//
//  Name:  Jane Doe
//	ID:  jdoe201r
//	Instructor:  Dr. Bullard
//	Class:  Data Structures
//	Term:  Summer 2014
//	Assignment #1 (TLIST)
//	Due Date:  May 26, 2014   
//	Due Time:  11:00PM
//	Points:  100 
//	
//  Description: This program accesses your C++language skills.  
//               After completing this assignment you will be able
//			     to perform the following:
//
//	(1) Manage dynamic memory (allocate and de-allocate memory).
//	(2) Implement a default, explicit-value, and copy constructor.
//	(3) Overload the assignment (=) and addition (+) as member functions, and 
//      the insertion (<<)  operators as friend functions.
//	(4) implement a template class
//
//******************************************************************************

#include <iostream>
#include <string>

using namespace std;
template <class t_type>

class TLIST

{

public:

	TLIST(){ };

  TLIST(const TLIST<t_type> &){ };

  bool IsEmpty(){return 0 };

  bool IsFull(){return 0 };

  int Search(t_type){return -1};

  TLIST<t_type> & operator+(const t_type ){return *this; };

  void Remove(const t_type){};

  TLIST<t_type> & operator=(const TLIST<t_type> &){return *this;};

  friend ostream & operator<<(ostream & out, TLIST<t_type> &  Org){return out;};

  void Double_Size(){};

//other functions you may want to implement

private:

  t_type *DB;

  int count;

  int capacity;

 //additonal state variables you may wish add

};

int main( )
{
	cout<<"Test1: Testing the default constructor for char\n";
	TLIST<char> Char_List, TempChar1, TempChar2; //testing default
	
	cout<<"Test2: Testing the default constructor for int\n";
	TLIST<int> Int_List, TempInt1, TempInt2;

	cout<<"Test3: Testing operator<< after default constuctor for char\n";
	cout<<Char_List<<endl;

	cout<<"Test4:Testing the default constructor for string\n";
	TLIST<string> String_List, TempString1, TempString2;


	//Testing functionality for character list
	cout<<"Test5: Testing operator+ for char\n";
	Char_List + 'W' + 'E' + 'L' + 'C' + 'O' + 'M' + 'E';
	cout<<"Testing operator+ after 7 adds for char\n";
	cout<<Char_List<<endl;

	cout<<"Test 6:Testing operator= with chaining for char\n";
	TempChar1 = TempChar2 = Char_List;
	cout<<"Testing operator= after for char\n";
	cout<<Char_List<<"  "<<TempChar2<<"  "<<TempChar1<<endl<<endl;
	
	cout<<"Test 7: Testing Remove for char\n";
	cout<<"Testing Search and IsEmpty also\n";
	TempChar1.Remove('E');
	TempChar1.Remove('Z');
	TempChar1.Remove('L');
	TempChar1.Remove('W');
	cout<<"Testing Remove after 4 removes char\n";
	cout<<TempChar1<<endl;

    cout<<"Test 8: Testing copy constructor for char\n";
	TLIST<char> TempChar3 = TempChar1;
	cout<<"Testing copy constructor for char after call\n";
	cout<<TempChar3<<"  "<<TempChar1<<endl;

	/////////////////////////////////////////////////////////////////////////////////
	// YOUR DRIVER SHOULD INCLUDE TESTS 5, 6 , 7 AND 8 FOR INTS AND STRINGS..
	cout<<"Testing functionality of the integer list\n";
	cout<<"Testing Double_Size and IsFull also\n";

	Int_List + 1 + 2 + 3 + 4 + 5 + 6 + 7;

	TempInt1 = TempInt2 = Int_List;

	TempInt1.Remove(7);
	TempInt1.Remove(100);
	TempInt1.Remove(3);
	TempInt1.Remove(1);

        TLIST<int> TempInt3 = TempInt1;


	//Testing Functinality of the string list

	String_List + "hello" + "everyone" + "study" + "hard" + "do not" + "copy" + "learn";

	TempString1 = TempString2 = String_List;

	TempString1.Remove("learn");
	TempString1.Remove("I live to program");
	TempString1.Remove("hello");
	TempString1.Remove("hard");

	 TLIST<string> TempString3 = TempString1;


	return 0;
}