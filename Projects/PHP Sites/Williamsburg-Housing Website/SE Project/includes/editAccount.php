<?php
    include 'misc.php';
    require 'login.php';
    session_start();
    $username = $_SESSION['username'];
    $tbl_name="Realtors"; // Table name 

    //try connect to db
    $mysqli = new mysqli($db_hostname, $db_username, $db_password,$db_database);

    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }

    if (isset($_POST["firstname"]) && !empty($_POST["firstname"])) {
        $firstname =  addslashes($_POST["firstname"]);
        $sql = "UPDATE $tbl_name SET first_name = '$firstname' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (isset($_POST["lastname"]) && !empty($_POST["lastname"])) {
        $lastname =  addslashes($_POST["lastname"]);
        $sql = "UPDATE $tbl_name SET last_name = '$lastname' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (isset($_POST["phone"]) && !empty($_POST["phone"])) {
        $phone =  $_POST["phone"];
        $sql = "UPDATE $tbl_name SET phone = '$phone' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (isset($_POST["email"]) && !empty($_POST["email"])) {
        $email =  $_POST["email"];
        $sql = "UPDATE $tbl_name SET email = '$email' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (isset($_POST["experience"]) && !empty($_POST["experience"])) {
        $experience =  $_POST["experience"];
        $sql = "UPDATE $tbl_name SET experience = '$experience' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (isset($_POST["licence"]) && !empty($_POST["licence"])) {
        $licence =  $_POST["licence"];
        $sql = "UPDATE $tbl_name SET licence = '$licence' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }

    if (is_uploaded_file($_FILES['uploadFile']['tmp_name'])) {

        $q = "SELECT profilePic FROM $tbl_name WHERE username = '$username'";
        $result = $mysqli->query($q);
        $obj = $result->fetch_object();
        $oldDir = $obj->profilePic;
        unlink($oldDir);

        if (move_uploaded_file($_FILES["uploadFile"]["tmp_name"], $oldDir)) {
            echo "The file ". basename( $_FILES["uploadFile"]["name"]). " has been uploaded.";
        }

        $sql = "UPDATE $tbl_name SET profilePic = '$oldDir' WHERE username = '$username'";
        $mysqli->query($sql);
        $_SESSION['accountUpdated'] = 1;
    }


    header('location:../editMyAccount.php')
?>
