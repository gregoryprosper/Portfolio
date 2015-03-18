
#include <iostream>
#include <string>

using namespace std;

#ifndef SLIST_H
#define SLIST_H

typedef string List_Type;
//declaration for a node in the list
class LIST_NODE
{
public:
		List_Type data; //data element
		LIST_NODE *next; //pointer element
};

//declaration of a list class
class LINKED_LIST_CLASS
{
public:
	LINKED_LIST_CLASS();//default constructor
	LINKED_LIST_CLASS(const LINKED_LIST_CLASS &);
	LINKED_LIST_CLASS & operator=(const LINKED_LIST_CLASS &);
	~LINKED_LIST_CLASS();
	void Print(); //accessor
	bool Is_Empty(){return front == 0;}; //accessor
	void AddFront(const List_Type &);
	void AddAnywhere(const List_Type &);
	LIST_NODE * Search(const List_Type &);
	void Remove(const List_Type &);
private:

	LIST_NODE *front, *back; 

};

#endif