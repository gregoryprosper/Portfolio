<?php
    session_start();
    require_once 'includes/login.php';
    include_once 'includes/misc.php';
    
    if (!isset($_SESSION['username'])){
        header('Location:index.php');
    }
    
    $mysqli = new mysqli($db_hostname,$db_username,$db_password,$db_database);
    
    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }
    
    if (!isset($_SESSION['listingUpdated'])) {
        $_SESSION['listingUpdated'] = 0;
    }
    
    if (isset($_POST['id'])){
        $id = $_POST['id'];
    }
    
    if (isset($_GET['id'])){
        $id = $_GET['id'];
    }
    
    $sql = "SELECT * FROM Listings WHERE id = '$id'";
    $result = $mysqli->query($sql);
    $property = $result->fetch_object();
    
    $coverPic = substr($property->cover, 3);
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Listing</title>
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css" media="screen">
        <link rel="stylesheet" href="css/jquery-ui.min.css" media="screen">
        <link href="css/main.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <?php include 'includes/nav.php'; ?> <!--Nav bar inserted here-->
                <div class="container">
                    <div>
                        <h1 class="TextTooBigForMobile"><span class="glyphicon glyphicon-pencil"></span> Edit Listing</h1>
                        <hr>
                    </div>
                    <div>
                        <div>
                            <?php
                                if ($_SESSION['listingUpdated'] == 1) {
                                    echo "<h4 style='text-align:center;'> Listing Updated </h4><br>";
                                    $_SESSION['listingUpdated'] = 0;
                                }
                            ?>
                        </div>
                        <div class="row">
                            <div id="coverPic" class="col-sm-4">
                                <img class="img-responsive" src="<?php echo $coverPic?>">
                            </div>
                            <div class="col-sm-8">
                                <form id="changeListingForm" class="form-horizontal changeForm" method="post" action="includes/editListing.php" enctype="multipart/form-data">
                                    <input type="hidden" name="id" value="<?php echo $id?>">
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="street">Street</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="street" name="street" type="text" class="form-control" placeholder="<?php echo $property->Street; ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="city">City</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="city" name="city" type="text" class="form-control" placeholder="<?php echo $property->City; ?>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="city">State</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="state" name="state" type="text" class="form-control" placeholder="<?php echo $property->State; ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="zipCode">Zip Code</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="zipCode" name="zipCode" type="text" class="form-control" placeholder="<?php echo $property->zipCode; ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="beds">Bedrooms</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="beds" name="beds" type="number" class="form-control" placeholder="<?php echo $property->Beds; ?>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="baths">Bathrooms</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="baths" name="baths" type="number" class="form-control" placeholder="<?php echo $property->Baths; ?>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="size">Size</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="size" name="size" type="text" class="form-control" placeholder="<?php echo $property->Size ?>">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="price">Price</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="price" name="price" type="text" class="form-control" placeholder="<?php echo $property->Price ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                        </div>
                                        <div class="col-sm-7">
                                            <?php $rentalChecked = $property->For_Rent_Bool == 1 ? 'checked' : ''; ?>
                                            <input id="forRent" name="forRent" type="checkbox" <?php echo $rentalChecked ?>> This listing is a rental
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                        </div>
                                        <div class="col-sm-7">
                                            <?php $petsChecked = $property->Pets_Allowed_Bool == 1 ? 'checked' : ''; ?>
                                            <input id="petsAllowed" name="petsAllowed" type="checkbox" <?php echo $petsChecked ?>> Pets are allowed (Leave blank If not applicable)
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="description">Description</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <textarea id="description" name="description" rows="10" class="form-control"><?php echo $property->Description ?></textarea>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="typeOfHome">Type of Home</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <select name="typeOfHome" class="form-control">
                                                <?php 
                                                    $value = $property->Type_of_Home;
                                                    $str = "<option value='$value'>$property->Type_of_Home</option>";
                                                    echo $str;
                                                    createOptionList($str);    
                                                ?>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="coverPhoto">Cover Photo</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="coverPhoto" name="uploadFile" type="file">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="listingPhotos">Add Listing Photos</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="listingPhotos" title="Choose up to 19 pictures at a time" name="uploadFiles[]" type="file" multiple>
                                        </div>
                                    </div>
                                    </br>
                                    <div class="row" style="margin-bottom: 20px;">
                                        <div style="text-align: center"><h4 class="TextTooBigForMobile">Select Photos to be Deleted</h4></div>
                                        <?php
                                            $tableName = 'Listing'.$id;
                                            
                                            $getImagesForDelete = "SELECT * FROM $tableName";
                                            
                                            $results = $mysqli->query($getImagesForDelete);
                                            
                                            while ($row = mysqli_fetch_array($results, MYSQLI_ASSOC)){
                                                $link = substr($row['directory'],3);
                                                $id =  $row['id'];
                                                $checkboxId = 'checkbox'.$id;
                                                echo "<div class='col-xs-4'>";
                                                    echo "<img style='margin-left:auto; margin-right:auto;' id='$id' class='listImageToBeDeleted img-responsive' src='$link'>";
                                                    echo "<input id='$checkboxId' type='checkbox' name='deleteCheckboxes[]' style='visibility: hidden; display:none;' value='$id'>";
                                                echo "</div>";
                                            }
                                        ?>
                                    </div>
                                    <div class="form-group">
                                        <div id="submitChangesButton" class="col-xs-12">
                                            <button  type="submit" class="btn-primary">Submit Changes</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div><!--Container ends here-->
            <div class="push"></div>
        </div>
        <?php include 'includes/footer.html'; ?><!--Footer inserted here-->
    </body>
</html>
