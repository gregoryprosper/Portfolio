//***************************************************************************************************************************
//PROGRAM HEADER
//
// Name: Gregory Prosper
// ID: gprosper
// Instructor: Dr. Bullard
// Class: Data Structures
// Term: Summer 2014
// Assignment #2 (WORD)
// Due Date: June 30, 2014
// Due Time: 11:59 PM
// Points: 100
//****************************************************************************************************************************

#include <iostream>
#include <string>
#include "word.h"

using namespace std;

int main()
{

	WORD you;
	cout<<"Testing the default constructor and printing empty word\n";
	cout<<you;
	
	WORD me("123abc345abc129012");
	cout<<"Testing the explicit-value constructor\n";
	cout<<me<<endl;
	
	WORD them = me;
	cout<<"Testing the copy constructor\n";
	cout<< them <<" = "<< me << endl;

	
	cout<<"Testing length\n";
	cout<<"The length of me is "<<me.length()<<endl;
	cout<<"The length of them is "<<them.length()<<endl;
	cout<<"The length of you is "<<you.length()<<endl;

	
	cout<<"Testing Insert by inserting me into you at position 0\n";
	you.insert(0,me);
	cout<<"The word you is "<<you<<endl;
	
	WORD us;
	us = "abc";
	cout<<"Testing operator= by assignment the value of \"abc\" to use\n";
	cout<<us<<endl;
	
	WORD word1, word2, word3;
	word1 = "ABC123XYZ";
	cout<<"Testing overloaded operator= with chaining by assignment the value of \"ABC123XYZ\" to use\n";
	word3=word2=word1;
	cout<<"word3 is "<<word3<<", word2 is "<<word2<<" and word1 is "<<word1<<endl;
	cout<<"Testing overloaded operator= with chaining by assignment the value of word1, \"ABC123XYZ\", into itself \n";
	word1 = word1 = word1;
	cout<<"word1 is "<<word1<<endl;
	
	them.remove(us);
	cout<<"Testing Remove by removing us from them \n";
	cout<<"The word them is "<<them<<endl;
	
	me.removeAll(us);
	cout<<"Testing RemoveAll by removing all occurrences of us in me \n";
	cout<<"The word me is "<<me<<endl;
	
	WORD our, him;

	our = "XXXCCCYYY";
	us = "XXXX";


	WORD their("abcABCDEF");
	WORD yo, child;

	cout<<"Testing operator+ by adding a string to the back of an empty string \n";
	cout<<"Adding to an empty word\n";
	yo + their;
	cout<<"The value of yo is "<<yo<<endl;

	cout<<"Adding an empty word to another word by adding an empty string to a non empty string \n";
	yo + child;
	cout<<"The value of yo is "<<yo<<endl;

	cout<<"Adding 2 words by adding us to the back of their.  Their is the current object \n";
	their + us;
	cout<<"their followed by us is "<<their<<endl;                 
	
	return 0;
}

	




	


