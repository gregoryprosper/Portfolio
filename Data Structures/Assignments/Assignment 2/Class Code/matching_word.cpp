

/*Consider the following:

front1 and front 2 are pointers to the front of a singly-linked list without a header noder.  The list pointed to by front1 
has a corresponding back pointer to the back of its list called "back1"; front2 also has a corresponding back pointer to the 
back of its list called "back2". The initial contents of each list are as follows:

front1->A->B->C->D->1->2->3->A->B->C->A->2->3->4

front2->C->D

*/

#include <iostream>
#include <string>

using namespace std;

class character
{
public:
	char symbol;
	character *next;
};

/*
Question 00:  Answer the following:
	a. What are the precondition(s)?
	b. What are the postcondition(s)?
	c. Write a description.
	d. How will this help you implement WORD?
*/
string Match(character *front1, character *front2)
{
	character *p = front1, *pb = front1; //comment
	character *q = front2; //comment
	character *k= front1; //comment
	bool found = false; //comment
	string s="false"; //comment

	//Q1: What is the purpose of this loop?
	while(p!=0 && !found)
	{
		k = pb;//Q2: What is the purpose of k, pb and p?  Be specify
		pb = p;

		while (q!=0)//Q3: What is the purpose of this loop?
		{
			if (q->symbol == p->symbol)//Q4: What is going on here? Represent in a diagram.
			{
				s+=q->symbol;
				s+=p->symbol;
				q=q->next;//Q5: What is the purpose of this statement?
				p=p->next;//Q6: What is the purpose of this statement?
			}
			else
			{
				s+=q->symbol;
				break;//Q7: What is the purpose of this statement?
			}

		}//while (q!=0)

		if (q == 0)//Q8: What is the purpose of this statement?
		{
			found = true;
			s=s+"true";
		}
		else
		{
			s+=pb->symbol;
			p = pb->next;//Q9:  What is the purpose of this statement?
			q = front2;//Q10:  What is the purpose of this statement?
		}
	}//while(p!=0 && !found)

	if (found == true)  //Q11: What do you think is the purpose of the following if/else statement?
	{
		s=s+"true";
    }
    else
    {
		s=s+"false";
    }
	
	return s;
}


/*
Question 01:  Answer the following:
	a. What are the precondition(s)?
	b. What are the postcondition(s)?
	c. Write a description.
	d. How will this help you implement WORD?
*/
void Insert(character * & front, character * & back, char item)
{

	//Q12: What is the purpose of the next three statements?
	character *p = new character;
	p->symbol = item;
	p->next = 0;

	//Q13: Whis is the purpose of the following if/else statement?
	if (front == 0)
	{
		front = back = p;
	}
	else
	{
		back->next = p;
		back = p;
	}
}


/*
Question 02:  Answer the following:
	a. What are the precondition(s)?
	b. What are the postcondition(s)?
	c. Write a description.
*/
void Print(character *front) //Q14: How is front passed?  
                             //Q15: What does this mean?
{
	while(front!=0)
	{
		cout<<front->symbol;
		front=front->next;
	}
	cout<<endl;
}


int main()
{
	character *front1=0, *back1=0; //comment
	character *front2=0, *back2=0; //comment

	Insert(front1,back1,'A'); //comment
	Insert(front1,back1,'B');
	Insert(front1,back1,'C');
	Insert(front1,back1,'D');
	Insert(front1,back1,'1');
	Insert(front1,back1,'2');
	Insert(front1,back1,'3');
	Insert(front1,back1,'A');
	Insert(front1,back1,'B');
	Insert(front1,back1,'C');
	Insert(front1,back1,'A');
	Insert(front1,back1,'2');
	Insert(front1,back1,'3');
	Insert(front1,back1,'4');
	Insert(front2,back2,'C'); //comment
	Insert(front2,back2,'D');

	cout<<"Printing front1"<<endl;
	Print(front1);
	cout<<endl<<"Printing front2"<<endl;
	Print(front2);
	cout<<endl<<Match(front1,front2)<<endl; //comment

	return 0;
}

