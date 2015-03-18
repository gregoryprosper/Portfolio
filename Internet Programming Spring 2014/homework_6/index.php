<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>PHP Exercise</title>
</head>

<body>

<table>
<tr>
<td>
<form action="index.php" method="GET">
First Name: <br /><input type="text" name="firstName" /><br />
Last Name: <br /><input type="text" name="lastName" /><br />
Birthday: <br /><input type="text" name="birthday" placeholder="January 31" /><br /><br />
<input type="submit" />
</form>
</td>
<td >
<?php
if (isset($_GET['firstName']) && isset($_GET['lastName']) && isset($_GET['birthday']))
{
	$firstName = $_GET['firstName'];
	$lastName = $_GET['lastName'];
	$birthday = strtotime($_GET['birthday']);
	$days_placeholder;
	
	fix_names($firstName, $lastName);
	
	//Remaining days till birthday in seconds.
	$remaining = $birthday - time();
	
	//Converts the remaining from seconds to days.
	$days_remaining = floor($remaining/86400);
	
	//Checks if remaining days is negative and if so makes it positive.
	if ($days_remaining < 0)
	{
		$days_remaining = $days_remaining + 366;
	}
	
	//Checks if days should be plural or singular.
	if($days_remaining == 1)
	{
		$days_placeholder = "day";
	}
	else $days_placeholder = "days";
	
	//Checks if variables are empty and prints out info.
	if (!empty($firstName) && !empty($lastName) && !empty($birthday))
	{
		echo "Hello $firstName $lastName, your birthday is in $days_remaining 			$days_placeholder";
	}
	else echo "Fill all fields";
}

//Function that cleans up the names.
function fix_names(&$a1, &$a2)
	{
		$a1 = ucfirst(strtolower($a1));
		$a2 = ucfirst(strtolower($a2));
	}

?>
</td>
</tr>
</table>


</body>
</html>
