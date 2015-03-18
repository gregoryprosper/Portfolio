//**************************************************************
//
// (c) Copyright 2012 Dr. Thomas Fernandez
//       All rights reserved.
//
//**************************************************************

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

// Three enhancements that C++ has over C are strings, streams and vectors.
// They are illustrated in the code below.

int main()
{
	int i = 42;
	double d = 3.14159;

	// Concatinating C++ strings with the '+' operator.
	string s1 = "Hello";
	string s2 = "world!";
	string s3 = s1 + " " + s2;

	i = s3.length();

	// Using the cout (console output stream) to easily display three 
	// different types of variables w/o formatting.
	cout << i <<" "<< d << " " << s3  <<endl;

	// Setting up an output file stream and associating it with 'MyFile.txt'.
	ofstream fout;
	fout.open("MyFile.txt");
 
	// Outputing to a file is the same as displaying stuff on the screen.
	fout << i <<" "<< d << " " << s3  <<endl;
	
	fout.close();

	// Next we look at the way C deals with arrays.
	{
		unsigned int count;

		// Reading in an integer from the keyboard using cin (console input stream)
		cout<<"How big an array do you want? >";
		cin>>count;


		// The line below does not work. You have to know what size an array is at compile time.
		//int a[count];

		// The best you can do is guess at compile time on how big an array you might need 
		// and hope it's big enough and doesn't waste too much memorey.

		int a[100];

		// Put the square of the indexes into the array.
		for(unsigned int j=0;j<count;j++)
		{
			a[j]=j*j;
		}


		// Display the array values.
		for(unsigned int j=0;j<count;j++)
		{
			cout<<a[j]<<"  ";
		}
		cout<<endl;
	}

	// The C++ way is to use vectors instead of arrays. 
	// Be sure to add the right include statement. (See above.)
	{
		unsigned int count;

		// Reading in an integer from the keyboard using cin (console input stream)
		cout<<"How many numbers do you want in the vector? >";
		cin>>count;

		// This is the vector 'a' You don't need to specify the size; it will grow as needed. 
		vector<int> a;

		// Put the square of the indexes into the array.
		for(unsigned int j=0;j<count;j++)
		{
			a.push_back(j*j);  // This adds j squared to the end of the vector.
		}
		
		// Display the vector values. Unlike arrays, vectors know how big they are.
		for(unsigned int j=0;j<a.size();j++)
		{
			// You can use square brackets to index a vector just like an array.
			cout<<a[j]<<"  ";
		}
		cout<<endl;
	}
	return 0;
}