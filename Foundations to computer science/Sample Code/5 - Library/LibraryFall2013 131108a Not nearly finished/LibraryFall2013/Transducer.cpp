#include "Transducer.h"
#include <vector>
#include "misc.h"
using namespace std;


//ADD This is a string.

string Transducer::transduce(string command)
{
	try
	{
		string result;
		vector<string> token = tokenize(command);
		if((token[0]=="HELP")||(token[0]=="H"))
		{
			result="Commands:\n";
			result+="-------------------\n";
			result+="\tCHECKOUT(CO) <PatronID>;<BookID>\n";
			result+="-------------------\n";
			result+="\tADDPATRON(AP) <name>;<address>;<phone number>\n";
			result+="\tDISPLAYPATRONS(DP)\n";
			result+="\tADDRANDPATRONS(ARP) <count>\n";
			result+="-------------------\n";
			result+="\tADDBOOK(AB) <title>;<author>;<subject>\n";
			result+="\tSAVEBOOKS(SB) <filename>\n";
			result+="\tLOADBOOKS(LB) <filename>\n";
			result+="\tREMOVEBOOK(RB) <id>\n";
			result+="\tBOOKCOUNT(BC)\n";
			result+="\tDISPLAYBOOKS(DB)\n";
			result+="\tDISPLAYBOOKSBYNAME(DBN)\n";
			result+="\tDISPLAYBOOKSBYAUTHOR(DBA)\n";
			result+="\tCLEARBOOKS(CB)\n";
			result+="\tADDRANDBOOKS(ARB) <count>\n";
			result+="\tHELP(H)\n";
			result+="\tQUIT(Q)\n";
		}
		else if((token[0]=="ADDBOOK")||(token[0]=="AB"))
		{
			if(token.size()!=4)
			{
				throw (string)"ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.addBook(token[1],token[2],token[3]);
			}
		}
		else if((token[0]=="ADDPATRON")||(token[0]=="AP"))
		{
			if(token.size()!=4)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.addPatron(token[1],token[2],token[3]);
			}
		}
		else if((token[0]=="DISPLAYPATRON")||(token[0]=="DP"))
		{
			result=library.displayPatrons();
		}
		else if((token[0]=="CHECKOUT")||(token[0]=="CO"))
		{
			try
			{
				if(token.size()!=3)
				{
					throw "ERROR: Wrong Number of Parameters.";
				}
				else
				{
					result=library.checkOut(stringToInt(token[1]),stringToInt(token[2]));
				}
			}
			catch(double e)
			{
				throw "Error: "+intToString(e);
			}
		}
		else if((token[0]=="LOADBOOKS")||(token[0]=="LB"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.loadBooks(token[1]);
			}
		}
		else if((token[0]=="SAVEBOOKS")||(token[0]=="SB"))
		{

			if(token.size()==1)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result="";
				for(int i=1;i<token.size();i++)
				{
					result+=library.saveBooks(token[i])+"\n";
				}
			}
		}
		else if((token[0]=="REMOVEBOOK")||(token[0]=="RB"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.removeBook(stringToInt(token[1]));
			}
		}
		else if((token[0]=="ADDRANDBOOKS")||(token[0]=="ARB"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.addRandBook(stringToInt(token[1]));
			}
		}
		else if((token[0]=="ADDRANDPATRONS")||(token[0]=="ARP"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.addRandPatron(stringToInt(token[1]));
			}
		}
		else if((token[0]=="BOOKSUBJECTREPORT")||(token[0]=="BSR"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.bookSubjectReport(token[1]);
			}
		}
		else if((token[0]=="BOOKRANGEREPORT")||(token[0]=="BRR"))
		{
			if(token.size()!=3)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.bookRangeReport(stringToInt(token[1]),stringToInt(token[2]));
			}
		}
		else if((token[0]=="REMOVEBOOKSBYSUBJECT")||(token[0]=="RBS"))
		{
			if(token.size()!=2)
			{
				throw "ERROR: Wrong Number of Parameters.";
			}
			else
			{
				result=library.removeBooksBySubject(token[1]);
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
			throw "Unrecognized Command!!!";
		}
		return result;
	}
	catch (string es)
	{
		return  es;
	}
	catch (char * es)
	{
		return  es;
	}
	catch(int e)
	{
		switch(e)
		{
		case 999:
			return "ATTENTION INVALID ID!!!!!";
			break;
		default:
			return "Unknown Error Code!!!!!";
			break;
		}
	}
}



