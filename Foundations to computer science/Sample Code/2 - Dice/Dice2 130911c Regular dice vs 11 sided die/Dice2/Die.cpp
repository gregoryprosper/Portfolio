#include "Die.h"
#include <stdlib.h>
#include <iostream>
using namespace std;

Die::Die()
{
	sides=6;
	init();
}

Die::Die(int s)
{
	sides=s;
	init();
}


Die::Die(vector<int> v)
{
	sides=v.size();
	sideValue=v;
	roll();
}


void Die::init()
{
	for(int i=1;i<=sides;i++)
	{
		sideValue.push_back(i);
	}
	roll();
}

int Die::roll()
{
	int index = (rand()%sides);
	x=sideValue[index];
	return x;
}

int Die::value()
{
	return x;
}
