#include <iostream>
#include <string>
using namespace std;

#include "array_class.cpp"

	

	int main()
	{
		Array_Class<int> I;

		I.Add(5);
		I.Add(4);
		I.Add(3);
		I.Add(2);
		I.Add(1);

		I+5 + 600 + -1000 + 455;

		I.operator+(200);

		//I.Print();

		I.Search(27);

		Array_Class<string> II;

		II.Add("hello");
		II.Add("world");
		II.Add("all");
		II.Add("is");
		II.Add("ok");

		//II.Print();

		
		//cout<<II<<I<<II<<I<<endl;//testing operator<<

		//II.Search("27");

		//Array_Class<string> III = II; //Testing copy constructor

		//II = II; //testing operator=

		//cout<<II<<endl<<II<<endl;


		return 0;
	}
