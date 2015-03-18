#include "Library.h"
#include "misc.h"
#include <algorithm>
#include <fstream>
using namespace std;


Library::Library()
{
	loadBooks("Default.txt");
}


Library::~Library()
{
	saveBooks("Default.txt");
}

string Library::removeBooksBySubject(string delSubject)
{
	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		// This is where you do something to (*iter)

		if((*iter).subject==delSubject)
		{
			vector<Book>::iterator iTemp;
			iTemp=iter;
			iter++;
			vBook.erase(iTemp);
		}
	}
	return "Books removed.";
}




//string Library::bookRangeReport(int startID, int endID)
//{
//	string result;
//
//	sort(vBook.begin(),vBook.end());
//
//	vector<Book>::iterator iter;
//
//	iter = vBook.begin();
//
//	while(iter!=vBook.end())
//	{
//		if( ((*iter).id>=startID) && ((*iter).id<=endID) )
//		{
//			result+=padLeft(intToString( (*iter).id),' ',8)+"  ";
//			result+=padRight( (*iter).name,' ',20);
//			result+=padRight( (*iter).author,' ',20);
//			result+=padRight( (*iter).subject,' ',20)+"\n";
//		}
//		iter++;
//	}
//	return result;
//}
//

string Library::bookRangeReport(int startID, int endID)
{
	string result;

	vector<Book> vBTemp;

	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		if( ((*iter).id>=startID) && ((*iter).id<=endID) )
		{
			vBTemp.push_back(*iter);
		}
		iter++;
	}

	sort(vBTemp.begin(),vBTemp.end());

	iter = vBTemp.begin();

	while(iter!=vBTemp.end())
	{
		result+=padLeft(intToString( (*iter).id),' ',8)+"  ";
		result+=padRight( (*iter).name,' ',20);
		result+=padRight( (*iter).author,' ',20);
		result+=padRight( (*iter).subject,' ',20)+"\n";
		iter++;
	}

	return result;
}





string Library::removeBooksById(int startID, int endID)
{
	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		if(((*iter).id>=startID)&&((*iter).id<=endID))
		{
			vBook.erase(iter);
		}

		iter++;
	}
	return "Books removed.";
}


//string Library::bookSubjectReport(string repSubject)
//{
//	string result;
//	
//	sort(vBook.begin(),vBook.end());
//	
//	vector<Book>::iterator iter;
//
//	iter = vBook.begin();
//
//	while(iter!=vBook.end())
//	{
//		if((*iter).subject==repSubject)
//		{
//			result+=padLeft(intToString( (*iter).id),' ',8)+"  ";
//			result+=padRight( (*iter).name,' ',20);
//			result+=padRight( (*iter).author,' ',20);
//			result+=padRight( (*iter).subject,' ',20)+"\n";
//		}
//		iter++;
//	}
//	return result;
//}


string Library::bookSubjectReport(string repSubject)
{
	string result;

	vector<Book> temp;
	
	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		if((*iter).subject==repSubject)
		{
			temp.push_back(*iter);
		}
		iter++;
	}

	sort(temp.begin(),temp.end());

	iter = temp.begin();

	while(iter!=temp.end())
	{
		result+=padLeft(intToString( (*iter).id),' ',8)+"  ";
		result+=padRight( (*iter).name,' ',20);
		result+=padRight( (*iter).author,' ',20);
		result+=padRight( (*iter).subject,' ',20)+"\n";
		iter++;
	}

	return result;
}



string Library::addRandBook(int count)
{
	for(int i=0;i<count;i++)
	{
		addBook(randString(8),randString(12),randString(18));
	}
	return "Random books added.";
}



string Library::addRandPatron(int count)
{
	for(int i=0;i<count;i++)
	{
		addPatron(randString(12),randString(20),randNumberString(10));
	}
	return "Random books added.";
}




string Library::removeBook(int delId)
{
	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		// This is where you do something to (*iter)

		if((*iter).id==delId)
		{
			vBook.erase(iter);
			return "Book removed.";
		}

		iter++;
	}
	return "Book ID not found.";
}

string Library::addPatron(string name,string address,string phoneNumber)
{
	Patron newPatron;
	newPatron.setName(name);
	newPatron.setAddress(address);
	newPatron.setPhoneNumber(phoneNumber);
	vPatron.push_back(newPatron);
	return "Patron added.";
}


string Library::addBook(string n,string a,string s)
{
	Book newBook;
	newBook.name=n;
	newBook.author=a;
	newBook.subject=s;
	newBook.assignNewID();
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

string Library::checkOut(int pID,int bID)
{
	string result;
	Book & curBook=getBookByID(bID);
	Patron curPatron=getPatronByID(pID);
	curBook.checkedOutBy=pID;
	curPatron.BooksCheckedOut.push_back(bID);
	result=curBook.name + " has been checked out by "+curPatron.getName()+"\n";
	return result;
}

Book & Library::getBookByID(int key)
{
	vector<Book>::iterator iter;
	
	for(iter=vBook.begin();iter!=vBook.end();iter++)
	{
		if((*iter).id==key)
		{
			return (*iter);
		}
	}
	return *(vBook.end());
}

Patron & Library::getPatronByID(int key)
{
	vector<Patron>::iterator iter;
	
	for(iter=vPatron.begin();iter!=vPatron.end();iter++)
	{
		if((*iter).libraryCard.getId()==key)
		{
			return (*iter);
		}
	}
	return *(vPatron.end());
}

string Library::displayPatrons()
{
	string result;
	//result+=padRight("Id",'.',10);
	//result+=padRight("Name",'.',20);
	//result+=padRight("Author",'.',20);
	//result+=padRight("Subject",'.',20)+"\n";
	//result+=padRight("",'-',70)+"\n";

	vector<Patron>::iterator iter;

	iter = vPatron.begin();

	while(iter!=vPatron.end())
	{
		result+=padLeft(intToString( (*iter).libraryCard.getId()),' ',8)+"  ";
		result+=padRight( (*iter).getName(),' ',15);
		result+=padRight( (*iter).getAddress(),' ',22);
		result+=padRight( (*iter).getPhoneNumber(),' ',15)+"\n";
		iter++;
	}
	return result;
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

string Library::saveBooks(string filename)
{
	ofstream fout;
	fout.open(filename);
	fout<<Book::lastBookIDUsed<<endl;

	vector<Book>::iterator iter;

	iter = vBook.begin();

	while(iter!=vBook.end())
	{
		fout<<(*iter).id<<' ';
		fout<<(*iter).name<<';';
		fout<<(*iter).author<<';';
		fout<<(*iter).subject<<endl;
		iter++;
	}
	return "Books saved in file.";
}

string Library::loadBooks(string filename)
{
	ifstream fin;
	fin.open(filename);
	if(fin.fail())return "File not found.";
	clearBooks();
	string inputLine;
	getline(fin,inputLine);
	Book::lastBookIDUsed=stringToInt(inputLine);
	while(!fin.eof())
	{
		getline(fin,inputLine);
		vector<string> token = tokenize(inputLine);
		if(token.size()==4)
		{
			Book readBook;
			readBook.id=stringToInt(token[0]);
			readBook.name=token[1];
			readBook.author=token[2];
			readBook.subject=token[3];
			vBook.push_back(readBook);
		}
	}
	return "File loaded";
}