#include <iostream>
#include "Die.h"
#include <stdlib.h>
#include <time.h>

using namespace std;

int main()
{
	srand((unsigned int)time(NULL));
	cout<<"Welcome to the Dice Experiment."<<endl;
	Die d1;
	Die d2;

	// Set up the vector v for the weirdDie
	vector<int> v;
	for(int i=2;i<=12;i++)
	{
		v.push_back(i);
	}
	Die weirdDie(v);

	int count=10000;
	int p1Wins=0;
	int p2Wins=0;
	int ties=0;
	for(int i=0;i<count;i++)
	{
		int p1total = d1.roll() + d2.roll();
		int p2total = weirdDie.roll();
		if(p1total>p2total)
		{
			p1Wins++;
		}
		else if(p2total>p1total)
		{
			p2Wins++;
		}
		else
		{
			ties++;
		}
	}
	cout<<"Player one percentage = "<<((double)p1Wins/(double)count)*100.0<<endl;
	cout<<"Player two percentage = "<<((double)p2Wins/(double)count)*100.0<<endl;
	cout<<"Ties percentage       = "<<((double)ties/(double)count)*100.0<<endl;
	return 0;
}


