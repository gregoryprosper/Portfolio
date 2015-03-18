//***************************************************************************************************************************
//PROGRAM HEADER
//
// Name: Gregory Prosper
// ID: gprosper
// Instructor: Dr. Bullard
// Class: Data Structures
// Term: Summer 2014
// Assignment #1 (TLIST)
// Due Date: May 29, 2014
// Due Time: 11:00 PM
// Points: 100
//
// Description: This program accesses your C++ language skills.
//				After completing this assignment you will be able
//				to perform the following:
//
// (1) Manage Dynamic Memory (allocate and de-allocate memory).
// (2) Implement a default, explicit-value, and copy constructor.
// (3) Overload the assignment (=) and addition (+) as member functions, and
//	   the insertion (<<) operators as friend functions.
// (4) Implement a template class.
//
//****************************************************************************************************************************

#include <iostream>
#include <string>
#include "tlist.h"

using namespace std;

int main()
{
	cout<<"Test1: Testing the default constructor for char\n";
	TLIST<char> Char_List, TempChar1, TempChar2; //testing default

	cout<<"Test2: Testing the default constructor for int\n";
	TLIST<int> Int_List, TempInt1, TempInt2;
	
	cout<<"Test3: Testing operator<< after default constructor for char\n";
	cout<<Char_List;
	
	cout<<"Test4: Testing the default constructor for string\n";
	TLIST<string> String_List, TempString1, TempString2;
	
	
	//Testing functionality for character list
	cout<<"Testing functionality of the character list\n";
	cout<<"Testing Double_Size and IsFull also\n\n";

	cout<<"Test5: Testing operator+ for char\n\n";
	Char_List + 'W' + 'E' + 'L' + 'C' + 'O' + 'M' + 'E';
	cout<<"Testing operator+ after 7 adds for char\n\n";
	cout<<Char_List<<endl;

	cout<<"Test 6:Testing operator= with chaining for char\n\n";
	TempChar1 = TempChar2 = Char_List;
	cout<<"Testing operator= after for char\n";
	cout<<Char_List<<TempChar2<<TempChar1<<endl;
	
	cout<<"Test 7: Testing Remove for char\n";
	cout<<"Testing Search and IsEmpty also\n";
	TempChar1.remove('E');
	TempChar1.remove('Z');
	TempChar1.remove('L');
	TempChar1.remove('W');
	cout<<"\nTesting Remove after 4 removes char\n\n";
	cout<<TempChar1<<endl;

    cout<<"Test 8: Testing copy constructor for char\n\n";
	TLIST<char> TempChar3 = TempChar1;
	cout<<"Testing copy constructor for char after call\n\n";
	cout<<TempChar3<<TempChar1<<endl;

	/////////////////////////////////////////////////////////////////////////////////
	// YOUR DRIVER SHOULD INCLUDE TESTS 5, 6 , 7 AND 8 FOR INTS AND STRINGS..
	cout<<"Testing functionality of the integer list\n";
	cout<<"Testing Double_Size and IsFull also\n\n";

	cout<<"Test9: Testing operator+ for int\n\n";
	Int_List + 1 + 2 + 3 + 4 + 5 + 6 + 7;
	cout<<"Testing operator+ after 7 adds for int\n\n";
	cout<<Int_List<<endl;

	cout<<"Test 10:Testing operator= with chaining for int\n\n";
	TempInt1 = TempInt2 = Int_List;
	cout<<"Testing operator= after for int\n";
	cout<<Int_List<<TempInt2<<TempInt1<<endl;

	cout<<"Test 11: Testing Remove for int\n";
	cout<<"Testing Search and IsEmpty also\n";
	TempInt1.remove(7);
	TempInt1.remove(100);
	TempInt1.remove(3);
	TempInt1.remove(1);
	cout<<"Testing Remove after 4 removes int\n\n";
	cout<<TempInt1<<endl;

	cout<<"Test 12: Testing copy constructor for int\n\n";
    TLIST<int> TempInt3 = TempInt1;
	cout<<"Testing copy constructor for int after call\n\n";
	cout<<TempInt3<<TempInt1<<endl;

	//Testing Functinality of the string list
	cout<<"Testing functionality of the string list\n";
	cout<<"Testing Double_Size and IsFull also\n\n";

	cout<<"Test13: Testing operator+ for string\n\n";
	String_List + "hello" + "guys" + "study" + "hard" + "goodluck";
	cout<<"Testing operator+ after 7 adds for string\n\n";
	cout<<String_List<<endl;

	cout<<"Test 14:Testing operator= with chaining for string\n\n";
	TempString1 = TempString2 = String_List;
	cout<<"Testing operator= after for string\n";
	cout<<String_List<<TempString2<<TempString1<<endl;

	cout<<"Test 15: Testing Remove for string\n";
	cout<<"Testing Search and IsEmpty also\n";
	TempString1.remove("guys");
	TempString1.remove("program");
	TempString1.remove("hello");
	TempString1.remove("hard");
	cout<<"Testing Remove after 4 removes string\n\n";
	cout<<TempString1<<endl;

	cout<<"Test 16: Testing copy constructor for string\n\n";
	TLIST<string> TempString3 = TempString1;
	cout<<"Testing copy constructor for string after call\n\n";
	cout<<TempString3<<TempString1<<endl;

	return 0;
}