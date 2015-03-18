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
		result+="\tADDBOOK(AB) <title>;<author>;<subject>\n";
		result+="\tSAVEBOOKS(SB) <filename>\n";
		result+="\tLOADBOOKS(LB) <filename>\n";
		result+="\tBOOKCOUNT(BC)\n";
		result+="\tDISPLAYBOOKS(DB)\n";
		result+="\tDISPLAYBOOKSBYNAME(DBN)\n";
		result+="\tDISPLAYBOOKSBYAUTHOR(DBA)\n";
		result+="\tCLEARBOOKS(CB)\n";
		result+="\tHELP(H)\n";
		result+="\tQUIT(Q)\n";
	}
	else if((token[0]=="ADDBOOK")||(token[0]=="AB"))
	{
		if(token.size()!=4)
		{
			result="ERROR: Wrong Number of Parameters.";
		}
		else
		{
			result=library.addBook(token[1],token[2],token[3]);
		}
	}
	else if((token[0]=="LOADBOOKS")||(token[0]=="LB"))
	{
		if(token.size()!=2)
		{
			result="ERROR: Wrong Number of Parameters.";
		}
		else
		{
			result=library.loadBooks(token[1]);
		}
	}
	else if((token[0]=="SAVEBOOKS")||(token[0]=="SB"))
	{
		if(token.size()!=2)
		{
			result="ERROR: Wrong Number of Parameters.";
		}
		else
		{
			result=library.saveBooks(token[1]);
		}
	}
	else if((token[0]=="CLEARBOOKS")||(token[0]=="CB"))
	{
		result=library.clearBooks();
	}
	else if((token[0]=="BOOKCOUNT")||(token[0]=="BC"))
	{
		result="There are "+intToString(library.bookCount())+" books in the library.";
	}
	else if((token[0]=="DISPLAYBOOKS")||(token[0]=="DB"))
	{
		result=library.display();
	}
	else if((token[0]=="DISPLAYBOOKSBYNAME")||(token[0]=="DBN"))
	{
		result=library.displayByName();
	}
	else if((token[0]=="DISPLAYBOOKSBYAUTHOR")||(token[0]=="DBA"))
	{
		result=library.displayByAuthor();
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



