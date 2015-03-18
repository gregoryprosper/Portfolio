#include <iostream>
#include <string>
#include "slist.h"

using namespace std;

LINKED_LIST_CLASS::LINKED_LIST_CLASS()
{
	cout<<"Inside the default constructor\n";
	front = back = 0;

}

LINKED_LIST_CLASS::LINKED_LIST_CLASS(const LINKED_LIST_CLASS & Org)
{
	cout<<"\n Copy Constructor has been called\n";
	front = back = 0;
	(*this) = Org;
}

LINKED_LIST_CLASS & LINKED_LIST_CLASS::operator=(const LINKED_LIST_CLASS & Org)
{
	cout<<"\n operator= has been called\n";

	LIST_NODE *p = front;
	
	if (this != & Org)
	{

		while (front != 0)
		{
			
			front = front->next;
			delete p;
		}
		back = 0;
	}

	p = Org.front;

	while(p!=0)
	{
		AddAnywhere(p->data);
		p=p->next;
		
	}

	return *this;
}

LINKED_LIST_CLASS::~LINKED_LIST_CLASS()
{

	cout<<"Destructor has been called\n";
	while (front != 0)
	{
		LIST_NODE *p = front;
		front = front->next;
		delete p;
	}

}

void LINKED_LIST_CLASS::Print()//accessor
{

	LIST_NODE *p = front;

	cout<<endl<<endl;

	while( p != 0)
	{
		cout<<p->data<<endl;
		p = p->next;
	}

	cout<<endl<<endl;
}

void LINKED_LIST_CLASS::AddFront(const List_Type & key)
{

	if (front == 0)
	{
		front = new LIST_NODE;
		front->data = key;
		front->next = 0;
		back = front;
	}
	else
	{
		LIST_NODE *p = new LIST_NODE;

		p->data = key;
		p->next = front;
		front = p;
	}

}

LIST_NODE * LINKED_LIST_CLASS::Search(const List_Type & key)
{

	LIST_NODE *p = front;

	while(p!=0)
	{
		if (p->data == key)
		{
			return p;
		}
		p = p->next;
	}
	return p;

}



void LINKED_LIST_CLASS::AddAnywhere(const List_Type & key)
{
	LIST_NODE *p = new LIST_NODE;

	p->data = key;
	p->next = 0;

	if (front == 0)
	{
		front = back = p;
	}
	else if (key <= front->data)
	{
		p->next = front;
		front = p;
	}
	else if (key >= back->data)
	{
		back->next = p;
		back = p;
	}
	else
	{
		LIST_NODE *back_ptr, *curr_ptr;
		back_ptr = curr_ptr = front;

		while(curr_ptr!=0 && key > curr_ptr->data)
		{
			back_ptr = curr_ptr;
			curr_ptr = curr_ptr->next;
		}

		p->next =curr_ptr;
		back_ptr ->next = p;
	}

}

void LINKED_LIST_CLASS::Remove(const List_Type & key)
{
	LIST_NODE *p = Search(key);

	if (p == 0)
	{
		cout<<key<<"\n"<<" not in list\n";
	}
	else
	{
		if (p==front && front==back)
		{
			delete p;
			front = back = 0;
		}
		else if (p==front)
		{
			front = front->next;
			delete p;
		}
		else
		{
			LIST_NODE *back_ptr = front;

			while(back_ptr!=0 && back_ptr->next!= p)
			{
				back_ptr = back_ptr->next;
			}
			back_ptr->next = p->next;
			delete p;
		}
	}
}


