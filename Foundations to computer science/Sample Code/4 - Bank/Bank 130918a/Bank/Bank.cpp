#include "Bank.h"


string Bank::openNewAccount(string customerName,double startAmount)
{
	string result;
	if(customerName=="Tom Fernandez")
	{
		result="This guy is banned from banking with us!!!!!";
	}
	else
	{
		result="The account is opened.\n";
		// code to open a new account
	}
	return result;
}


string Bank::closeAccount(string customerName)
{
	string result;
	result="The account is closed.\n";



	return result;
}


string Bank::transferToAccount(string customerName,double transferAmount)
{
	string result;
	result="The transfer to the account is complete.\n";



	return result;
}
