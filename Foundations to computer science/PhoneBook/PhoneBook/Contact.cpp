#include "Contact.h"


int Contact::lastContactId = 0;

bool Contact::operator <(Contact otherContact)
	{
		return name<otherContact.name;
	}

bool compareCat(Contact & c1,Contact & c2)
{
	return c1.group<c2.group;
}