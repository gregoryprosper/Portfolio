#include<iostream>
#include <time.h>
#include "Die.h"

using namespace std;

int main()
{
	cout<<"Welcome to the Dice Experiment."<<endl;
	srand (time(NULL));
	Die d1,d2;
	double bigTotal=0.0;
	int count = 10000;
	for(int i=0;i<count;i++)
	{
		int total = d1.roll() + d2.roll();
		bigTotal+=total;
	}
	cout<<"Average was "<<bigTotal/count<<endl;
	return 0;
}


