#pragma once



class LibraryCard
{
public:
	LibraryCard();
	int getId(){return id;}
private:
	int id;
	static int lastIdUsed;
	//Date experationDate;
};

