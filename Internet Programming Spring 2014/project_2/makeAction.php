<?php
require_once 'login.php';
$db_server = mysql_connect($db_hostname,$db_username,$db_password);

if(!$db_server) die("Unable to connect to MySQL: ".mysql_error());

mysql_select_db($db_database) or die("Unable to select database: ".mysql_error());

$make = isset($_REQUEST['make']) ? $_REQUEST['make'] : 'default';

$query = "SELECT * FROM Cars WHERE Make='$make'";
$result = mysql_query($query);

if (!$result) die ("Database access failed: ".mysql_error());


$rows = mysql_num_rows($result);

echo "<option value=''>&mdash; choose a Model &mdash;</option>";

for ($j = 0; $j < $rows; $j++)
{
	$model = mysql_result($result,$j,'Model');
	echo "<option value='$model')'> $model </option> . '<br/>";
}
?>