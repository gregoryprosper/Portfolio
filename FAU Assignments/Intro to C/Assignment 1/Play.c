#define _CRT_SECURE_NO_DEPRECATE
#include <stdio.h>  // for input/output
#include <time.h>   // to use the internal system clock for random number generation
#include <stdlib.h> // to use the random number generator

#define MAXGUESSES 6 
#define MAXGAMES 20
#define MAXNUMBER 100

// this function will use a random number generator to create a file of numbers.
void MakeFile();
//this function runs one game. It repeats until the maximum number of
//guesses have been made. It stops executing when the user
//has either guessed the number correctly or has run out of guesses.
int Play(int answer);
//this function prompts the player to make a guess and returns that guess
//this function is called from the Play() function described above
int GetGuess();
//this function takes two arguments, the guess from the player and the 
//answer from the file. It lets the user know if the guess is too high, too 
//low or correct. The function returns 0 if not correct, 1 if correct.
int CompareGuess(int guess, int answer);
/* *************************************************************************** */

int main()
{

	int i;
	int gamesToPlay;
	int answer;
	int result;
	FILE*myFile;

	// *!* use printfs to display instructions about how to play
	printf("Welcome to Number Guess\n\n");
	printf("You need to enter the number of games to play (1-20).\n");
	printf("You have 6 chances to guess the number.\n\n");
	printf("Let's begin.\n\n");

	// *!* call the function that creates a file of random numbers
	MakeFile();

	// *!* open that file that was created (use the fopen statement)
	myFile = fopen("num.txt","r"); 
	// *!* ask the user for the number of games to play (up to 20)
	printf("How many games would you like play? (1-20):");
	scanf("%d", &gamesToPlay);
    // Play as many games as the user requested, which is stored in
	// the variable "gamesToPlay".
	for( i = 0; i < gamesToPlay; i++)
	{
		// *!* read one line from the file, which will be an integer
		// *!* save this integer. it will be the number you are trying to guess
		fscanf(myFile, "%d", &answer); 


		// *!* execute the function to play one game
		// *!* check the result of the function for a win or loss
		printf("\n\nLet's Play Game %d.\n", i + 1);
		printf("You have 6 chances to get the correct answer.\n\n");
		
		result = Play(answer);
		if (result == 0)
		{
			printf("You Lose! You were trying to guess %d\n", answer);
			printf("Game Over\n\nHope you had fun!!");

		}
		
	}
	
	// *!* close the file
	fclose(myFile);

	return 0;
}

/* *************************************************************************** */


/*Functions*/


//this function runs one game. It repeats until the maximum number of
//guesses have been made. It stops executing when the user
//has either guessed the number correctly or has run out of guesses.

int Play(int answer)
{	
	int numGuesses = 0;	
	int guess;
	int result;

	/* This do/while loop executes the code between braces over and over, as
	   long as numGuesses is less than MAXGUESSES.  It is a good thing that
	   we add 1 to numGuess each time, or the loop would never end! */
	do
	{	
		printf("Please enter a number between 1 - 100: ");
		// *!* ask the user to guess the number (call a function)
		guess = GetGuess();
		// *!* check if the guess was correct or not (call a function)
		// *!* NOTE: If the right number is guessed, you want to
		// *!*       print a message, and then return 1; (1 means win)
		result = CompareGuess(guess, answer);
		if (result == 1)
			{
				return 1;
			}
		
		numGuesses = numGuesses +1; // increases number of guesses made so far
	} 
	while( numGuesses < MAXGUESSES);

	// If we got this far, then the player ran out of guesses, and therefore
	// has lost the game. return 0 to mean loss (1 means win)
	return 0; 
}

// this function will use a random number generator to create a file of numbers.

void MakeFile()
{
	
	// *!* declare variables (Hint: see what variables are already used in this function)
	int i;
	int num;
	FILE*myFile;

	// *!* open file to save numbers 
	myFile = fopen("num.txt", "w");

	srand(time(NULL)); // "seeds" the number generator to randomize
	
	for(i = 0; i < MAXGAMES; i++)
	{
		// generate a random number between 1 and MAXNUMBER
		// assign that number to num
		num = rand() % 100 + 1; 
		
		// *!* write the number to the file
		fprintf(myFile, "%d\n", num);
	}
	// *!* close the file
	fclose(myFile);

	return;

}

//this function prompts the player to make a guess and returns that guess
//this function is called from the Play() function described above

int GetGuess()
{
	int guess;
	scanf("%d", &guess);
	return (guess);
}

//this function takes two arguments, the guess from the player and the 
//answer from the file. It lets the user know if the guess is too high, too 
//low or correct. The function returns 0 if not correct, 1 if correct.

int CompareGuess(int guess, int answer)
{
	if (guess == answer)
	{
		printf("\nGood job. You guessed it!!\nGame Over !");
		return 1;
	}
	else
		if(guess > answer)
		{
			printf("\nIncorrect. Your guess is too high.\n\n");
			return 0;
		}
		else
			if(guess < answer)
			{
				printf("\nIncorrect. Your guess is too low.\n\n");
				return 0;
			}
}
