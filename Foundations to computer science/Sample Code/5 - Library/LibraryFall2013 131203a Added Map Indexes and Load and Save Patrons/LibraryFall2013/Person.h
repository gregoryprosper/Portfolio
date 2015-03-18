#pragma once
#include <string>
using namespace std;


class Person
{
public:
	string getName(){return name;}
	void setName(string s){name=s;}
	string getPhoneNumber(){return phoneNumber;}
	void setPhoneNumber(string s){phoneNumber=s;}
	string getAddress(){return address;}
	void setAddress(string s){address=s;}
private:
	string name;
	string phoneNumber;
	string address;
};



//class Person
//{
//
//protected:
//	string name;
//	string phoneNumber;
//	string address;
//};






