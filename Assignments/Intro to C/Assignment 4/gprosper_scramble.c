/*   File Name:                 gprosper_scramble.c

     Author:                    Gregory Prosper

     Course:                    Introduction to Programming in C (Spring 2013)

     Assignment:                Assignment #4

     Date:                      4/25/2013

*/


#include <stdio.h>
#include <ctype.h>
#include <string.h>

//A function that locates and places the selected letter where it belongs in the partially solved array.
void instructions ();
//A function that plays one entire game
void play (int z);
//A function that returns the letter that the user has guessed.
char getLetter ();
//A function that sets all of the characters in a word to the same case (upper or lower).
void caseChange (char guessWord,int d);
//A function that locates and places the selected letter where it belongs in the partially solved array.
void solvedLetters (char wordToGuess,char partiallySolvedStars);
//A function that tells the user if they won or lost
int endGame (char guessWord,char scrambledWord,int guessesSoFar,int z);
//A function that determines if the player would like to play again. 
int playAgain ();

int main ()
{
	int z;
	int playAgainChoice;

	//Loops game to play for all nine words until user selects No to continuing.
	//Passes play function int z so next word can be used from the list.
	for(z = 0; z <= 9; z++)
	{
	play (z);

	//Ask user if they would like to play again.
	playAgainChoice = playAgain ();

		if (playAgainChoice == 0)
		{
		return 0;
		}
	}

	return 0;

}

//A function that plays one entire game
void play (int z)
{
	FILE *f = fopen("words.txt","r");
	int result;
	int numberPairs;
	char wordToBeGuessed[20][20];
	char scrambledWord[20][20];
	char partiallySolvedStars[1][20];
	char guessWord[1][20];
	int endValue;
	char letterPicked;
	char asterisk = '*';
	int guessesSoFar = 1;
	int i;
	int numberOfLetters;
	int d;

	instructions();

	numberPairs = 0;
	
	//scans words from txt file.
	do
	{
		result = fscanf(f,"%s\n",wordToBeGuessed[numberPairs]);
		result = fscanf(f,"%s\n",scrambledWord[numberPairs]);

		if (result != EOF)
		{
			numberPairs++;
		}
	}
	while (result != EOF);	

	//inputs asterisks in places where letters are to be placed.
	for (d = 0; wordToBeGuessed[z][d] !='\0'; d++)
	{
		partiallySolvedStars[0][d] = asterisk;
	}
	

	////////////////////////////////////////////////////////////////////////
	
	//d keeps count of how many letters are in the word to be guessed.
	d = d - 1;


	do
	{
	printf("======================================== GAME %d\n",z + 1);
	printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
	printf("~~PICK A LETTER\n\n");
	printf("Here is the scrambled word:\n\n");

	printf("\t%s\n\n",wordToBeGuessed[z]);
	
	printf("Here are the letters you have so far:\n\n\t");
	for (i = 0; i <= d; i++)
	{
	printf("%c",partiallySolvedStars[0][i]);
	}
	printf("\n");

	//Lets user input letter.
	printf("WHAT LETTER WOULD YOU LIKE TO PLACE?");
	letterPicked = getLetter();
	letterPicked = tolower(letterPicked);

	printf("You want to place the letter %c\n\n",letterPicked);

	printf("Here is the scrambled word again:\n\n");
	printf("\t%s\n\n",wordToBeGuessed[z]);

	//Places letters that match into proper places.
	solvedLetters(scrambledWord, partiallySolvedStars,z, letterPicked, d);

	//Shows partially solved word with guessed letters in the right places.
	printf("Here are the letters you have so far:\n\n\t");
	for (i = 0; i <= d; i++)
	{
	printf("%c",partiallySolvedStars[0][i]);
	}
	printf("\n\n");
	
	printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	printf("GUESS THE WORD\n\n");

	//User guesses the entire word.
	printf("GUESS THE WORD:");
	scanf("%s",guessWord[0]);

	//If user inputs any uppercase letters, this changes them to lowercase.
	caseChange (guessWord,d);
	
	//endGame function returns a value of 1 or 0 to endValue which is used to end loop
	//if user chooses right word before the run out of guesses.
	endValue = endGame (guessWord,scrambledWord,guessesSoFar,z);

	guessesSoFar++;

	}
	while (guessesSoFar <= 3 && endValue != 1);


	fclose(f);

	return;
}
//A function that determines if the player would like to play again. 
int playAgain ()
{
	char answer;

	printf("Would you like to play again (Y or N)?");
	scanf(" %c",&answer);
	answer = tolower(answer);

	if (answer == 'n')
	{
		return 0;
	}

}
//A function that sets all of the characters in a word to the same case (upper or lower).
void caseChange (char guessWord[1][20],int d)
{
	int i;

	for (i = 0; i <= d; i++)
	{
		guessWord[0][i] = tolower(guessWord[0][i]);
	}

}
//A function that tells the user if they won or lost
int endGame (char guessWord[1][20],char scrambledWord[20][20],int guessesSoFar,int z)
{
	if(strcmp(guessWord[0],scrambledWord[z]) == 0)
	{
		printf("The word was '%s'.\n\n",scrambledWord[z]);
		printf("Yay! - you won :)\n\n");

		return 1;
	}
	else
	{
		printf("\n\nSorry, '%s' is not the word.\n\n",guessWord[0]);
		printf("That was guess number %d!\n\n",guessesSoFar);

		if (guessesSoFar == 3)
		{
			printf("The word was '%s'.\n\n",scrambledWord[z]);
			printf("Sorry you did not win this round!\n\n");
			
			return 0;
		}

		
	}
}
//A function that returns the letter that the user has guessed.
char getLetter()
{
	char guess;
	scanf(" %c",&guess);

	return guess;
}
//A function that displays the instructions on how to play the game.
void instructions ()
{
	printf("Welcome to the SCRAMBLED game!\n\n");
	printf("You will see the word in scrambled form.\n\n");
	printf("Pick a letter and I will show you where the letter belongs in the word.\n\n");
	printf("...Next you guess the word...\n\n");
	printf("If you can't guess it, don't worry...You will have three\nguesses to figure it out!\n\n");
	printf("Let's begin!\n\n");

	return;
}
//A function that locates and places the selected letter where it belongs in the partially solved array.
void solvedLetters (char scrambledWord[20][20],char partiallySolvedStars[1][20],int z,char letterPicked,int d)
{
	int x;

	for (x = 0; x <= d; x++)
	{
		if(scrambledWord[z][x] == letterPicked)
		{
			partiallySolvedStars[0][x] = letterPicked;
		}

	}
	

}
