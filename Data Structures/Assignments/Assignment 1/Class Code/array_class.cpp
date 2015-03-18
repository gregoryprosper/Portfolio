
#include <string>
#include <iostream>
#include "array_class.h"

using namespace std;

/*********************************************************************/
	// Class definitins for the member function of Array_Class
	/*********************************************************************/


	////////////////////////////////////////////////////////////////////////
	// Function Name:  Array_Class
    // Precondition: State of class has not been initialize
    // Postcondition:  State has been initialize
    // Description:  Default constructor that initialized A, count and SIZE.
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>
	Array_Class<New_Type>::Array_Class()
	{
		cout<<"You are inside the default constructor\n";
		cout<<"New_Type has a size of "<<sizeof(New_Type)<<" bytes\n\n";
		count=0;
		SIZE = 2;
		A = new New_Type[SIZE];
	}


	////////////////////////////////////////////////////////////////////////
	// Function Name:  ~Array_Class
    // Precondition:  Dynamic memory state allocated for the class/object
    // Postcondition: Dynamic memory has been de-allocated
    // Description:  Destructor of the class which de-allocates dynamic memory
	//               allocated by operator new
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>
	Array_Class<New_Type>::~Array_Class()
	{
		cout<<"The Destructor has been called\n\n";
		delete [] A;
		count = 0;
		A = 0;
	}

	////////////////////////////////////////////////////////////////////////
	// Function Name:  Array_Class
    // Precondition: State of class has not been initialize
    // Postcondition:  State has been initialize
    // Description:  Copy constructor that performs a deep copy
	////////////////////////////////////////////////////////////////////////

	template <class New_Type>
	Array_Class<New_Type>::Array_Class( Array_Class<New_Type> & Org)
	{
		count = Org.count;
		SIZE = Org.SIZE;
		A = new New_Type[SIZE];
		for(int i=0; i<count; i++)
		{
			A[i] = Org.A[i];
		}
	}

	template <class New_Type>
	Array_Class<New_Type> & Array_Class<New_Type>::operator=(const Array_Class<New_Type> & Org)
	{
		if (this != &Org)
		{
		  count = Org.count;
		  SIZE = Org.SIZE;
		  A = new New_Type[SIZE];
		  for(int i=0; i<count; i++)
		  {
			A[i] = Org.A[i];
		  }
		}
		return *this;
	}

	////////////////////////////////////////////////////////////////////////
	// Function Name:  Add
    // Precondition: Item has not be added to A
    // Postcondition:  Item has been add to array A; count incremented;
	//                 A's SIZE was doubled if necessary
    // Description:  Add an item to the dynamic array of the class
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>
	void Array_Class<New_Type>::Add(New_Type item)
	{
		if (count<SIZE)
		{
			A[count++] = item;
		}
		else
		{
			Double_Size();
			Add(item);
		}
	}

	////////////////////////////////////////////////////////////////////////
	// Function Name:  Search
    // Precondition: item contains the search key
    // Postcondition:  if item found it location is returned;
	//                 otherwise -1 is returned.
    // Description:  Locates item in A if it is there
	////////////////////////////////////////////////////////////////////////
	
	template <class New_Type>
	int Array_Class<New_Type>::Search(New_Type item)
	{
		int i;

		for(i=0; i<count; i++)
		{
			if (item == A[i])
			{
				return i;
			}
		}
		return -1;
	}
	////////////////////////////////////////////////////////////////////////
	// Function Name:  Print
    // Precondition: None
    // Postcondition:  A has been printed to the screen
    // Description:  Prints all the elements stored in A
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>
	void Array_Class<New_Type>::Print()
	{
		int i;

		for(i=0; i<count; i++)
		{
			cout<<"A[i"<<i<<"] = "<<A[i]<<endl;
		}
	}


	////////////////////////////////////////////////////////////////////////
	// Function Name:  operator+
    // Precondition: Org has not be added to A
    // Postcondition:  Org has been add to array A; count incremented;
	//                 A's SIZE was doubled if necessary
    // Description:  Add an item to the dynamic array of the class; overloading
	//               operator+ as a member function with chaining
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>

	Array_Class<New_Type> & Array_Class<New_Type>::operator+(const New_Type & Org)
	{
		if (count<SIZE)
		{
			A[count++] = Org;
			//(*this).A[count++] = Org;
			//this->A[count++] = Org;
		}
		else
		{
			Double_Size();
			(*this) + Org;
		}

		return *this;
	}


	////////////////////////////////////////////////////////////////////////
	// Function Name:  Double_Size
    // Precondition: A, the dynamic array is FULL.
    // Postcondition:  A the size/capacity has been doubled
    // Description:  Doubles the dynamic memory of the array A
	////////////////////////////////////////////////////////////////////////
	template <class New_Type>
	void Array_Class<New_Type>::Double_Size()
	{
		cout<<endl<<endl;
		cout<<"Double_Size has been called\n";
		cout<<"Old Size = "<<SIZE<<endl;
		cout<<"New Size = "<<SIZE*2<<endl;

		(*this).SIZE *= 2;
		New_Type *temp = new New_Type[SIZE];

		for(int i=0; i<count; i++)
		{
			temp[i] = A[i];
		}

		delete [ ] A;
		A = temp;

	}

	