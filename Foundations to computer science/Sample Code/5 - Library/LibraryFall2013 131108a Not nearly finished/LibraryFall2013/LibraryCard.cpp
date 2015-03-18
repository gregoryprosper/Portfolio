
#include "LibraryCard.h"


int LibraryCard::lastIdUsed=19000;


LibraryCard::LibraryCard()
{
	lastIdUsed++;
	id=lastIdUsed;
}
