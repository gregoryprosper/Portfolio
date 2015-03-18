#pragma once
#include "Book.h"
#include <vector>
using namespace std;

class Library
{
public:
	Library();
	string display();
	string displayByName();
	string displayByAuthor();
	int bookCount(){return vBook.size();}
	string addBook(string n,string a,string s);
private:
	vector<Book> vBook;
	int lastBookIDUsed;
};