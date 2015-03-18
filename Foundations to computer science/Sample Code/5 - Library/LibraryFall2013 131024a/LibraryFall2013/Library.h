#pragma once
#include "Book.h"
#include "Patron.h"
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
    string removeBook(int bookId);
	string addRandBook(int count);
	string addRandPatron(int count);
	string bookSubjectReport(string repSubject);
	string bookRangeReport(int startID, int endID);
	string removeBooksById(int startID, int endID);
	string removeBooksBySubject(string delSubject);
	string addPatron(string name,string address,string phoneNumber);
	string displayPatrons();
	string checkOut(int pID,int bID);
private:
	Book & getBookByID(int id);
	Patron & getPatronByID(int id);
	vector<Book> vBook;
	vector<Patron> vPatron;

};