//***************************************************************************************************************************
//PROGRAM HEADER
//
// Name: Gregory Prosper
// ID: gprosper
// Instructor: Dr. Bullard
// Class: Data Structures
// Term: Summer 2014
// Assignment #3 (STACK)
// Due Date: June 30, 2014
// Due Time: 11:59 PM
// Points: 100
//****************************************************************************************************************************

#include <iostream>
#include <string>
#include "stack.h"

using namespace std;

string inFix(string exp);

int main()
{

	string postFixExp;
	int operandCount = 0;
	int operatorCount = 0;

	cout<< "NOTE: Enter # for infix expression to stop.\n";
	while (postFixExp != "#")
	{
		cout<< "\nPostfix expression? ";
		getline(cin, postFixExp);

		//resets count
		operandCount = 0;
		operatorCount = 0;

		//counts operands and operators
		for (int i = 0; i < postFixExp.length(); i++)
		{
			if (postFixExp[i] == '+' || postFixExp[i] == '-' || postFixExp[i] == '/' || postFixExp[i] == '*')
			{
				operatorCount++;
			}
			else if (postFixExp[i] == ' ')
			{

			}
			else
			{
				operandCount++;
			}
		}


		if (operatorCount == operandCount - 1)
		{
			cout<< "Infix expression is " << inFix(postFixExp) << endl;
		}
		else if (operandCount > operatorCount)
		{
			cout<< "Too many operands and not enough operators\n";
		}
		else
		{
			cout<< "Too many operators and not enough operands\n";
		}
		
	}

	return 0;
}

////////////////////////////////////////////////////////////////////
// Name: InFix
// Pre-condition: User input was recieved and saved in string.
// Post-condition: String is converted to Infix.
// Description: String is converted to Infix.
///////////////////////////////////////////////////////////////
string inFix(string exp)
{
	char token;
	string s = "";
	string loper;
	string Roper;
	Stack stack;
	string inFixExp;

	for (int i = 0; i < exp.length(); i++)
	{
		token = exp[i];
		switch (token)
		{
		case ' ': // Ignores spaces
			break;

		case '+': case '-': case '/': case '*': // Pops right and left operand and adds to stack
			Roper = stack.pop();
			loper = stack.pop();

			s = "(" + loper + " " + token + " " + Roper + ")";
			stack.push(s);
			break;

		default:								// Adds operands to stack
			s = "";
			s += token;
			stack.push(s);
			break;
		}
	}
	inFixExp = stack.pop(); //Returns final string on stack.

	return inFixExp;
}