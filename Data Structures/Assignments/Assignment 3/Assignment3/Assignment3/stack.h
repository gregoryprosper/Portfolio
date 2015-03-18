#include <string>
#pragma once

using namespace std;


class Stack_node
{
public:
	string data;
	Stack_node *next;
private:

};


class Stack
{
public:
	Stack(){s_top=0;}
	~Stack(void);
	string pop();
	void push(string);

private:
	Stack_node *s_top;
};

