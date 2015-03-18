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
    
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Add Listing</title>
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
        <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <?php include 'includes/nav.php'; ?> <!--Nav bar inserted here-->
                <div class="container">
                    <div>
                        <h1 class="TextTooBigForMobile"><span class="glyphicon glyphicon-pencil"></span> Add Listing</h1>
                        <hr>
                    </div>
                    <div class="row">
                        <div id="coverPic" class="col-sm-4">
                            <img class="img-responsive" src="images/placeholder.jpg">
                        </div>
                        <div class="col-sm-8">
                            <form id="addListingForm" class="form-horizontal changeForm" method="post" action="includes/addListing.php" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="street">Street</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="street" name="street" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="city">City</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="city" name="city" type="text" class="form-control">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="city">State</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="state" name="state" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="zipCode">Zip Code</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="zipCode" name="zipCode" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="beds">Bedrooms</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="beds" name="beds" type="number" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="baths">Bathrooms</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="baths" name="baths" type="number" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="size">Size</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="size" name="size" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label  for="price">Price</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="price" name="price" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="forRent" name="forRent" type="checkbox"> This listing is a rental
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                    </div>
                                    <div class="col-sm-7">
                                        <input id="petsAllowed" name="petsAllowed" type="checkbox"> Pets are allowed
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label for="description">Description</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <textarea id="description" name="description" rows="10" class="form-control"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label for="typeOfHome">Type of Home</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <select name="typeOfHome" class="form-control">
                                            <option value='Single Family Home'>Single Family Home</option>
                                            <option value='Condo'>Condo</option>
                                            <option value='TownHome'>Townhome</option>
                                            <option value='Apartment'>Apartment</option>
                                            <option value='Houseboat'>Houseboat</option>
                                            <option value='Multi Family Home'>Multi Family Home</option>
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
                                <div class="form-group">
                                    <div id="submitChangesButton" class="col-sm-10  hidden-xs">
                                        <button style="float: right;" type="submit" class="btn-default">Submit Changes</button>
                                    </div>
                                    <div id="submitChangesButton" class="col-sm-10  visible-xs">
                                        <button type="submit" class="btn-default">Submit Changes</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div><!--Container ends here-->
            <div class="push"></div>
        </div>
        <?php include 'includes/footer.html'; ?><!--Footer inserted here-->
    </body>
</html>
