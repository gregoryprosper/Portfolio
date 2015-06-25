#include "Transducer.h"
#include <vector>
#include "misc.h"
using namespace std;


//ADD This is a string.

string Transducer::transduce(string command)
{
	string result;
	vector<string> token = tokenize(command);
	if((token[0]=="HELP")||(token[0]=="H"))
	{
		result="Commands:\n";
		result+="-------------------\n";
		result+="\tADDCONTACT(AC) <Name>;<Phone#>;<Group>\n";
		result+="\tADDCOMPANY(ACP) <Name>;<Address>;<Catagory>\n";
		result+="\tREMOVECONTACT(RC) <Name>\n";
		result+="\tREMOVECOMPANY(RCP) <Name>\n";
		result+="\tSHOW(S)\n";
		result+="\tSHOWCOMPANIES(SC)\n";
		result+="\tSHOWCONTACTGROUP(SG)\n";
		result+="\tSHOWCOMPANIESCAT(SCC)\n";
		result+="\tCLEAR(C)\n";
		result+="\tCLEARCOMPANIES(CC)\n";
		result+="\tCOUNT(CT)\n";
		result+="\tCOMPANYCOUNT(CCT)\n";
		result+="\tSAVE(SA) <filename>\n";
		result+="\tLOAD(L) <filename>\n";
		result+="\tADDRANDPERSON(ARP) <COUNT>\n";
		result+="\tADDRANDCOMPANINIES(ARC) <COUNT>\n";
		result+="\tASSOCIATE(ASS) <person ID>;<company ID>\n";
		result+="\tHELP(H)\n";
		result+="\tQUIT(Q)\n";
		
	}
	else if((token[0]=="ADDCONTACT")||(token[0]=="AC"))
	{
		result = phoneBook.addContact(token[1],(token[2]),token[3]);
	}
	else if((token[0]=="ADDCOMPANY")||(token[0]=="ACP"))
	{
		result = phoneBook.addCompany(token[1],(token[2]),token[3]);
	}
	else if((token[0]=="REMOVECONTACT")||(token[0]=="RC"))
	{
		result = phoneBook.removeContact(token[1]);
	}
	else if((token[0]=="REMOVECOMPANY")||(token[0]=="RCP"))
	{
		result = phoneBook.removeCompany(token[1]);
	}
	else if((token[0]=="SHOW")||(token[0]=="S"))
	{
		result = phoneBook.showContacts();
	}
	else if((token[0]=="SHOWCOMPANIES")||(token[0]=="SC"))
	{
		result = phoneBook.showCompany();
	}
	else if((token[0]=="SHOWCONTACTGROUP")||(token[0]=="SG"))
	{
		result = phoneBook.showContactCat();
	}
	else if((token[0]=="SHOWCOMPANYCAT")||(token[0]=="SCC"))
	{
		result = phoneBook.showCompanyCat();
	}
	else if((token[0]=="CLEAR")||(token[0]=="C"))
	{
		result = phoneBook.clearContacts();
	}
	else if((token[0]=="CLEARCOMPANIES")||(token[0]=="CC"))
	{
		result = phoneBook.clearCompanies();
	}
	else if((token[0]=="COUNT")||(token[0]=="CT"))
	{
		result = "There are " + intToString(phoneBook.contactCount()) + " contacts in your phone book";
	}
	else if((token[0]=="COMPANIESCOUNT")||(token[0]=="CCT"))
	{
		result = "There are " + intToString(phoneBook.companyCount()) + " Companies in your phone book";
	}
	else if((token[0]=="SAVE")||(token[0]=="SA"))
	{
		result = phoneBook.savePhoneBook(token[1]);
	}
	else if((token[0]=="LOAD")||(token[0]=="L"))
	{
		result = phoneBook.loadPhoneBook(token[1]);
	}
	else if((token[0]=="ADDRANDPERSON")||(token[0]=="ARP"))
	{
		result = phoneBook.addRandPerson(stringToInt(token[1]));
	}
	else if((token[0]=="ADDRANDCOMPANIES")||(token[0]=="ARC"))
	{
		result = phoneBook.addRandCompany(stringToInt(token[1]));
	}
	else if((token[0]=="ASSOCIATE")||(token[0]=="ASS"))
	{
		result = phoneBook.Associate(stringToInt(token[1]),stringToInt(token[2]));
	}
	else if((token[0]=="QUIT")||(token[0]=="Q"))
	{
		result="Goodbye.";
	}
	else
	{
		result="Unrecognized Command!!!";
	}
	return result;
}
