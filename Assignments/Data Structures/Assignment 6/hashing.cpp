//***************************************************************************************************************************
//PROGRAM HEADER
//
// Name: Gregory Prosper
// ID: gprosper
// Instructor: Dr. Bullard
// Class: Data Structures
// Term: Summer 2014
// Assignment #6 (HASHING)
// Due Date: July 18, 2014
// Due Time: 11:59 PM
// Points: 100
//****************************************************************************************************************************


#include <iostream>
#include <fstream>
#include <string>

using namespace std;


class Client_Node  //node in the doubly linked list
{
  public:
   string lastname, firstname, address, phone_number;
   Client_Node *prev, *next;   //pointer information
};

class Clients_Info_List //doubly linked list
{
   public:
	  Clients_Info_List();//store the data in the hash table
	  ~Clients_Info_List();//destructor
	  void Insert(const string & s);
	  void Remove(const string & s);
	  void Update(string name, string address, string phone);
	  void Print();
	  void Print_Hash_Table_to_File(ofstream & file);
	  string padRight(string s,char fill,  unsigned int size);
	  string getAddress(string s);
	  string getPhoneNumber(string s);

    private:
       Client_Node *front; //---state information
};

 


class Client_Address_Book
{
    public:
	 Client_Address_Book();//default constructor will read data from input file "client_address_data.txt".
	 void Insert(const string & s);
	 void Remove(const string & s);
	 void Update(const string & s);
	 void Print_List(const string & s);
	 void Print_Hash_Table();
	 void Print_Hash_Table_to_File(const string filename);//function will print hash table to  output file "sorted_client_address_bk.txt".
     int Hash_Function(const string & s);

  private:
     Clients_Info_List   hash_table[26];
};


//Clients_Info_List/////////////////////////////////////Clients_Info_List///////////////////////////////////////Clients_Info_List/////////////////////////////////

////////////////////////////////////////////////////////
// Name: Default Constructor
// Pre-condition: Client Info List has not been initialized.
// Post-condition: Client Info List is initialized.
// Description: Creates instance of Client Info List. 
////////////////////////////////////////////////////////
Clients_Info_List::Clients_Info_List()
{
	front = 0;
}

////////////////////////////////////////////////////////
// Name: Destructor
// Pre-condition: Memory for Client Info List has already been alocated.
// Post-condition: Memory for Client Info List is de-alocated.
// Description: Client Info List is deleted.
////////////////////////////////////////////////////////
Clients_Info_List::~Clients_Info_List()
{
	while(front != 0)
	{
		Client_Node *p = front;
		front = p->next;
		delete p;
	}
}

////////////////////////////////////////////////////////
// Name: Print
// Pre-condition: Client Info List has been initialized.
// Post-condition: List items are printed.
// Description: List items are printed.
////////////////////////////////////////////////////////
void Clients_Info_List::Print()
{
	static bool columns = false; //bool is set too true when column title is printed

	if(front == 0)
	{
		cout<<"List is Empty \n";
	}
	else
	{
		if (columns == false) //Makes sure Column titles are printed once
		{
			cout<<"Last Name      First Name        Address                         Phone Number\n";
			cout<<"-----------------------------------------------------------------------------\n";
		}
		columns = true;
		Client_Node *p = front;
		while(p != 0)
		{
			cout<< padRight(p->lastname,' ',15) << padRight(p->firstname,' ',18) << padRight(p->address,' ',32) << padRight(p->phone_number,' ',10) <<endl;
			p = p->next;
		}
	}
}

////////////////////////////////////////////////////////
// Name: Print Hash Table to File
// Pre-condition: Client Info List has been initialized.
// Post-condition: List items are printed to a file.
// Description: List items are printed to a file.
////////////////////////////////////////////////////////
void Clients_Info_List::Print_Hash_Table_to_File(ofstream & file)
{

	static bool fileColumns = false; //bool is set too true when column title is printed

	if(front == 0)
	{
		file<<"List is Empty \n";
	}
	else
	{
		if (fileColumns == false) //Makes sure Column titles are printed once
		{
			file<<"Last Name      First Name     Address                       Phone Number\n";
			file<<"------------------------------------------------------------------------\n";
		}
		fileColumns = true;
		Client_Node *p = front;
		while(p != 0)
		{
			file<< padRight(p->lastname,' ',15) << padRight(p->firstname,' ',15) << padRight(p->address,' ',30) << padRight(p->phone_number,' ',10) <<endl;
			p = p->next;
		}
	}

}

////////////////////////////////////////////////////////
// Name: Insert
// Pre-condition: Client Info List Has been initialized.
// Post-condition: Client is added to List.
// Description: Takes client info and adds it to List alphabetically.
////////////////////////////////////////////////////////
void Clients_Info_List::Insert(const string & s)
{
	Client_Node *newNode = new Client_Node;
	Client_Node *p = front;
	string data = s;
	int nameCount = 0;

	//Setting Info Fields
	for (int i = 0; i < data.length() - 1; i++)
	{

		if (data[i] == ' ' && nameCount == 0)
		{
			newNode->lastname = data.substr(0,i);
			data = data.substr(i+1,data.length()-1);
			i = 0;
			nameCount++;
		}
		
		if (data[i] == ' ' && nameCount == 1)
		{
			newNode->firstname = data.substr(0,i);
			data = data.substr(i+1,data.length()-1);
			i = 0;
			nameCount++;
			break;
		}
	}

	int phnStartPos = ((data.length()-1) - 7);
	newNode->phone_number = data.substr(phnStartPos,8);
	data = data.substr(0, data.length() - 9);
	newNode->address = data;


	//Creating Node
	if (front == 0)
	{
		front = newNode;
	}
	else 
	{
		while (p != 0)
		{
			if (p->lastname + p->firstname > newNode->lastname + newNode->firstname && p->prev == 0)
			{
				p->prev = newNode;
				newNode->next = p;
				front = newNode;
				break;
			}
			else if (p->lastname + p->firstname > newNode->lastname + newNode->firstname && p->next == 0)
			{
				p->prev->next = newNode;
				newNode->next = p;
				p->prev = newNode;
				break;
			}
			else if (p->lastname + p->firstname < newNode->lastname + newNode->firstname && p->next == 0)
			{
				p->next = newNode;
				newNode->prev = p;
				break;
			}
			else if (p->lastname + p->firstname > newNode->lastname + newNode->firstname)
			{
				newNode->next = p;
				p->prev->next = newNode;
				newNode->prev = p->prev;
				p->prev = newNode;
				break;
			}
			p = p->next;
		}
	
	}

	
}

////////////////////////////////////////////////////////
// Name: Remove
// Pre-condition: Client Info List is initilized and Contains items.
// Post-condition: Removes first occurance of the Client in the list.
// Description: Removes first occurance of the Client in the list.
////////////////////////////////////////////////////////
void Clients_Info_List::Remove(const string & s)
{
	if (front != 0)
	{
		Client_Node *p = front;

		while (p != 0)
		{
			if (p->lastname + ' ' + p->firstname == s && p->prev == 0)
			{
				Client_Node *z = p;
				front = z->next;
				front->prev = 0;
				delete z;
				break;
			}
			else if (p->lastname + ' ' + p->firstname == s && p->next == 0)
			{
				Client_Node *z = p;
				z->prev->next = 0;
				delete z;
				break;
			}
			else if (p->lastname + ' ' + p->firstname == s)
			{
				Client_Node *z = p;
				z->prev->next = z->next;
				z->next->prev = z->prev;
				delete z;
				break;
			}
			
			p = p->next;
		}
	}
	else cout<<"Nothing to remove, List is Empty";
	
}

////////////////////////////////////////////////////////
// Name: Get Address
// Pre-Condition: Client Info List is initilized and Contains items.
// Post-Condition: Address of desired Client is returned.
// Description: Address of desired Client is returned.
////////////////////////////////////////////////////////
string Clients_Info_List::getAddress(string s)
{
	if (front != 0)
	{
		Client_Node *p = front;

		while (p != 0)
		{
			if (p->lastname + ' ' + p->firstname == s)
			{
				return p->address;
			}
			p = p->next;
		}
	}
}

////////////////////////////////////////////////////////
// Name: Get Phone Number
// Pre-Condition: Client Info List is initilized and Contains items.
// Post-Condition: Phone Number of desired Client is returned.
// Description: Phone Number of desired Client is returned.
////////////////////////////////////////////////////////
string Clients_Info_List::getPhoneNumber(string s)
{
	if (front != 0)
	{
		Client_Node *p = front;

		while (p != 0)
		{
			if (p->lastname + ' ' + p->firstname == s)
			{
				return p->phone_number;
			}
			p = p->next;
		}
	}
}

////////////////////////////////////////////////////////
// Name: Update
// Pre-Condition: Client Info List is initilized and Contains items.
// Post-Condition: Client Info is updated with info given.
// Description: Client Info is updated with info given.
////////////////////////////////////////////////////////
void Clients_Info_List::Update(string name, string address, string phone)
{
	if (front != 0)
	{
		Client_Node *p = front;

		while (p != 0)
		{
			if (p->lastname + p->firstname == name)
			{
				if (address != " ")
				{
					p->address = address;
				}
				if (phone != " ")
				{
					p->phone_number = phone;
				}
			}
			p = p->next;
		}
	}
}

////////////////////////////////////////////////////////
// Name: Pad Right
// Pre-Condition: Client Address Book Has been initialized.
// Post-Condition: Entire Client Info List is printed.
// Description: Entire Client Info List is printed.
////////////////////////////////////////////////////////
string Clients_Info_List::padRight(string s,char fill,  unsigned int size)
{
	while(s.length()<size)
	{
		s= s + fill;
	}
	return s;
}
//////END/////////////////END///////////////////////////END///////////////////////////END////////////////////////////////END//////////////


//Client_Address_Book////////////////////////////////////Client_Address_Book//////////////////////////////////////////Client_Address_Book/

////////////////////////////////////////////////////////
// Name: Default Constructor
// Pre-condition: Client Address Book has not been initialized.
// Post-condition: Client Address Book is initialized.
// Description: Creates instance of Client Address Book. 
////////////////////////////////////////////////////////
Client_Address_Book::Client_Address_Book()
{
	ifstream file("client_address_data.txt");
	string str;

	while (getline(file,str))
	{
		Insert(str);
	}

}

////////////////////////////////////////////////////////
// Name: 
// Pre-Condition:
// Post-Condition:
// Description: 
////////////////////////////////////////////////////////
void Client_Address_Book::Insert(const string & s)
{
	int index = Hash_Function(s);
	hash_table[index].Insert(s);
}

////////////////////////////////////////////////////////
// Name: Insert
// Pre-condition: Client Address Book Has been initialized.
// Post-condition: Hash table's Client List insert function
//				   is called with client info passed in.
// Description: Hash table's Client List insert function
//				is called with client info passed in.
////////////////////////////////////////////////////////
void Client_Address_Book::Print_List(const string & s)
{
	int index = Hash_Function(s);
	hash_table[index].Print();
}

////////////////////////////////////////////////////////
// Name: Print List
// Pre-Condition: Client Address Book Has been initialized.
// Post-Condition: Client Info List is printed by first letter given.
// Description: Client Info List is printed by first letter given.
////////////////////////////////////////////////////////
int Client_Address_Book::Hash_Function(const string & s)
{
	switch (s[0])
	{
	case 'A': return 0;
	case 'B': return 1;
	case 'C': return 2;
	case 'D': return 3;
	case 'E': return 4;
	case 'F': return 5;
	case 'G': return 6;
	case 'H': return 7;
	case 'I': return 8;
	case 'J': return 9;
	case 'K': return 10;
	case 'L': return 11;
	case 'M': return 12;
	case 'N': return 13;
	case 'O': return 14;
	case 'P': return 15;
	case 'Q': return 16;
	case 'R': return 17;
	case 'S': return 18;
	case 'T': return 19;
	case 'U': return 20;
	case 'V': return 21;
	case 'W': return 22;
	case 'X': return 23;
	case 'Y': return 24;
	case 'Z': return 25;
	default:
		break;
	}
}

////////////////////////////////////////////////////////
// Name: Hash Function
// Pre-Condition: N/A
// Post-Condition: Number returned based on first letter of
//				   string passed in.
// Description: Number returned based on first letter of
//				string passed in.
////////////////////////////////////////////////////////
void Client_Address_Book::Print_Hash_Table()
{
	for (int i = 0; i <= 25; i++)
	{
		hash_table[i].Print();
	}
}

////////////////////////////////////////////////////////
// Name: Print Hast Table to File
// Pre-Condition: N/A
// Post-Condition: String is padded and returned.
// Description: String is padded and returned.
////////////////////////////////////////////////////////
void Client_Address_Book::Print_Hash_Table_to_File(const string filename)
{
	ofstream file;
	file.open(filename);

	for (int i = 0; i <= 25; i++)
	{
		hash_table[i].Print_Hash_Table_to_File(file);
	}

	file.close();
}

////////////////////////////////////////////////////////
// Name: Remove
// Pre-condition: Client Address Book Has been initialized.
// Post-condition: Hash table's Client List Remove function
//				   is called with client info passed in.
// Description: Hash table's Client List Remove function
//				is called with client info passed in.
////////////////////////////////////////////////////////
void Client_Address_Book::Remove(const string & s)
{
	hash_table[Hash_Function(s)].Remove(s);
}

////////////////////////////////////////////////////////
// Name: Update
// Pre-Condition: Client Address Book Has been initialized.
// Post-Condition: Client Info Update functioon is called with info 
//				   passed in.
// Description: Client Info Update functioon is called with info 
//				passed in.
////////////////////////////////////////////////////////
void Client_Address_Book::Update(const string & s)
{
	char option = s[0];
	string data = s;
	string lastNameToBeUpdated;
	string firstNameToBeUpdated;

	
	data = data.substr(2,data.length()); //Removes first number string
	int nameCount = 0;

	//Gets first and last names to be updated
	for (int i = 0; i < data.length() - 1; i++)
	{
		if (data[i] == ' ' && nameCount == 0)
		{
			lastNameToBeUpdated = data.substr(0,i);
			data = data.substr(i+1,data.length()-1);
			i = 0;
			nameCount++;
		}
		if (data[i] == ' ' && nameCount == 1)
		{
			firstNameToBeUpdated = data.substr(0,i);
			data = data.substr(i+1,data.length()-1);
			i = 0;
			nameCount++;
			break;
		}
	}

	//Variables that could be used in cases (Not everyone will be used in every case)
	string firstName;
	string lastName;
	string phone;
	string address;
	int phnStartPos = ((data.length()-1) - 7);

	switch (option)
	{
	case '1':
		hash_table[Hash_Function(lastNameToBeUpdated)].Remove(lastNameToBeUpdated + " " + firstNameToBeUpdated);
		hash_table[Hash_Function(data)].Insert(data);
		break;
	case '2':
		phone = hash_table[Hash_Function(lastNameToBeUpdated)].getPhoneNumber(lastNameToBeUpdated + ' ' + firstNameToBeUpdated);
		hash_table[Hash_Function(lastNameToBeUpdated)].Remove(lastNameToBeUpdated + " " + firstNameToBeUpdated);
		hash_table[Hash_Function(data)].Insert(data + ' ' + phone);
		break;
	case '3':
		phone = data.substr(phnStartPos,8);
		data = data.substr(0, data.length() - 9);
		address = data;
		hash_table[Hash_Function(lastNameToBeUpdated)].Update(lastNameToBeUpdated + firstNameToBeUpdated,address,phone);
		break;
	case '4':
		address = hash_table[Hash_Function(lastNameToBeUpdated)].getAddress(lastNameToBeUpdated + ' ' + firstNameToBeUpdated);
		phone = data.substr(phnStartPos,8);
		hash_table[Hash_Function(lastNameToBeUpdated)].Remove(lastNameToBeUpdated + " " + firstNameToBeUpdated);
		
		//Get first and last names
		nameCount = 0;
		for (int i = 0; i < data.length() - 1; i++)
		{
			if (data[i] == ' ' && nameCount == 0)
			{
				lastName = data.substr(0,i);
				data = data.substr(i+1,data.length()-1);
				i = 0;
				nameCount++;
			}
			if (data[i] == ' ' && nameCount == 1)
			{
				firstName = data.substr(0,i);
				data = data.substr(i+1,data.length()-1);
				i = 0;
				nameCount++;
				break;
			}
		}

		hash_table[Hash_Function(lastName)].Insert(lastName + ' ' + firstName + ' ' + address + ' ' + phone);
		break;
	case '5':
		address = hash_table[Hash_Function(lastNameToBeUpdated)].getAddress(lastNameToBeUpdated + ' ' + firstNameToBeUpdated);
		phone = hash_table[Hash_Function(lastNameToBeUpdated)].getPhoneNumber(lastNameToBeUpdated + ' ' + firstNameToBeUpdated);
		hash_table[Hash_Function(lastNameToBeUpdated)].Remove(lastNameToBeUpdated + " " + firstNameToBeUpdated);
		hash_table[Hash_Function(data)].Insert(data + ' ' + address + ' ' + phone);
		break;
	case '6':
		address = data;
		hash_table[Hash_Function(lastNameToBeUpdated)].Update(lastNameToBeUpdated + firstNameToBeUpdated,address," ");
		break;
	case '7':
		phone = data;
		hash_table[Hash_Function(lastNameToBeUpdated)].Update(lastNameToBeUpdated + firstNameToBeUpdated," ",phone);
		break;
	default:
		cout<<"Invalid Option\n";
		break;
	}

}
///////END///////////////////END////////////////////////////END///////////////////////////////////END//////////////////////////////


int main()
{
	Client_Address_Book My_Book;

	My_Book.Insert("Bullard Lofton 777 Glades Road 207-2780");
	//My_Book.Remove("Bullard Lofton");
	My_Book.Update("7 Bullard Lofton 908-9098");
	//My_Book.Print_List("B");
	My_Book.Print_Hash_Table();
	My_Book.Print_Hash_Table_to_File("sorted_client_address_bk.txt");
	return 0;
}