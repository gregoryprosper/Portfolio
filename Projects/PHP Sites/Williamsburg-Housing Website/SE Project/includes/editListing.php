<?php
    include 'misc.php';
    require 'login.php';
    session_start();
    $id = $_POST['id'];
    $valid_formats = array("jpg", "png", "gif", "jpeg");
    $tableName = "Listing".$id;

    //try connect to db
    $mysqli = new mysqli($db_hostname, $db_username, $db_password,$db_database);

    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }

    if (isset($_POST["street"]) && !empty($_POST["street"])) {
        $street =  addslashes($_POST["street"]);
        $sql = "UPDATE Listings SET Street = '$street' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["city"]) && !empty($_POST["city"])) {
        $city = addslashes($_POST["city"]);
        $sql = "UPDATE Listings SET City = '$city' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["state"]) && !empty($_POST["state"])) {
        $state = addslashes($_POST["state"]);
        $sql = "UPDATE Listings SET State = '$state' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["zipCode"]) && !empty($_POST["zipCode"])) {
        $zipCode =  $_POST["zipCode"];
        $sql = "UPDATE Listings SET zipCode = '$zipCode' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["beds"]) && !empty($_POST["beds"])) {
        $beds =  $_POST["beds"];
        $sql = "UPDATE Listings SET Beds = $beds WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["baths"]) && !empty($_POST["baths"])) {
        $baths =  $_POST["baths"];
        $sql = "UPDATE Listings SET Baths = $baths WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["size"]) && !empty($_POST["size"])) {
        $size =  $_POST["size"];
        $sql = "UPDATE Listings SET Size = $size WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["price"]) && !empty($_POST["price"])) {
        $price =  $_POST["price"];
        $sql = "UPDATE Listings SET Price = $price WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["forRent"])) {
        $sql = "UPDATE Listings SET For_Rent_Bool = 1 WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (!isset($_POST["forRent"])) {
        $sql = "UPDATE Listings SET For_Rent_Bool = 0 WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["petsAllowed"])) {
        $sql = "UPDATE Listings SET Pets_Allowed_Bool = 1 WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (!isset($_POST["petsAllowed"])) {
        $sql = "UPDATE Listings SET Pets_Allowed_Bool = 0 WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["description"])) {
        $description = addslashes($_POST['description']);
        $sql = "UPDATE Listings SET Description = '$description' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (isset($_POST["typeOfHome"])) {
        $typeOfHome = $_POST['typeOfHome'];
        $sql = "UPDATE Listings SET Type_of_Home = '$typeOfHome' WHERE id = $id";
        $mysqli->query($sql);
        $_SESSION['listingUpdated'] = 1;
    }

    if (is_uploaded_file($_FILES['uploadFile']['tmp_name'])) {
        $path = "error"; //Variable that holds path of profile pic if it was uploaded
        $ext = strtolower(pathinfo($_FILES["uploadFile"]['name'], PATHINFO_EXTENSION)); // Pick a file extension 

        if (in_array($ext,$valid_formats)){
            $q = "SELECT cover FROM Listings WHERE id = $id";
            $result = $mysqli->query($q);
            $obj = $result->fetch_object();
            $oldDir = $obj->cover;
            unlink($oldDir);

            $target_dir = "../listingUploads/";
            $target_dir = $target_dir . time(). rand(1,999999) . $_SERVER['REMOTE_ADDR'] .'.'. $ext;

            if (move_uploaded_file($_FILES["uploadFile"]["tmp_name"], $target_dir)) {
                echo "The file ". basename( $_FILES["uploadFile"]["name"]). " has been uploaded.";
                $path = $target_dir;
            }

            $sql = "UPDATE Listings SET cover = '$path' WHERE id = '$id'";
            $mysqli->query($sql);
            $_SESSION['listingUpdated'] = 1;
        }
    }

    if (isset($_POST['deleteCheckboxes'])) 
    {
        $photos = $_POST['deleteCheckboxes']; 

        foreach ($photos as $photo) {
            $getPhoto = "SELECT * FROM $tableName WHERE id = $photo";
            $result = $mysqli->query($getPhoto);
            $photoToBeDeleted = $result->fetch_object();

            unlink($photoToBeDeleted->directory);
            $sql = "DELETE FROM $tableName WHERE id = $photo";
            $mysqli->query($sql);
        }
    }


    foreach ($_FILES['uploadFiles']['name'] as $file => $name){
        // get a file extension 
        $ext = strtolower(pathinfo($_FILES["uploadFiles"]['name'][$file], PATHINFO_EXTENSION));
        if (in_array($ext,$valid_formats)){
            $target_dir = "../listingUploads/";
            $target_dir = $target_dir . time(). rand(1,900000) . $_SERVER['REMOTE_ADDR'] .'.'. $ext;

            if(move_uploaded_file($_FILES["uploadFiles"]["tmp_name"][$file], $target_dir)){
                $path = $target_dir;
                $insert = "INSERT INTO $tableName (directory) VALUES ('$path')";
                $mysqli->query($insert);
            }
        }
    }

    header("location:../editMyListing.php?id=$id")
?>

