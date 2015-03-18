#pragma once
#include "Contact.h"
#include "Companies.h"
#include <vector>
#include <string>

using namespace std;



class PhoneBook
{

public:
	PhoneBook();
	~PhoneBook();
	string addContact (string s, string n, string g);
	string addCompany (string n, string a, string c);
	string removeContact (string r);
	string removeCompany (string r);
	string showContacts ();
	string showCompany ();
	string showContactCat ();
	string showCompanyCat ();
	string Associate (int p,int c);
	string clearContacts(){vContacts.clear(); return "Contacts was cleared.";}
	string clearCompanies(){vCompanies.clear(); return "Companies was cleared.";}
	int contactCount(){return vContacts.size();}
	int companyCount(){return vCompanies.size();}
	string savePhoneBook (string filename);
	string loadPhoneBook (string filename);

	string addRandPerson (int count);
	string addRandCompany (int count);


private:
	vector<char> numbers;
	vector<Contact> vContacts;
	vector<Company> vCompanies;
};