#include "Book.h"

int Book::lastBookIDUsed = 0;


bool Book::operator <(Book otherBook)
{
	return name<otherBook.name;
}


bool compareByAuthor(Book & b1,Book & b2)
{
	return b1.author<b2.author;
}