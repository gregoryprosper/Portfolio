#include "deck.h"
#include "card.h"
#include <algorithm>

using namespace std;



Deck::Deck()
{
	for (int i = 0; i <= 51; i++)
	{
		Card c;

		c.suit = i/13;
		c.rank = i%13;

		vCards.push_back(c);

	}

}

void Deck::shuffle()
{
	random_shuffle(vCards.begin(),vCards.end());
}

Hand Deck::dealHand()
{
	Hand result; 

	for (int i = 0; i <= 4; i++)
	{
		result.addCards(vCards[i]);
	}

	return result;
}