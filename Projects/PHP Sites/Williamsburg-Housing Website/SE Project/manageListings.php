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
    
    $username = $_SESSION['username'];
    
    $sql = "SELECT id FROM Realtors WHERE username = '$username'";
    $result = $mysqli->query($sql);
    $user = $result->fetch_object();
    $id = $user->id;
    
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Listings</title>
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css" media="screen">
        <link href="css/main.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <?php include 'includes/nav.php'; ?> <!--Nav bar inserted here-->
                <div class="container">
                    <div>
                        <h1 class="TextTooBigForMobile"><span class="glyphicon glyphicon-pencil"></span> Manage Listings</h1>
                        <hr>
                    </div>
                        <div style="text-align: center;" class="visible-xs visible-sm">
                            <a href="addMyListing.php" class="btn btn-primary btn-xs">Add New Listing</a>
                        </div>
                        <div style="text-align: center;" class=" hidden-xs hidden-sm">
                            <a href="addMyListing.php" class="btn btn-primary btn-lg">Add New Listing</a>
                        </div>
                    <br>
                    <div>
                        <?php 
                            include 'includes/listUserItems.php';
                            // Make the links to other pages.
                            if ($pages > 1) {
                                    echo '<nav style="text-align:center">';
                                    echo '<ul class="pagination">';
                                    $current_page = ($start/$display) + 1;

                                    // If it's not the first page, make a Previous button:
                                    if ($current_page != 1) {
                                        echo '<li><a href="manageListings.php?s=' . ($start - $display) . '&p=' . $pages . $lastSearch.'"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
                                    }else{
                                        echo '<li class="disabled"><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
                                    }

                                    // Make all the numbered pages:
                                    for ($i = 1; $i <= $pages; $i++) {
                                        if ($i != $current_page) {
                                            echo '<li><a href="manageListings.php?s=' . (($display * ($i - 1))) . '&p=' . $pages . $lastSearch. '">' . $i . '</a><li>';
                                        }  
                                        else {
                                            echo '<li class="disabled"><a href="#">' . $i . '</a><li>';
                                        }
                                    } // End of FOR loop.

                                    // If it's not the last page, make a Next button:
                                    if ($current_page != $pages) {
                                        echo '<li><a href="manageListings.php?s=' . ($start + $display) . '&p=' . $pages . $lastSearch. '"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
                                    }
                                    else{
                                        echo '<li class="disabled"><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
                                    }
                                    echo '</ul>';
                                    echo '</nav>'; // Close the paragraph.

                            } // End of links section.
                        ?>
                    </div>
                </div><!--Container ends here-->
            <div class="push"></div>
        </div>
        <?php include 'includes/footer.html'; ?><!--Footer inserted here-->
    </body>
</html>
