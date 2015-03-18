#include <iostream>
#include <string>

using namespace std;

#ifndef WORD_H
#define WORD_H

class CHARACTER
{
public:
	char data;
	CHARACTER *next;

};


class WORD
{
public:
	WORD();
	WORD(const WORD &);
	WORD(string s);
	~WORD();
	bool isEmpty(){return front == 0;};
	bool matchExist(const WORD &);
	int length(){return count;};
	void remove(const WORD &);
	void removeAll(const WORD &);
	void insertChar(char c);
	void insert(int position,const WORD &);
	CHARACTER* search(char c);
	void print();
	WORD & operator=(const WORD &);
	WORD & operator=(string s);
	void operator+(const WORD &);

	friend ostream & operator<<(ostream & out, WORD & org)
	{
		CHARACTER *p = org.front;
		while(p != 0)
		{
			cout<<p->data;
			p = p->next;
		}
		return out;
	}

private:
	CHARACTER *front,*back;
	int count;
};


#endif