#include "Companies.h"

int Company::lastCompanyId = 0;

bool Company::operator <(Company otherCompany)
	{
		return name<otherCompany.name;
	}

bool compareCatCP(Company & c1,Company & c2)
{
	return c1.category<c2.category;
}