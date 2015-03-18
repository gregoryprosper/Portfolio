#pragma once
using namespace std;



class Card
{
public:

	friend class Deck;
	friend class Hand;

private:

	int suit;
	int rank;

};