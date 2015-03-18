#include "deck.h"
#include "hand.h"
#include <time.h>
#include <iostream>

using namespace std;




int main ()
{
	srand((unsigned int)time(0));

	Deck deck;

	int count = 16000;
	int flushes = 0;



	for (int i =0; i <= count; i++)
	{
		deck.shuffle();
		Hand hand = deck.dealHand();

		if (hand.isFlush())flushes++;

	}
		 
	cout << "Percentage = ";
	cout << ((double)flushes / (double)count)*100 << endl;

	system ("pause");

	return 0;
}