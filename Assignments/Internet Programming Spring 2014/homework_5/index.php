<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>PHP Hello Guest</title>
</head>

<body>
<h1>
<?php
$names = array('John', 'Bobby','Jenny','Sarah','Tom');
$i = rand(0,4);
$name = $names[$i];
?>

<?php
echo "Hello $name";
?> Today is <?php echo date("l F j\, Y") ?>
</h1>
</body>
</html>
