#pragma once
#include <vector>
#include "card.h"
#include "hand.h"

using namespace std;



class Deck
{
public:

	Deck();
	
	void shuffle();

	Hand dealHand();

private:
	vector <Card> vCards;

};