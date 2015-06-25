
#include "hand.h"
using namespace std;


void Hand::addCards(Card c)
{
	cardsInHand.push_back(c);
}


bool Hand::isFlush()
{
	return
		(
		(cardsInHand[0].suit == cardsInHand[1].suit)&&
		(cardsInHand[1].suit == cardsInHand[2].suit)&&
	   	(cardsInHand[2].suit == cardsInHand[3].suit)&&
		(cardsInHand[3].suit == cardsInHand[4].suit)
		);

}