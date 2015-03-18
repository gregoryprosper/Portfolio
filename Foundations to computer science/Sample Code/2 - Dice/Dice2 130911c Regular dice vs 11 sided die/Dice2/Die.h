#pragma once
#include <vector>
using namespace std;


class Die
{
public:
	Die();
	Die(int s);
	Die(vector<int> v);
	int roll();
	int value();
private:
	void init();
	int sides;
	int x;
    vector<int> sideValue;
};