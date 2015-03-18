#pragma once
#include <vector>
#include "card.h"

using namespace std;



class Hand
{
public:
	
	void addCards(Card c);

	bool isFlush();

private:

	vector<Card> cardsInHand;

};