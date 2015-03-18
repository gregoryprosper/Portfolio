#include <iostream>
#include <string>
#include "word.h"

using namespace std;

////////////////////////////////////////////////////////////////////
// Name: Default Constructor
// Pre-condition: WORD List has not been initialized.
// Post-condition: WORD List is initialized.
// Description: Creates instance of WORD.
////////////////////////////////////////////////////////////////////
WORD::WORD()
{
	cout<<"Default constructor invoked \n";
	front = back = 0;
	count = 0;
}


////////////////////////////////////////////////////////////////////
// Name: Copy Constructor
// Pre-condition: WORD List has not been initialized.
// Post-condition: WORD is initialized with a copy of another.
// Description: Creates List with copy of another.
////////////////////////////////////////////////////////////////////
WORD::WORD(const WORD & org)
{
	cout<<"Copy constructor invoked \n";

	front = back = 0;
	count = 0;

	(*this) = org;
}


////////////////////////////////////////////////////////////////////
// Name: Explicit Constructor
// Pre-condition: WORD List has not been initialized.
// Post-condition: WORD is initialized with a string.
// Description: Creates List with string passed in.
////////////////////////////////////////////////////////////////////
WORD::WORD(string s)
{
	count = 0;

	for(int i = 0; i < s.length(); i++)
	{
		insertChar(s[i]); //Goes through every letter in the string and adds it the WORD List
	}
}


////////////////////////////////////////////////////////////////////
// Name: Destructor
// Pre-condition: Memorey for WORD has already been alocated.
// Post-condition: Memory is de-alocated.
// Description: List is deleted.
////////////////////////////////////////////////////////////////////
WORD::~WORD()
{
	cout<<"Destructor was invoked \n";

	while(front != 0)
	{
		CHARACTER *p = front;
		front = p->next;
		delete p;
	}
}


////////////////////////////////////////////////////////////////////
// Name: Insert Char
// Pre-condition: List Has been initialized.
// Post-condition: Char is added to List.
// Description: Takes a char and adds it to the back of the list.
////////////////////////////////////////////////////////////////////
void WORD::insertChar(char c)
{
	CHARACTER *p = new CHARACTER;
	p->data = c;
	p->next = 0;

	if (front == 0) // If List is empty char becomes first Node
	{
		front = p;
		back = front;
		count++;
	}
	else // char is added to the back
	{
		back->next = p;
		back = p;
		count++;
	}
}


////////////////////////////////////////////////////////////////////
// Name: Print
// Pre-condition: A List that has been initialized.
// Post-condition: List items are printed.
// Description: List items are printed.
////////////////////////////////////////////////////////////////////
void WORD::print()
{
	cout<<"Print was invoked \n";

	if(front == 0)
	{
		cout<<"List is Empty \n";
	}
	else
	{
		CHARACTER *p = front;
		while(p != 0)
		{
			cout<<p->data;
			p = p->next;
		}

		cout<<endl;
	}
}


////////////////////////////////////////////////////////////////////
// Name: Overloaded operator=
// Pre-condition: Word has been initialized.
// Post-condition: WORD is copied into another.
// Description: WORD is copied into another.
////////////////////////////////////////////////////////////////////
WORD & WORD::operator=(const WORD & org)
{
	CHARACTER *p = front;

	if(this != & org) // If current list is equal to external List nothing happens
	{
		while(front != 0)
		{
			CHARACTER *p = front;
			front = front->next;
			delete p;
		}
		back = 0;

		p = org.front;

		while (p != 0)
		{
			insertChar(p->data);
			p=p->next;
		}

		return (*this);
	}

}


////////////////////////////////////////////////////////////////////
// Name: Insert
// Pre-condition: WORD has been initialized.
// Post-condition: WORD is added to another word at specified position.
// Description: WORD is added to another word at specified position.
////////////////////////////////////////////////////////////////////
void WORD::insert(int position,const WORD & org)
{
	CHARACTER *p = front;
	CHARACTER *b = org.front;
	count += org.count; 

	if (position == 0) // Word will be added in front
	{
		CHARACTER *z = front;
		
		for (int i = 0; i < org.count; i++)
		{
			if (i == 0)
			{
				CHARACTER *n = new CHARACTER;
				n->data = b->data;
				n->next = 0;
				
				front = n;
				p = n;
				b = b->next;

				if (i == org.count - 1)
				{
					n->next = z;
				}
			}
			else if (i < org.count - 1)
			{
				CHARACTER *n = new CHARACTER;
				n->data = b->data;
				n->next = 0;
				
				p->next = n;
				p = p->next;
				b = b->next;
			}
			else
			{
				CHARACTER *n = new CHARACTER;
				p->next = n;
				n->data = b->data;
				n->next = z;
			}
		}
	}
	else if (position < count) // Word will be placed at position
	{
			for (int i = 0; i < position - 1; i++)
		{
			p = p->next;
		}

		CHARACTER *z = p->next;

		for (int i = 0; i < org.count; i++)
		{

			CHARACTER *n = new CHARACTER;
			n->data = b->data;
			n->next = 0;

			p->next = n;
			p = p->next;
			b = b->next;

			if (i == org.count - 1)
			{
				n->next = z;
			}
		}

	}
	else // Word will be placed in the back
	{
		for (int i = 0; i < org.count; i++)
		{
			CHARACTER *n = new CHARACTER;
			n->data = b->data;
			n->next = 0;

			back->next = n;
			back = n;
			b = b->next;
		}
		
	}
}


////////////////////////////////////////////////////////////////////
// Name: Overloaded operator=
// Pre-condition: WORD has been initialized.
// Post-condition: String is copied into List.
// Description: String is copied into List.
////////////////////////////////////////////////////////////////////
WORD & WORD::operator=(string s)
{

	if (front != 0)
	{
		while(front != 0)
		{
			CHARACTER *p = front;
			front = p->next;
			delete p;
			count--;
		}
		back = 0;

	}
	
	for(int i = 0; i < s.length(); i++)
	{
		insertChar(s[i]);
	}

	return (*this);
}


////////////////////////////////////////////////////////////////////
// Name: Overloaded operator+
// Pre-condition: WORD has been initialized.
// Post-condition: Contents of WORD are added to back of current WORD.
// Description: Contents of WORD are added to back of current WORD
////////////////////////////////////////////////////////////////////
void WORD::operator+(const WORD & org)
{
	CHARACTER *b = org.front;

	for(int i = 0; i < org.count; i++)
	{
		insertChar(b->data);
		b = b->next;
	}
	
}


////////////////////////////////////////////////////////////////////
// Name: Search
// Pre-condition: WORD has been initialized.
// Post-condition: Pointer to found Character is returned.
// Description: Searches for letter and retruns pointer to it.
////////////////////////////////////////////////////////////////////
CHARACTER* WORD::search(char c)
{
	CHARACTER *p = front;

	while (p != 0)
	{
		if (p->data == c)
		{
			return p;
		}
		p = p->next;
	}
	return p;
}


////////////////////////////////////////////////////////////////////
// Name: Remove
// Pre-condition: WORD is initilized.
// Post-condition: Removes first occurance of WORD in current list.
// Description: Removes first occurance of WORD in current list.
////////////////////////////////////////////////////////////////////
void WORD::remove(const WORD & us)
{
	CHARACTER *p = front;
	CHARACTER *b = front;
	CHARACTER *q = us.front;
	int oldCount = count;

	while (p != 0)
	{
	
		if (p->data == q->data) // Checks to see if there is a match of characters
		{
			b = p;

			while (q != 0) // Checks subsequent characters for match
			{
				b = b->next;
				q = q->next;

				if (b->data == us.back->data) // Checks to see if last character of external word has found a match
				{
					q = 0;

					if (p == front) // If word to be removed starts at the front
					{
						front = b->next;
						b = b->next;

						CHARACTER *d = p;
						while(d != b)
						{
							p = d;
							d = d->next;
							delete p;
							count--;
						}
					}
					else // Word is in the middle or end
					{
						CHARACTER *newP = front;
						while (newP->next != p)
						{
							newP = newP->next;
						}

						newP->next = b->next;
						b = b->next;


						CHARACTER *d = p;
						while(d != b)
						{
							p = d;
							d = d->next;
							delete p;
							count--;
						}
					}

				}
			}
			q = us.front; // Resets q back to front of external list

		}
		if (count == (oldCount - us.count)) // Checks to see if remove was successful and sets p to 0 to end loop
		{
			p = 0;
		}
		else p = p->next;
	}
}


////////////////////////////////////////////////////////////////////
// Name: Match Exist
// Pre-condition: WORD is initialized.
// Post-condition: Returns True if a match is found.
// Description: Checks to see if a match exist.
////////////////////////////////////////////////////////////////////
bool WORD::matchExist(const WORD & org)
{
	CHARACTER *p = front;
	CHARACTER *b = front;
	CHARACTER *q = org.front;

	while (p != 0)
	{
	
		if (p->data == q->data) // Checks to see if there is a match of characters
		{
			b = p;

			while (q != 0) // Checks to see if last character of external word has found a match
			{
				b = b->next;
				q = q->next;

				if (b->data == org.back->data) // If last character is matched returns true
				{
					return true;
				}

			}
			
		}

		p = p->next;

	}
	return false; // When this is reached match was not found
}


////////////////////////////////////////////////////////////////////
// Name: Remove All
// Pre-condition: WORD is initialized.
// Post-condition: All ocurrances of WORD are removed from current list.
// Description: Removes all ocurrances of WORD from list.
////////////////////////////////////////////////////////////////////
void WORD::removeAll(const WORD & us)
{
	while (matchExist(us)) // While there is a match remvove is called
	{
		remove(us);
	}
}