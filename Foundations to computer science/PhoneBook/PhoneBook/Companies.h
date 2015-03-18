#pragma once
#include <string>
#include <vector>

using namespace std;


class Company
{
public:
	bool lessThan(Company otherCompany); 
	bool operator <(Company otherCompany);

	friend class PhoneBook;
	friend bool compareCatCP(Company &,Company &);

	void assignNewId (){lastCompanyId++; id = lastCompanyId;}
private:
	unsigned int id;
	static int lastCompanyId;
	string name;
	string address;
	string category;
	vector<int> vAssociateContacts;
};