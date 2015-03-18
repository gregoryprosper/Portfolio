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
		result+="\tNEWACCOUNT(NA)\n";
		result+="\tHELP(H)\n";
		result+="\tQUIT(Q)\n";
	}
	else if((token[0]=="QUIT")||(token[0]=="Q"))
	{
		result="Goodbye.";
	}
	else if((token[0]=="NEWACCOUNT")||(token[0]=="NA"))
	{
		result = bank.openNewAccount(token[1],stringToDouble(token[2]));
	}
	else
	{
		result="Unrecognized Command!!!";
	}
	return result;
}
