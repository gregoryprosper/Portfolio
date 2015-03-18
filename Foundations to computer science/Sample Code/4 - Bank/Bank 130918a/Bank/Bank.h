#pragma once
#include <string>
using namespace std;



class Bank
{
public:
	string openNewAccount(string customerName,double startAmount);
	string closeAccount(string customerName);
	string transferToAccount(string customerName,double transferAmount);

private:
	//vector<Customer> vCustomer;
	//vector<Account> vAccount;
};