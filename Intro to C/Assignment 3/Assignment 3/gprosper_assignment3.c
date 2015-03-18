/*   File Name:                 gprosper_Assignment2.c

     Author:                    Gregory Prosper

     Course:                    Introduction to Programming in C (Spring 2013)

     Assignment:                Assignment #3

     Date:                      4/1/2013

*/


#include <stdio.h>

// Function that adds the sum of intergers up to selected number.
void sumIntergers (FILE*f);
// This function asks the user to enter a series of dollar
// amounts, displays their sum.
void sumDoubles (FILE*f);
// This function asks the user to enter a positive integer value, and
// calculates the factorial of that value.  
void calcFactorial (FILE*f);


int main ()
{
	char option;
	int z = 1;
	FILE *f = fopen("assignment3.txt","w");

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
				sumIntergers (f);
				break;
			case '2':
				sumDoubles (f);
				break;
			case '3':
				calcFactorial (f);
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
	
	fclose(f);

	return 0;
}

// Function that adds the sum of intergers up to selected number.
void sumIntergers (FILE*f)
{
	int num;
	int i;
	int j = 0;
	int sum = 0;
	int evenNumbers[100];


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
		j++;
		evenNumbers[j-1] = i;
	}


	for (i = 0; i <= (j-1); i++)
	{
		sum += evenNumbers[i];
	}

	printf("\nThe even numbers up to %d sum to %d",num,sum);

	///////////////////////////////////////////////////////////////////////

	fprintf(f,"You chose to sum the even integers from 0 to %d.\n\n",num);
	fprintf(f,"These are the even integers:\n\n");

	for (i = 1
		; i <= (j-1); i++)
	{
		fprintf(f,"%d\n",evenNumbers[i]);
	}

	fprintf(f,"The sum of the %d integers is: %d\n\n",j,sum);
	fprintf(f,"__________________________________________\n\n");

	return;
}

// This function asks the user to enter a series of dollar
// amounts, displays their sum.
void sumDoubles (FILE*f)
{
	int i = 0;
	int j;
	double dollar = 0;
	double total = 0;
	double dollarAmounts[100];

	printf("\n\nI want to sum up some dollar amounts.\n\n");

	do
	{
		printf("Enter dollar amount (enter -1 to quit):");
		scanf("%lf",&dollar);
		if (dollar != -1)
		{
		dollarAmounts[i] = dollar;
		i++;

		total += dollar;
		printf(" The total so far is: $%.2f\n",total);
		}
		else
		{
			total = 0; 

			for (j = 0; j <= (i - 1); j++)
			{
				total += dollarAmounts[j];
			}
			printf("The sum of the dollar amounts is $%.2f.\n",total);
		}

	}
	while (dollar != -1);

	//////////////////////////////////////////////////////////////////////

	fprintf(f,"You chose to sum a list of dollar amounts.\n\nThese are the dollar amounts:\n\n");

	for (j = 0; j <= (i - 1); j++)
	{
		fprintf(f,"%.2f\n",dollarAmounts[j]);
	}

	fprintf(f,"The sum of the %d dollar amounts is $%.2f\n\n",i,total);
	fprintf(f,"__________________________________________\n\n");

	return;
}

// This function asks the user to enter a positive integer value, and
// calculates the factorial of that value.
void calcFactorial (FILE*f)
{
	int i;
	int x = 0;
	int choice;
	int factorial = 1;
	int factors[100];

	printf("\n\nI want to calculate the factorial of x.\n\n");

	

	do
	{

		printf("Enter a value for x:");
		scanf("%d",&choice);

		if (choice >= 0)
		{
			
			for (i = choice; i >= 1; i--)
				{
					factors[i-1] = i;
				}

			printf("%d! = ",choice);
			printf("%d",choice);

			for (i = (choice - 1); i >= 1; i--)
			{
				printf("*%d",factors[i-1]);
			}

			for (i = choice; i >= 1; i--)
			{
				factorial *= i;
			
			}
			printf("\n\nThe factorial of %d is %d",choice,factorial);
		}
		else
			printf("\n\n***Negative numbers are invalid!***\n\n");

	}
	while (choice < 0);

	///////////////////////////////////////////////////////////////////////

	fprintf(f,"You chose to calculate the factorial of %d.\n\n",choice);

	fprintf(f,"%d! = ",choice);
	fprintf(f,"%d",choice);

	for (i = (choice - 1); i >= 1; i--)
		{
			fprintf(f,"*%d",factors[i-1]);
		}


	fprintf(f,"\n\nThe factorial of %d is %d\n\n",choice,factorial);
	fprintf(f,"__________________________________________\n\n");

	return;
}

