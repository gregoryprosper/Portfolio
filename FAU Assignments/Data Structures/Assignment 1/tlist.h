#include <iostream>
#include <string>
using namespace std;

#ifndef TLIST_H
#define TLIST_H


template <class t_type>
class TLIST
{
public:
	TLIST();
	~TLIST();
	TLIST(const TLIST<t_type> &);
	void print();
	bool IsFull();
	bool IsEmpty();
	void printBefore();
	void printAfter();
	void add(t_type item);
	int search(t_type item);
	void remove(t_type item);
	void doubleSize();
	TLIST<t_type> & operator+(const t_type &);
	TLIST<t_type> & operator=(const TLIST<t_type> &);

	friend ostream & operator<<(ostream & out, TLIST<t_type> & In)
	{
		cout<<"Operator<< has been called with chaining"<<endl;
		
		for(int i = 0; i < In.count; i++)
		{
			out<<"DB["<<i<<"]="<<In.DB[i]<<" ";
		}
		cout<<endl;
		return out;
	}
private:
	t_type *DB;
	int count;
	int capacity;
};


/******************************************************************************************/
       //Class definitions for the member function of TLIST Class
	/**************************************************************************************/

////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: TLIST (Default Constructor)
// Precondition: List item has not been initialized.
// Postcondition: List item will be initialized.
// Description: The default constructor which is called to initialize List instance.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
TLIST<t_type>::TLIST()
{
	cout<<"Default constructor Invoked"<<endl;

	count = 0;
	capacity = 2;
	DB = new t_type[capacity];

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: ~TLIST (Destructor)
// Precondition: List item has been initialized already.
// Postcondition: List item is deleted.
// Description: Dealocates the memory used by object.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
TLIST<t_type>::~TLIST()
{
	cout<<"Destructor Invoked"<<endl;

	delete [] DB;
	count = 0;
	DB = 0;

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: TLIST (Copy Constructor)
// Precondition: List item has not been initialized.
// Postcondition: List item is initialized with a copy of another list item.
// Description: Copies one list item into another which is being initialized.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
TLIST<t_type>::TLIST(const TLIST<t_type> & In)
{
	cout<<"Copy Constructor Invoked"<<endl;

	cout<<"Original List: ";
	print();
	cout<<endl;

	count = In.count;
	capacity = In.capacity;

	DB = new t_type[capacity];

	for(int i = 0; i < count; i++)
	{
		DB[i] = In.DB[i];
	}

	//(*this) = In;

	cout<<"Copied List: ";
	print();
	cout<<endl;
}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Print
// Precondition: None
// Postcondition: Prints the objects within a List item.
// Description: Prints the objects within a List item.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::print() 
{
	if(count > 0)
	{
		for(int i = 0; i < count; i++)
		{

			cout<<"DB["<<i<<"]="<<DB[i]<<" ";
		}
	}
	else cout<<"List is empty"<<endl;
	

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: IsFull
// Precondition: List item has been initialized already.
// Postcondition: None
// Description: Returns true if the List item is full.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
bool TLIST<t_type>::IsFull()
{

	if(count == capacity)
	{
		cout<<"List is Full"<<endl;
		return true;
	}
	else return false;

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: IsEmpty
// Precondition: List item has been initialized already.
// Postcondition: None
// Description: Returns true if the List item is full.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
bool TLIST<t_type>::IsEmpty()
{

	if(count == 0)
	{
		cout<<"List is empty"<<endl;
		return true;
	}
	else return false;

}

////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Add
// Precondition: List item has been initialized.
// Postcondition: object is added to List item.
// Description: Takes an object and adds it to the List item.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::add(t_type item)
{
	bool full = IsFull();

	if(!full)
	{
		DB[count++] = item;
	}
	else
	{
		doubleSize();
		add(item);
	}
	

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Search
// Precondition: List item has been initialized.
// Postcondition: None
// Description: Returns the index of the item that was searched for, if it does not 
//				exist; return -1.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
int TLIST<t_type>::search(t_type item)
{
	cout<<endl<<"Search Invoked"<<endl;

	bool empty = IsEmpty();

	if(!empty)
	{
		for(int i = 0; i < count; i++)
		{
			if(DB[i] == item)
			{
				cout<<item<<" was found at index: "<<i<<endl;
				return i;
			}
		}
	
		cout<<item<<" was not found."<<endl;
	}
	return -1;

}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Remove
// Precondition: List item was initialized.
// Postcondition: Item was removed from List item.
// Description: Searches for what is to be removed and removes it.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::remove(t_type item)
{
	cout<<endl<<"Remove Invoked"<<endl;
	print();

	int location = search(item);

	if(location != -1)
	{
		for(int i = location; i < count - 1; i++)
		{
			DB[i] = DB[i+1];
		}
		
		count--;
	}

	print();
}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Double Size
// Precondition: List item was initialized and is full.
// Postcondition: List item capacity is doubled.
// Description: If the List item is full then it is doubled.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::doubleSize()
{
	cout<<"Double Size Invoked"<<"   ";

	//Prints the size before and after size is doubled
	cout<<"Old Size: "<<capacity<<" ";
	cout<<"New Size: "<<capacity * 2<<endl;

	capacity *= 2;
	t_type *temp = new t_type[capacity];

	for(int i = 0; i < count; i++)
	{
		temp[i] = DB[i];
	}

	delete [] DB;
	DB = temp;
}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Operator +
// Precondition: List item was initialized.
// Postcondition: Operand is added to List Item.
// Description: Takes the argument on the right and adds it to the List item.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
TLIST<t_type> & TLIST<t_type>::operator+(const t_type & In)
{

	cout<<"Operator+ has been called with chaining"<<endl;
	
	//Prints contents before item added
	printBefore();
	
	bool full = IsFull();
	if(!full)
	{
		DB[count++] = In;
	}
	else
	{
		doubleSize();
		(*this) + In;

		return *this;
	}
	
	//Prints contents after item added
	printAfter();
	return *this;
}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Operator =
// Precondition: List item is initialiezed.
// Postcondition: The state of one List item is copied into another list item
// Description: Copies one List into another.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
TLIST<t_type> & TLIST<t_type>::operator=(const TLIST<t_type> & In)
{
	cout<<"Operator= Invoked"<<endl;

	cout<<"Original List: ";
	print();
	cout<<endl;

	if(this != &In)
	{
		delete [] DB;
		count = In.count;
		capacity = In.capacity;

		DB = new t_type[capacity];

		for(int i = 0; i < count; i++)
		{
			DB[i] = In.DB[i];
		}
	}

	cout<<"Copied List: ";
	print();
	cout<<endl;
	
	return *this;
}


////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Print Before
// Precondition: None
// Postcondition: Prints the contents of a List item before its changed.
// Description:
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::printBefore()
{
	cout<<"Before: ";

	bool empty = IsEmpty();

	if(!empty)
	{
		
		for(int i = 0; i < count; i++)
		{

			cout<<"DB["<<i<<"]="<<DB[i]<<" ";
		}
		cout<<endl;
	}
}
	

////////////////////////////////////////////////////////////////////////////////////////////
// Function Name: Print After
// Precondition: None
// Postcondition: Prints the contents of a List item after its changed.
// Description: Prints the contents of a List item after its changed.
////////////////////////////////////////////////////////////////////////////////////////////
template <class t_type>
void TLIST<t_type>::printAfter()
{
	cout<<"After: ";
	for(int i = 0; i < count; i++)
	{

		cout<<"DB["<<i<<"]="<<DB[i]<<" ";
	}
	cout<<endl;
}
#endif