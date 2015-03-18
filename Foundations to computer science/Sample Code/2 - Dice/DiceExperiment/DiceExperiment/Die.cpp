#include"Die.h"
#include <stdlib.h>


int Die::roll()
{
	x=(rand()%6)+1;
	return x;
}

int Die::value()
{
	return x;
}
