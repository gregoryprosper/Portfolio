<?php
require_once 'login.php';
$db_server = mysql_connect($db_hostname,$db_username,$db_password);

if(!$db_server) die("Unable to connect to MySQL: ".mysql_error());

mysql_select_db($db_database) or die("Unable to select database: ".mysql_error());

$make = isset($_REQUEST['options']) ? $_REQUEST['options'] : 'default';

$query = "SELECT * FROM Options";
$result = mysql_query($query);

if (!$result) die ("Database access failed: ".mysql_error());


$rows = mysql_num_rows($result);

echo "Please select your desired option(s): <br/><br/>";
for ($j = 0; $j < $rows; $j++)
{
	$carOption = mysql_result($result,$j,'Options');
	echo "<input type='checkbox' name='carOptions[]' value='$carOption' /> $carOption <br/>";
}
?>