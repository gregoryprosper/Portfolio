<?php

    require_once 'login.php';
    $tbl_name = "Realtors"; // Table name
    // Connect to server and select databse.
    $db = mysql_connect($db_hostname, $db_username, $db_password);
    if (!$db)
        die("Unable to connect to MySQL: " . mysql_error());
    mysql_select_db($db_database) or die("Unable to select database: " . mysql_error());

    // username and password sent from form
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT salt FROM $tbl_name WHERE username = '$username'";
    $result = mysql_query($sql);
    $row = mysql_fetch_array($result);
    $salt = $row['salt'];
    $password = sha1($salt . $password);

    $sql = "SELECT id FROM $tbl_name WHERE username = '$username' and BINARY password = '$password'";
    $result = mysql_query($sql);

    // Mysql_num_row is counting table row
    $count = mysql_num_rows($result);

    // If result matched $username and $password, table row must be 1 row

    if ($count == 1) {
        // Register $username, $password and redirect to file "login_success.php"
        session_start();
        $_SESSION["username"] = $username;
        echo "true";
    } else {
        echo "false";
    }
?>