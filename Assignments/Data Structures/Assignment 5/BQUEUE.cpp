#include "BQUEUE.h"
#include <iostream>

using namespace std;


////////////////////////////////////////////////////////////////////
// Name: Default Constructor
// Pre-condition: Queue has not been initialized.
// Post-condition: Queue List is initialized.
// Description: Creates instance of Queue.
////////////////////////////////////////////////////////////////////
BQUEUE::BQUEUE(void)
{
	cout<<"Default constructobar has been called\n";
	back = 0;
}

////////////////////////////////////////////////////////////////////
// Name: Destructor
// Pre-condition: Queue was initialized.
// Post-condition: Memory for Queue is de-allocated.
// Description: Frees up memory used to create Queue.
///////////////////////////////////////////////////////////////
BQUEUE::~BQUEUE(void)
{
	cout<<"Destructor was called\n";

	while (back != 0)
	{
		Dequeue();
	}
}


////////////////////////////////////////////////////////////////////
// Name: Copy Constructor
// Pre-condition: Queue has not been initialized.
// Post-condition: Queue is initialized with a copy of another.
// Description: Creates Queue with copy of another.
////////////////////////////////////////////////////////////////////
BQUEUE::BQUEUE(const BQUEUE & org)
{
	cout<<"Copy constructor has been called\n";
	back = 0;
	
	bqnode *p = org.back;

	if (p != 0)
	{
		bqnode *front = p->next;

		while (front != p)
		{
			Enqueue(front->time);
			front = front->next;
		}

		Enqueue(front->time);

	}
}


////////////////////////////////////////////////////////////////////
// Name: Print
// Pre-condition: A Queue that has been initialized.
// Post-condition: Queue items are printed.
// Description: Queue items are printed.
////////////////////////////////////////////////////////////////////
void BQUEUE::Print()
{
	if (back != 0)
	{
		bqnode *front = back->next;

		while (front != back)
		{
			cout<< front->time <<" ";
			front = front->next;
		}

		cout<< front->time <<" " <<endl;

	}
	else cout<<"Can't Print, Queue Empty\n";
}


////////////////////////////////////////////////////////////////////
// Name: Enqueue
// Pre-condition: Queue Has been initialized.
// Post-condition: Number is added to Queue.
// Description: Takes a number and adds it to the back of the Queue.
////////////////////////////////////////////////////////////////////
void BQUEUE::Enqueue(int i)
{
	bqnode *p = new bqnode;
	p->time = i;

	if (back == 0)
	{
		back = p;
		back->next = back;
		back->prev = back;
	}
	else if (back->next == back)
	{
		back->next = p;
		back->prev = p;
		p->prev = back;
		p->next = back;
		back = p;
		
	}
	else
	{
		back->next->prev = p;
		p->next = back->next;
		back->next = p;
		p->prev = back;
		back = p;
	}
}


////////////////////////////////////////////////////////////////////
// Name: Dequeue
// Pre-condition: Queue Has been initialized.
// Post-condition: Number is removed from Queue.
// Description: Removes a number from the front of the Queue.
////////////////////////////////////////////////////////////////////
void BQUEUE::Dequeue()
{
	if(back != 0)
	{
		if(back != 0 && back != back->next)
		{
			bqnode *front = back->next;
			front->next->prev = back;
			back->next = front->next;
			delete front;
		}
		else if (back == back->next && back != 0)
		{
			bqnode *front = back->next;
			delete front;
			back = 0;
		}
		

	}
	else cout<<"Queue Empty\n";
}