#pragma once
#include <string>
using namespace std;




class Book
{
public:
	Book() {checkedOutBy=0;isDeleted=false;}
	bool lessThan(Book otherBook);  // a.lessThan(b)
	bool operator <(Book otherBook);// a<b
	friend class Library;
	friend bool compareByAuthor(Book &,Book &);
	void assignNewID(){lastBookIDUsed++;id=lastBookIDUsed;}
private:
	string name;
	string author;
	string subject;
	unsigned int id;
	static int lastBookIDUsed;   //obj.lastBookIDUsed++;
	int checkedOutBy;
	bool isDeleted;
};
