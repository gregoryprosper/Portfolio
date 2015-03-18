<?php
    require_once 'login.php';
    
    $mysqli = new mysqli($db_hostname,$db_username,$db_password,$db_database);
    
    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }
    
    $id = $_POST['id'];
    
    $sql = "SELECT * FROM Listings WHERE id = '$id'";
    $result = $mysqli->query($sql);
    $property = $result->fetch_object();
    $coverPic = $property->cover;
    
    $sql = "DELETE FROM Listings WHERE id = '$id'";
    
    if ($mysqli->query($sql) === TRUE) {
        echo "Record deleted successfully";
        unlink($coverPic);
		
        $tableName = 'Listing'.$id;
        $getImages = "SELECT * FROM $tableName";
        $imageTable = $mysqli->query($getImages);

        while ($row = $imageTable->fetch_assoc()){
                unlink($row['directory']);
        }

        $deleteTableQuery = "DROP TABLE $tableName";
        $mysqli->query($deleteTableQuery);
		
    } else {
        echo "Error deleting record: " . $conn->error;
    }
    
    header('Location:../manageListings.php')
?>