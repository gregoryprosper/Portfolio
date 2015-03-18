#include <iostream>
#include "stack.h"


////////////////////////////////////////////////////////////////////
// Name: Destructor
// Pre-condition: Stack was initialized.
// Post-condition: Memory for stack is de-allocated.
// Description: Frees up memory used to create stack.
///////////////////////////////////////////////////////////////
Stack::~Stack()
{
	while(s_top != 0)
	{
		pop();
	}
}

////////////////////////////////////////////////////////////////////
// Name: Pop
// Pre-condition: Initialized stack with items added.
// Post-condition: Top item returned and deleted.
// Description: Top item returned and deleted.
///////////////////////////////////////////////////////////////
string Stack::pop()
{
	Stack_node *p;
	string top = s_top->data;

	if (s_top != 0)
	{
		p = s_top;
		s_top = s_top->next;
		delete p;
	}
	return top;
}

////////////////////////////////////////////////////////////////////
// Name: Push
// Pre-condition: Initialized Stack.
// Post-condition: Adds string item to stack.
// Description: Adds string item to stack.
///////////////////////////////////////////////////////////////
void Stack::push(string c)
{
	Stack_node *p = new Stack_node;

	p->data = c;
	p->next = s_top;
	s_top = p;
}
