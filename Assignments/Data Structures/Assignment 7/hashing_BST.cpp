//***************************************************************************************************************************
//PROGRAM HEADER
//
// Name: Gregory Prosper
// ID: gprosper
// Instructor: Dr. Bullard
// Class: Data Structures
// Term: Summer 2014
// Assignment #7 (BST)
// Due Date: August 1, 2014
// Due Time: 11:59 PM
// Points: 100
//****************************************************************************************************************************


#include <iostream>
#include <fstream>
#include <string>

using namespace std;


class BST_Node  
{
  public:
   string lastname, firstname, address, phone_number;
   BST_Node  *lchild, *rchild;   //pointer information
};

class Clients_Info_BST //BST
{
   public:
	  Clients_Info_BST();//store the data in the hash table
	 ~Clients_Info_BST();//destructor
	  void Insert(string);
	  void Insert_aux(BST_Node * &,string);
	  void Remove(const string &);
	  void Remove_aux(BST_Node * &,const string &);
	  BST_Node* Inorder_succ(BST_Node * &);
	  void Update(string name, string address, string phone);
	  void Print();
	  void Print_aux(BST_Node * &);
	  void Print_Hash_Table_to_File(ofstream &);
	  void Print_Hash_Table_to_File_aux(BST_Node * &, ofstream &);
	  string padRight(string s,char fill,  unsigned int size);
	  string getAddress(string s);
	  string getPhoneNumber(string s);

    private:
       BST_Node *root; //---state information
};

 


class Client_Address_Book
{
    public:
	 Client_Address_Book();//default constructor will read data from input file "client_address_data.txt".
	 void Insert(const string & s);
	 void Remove(const string & s);
	 void Update(const string & s);
	 void Print_BST(const string & s);
	 void Print_Hash_Table();
	 void Print_Hash_Table_to_File(const string filename);//function will print hash table to  output file "sorted_client_address_bk.txt".
     int Hash_Function(const string & s);

  private:
     Clients_Info_BST   hash_table[26];
};


//Clients_Info_BST/////////////////////////////////////Clients_Info_BST///////////////////////////////////////Clients_Info_BST/////////////////////////////////

////////////////////////////////////////////////////////
// Name: Default Constructor
// Pre-condition: Client Info BST has not been initialized.
// Post-condition: Client Info BST is initialized.
// Description: Creates instance of Client Info BST. 
////////////////////////////////////////////////////////
Clients_Info_BST::Clients_Info_BST()
{
	root = 0;
}

////////////////////////////////////////////////////////
// Name: Destructor
// Pre-condition: Memory for Client Info BST has already been alocated.
// Post-condition: Memory for Client Info BST is de-alocated.
// Description: Client Info BST is deleted.
////////////////////////////////////////////////////////
Clients_Info_BST::~Clients_Info_BST()
{
	static int count = 0;

	while (root != 0)
	{
		cout<<root->lastname + ' ' + root->firstname <<endl;
		count++;
		Remove(root->lastname + ' ' + root->firstname);
	}
	cout<<"Count: "<<count <<endl;
}

//////////////////////////////////////////////////////
//Name: Print
//Pre-condition: Client Info BST has been initialized.
//Post-condition: BST items are printed.
//Description: BST items are printed.
//////////////////////////////////////////////////////
void Clients_Info_BST::Print()
{
	Print_aux(root);
}

//////////////////////////////////////////////////////
//Name: Print Aux
//Pre-condition: Client Info BST has been initialized.
//Post-condition: BST items are printed.
//Description: BST items are printed.
//////////////////////////////////////////////////////
void Clients_Info_BST::Print_aux(BST_Node * & ptr)
{
	static bool columns = false; //bool is set too true when column title is printed

	if(root == 0)
	{
		cout<<"BST is Empty \n";
	}
	else
	{
		if (columns == false) //Makes sure Column titles are printed once
		{
			cout<<"Last Name      First Name        Address                         Phone Number\n";
			cout<<"-----------------------------------------------------------------------------\n";
		}
		columns = true;

		if(ptr != 0)
		{
			Print_aux(ptr->lchild);
			cout<< padRight(ptr->lastname,' ',15) << padRight(ptr->firstname,' ',18) << padRight(ptr->address,' ',32) << padRight(ptr->phone_number,' ',10) <<endl;
			Print_aux(ptr->rchild);
		}
	}
}

////////////////////////////////////////////////////////
// Name: Print Hash Table to File
// Pre-condition: Client Info BST has been initialized.
// Post-condition: BST items are printed to a file.
// Description: BST items are printed to a file.
////////////////////////////////////////////////////////
void Clients_Info_BST::Print_Hash_Table_to_File(ofstream & file)
{
	Print_Hash_Table_to_File_aux(root,file);
}

////////////////////////////////////////////////////////
// Name: Print Hash Table to File Aux
// Pre-condition: Client Info BST has been initialized.
// Post-condition: BST items are printed to a file.
// Description: BST items are printed to a file.
////////////////////////////////////////////////////////
void Clients_Info_BST::Print_Hash_Table_to_File_aux(BST_Node * & ptr, ofstream & file)
{

	static bool fileColumns = false; //bool is set too true when column title is printed

	if(root == 0)
	{
		file<<"BST is Empty \n";
	}
	else
	{
		if (fileColumns == false) //Makes sure Column titles are printed once
		{
			file<<"Last Name      First Name     Address                       Phone Number\n";
			file<<"------------------------------------------------------------------------\n";
		}
		fileColumns = true;
		if(ptr != 0)
		{
			Print_Hash_Table_to_File_aux(ptr->lchild,file);
			file<< padRight(ptr->lastname,' ',15) << padRight(ptr->firstname,' ',15) << padRight(ptr->address,' ',30) << padRight(ptr->phone_number,' ',10) <<endl;
			Print_Hash_Table_to_File_aux(ptr->rchild,file);
		}
	}

}

////////////////////////////////////////////////////////
// Name: Insert
// Pre-condition: Client Info BST Has been initialized.
// Post-condition: Client is added to BST.
// Description: Takes client info and adds it to BST alphabetically.
////////////////////////////////////////////////////////
void Clients_Info_BST::Insert(string s)
{
	Insert_aux(root,s);
}

////////////////////////////////////////////////////////
// Name: Insert Aux
// Pre-condition: Client Info BST Has been initialized.
// Post-condition: Client is added to BST.
// Description: Takes client info and adds it to BST alphabetically.
////////////////////////////////////////////////////////
void Clients_Info_BST::Insert_aux(BST_Node * & ptr, string s)
{
	BST_Node *newNode = new BST_Node;
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
	if (ptr == 0)
	{
		ptr = newNode;
	}
	else if(ptr->lastname + ptr->firstname > newNode->lastname + newNode->firstname)
		Insert_aux(ptr->lchild,s);
	else if(ptr->lastname + ptr->firstname < newNode->lastname + newNode->firstname)
		Insert_aux(ptr->rchild,s);
	else
		cout<<"Contact already Exist";

	
}

////////////////////////////////////////////////////////
// Name: Remove
// Pre-condition: Client Info BST is initilized and Contains items.
// Post-condition: Removes first occurance of the Client in the BST.
// Description: Removes first occurance of the Client in the BST.
////////////////////////////////////////////////////////
void Clients_Info_BST::Remove(const string & s)
{
	Remove_aux(root,s);
}

////////////////////////////////////////////////////////
// Name: Remove Aux
// Pre-condition: Client Info BST is initilized and Contains items.
// Post-condition: Removes first occurance of the Client in the BST.
// Description: Removes first occurance of the Client in the BST.
////////////////////////////////////////////////////////
void Clients_Info_BST::Remove_aux(BST_Node * & ptr,const string & s)
{
	if (ptr == 0)
	{
		cout<<"Can't Remove, Contact is not in Address Book\n";
	}
	else if (ptr->lastname + ' ' + ptr->firstname > s)
	{
		Remove_aux(ptr->lchild,s);
	}
	else if (ptr->lastname + ' ' + ptr->firstname < s)
	{
		Remove_aux(ptr->rchild,s);
	}
	else
	{
		BST_Node * p;

		if (ptr->lchild == 0)
		{
			p = ptr->rchild;
			delete ptr;
			ptr = p;
		}
		else if (ptr->rchild == 0)
		{
			p = ptr->lchild;
			delete ptr;
			ptr = p;
		}
		else
		{
			p = Inorder_succ(ptr);

			ptr->lastname = p->lastname;
			ptr->firstname = p->firstname;
			ptr->address = p->address;
			ptr->phone_number = p->phone_number;

			Remove_aux(ptr->rchild,p->lastname + ' ' + p->firstname);
		}
	}
}

////////////////////////////////////////////////////////
// Name: Inorder Succ
// Pre-condition: Client Info BST is initilized and Contains items.
// Post-condition: Inorder Successor node is returned
// Description: Inorder Successor node is returned
////////////////////////////////////////////////////////
BST_Node* Clients_Info_BST::Inorder_succ(BST_Node * & ptr)
{
	BST_Node *p = ptr->rchild;

	while (p->lchild != 0)
	{
		p = p->lchild;
	}
	return p;
}

////////////////////////////////////////////////////////
// Name: Get Address
// Pre-Condition: Client Info BST is initilized and Contains items.
// Post-Condition: Address of desired Client is returned.
// Description: Address of desired Client is returned.
////////////////////////////////////////////////////////
string Clients_Info_BST::getAddress(string s)
{
	BST_Node *ptr = root;

	while (ptr->lastname + ' ' + ptr->firstname != s)
	{
		if (ptr->lastname + ' ' + ptr->firstname > s)
			ptr = ptr->lchild;
		else
			ptr = ptr->rchild;
	}

	return ptr->address;
}

////////////////////////////////////////////////////////
// Name: Get Phone Number
// Pre-Condition: Client Info BST is initilized and Contains items.
// Post-Condition: Phone Number of desired Client is returned.
// Description: Phone Number of desired Client is returned.
////////////////////////////////////////////////////////
string Clients_Info_BST::getPhoneNumber(string s)
{
	BST_Node *ptr = root;

	while (ptr->lastname + ' ' + ptr->firstname != s)
	{
		if (ptr->lastname + ' ' + ptr->firstname > s)
			ptr = ptr->lchild;
		else
			ptr = ptr->rchild;
	}

	return ptr->phone_number;
}

////////////////////////////////////////////////////////
// Name: Update
// Pre-Condition: Client Info BST is initilized and Contains items.
// Post-Condition: Client Info is updated with info given.
// Description: Client Info is updated with info given.
////////////////////////////////////////////////////////
void Clients_Info_BST::Update(string name, string address, string phone)
{
	BST_Node *ptr = root;

	while (ptr->lastname + ptr->firstname != name)
	{
		if (ptr->lastname + ptr->firstname > name)
		{
			ptr = ptr->lchild;
		}
		else if (ptr->lastname + ptr->firstname < name)
		{
			ptr = ptr->rchild;
		}
	}

	if (address != " ")
	{
		ptr->address = address;
	}
	if (phone != " ")
	{
		ptr->phone_number = phone;
	}
}

////////////////////////////////////////////////////////
// Name: Pad Right
// Pre-Condition: Client Address Book Has been initialized.
// Post-Condition: Entire Client Info BST is printed.
// Description: Entire Client Info BST is printed.
////////////////////////////////////////////////////////
string Clients_Info_BST::padRight(string s,char fill,  unsigned int size)
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
// Post-condition: Hash table's Client BST insert function
//				   is called with client info passed in.
// Description: Hash table's Client BST insert function
//				is called with client info passed in.
////////////////////////////////////////////////////////
void Client_Address_Book::Print_BST(const string & s)
{
	int index = Hash_Function(s);
	hash_table[index].Print();
}

////////////////////////////////////////////////////////
// Name: Print BST
// Pre-Condition: Client Address Book Has been initialized.
// Post-Condition: Client Info BST is printed by first letter given.
// Description: Client Info BST is printed by first letter given.
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
		default:return -1;
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
// Name: Print Hash Table to File
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
// Post-condition: Hash table's Client BST Remove function
//				   is called with client info passed in.
// Description: Hash table's Client BST Remove function
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

	//My_Book.Insert("Bullard Lofton 777 Glades Road 207-2780");
	//My_Book.Remove("Bullard Lofton");
	//My_Book.Update("7 Bullard Lofton 899-9018");
	//My_Book.Print_BST("B");
	//My_Book.Print_Hash_Table();
	//My_Book.Print_Hash_Table_to_File("sorted_client_data.txt");
	return 0;
}