#include "PhoneBook.h"
#include <algorithm>
#include "misc.h"
#include <fstream>
using namespace std;


PhoneBook::PhoneBook()
{
	loadPhoneBook("Default");
}

PhoneBook::~PhoneBook()
{
	savePhoneBook("Default");
}

string PhoneBook::addContact (string s, string n, string g)
{
	Contact newContact;
	newContact.name = s;
	copy(n.begin(),n.end(),back_inserter(newContact.number));
	newContact.group = g;
	newContact.assignNewId();
	vContacts.push_back(newContact);

	return "Contact was Added.";
}

string PhoneBook::addCompany (string n, string a, string c)
{
	Company newCompany;
	newCompany.name = n;
	newCompany.address = a;
	newCompany.category = c;
	newCompany.assignNewId();
	vCompanies.push_back(newCompany);

	return "Company was Added.";
}

string PhoneBook::removeContact (string r)
{
	for (size_t i = 0; i<=vContacts.size();)
	{
		if (r == vContacts[i].name)
		{
			vContacts.erase(vContacts.begin()+i);
			return "Contact was Removed.";
		}
		else i++;
	}
	return "Contact was Removed.";
}

string PhoneBook::removeCompany (string r)
{
	for (size_t i = 0; i<=vCompanies.size();)
	{
		if (r == vCompanies[i].name)
		{
			vCompanies.erase(vCompanies.begin()+i);
			return "Company was Removed.";
		}
		else i++;
	}
	return "Company was Removed.";
}

string PhoneBook::showContacts ()
{
	string result;
	string number;

	sort(vContacts.begin(),vContacts.end());

	result+=padRight("ID",'.',5);
	result+=padRight("Name",'.',20);
	result+=padRight("Group",'.',20);
	result+=padRight("Number",'.',20)+"\n";
	result+=padRight("",'-',70)+"\n";

	for (unsigned int i = 0; i<vContacts.size();i++)
	{
		result += padRight( intToString(vContacts[i].id),' ',5);
		result += padRight( vContacts[i].name,' ',20);
		result += padRight( vContacts[i].group,' ',20);
		number.assign(vContacts[i].number.begin(),vContacts[i].number.end());
		result += padRight( number,' ',20) + '\n';

		for (unsigned int n = 0; n < vContacts[i].vAssociateCompanies.size();n++)
		{
			for (unsigned int x = 0; x< vCompanies.size();x++)
			{
			if (vContacts[i].vAssociateCompanies[n] == vCompanies[x].id)
				{
					result +=        padRight ("",' ',5);
					result +=		 padRight (vCompanies[x].name,' ',15) + '\n';
				}
			}
		}

	}

	return result;

}

string PhoneBook::showCompany ()
{
	string result;

	sort(vCompanies.begin(),vCompanies.end());
	
	result+=padRight("ID",'.',5);
	result+=padRight("Name",'.',15);
	result+=padRight("Address",'.',45);
	result+=padRight("Category",'.',10)+"\n";
	result+=padRight("",'-',70)+"\n";

	for (unsigned int i = 0; i<vCompanies.size();i++)
	{
		result += padRight( intToString(vCompanies[i].id),' ',5);
		result += padRight( vCompanies[i].name,' ',15);
		result += padRight( vCompanies[i].address,' ',45);
		result += padRight( vCompanies[i].category,' ',10) + '\n';

		for (unsigned int n = 0; n < vCompanies[i].vAssociateContacts.size();n++)
		{
			for (unsigned int x = 0; x< vContacts.size();x++)
			{
			if (vCompanies[i].vAssociateContacts[n] == vContacts[x].id)
				{
					result +=        padRight ("",' ',5);
					result +=		 padRight (vContacts[x].name,' ',15) + '\n';
				}
			}
		}
	}

	return result;
}

string PhoneBook::showContactCat ()
{
	string result;
	string number;

	sort(vContacts.begin(),vContacts.end(),compareCat);

	result+=padRight("ID",'.',5);
	result+=padRight("Name",'.',20);
	result+=padRight("Group",'.',20);
	result+=padRight("Number",'.',20)+"\n";
	result+=padRight("",'-',70)+"\n";

	for (unsigned int i = 0; i<vContacts.size();i++)
	{
		result += padRight( intToString(vContacts[i].id),' ',5);
		result += padRight( vContacts[i].name,' ',20);
		result += padRight( vContacts[i].group,' ',20);

		number.assign(vContacts[i].number.begin(),vContacts[i].number.end());

		result += padRight( number,' ',20) + '\n';

	}

	return result;

}

string PhoneBook::showCompanyCat ()
{
	string result;

	sort(vCompanies.begin(),vCompanies.end(),compareCatCP);

	result+=padRight("ID",'.',5);
	result+=padRight("Name",'.',15);
	result+=padRight("Address",'.',45);
	result+=padRight("Category",'.',20)+"\n";
	result+=padRight("",'-',70)+"\n";

	for (unsigned int i = 0; i<vCompanies.size();i++)
	{
		result += padRight( intToString(vCompanies[i].id),' ',5);
		result += padRight( vCompanies[i].name,' ',15);
		result += padRight( vCompanies[i].address,' ',45);
		result += padRight( vCompanies[i].category,' ',20) + '\n';

	}
	return result;
}

string PhoneBook::savePhoneBook(string filename)
{
	string number;
	ofstream f1out,f2out;
	f1out.open(filename + ".persons.txt");
	f2out.open(filename + ".companies.txt");
	f1out<<Contact::lastContactId<<endl;
	f2out<<Company::lastCompanyId<<endl;
	
	for (unsigned int i = 0; i<vContacts.size();i++)
	{
		f1out << vContacts[i].id <<' ';
		f1out << vContacts[i].name <<';';
		number.assign(vContacts[i].number.begin(),vContacts[i].number.end());
		f1out << number <<';';
		f1out << vContacts[i].group <<endl;
	}

	for (unsigned int i = 0; i<vCompanies.size();i++)
	{
		f2out << vCompanies[i].id <<' ';
		f2out << vCompanies[i].name <<';';
		f2out << vCompanies[i].address <<';';
		f2out << vCompanies[i].category <<endl;
	}

	return "Phone Book was saved.";
}

string PhoneBook::loadPhoneBook(string filename)
{
	clearContacts();
	clearCompanies();
	ifstream f1in,f2in;
	f1in.open(filename + ".persons.txt");
	f2in.open(filename + ".companies.txt");
	string inputLine1;
	string inputLine2;
	getline(f1in,inputLine1);
	getline(f2in,inputLine2);
	Contact::lastContactId=stringToInt(inputLine1);
	Company::lastCompanyId=stringToInt(inputLine2);
	while(!f1in.eof())
	{
		getline(f1in,inputLine1);
		vector<string> token = tokenize(inputLine1);
		if(token.size()==4)
		{
			Contact readFile;
			readFile.id = stringToInt(token[0]);
			readFile.name=token[1]; //+ ' ' + token[2];
			copy(token[2].begin(),token[2].end(),back_inserter(readFile.number));
			readFile.group=token[3];
			vContacts.push_back(readFile);
		}
	}

	while(!f2in.eof())
	{
		getline(f2in,inputLine2);
		vector<string> token = tokenize(inputLine2);
		if(token.size()==4)
		{
			Company readFile;
			readFile.id = stringToInt(token[0]);
			readFile.name = token[1]; //+ ' ' + token[2];
			readFile.address = token[2];
			readFile.category = token[3];
			vCompanies.push_back(readFile);
		}
	}

	return "Phone Book loaded.";
}

string PhoneBook::addRandPerson(int count)
{
	for (int i = 0; i < count;i++)
	{
		addContact(randString(8),randNumberString(10),randString(5));
	}
	return "Random Contacts added";

}

string PhoneBook::addRandCompany(int count)
{
	for (int i = 0; i < count;i++)
	{
		addCompany(randString(5),randString(10),randString(5));
	}
	return "Random Companies added";
}

string PhoneBook::Associate (int p,int c)
{
	for (unsigned int i = 0;i < vContacts.size();i++)
	{
		if (vContacts[i].id == p)
		{
			for (unsigned int n = 0;n < vCompanies.size();n++)
			{
				if (vCompanies[n].id == c)
				{
					vContacts[i].vAssociateCompanies.push_back(vCompanies[n].id);
					vCompanies[n].vAssociateContacts.push_back(vContacts[i].id);
				}
				//else return "Company Id Not Found";
			}
			
		}
		//else return "Contact Id Not Found";
	}
	return "Contacts Associated";
}