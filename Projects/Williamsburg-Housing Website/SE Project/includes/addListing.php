<?php
    session_start();
    $username = $_SESSION['username'];
    require_once 'login.php';
    include_once 'misc.php';
    
    $mysqli = new mysqli($db_hostname,$db_username,$db_password,$db_database);
    
    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }
    
    $path = "error"; //Variable that holds path of profile pic if it was uploaded
    $valid_formats = array("jpg", "png", "gif", "jpeg");
    
    if (isset($_FILES['uploadFile'])) {

        // Pick a file extension 
        $ext = strtolower(pathinfo($_FILES["uploadFile"]['name'], PATHINFO_EXTENSION));
		
        if (in_array($ext,$valid_formats)){
            $target_dir = "../listingUploads/";
            $target_dir = $target_dir . time(). rand(1,999999) . $_SERVER['REMOTE_ADDR'] .'.'. $ext;

            //Uploads
            if (move_uploaded_file($_FILES["uploadFile"]["tmp_name"], $target_dir)) {
                    echo "The file ". basename( $_FILES["uploadFile"]["name"]). " has been uploaded.";
                    $path = $target_dir;
            }
        }
    }
    
    $street =  $_POST["street"];
    $city =  $_POST["city"];
    $state =  $_POST["state"];
    $zipCode =  $_POST["zipCode"];
    $beds =  $_POST["beds"];
    $baths =  $_POST["baths"];
    $size = str_replace(',','',$_POST["size"]);
    $price =  str_replace(',','',$_POST["price"]);
    $description = addslashes($_POST["description"]);
    $typeOfHome = $_POST['typeOfHome'];
    
    if (isset($_POST["forRent"])) {
        $forRent = 1;
    }
    else{
        $forRent = 0;
    }
    
    if (isset($_POST["petsAllowed"])) {
        $petsAllowed = 1;
    }
    else{
        $petsAllowed = 0;
    }
    
    $sql = "SELECT id FROM Realtors WHERE username = '$username'";
    $result = $mysqli->query($sql);
    $user = $result->fetch_object();
    $userId = $user->id;
    
    $query = "INSERT INTO Listings(Street, City, State, zipCode, Beds, Baths, Size, Price, For_Rent_Bool, Pets_Allowed_Bool, Description, Type_of_Home, Posted_By, cover) VALUES "
            . "('$street','$city','$state','$zipCode','$beds','$baths','$size','$price','$forRent','$petsAllowed','$description','$typeOfHome','$userId','$path')";
    
    if ($mysqli->query($query) === TRUE) {
        echo "New Listing created successfully";
		
        $postId = $mysqli->insert_id; //Gets id of Listing that was added

        $tableName = "Listing".$postId;
        $createTableQuery = "CREATE TABLE $tableName (
                                                        id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                                        directory VARCHAR(200) NOT NULL)";

        $mysqli->query($createTableQuery);

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
        header('location:../manageListings.php');

    }else {
        echo "Error: " . $query . "<br>" . $mysqli->error;
    }

?>