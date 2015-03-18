#pragma once
#include "Book.h"
#include <vector>
using namespace std;

class Library
{
public:
	Library();
	~Library();
	string display();
	string displayByName();
	string displayByAuthor();
	int bookCount(){return vBook.size();}
	string addBook(string n,string a,string s);
	string clearBooks(){vBook.clear();return "Books cleared.";}
	string saveBooks(string filename);
	string loadBooks(string filename);
private:
	vector<Book> vBook;
	int lastBookIDUsed;
};