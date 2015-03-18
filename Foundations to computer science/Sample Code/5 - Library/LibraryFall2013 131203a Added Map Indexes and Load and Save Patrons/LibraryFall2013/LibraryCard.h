#pragma once



class LibraryCard
{
public:
	friend class Library;
	LibraryCard();
	int getId(){return id;}
private:
	int id;
	static int lastIdUsed;
	//Date experationDate;
};

