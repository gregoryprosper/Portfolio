#pragma once
#include "Book.h"
#include "Patron.h"
#include <vector>
#include <map>
using namespace std;

class Library
{
public:
	Library();
	~Library();
	string display();
	
	string displayByIndex(multimap<string,int> & index);

	string displayByName() {return displayByIndex(titleIndex);}
	string displayByAuthor() {return displayByIndex(authorIndex);}
	int bookCount(){return vBook.size();}
	string addBook(string n,string a,string s);
	string addBook(int i, string n,string a,string s);
	string clearBooks(){vBook.clear();return "Books cleared.";}
	string clearPatrons(){vPatron.clear();return "Patrons cleared.";}
	string saveBooks(string filename);
	string loadBooks(string filename);
	string savePatrons(string filename);
	string loadPatrons(string filename);
    string removeBook(int bookId);
	string addRandBook(int count);
	string addRandPatron(int count);
	string bookSubjectReport(string repSubject);
	string bookRangeReport(int startID, int endID);
	string removeBooksById(int startID, int endID);
	string removeBooksBySubject(string delSubject);
	string addPatron(string name,string address,string phoneNumber);
	string addPatron(int i, string name,string address,string phoneNumber);
	string displayPatrons();
	string checkOut(int pID,int bID);
private:
	void addBookToVBook(Book & b);
	Book & getBookByID(int id);
	Patron & getPatronByID(int id);
	Book & findBookByID(int key,int lowI,int hiI);
	Book & seqFindBookByID(int key,int lowI,int hiI);
	vector<Book> vBook;
	multimap<string,int> titleIndex;
	multimap<string,int> authorIndex;
	multimap<string,int> subjectIndex;
	map<int,int> IdIndex;
	vector<Patron> vPatron;
};