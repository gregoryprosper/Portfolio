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


string Library::addPatron(int i,string name,string address,string phoneNumber)
{
	Patron newPatron;
	newPatron.libraryCard.id=i;
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
	addBookToVBook(newBook);
	return "Book added.";
}


void Library::addBookToVBook(Book & b)
{
	pair<string,int> p;
	p.first=b.name;
	p.second = vBook.size();
	titleIndex.insert(p);
	p.first=b.author;
	authorIndex.insert(p);
	p.first=b.subject;
	subjectIndex.insert(p);
	vBook.push_back(b);
}

string Library::addBook(int i,string n,string a,string s)
{
	Book newBook;
	newBook.name=n;
	newBook.author=a;
	newBook.subject=s;
	newBook.id=i;
	addBookToVBook(newBook);
	return "Book added.";
}

string Library::displayByIndex(multimap<string,int> & index)
{
	string result;
	multimap<string,int>::iterator mmIter;
	mmIter=index.begin();
	while(mmIter!=index.end())
	{
		if(!vBook[(*mmIter).second].isDeleted)
		{
			result+=padLeft(intToString( vBook[(*mmIter).second].id),' ',8)+"  ";
			result+=padRight( vBook[(*mmIter).second].name,' ',20);
			result+=padRight( vBook[(*mmIter).second].author,' ',20);
			result+=padRight( vBook[(*mmIter).second].subject,' ',20)+"\n";
		}
		mmIter++;
		
	}
	return result;
}


//string Library::displayByAuthor()
//{
//	// sort vBooks by Author
//	sort(vBook.begin(),vBook.end(),compareByAuthor);
//	return display();
//}

string Library::checkOut(int pID,int bID)
{
	string result;
	Book & curBook=getBookByID(bID);
	Patron & curPatron=getPatronByID(pID);
	curBook.checkedOutBy=pID;
	curPatron.BooksCheckedOut.push_back(bID);
	result=curBook.name + " has been checked out by "+curPatron.getName()+"\n";
	return result;
}

//Book & Library::getBookByID(int key)
//{
//	vector<Book>::iterator iter;
//	
//	for(iter=vBook.begin();iter!=vBook.end();iter++)
//	{
//		if((*iter).id==key)
//		{
//			return (*iter);
//		}
//	}
//	throw (string)"Book ID "+intToString(key)+ " does not exist.";
//}

Book & Library::getBookByID(int key)
{
	return findBookByID(key,0,vBook.size()-1);
}

Book & Library::seqFindBookByID(int key,int lowI,int hiI)
{
	for(int i=lowI;i<=hiI;i++)
	{
		if(vBook[i].id==key) return vBook[i];
	}
	throw "Error: Invalid book ID:"+intToString(key);
}


Book & Library::findBookByID(int key,int lowI,int hiI)
{
	int midI=(lowI+hiI)/2;
	if(vBook[midI].id==key) return vBook[midI];
	if( (hiI-lowI)<20 ) seqFindBookByID(key,lowI,hiI);
	if(vBook[midI].id>key)
	{
		return findBookByID(key,lowI,midI-1);
	}
	else
	{
		return findBookByID(key,midI+1,hiI);
	}
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
	throw 999;
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

string Library::savePatrons(string filename)
{
	ofstream fout;
	fout.open(filename);
	fout<<LibraryCard::lastIdUsed<<endl;

	vector<Patron>::iterator iter;

	iter = vPatron.begin();

	while(iter!=vPatron.end())
	{
		fout<<(*iter).libraryCard.id<<' ';
		fout<<(*iter).getName()<<';';
		fout<<(*iter).getPhoneNumber()<<';';
		fout<<(*iter).getAddress()<<endl;
		fout<<(*iter).BooksCheckedOut.size()<<endl;
		for(int i=0;i<(*iter).BooksCheckedOut.size();i++)
		{
			fout<<(*iter).BooksCheckedOut[i]<<endl;
		}
		iter++;
	}
	return "Patrons saved in file "+filename+" .";
}

string Library::loadPatrons(string filename)
{
	ifstream fin;
	fin.open(filename);
	if(fin.fail())return "File not found.";
	clearPatrons();
	string inputLine;
	getline(fin,inputLine);
	LibraryCard::lastIdUsed=stringToInt(inputLine);
	while(!fin.eof())
	{
		getline(fin,inputLine);
		vector<string> token = tokenize(inputLine);
		if(token.size()==4)
		{
			addPatron(stringToInt(token[0]),token[1],token[2],token[3]);
		}
		getline(fin,inputLine);
		int bCount=stringToInt(inputLine);
		for(int i=0;i<bCount;i++)
		{
			getline(fin,inputLine);
			int bID=stringToInt(inputLine);
			vPatron[vPatron.size()-1].BooksCheckedOut.push_back(bID);
		}
	}
	return "File loaded";
}
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
	return "Books saved in file "+filename+" .";
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
			addBook(stringToInt(token[0]),token[1],token[2],token[3]);
		}
	}
	return "File loaded";
}
