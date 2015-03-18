#pragma once
#include <string>
#include <vector>

using namespace std;


class Contact
{
public:
	bool lessThan(Contact otherContact); 
	bool operator <(Contact otherContact);

	friend class PhoneBook;
	friend bool compareCat(Contact &,Contact &);

	void assignNewId (){lastContactId++; id = lastContactId;}
private:
	unsigned int id;
	static int lastContactId;
	string name;
	vector<char> number;
	string group;
	vector<int> vAssociateCompanies;
};