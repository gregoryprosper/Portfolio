#include "Library.h"
#include "misc.h"
#include <algorithm>
using namespace std;


Library::Library()
{
	lastBookIDUsed=0;
}


string Library::addBook(string n,string a,string s)
{
	Book newBook;
	newBook.name=n;
	newBook.author=a;
	newBook.subject=s;
	lastBookIDUsed++;
	newBook.id=lastBookIDUsed;
	vBook.push_back(newBook);
	return "Book added.";
}

string Library::displayByName()
{
	// sort vBooks by Name
	sort(vBook.begin(),vBook.end());
	return display();
}


string Library::displayByAuthor()
{
	// sort vBooks by Author
	sort(vBook.begin(),vBook.end(),compareByAuthor);
	return display();
}



string Library::display()
{
	string result;
	result+=padRight("Id",'.',10);
	result+=padRight("Name",'.',20);
	result+=padRight("Author",'.',20);
	result+=padRight("Subject",'.',20)+"\n";
	result+=padRight("",'-',70)+"\n";

	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		result+=padLeft(intToString( (*iter).id),' ',8)+"  ";
		result+=padRight( (*iter).name,' ',20);
		result+=padRight( (*iter).author,' ',20);
		result+=padRight( (*iter).subject,' ',20)+"\n";
		iter++;
	}
//	for(unsigned int i=0;i<vBook.size();i++)
//	{
//		result+=padLeft(intToString(vBook[i].id),' ',8)+"  ";
//		result+=padRight(vBook[i].name,' ',20);
//		result+=padRight(vBook[i].author,' ',20);
//		result+=padRight(vBook[i].subject,' ',20)+"\n";
//	}
	return result;
}

//string Library::display()
//{
//	string result;
//	result+=padRight("Id",'.',10);
//	result+=padRight("Name",'.',20);
//	result+=padRight("Author",'.',20);
//	result+=padRight("Subject",'.',20)+"\n";
//	result+=padRight("",'-',70)+"\n";
//	for(unsigned int i=0;i<vBook.size();i++)
//	{
//		result+=padLeft(intToString(vBook[i].id),' ',8)+"  ";
//		result+=padRight(vBook[i].name,' ',20);
//		result+=padRight(vBook[i].author,' ',20);
//		result+=padRight(vBook[i].subject,' ',20)+"\n";
//	}
//	return result;
//}

