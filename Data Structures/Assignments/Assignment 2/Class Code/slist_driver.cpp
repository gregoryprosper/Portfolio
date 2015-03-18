#include <iostream>
#include <string>

#include "slist.h"

using namespace std;

int main()
{
	LINKED_LIST_CLASS L,K;

	//K.AddFront("hello");
	//K.AddFront("bye");
	//K.AddFront("good");
	//K.AddFront("bad");
	//K.Print();*/

	L.AddAnywhere("hello");
	L.AddAnywhere("bye");
	L.AddAnywhere("good");
	L.AddAnywhere("bad");
	L.AddAnywhere("apple");
	L.AddAnywhere("zebra");
	L.Print();

	LINKED_LIST_CLASS Z=L;
	L.Print();

	L.Remove("apple");
	L.Print();

	L.Remove("hello");
	L.Print();

	L.Remove("zebra");
	L.Print();

	

	LINKED_LIST_CLASS A;
	A=Z;
	A.Print();

	return 0;
}