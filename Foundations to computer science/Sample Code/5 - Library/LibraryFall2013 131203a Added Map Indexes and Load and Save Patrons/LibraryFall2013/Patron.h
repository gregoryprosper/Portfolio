#pragma once
#include"Person.h"
#include"LibraryCard.h"
#include <vector>
using namespace std;


//#ifndef PATRON
//#define PATRON


class Patron : public Person
{
public:
	friend class Library;
private:
	LibraryCard libraryCard;
	vector<int> BooksCheckedOut;
};








//#endif






