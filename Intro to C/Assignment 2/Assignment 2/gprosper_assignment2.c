/*   File Name:                 gprosper_Assignment2.c

     Author:                    Gregory Prosper

     Course:                    Introduction to Programming in C (Spring 2013)

     Assignment:                Assignment #2

     Date:                      4/1/2013

*/


#include <stdio.h>

// Function that adds the sum of intergers up to selected number.
void sumIntergers ();
// This function asks the user to enter a series of dollar
// amounts, displays their sum.
void sumDoubles ();
// This function asks the user to enter a positive integer value, and
// calculates the factorial of that value.  
void calcFactorial ();


int main ()
{
	char option;
	int z = 1;


	printf("Welcome To Assignment #2\n");
	
	
	do
	{

		printf("\n\nPlease choose from the following options:\n\n");
		printf("1 - Sum of even integers\n");
		printf("2 - Sum of dollar amounts\n");
		printf("3 - Factorial of a number\n\n");
		printf("Q - Quit\n\n");

		printf("Enter your choice (1,2,3,Q):");
		scanf(" %c",&option);
		

		switch (option)
			{
			case '1':
				sumIntergers ();
				break;
			case '2':
				sumDoubles ();
				break;
			case '3':
				calcFactorial ();
				break;
			case 'Q':
				return 0;
				break;
			default:
				printf("\n\n***You entered an invalid choice!!!***");
			}

		printf("\n\nThank you for playing\n\n");
	}
	while (z == 1);
	

	return 0;
}

// Function that adds the sum of intergers up to selected number.
void sumIntergers ()
{
	int num = 0;
	int i;
	int sum = 0;
	
	printf("\n\nI want to sum up some even integers.\n\n");
	printf("Enter an integer:");
	scanf("%d",&num);

	while (num < 0)
	{
	printf("\n\n***Negative numbers are invalid!***\n\n");
	printf("Enter an integer:");
	scanf("%d",&num);
	}
	
	for (i = 0; i <= num; i++,i++)
	{
		sum += i;
	}

	printf("\nThe even numbers up to %d sum to %d",num,sum);

	return;
}

// This function asks the user to enter a series of dollar
// amounts, displays their sum.
void sumDoubles ()
{
	double dollar = 0;
	double total = 0;

	printf("\n\nI want to sum up some dollar amounts.\n\n");

	do
	{
		printf("Enter dollar amount (enter -1 to quit):");
		scanf("%lf",&dollar);
		if (dollar != -1)
		{
		total += dollar;
		printf(" The total so far is: $%.2f\n",total);
		}
		else
			printf("The sum of the dollar amounts is $%.2f.\n",total);

	}
	while (dollar != -1);

	return;
}

// This function asks the user to enter a positive integer value, and
// calculates the factorial of that value.
void calcFactorial ()
{
	int i;
	int x = 0;
	int choice;
	int factorial = 1;

	printf("\n\nI want to calculate the factorial of x.\n\n");

	

	do
	{

		printf("Enter a value for x:");
		scanf("%d",&choice);

		if (choice >= 0)
		{
			
			for (i = choice; i >= 1; i--)
				{
					factorial *= i;
				}

			printf("X! = %d",factorial);
		}
		else
			printf("\n\n***Negative numbers are invalid!***\n\n");

	}
	while (choice < 0);

	

	return;
}

