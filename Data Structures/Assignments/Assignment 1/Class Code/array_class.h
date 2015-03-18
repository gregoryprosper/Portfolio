
#include <iostream>
#include <string>
using namespace std;
	
//using std::ostream;

#ifndef ARRAY_CLASS_H
#define ARRAY_CLASS_H
//const int SIZE=5;

	/*********************************************************************/
	// Class declaraton for Array_Class
	/*********************************************************************/
	template <class New_Type>
	class Array_Class
	{
	public:
		Array_Class();
		~Array_Class();
		Array_Class( Array_Class<New_Type> &);
		void Add(New_Type item);
		int Search(New_Type item);
		void Print();
		void Double_Size();
		Array_Class<New_Type> & operator+(const New_Type &);
		Array_Class<New_Type> & operator=(const Array_Class<New_Type> &);
	
		//example of inline implementation
		//what do inline mean?

		//cout<<XXX<<XXX<<XXX<<XXX;
		friend ostream & operator<<(ostream & out, Array_Class<New_Type> & Org)
	    {
		  int i;

		  for(i=0; i<Org.count; i++)
		  {
			out<<"A[i"<<i<<"] = "<<Org.A[i]<<endl;
		  }
		  return out;
		}

	
	private:
		New_Type *A;
		int count;
		int SIZE;
	};

#endif
