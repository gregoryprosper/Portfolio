<?php
    session_start();
    require_once 'includes/login.php';
    require_once 'includes/misc.php';
    
    $mysqli = new mysqli($db_hostname,$db_username,$db_password,$db_database);
    
    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }

    if (isset($_GET['search'])){
        $search = $_GET['search'];
        $resetQuery = '?search='. str_replace(' ', '+', $search);
        $lastSearch = '&search='. str_replace(' ', '+', $search);
        $query = "SELECT * FROM Listings WHERE (City = '$search' OR zipCode = '$search')";
    }
    
    if (isset($_GET['minPrice']) && $_GET['minPrice'] != ''){
        $minPrice = $_GET['minPrice'];
        $query = $query. " AND Price >= $minPrice";
        $lastSearch = $lastSearch. '&minPrice='. $minPrice;
    }
    
    if (isset($_GET['maxPrice']) && $_GET['maxPrice'] != ''){
        $maxPrice = $_GET['maxPrice'];
        $query = $query. " AND Price <= $maxPrice";
        $lastSearch = $lastSearch. '&maxPrice='. $maxPrice;
    }
    
    if (isset($_GET['beds']) && $_GET['beds'] != ''){
        $beds = $_GET['beds'];
        $query = $query. " AND Beds >= $beds";
        $lastSearch = $lastSearch. '&beds='. $beds;
    }
    
    if (isset($_GET['baths']) && $_GET['baths'] != ''){
        $baths = $_GET['baths'];
        $query = $query. " AND Baths >= $baths";
        $lastSearch = $lastSearch. '&baths='. $baths;
    }
    
    if (isset($_GET['propertyType']) && $_GET['propertyType'] != ''){
        $propertyType = $_GET['propertyType'];
        $query = $query. " AND Type_of_Home = '$propertyType'";
        $lastSearch = $lastSearch. '&propertyType='. $propertyType;
    }
    
    if (isset($_GET['forRent'])){
        $query = $query. " AND For_Rent_Bool = 1";
        $lastSearch = $lastSearch. '&forRent=1';
    }
    
    if (isset($_GET['petsAllowed'])){
        $query = $query. " AND Pets_Allowed_Bool = 1";
        $lastSearch = $lastSearch. '&petsAllowed=1';
    }
    
    //Next two lines are for showing total number of Listings before or after filter
    $r = $mysqli->query($query);
    $totalNumberOfListing= $r->num_rows;
        
        
    // Number of records to show per page:
    $display = 5;
    
    if (isset($_GET['p']) && is_numeric($_GET['p'])) { // Already been determined.
	$pages = $_GET['p'];
    } else { // Need to determine.
 	// Count the number of records:
	$q = $query." ORDER BY Post_Date DESC";
	$r = $mysqli->query($q);
	$records= $r->num_rows;
	// Calculate the number of pages...
	if ($records > $display) { // More than 1 page.
		$pages = ceil ($records/$display);
	} else {
		$pages = 1;
	}
    }
    
    if (isset($_GET['s']) && is_numeric($_GET['s'])) {
	$start = $_GET['s'];
    } else {
	$start = 0;
    }
    
    $sql = $query. " ORDER BY Post_Date DESC LIMIT $start, $display";
    $results = $mysqli->query($sql);
      
?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Williamsburg-Housing - Search Results</title>
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
            <!--start of page container-->
            <div class="container">
                <div>
                    <h1 class="TextTooBigForMobile">Listings In "<?php echo $search;?>"</h1>
                </div>
                <!--Start of Filter-->
                <!--Desktop Filter-->
                <div id="filter" class="hidden-xs hidden-sm">  
                    <form class="form-inline" method="get" action="listings.php">
                        <input type="hidden" name="search" value="<?php echo $search?>">
                        <select name="minPrice" class="form-control input-sm">
                            <?php
                                if (isset($_GET['minPrice']) && $_GET['minPrice'] != ''){
                                    $num = number_format($minPrice);
                                    echo "<option value='$minPrice'>$$num</option>";
                                }
                            ?>
                            <option value="">No Min Price</option>
                            <option value="500">$500</option>
                            <option value="1000">$1,000</option>
                            <option value="1500">$1,500</option>
                            <option value="2000">$2,000</option>
                            <option value="2500">$2,500</option>
                            <option value="3000">$3,000</option>
                            <option value="3500">$3,500</option>
                            <option value="4000">$4,000</option>
                            <option value="4500">$4,500</option>
                            <option value="5000">$5,000</option>
                            <option value="5500">$5,500</option>
                            <option value="6000">$6,000</option>
                            <option value="6500">$6,500</option>
                            <option value="7000">$7,000</option>
                            <option value="7500">$7,500</option>
                            <option value="8000">$8,000</option>
                            <option value="8500">$8,500</option>
                            <option value="9000">$9,000</option>
                            <option value="9500">$9,500</option>
                            <option value="10000">$10,000</option>
                            <option value="20000">$20,000</option>
                            <option value="30000">$30,000</option>
                            <option value="50000">$50,000</option>
                            <option value="100000">$100,000</option>
                            <option value="130000">$130,000</option>
                            <option value="150000">$150,000</option>
                            <option value="200000">$200,000</option>
                            <option value="250000">$250,000</option>
                            <option value="300000">$300,000</option>
                            <option value="350000">$350,000</option>
                            <option value="400000">$400,000</option>
                            <option value="450000">$450,000</option>
                            <option value="500000">$500,000</option>
                            <option value="550000">$550,000</option>
                            <option value="600000">$600,000</option>
                            <option value="650000">$650,000</option>
                            <option value="700000">$700,000</option>
                            <option value="750000">$750,000</option>
                        </select>
                        <select name="maxPrice" class="form-control input-sm">
                            <?php
                                if (isset($_GET['maxPrice']) && $_GET['maxPrice'] != ''){
                                    $num = number_format($maxPrice);
                                    echo "<option value='$maxPrice'>$$num</option>";
                                }
                            ?>
                            <option value="">No Max Price</option>
                            <option value="500">$500</option>
                            <option value="1000">$1,000</option>
                            <option value="1500">$1,500</option>
                            <option value="2000">$2,000</option>
                            <option value="2500">$2,500</option>
                            <option value="3000">$3,000</option>
                            <option value="3500">$3,500</option>
                            <option value="4000">$4,000</option>
                            <option value="4500">$4,500</option>
                            <option value="5000">$5,000</option>
                            <option value="5500">$5,500</option>
                            <option value="6000">$6,000</option>
                            <option value="6500">$6,500</option>
                            <option value="7000">$7,000</option>
                            <option value="7500">$7,500</option>
                            <option value="8000">$8,000</option>
                            <option value="8500">$8,500</option>
                            <option value="9000">$9,000</option>
                            <option value="9500">$9,500</option>
                            <option value="10000">$10,000</option>
                            <option value="20000">$20,000</option>
                            <option value="30000">$30,000</option>
                            <option value="50000">$50,000</option>
                            <option value="100000">$100,000</option>
                            <option value="130000">$130,000</option>
                            <option value="150000">$150,000</option>
                            <option value="200000">$200,000</option>
                            <option value="250000">$250,000</option>
                            <option value="300000">$300,000</option>
                            <option value="350000">$350,000</option>
                            <option value="400000">$400,000</option>
                            <option value="450000">$450,000</option>
                            <option value="500000">$500,000</option>
                            <option value="550000">$550,000</option>
                            <option value="600000">$600,000</option>
                            <option value="650000">$650,000</option>
                            <option value="700000">$700,000</option>
                            <option value="750000">$750,000</option>
                        </select>
                        <select name="beds" class="form-control input-sm">
                            <?php
                                if (isset($_GET['beds']) && $_GET['beds'] != ''){
                                    echo "<option value='$beds'>$beds+</option>";
                                }
                            ?>
                            <option value="">All Beds</option>
                            <option value="2">2+</option>
                            <option value="3">3+</option>
                            <option value="4">4+</option>
                            <option value="5">5+</option>
                        </select>
                        <select name="baths" class="form-control input-sm">
                            <?php
                                if (isset($_GET['baths']) && $_GET['baths'] != ''){
                                    echo "<option value='$baths'>$baths+</option>";
                                }
                            ?>
                            <option value="">All Baths</option>
                            <option value="2">2+</option>
                            <option value="3">3+</option>
                            <option value="4">4+</option>
                            <option value="5">5+</option>
                        </select>
                        <select name="propertyType" class="form-control input-sm">
                            <?php
                                if (isset($_GET['propertyType']) && $_GET['propertyType'] != ''){
                                    echo "<option value='$propertyType'>$propertyType</option>";
                                }
                            ?>
                            <option value="">All Property Types</option>
                            <option value="Single Family Home">Single Family Home</option>
                            <option value="Condo">Condo</option>
                            <option value="TownHome">Townhome</option>
                            <option value="Apartment">Apartment</option>
                            <option value="HouseBoat">Houseboat</option>
                            <option value="Multi Family Home">Multi Family Home</option>
                        </select>
                        <div class="checkbox">
                            <label>
                                <?php $rentalChecked = isset($_GET['forRent']) ? 'checked' : ''; ?>
                                <input name="forRent" type="checkbox" value="forRent" <?php echo $rentalChecked;?>> Rental
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <?php $petFriendlyChecked = isset($_GET['petsAllowed']) ? 'checked' : ''; ?>
                                <input name="petsAllowed" type="checkbox" value="petsAllowed" <?php echo $petFriendlyChecked;?>> Allow Pets
                            </label>
                        </div>
                        <a class="btn btn-default btn-sm" href="listings.php<?php echo $resetQuery;?>">Reset</a>
                        <button class="btn btn-primary btn-sm" type="submit">Filter</button>
                    </form>
                </div>
                
                <!--Mobile Filter-->
                <div class="modal fade" id="filterModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <img id="logo2" src="images/logo2.png">
                                    <span style="font-size: large" class="modal-title"> Filter Listings</span>
                            </div>
                            <form class="form-horizontal" method="get" action="listings.php">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <input type="hidden" name="search" value="<?php echo $search?>">
                                            <select name="minPrice" class="form-control input-sm">
                                                <?php
                                                    if (isset($_GET['minPrice']) && $_GET['minPrice'] != ''){
                                                        $num = number_format($minPrice);
                                                        echo "<option value='$minPrice'>$$num</option>";
                                                    }
                                                ?>
                                                <option value="">No Min Price</option>
                                                <option value="500">$500</option>
                                                <option value="1000">$1,000</option>
                                                <option value="1500">$1,500</option>
                                                <option value="2000">$2,000</option>
                                                <option value="2500">$2,500</option>
                                                <option value="3000">$3,000</option>
                                                <option value="3500">$3,500</option>
                                                <option value="4000">$4,000</option>
                                                <option value="4500">$4,500</option>
                                                <option value="5000">$5,000</option>
                                                <option value="5500">$5,500</option>
                                                <option value="6000">$6,000</option>
                                                <option value="6500">$6,500</option>
                                                <option value="7000">$7,000</option>
                                                <option value="7500">$7,500</option>
                                                <option value="8000">$8,000</option>
                                                <option value="8500">$8,500</option>
                                                <option value="9000">$9,000</option>
                                                <option value="9500">$9,500</option>
                                                <option value="10000">$10,000</option>
                                                <option value="20000">$20,000</option>
                                                <option value="30000">$30,000</option>
                                                <option value="50000">$50,000</option>
                                                <option value="100000">$100,000</option>
                                                <option value="130000">$130,000</option>
                                                <option value="150000">$150,000</option>
                                                <option value="200000">$200,000</option>
                                                <option value="250000">$250,000</option>
                                                <option value="300000">$300,000</option>
                                                <option value="350000">$350,000</option>
                                                <option value="400000">$400,000</option>
                                                <option value="450000">$450,000</option>
                                                <option value="500000">$500,000</option>
                                                <option value="550000">$550,000</option>
                                                <option value="600000">$600,000</option>
                                                <option value="650000">$650,000</option>
                                                <option value="700000">$700,000</option>
                                                <option value="750000">$750,000</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <select name="maxPrice" class="form-control input-sm">
                                                <?php
                                                    if (isset($_GET['maxPrice']) && $_GET['maxPrice'] != ''){
                                                        $num = number_format($maxPrice);
                                                        echo "<option value='$maxPrice'>$$num</option>";
                                                    }
                                                ?>
                                                <option value="">No Max Price</option>
                                                <option value="500">$500</option>
                                                <option value="1000">$1,000</option>
                                                <option value="1500">$1,500</option>
                                                <option value="2000">$2,000</option>
                                                <option value="2500">$2,500</option>
                                                <option value="3000">$3,000</option>
                                                <option value="3500">$3,500</option>
                                                <option value="4000">$4,000</option>
                                                <option value="4500">$4,500</option>
                                                <option value="5000">$5,000</option>
                                                <option value="5500">$5,500</option>
                                                <option value="6000">$6,000</option>
                                                <option value="6500">$6,500</option>
                                                <option value="7000">$7,000</option>
                                                <option value="7500">$7,500</option>
                                                <option value="8000">$8,000</option>
                                                <option value="8500">$8,500</option>
                                                <option value="9000">$9,000</option>
                                                <option value="9500">$9,500</option>
                                                <option value="10000">$10,000</option>
                                                <option value="20000">$20,000</option>
                                                <option value="30000">$30,000</option>
                                                <option value="50000">$50,000</option>
                                                <option value="100000">$100,000</option>
                                                <option value="130000">$130,000</option>
                                                <option value="150000">$150,000</option>
                                                <option value="200000">$200,000</option>
                                                <option value="250000">$250,000</option>
                                                <option value="300000">$300,000</option>
                                                <option value="350000">$350,000</option>
                                                <option value="400000">$400,000</option>
                                                <option value="450000">$450,000</option>
                                                <option value="500000">$500,000</option>
                                                <option value="550000">$550,000</option>
                                                <option value="600000">$600,000</option>
                                                <option value="650000">$650,000</option>
                                                <option value="700000">$700,000</option>
                                                <option value="750000">$750,000</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <select name="beds" class="form-control input-sm">
                                                <?php
                                                    if (isset($_GET['beds']) && $_GET['beds'] != ''){
                                                        echo "<option value='$beds'>$beds+</option>";
                                                    }
                                                ?>
                                                <option value="">All Beds</option>
                                                <option value="2">2+</option>
                                                <option value="3">3+</option>
                                                <option value="4">4+</option>
                                                <option value="5">5+</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <select name="baths" class="form-control input-sm">
                                                <?php
                                                    if (isset($_GET['baths']) && $_GET['baths'] != ''){
                                                        echo "<option value='$baths'>$baths+</option>";
                                                    }
                                                ?>
                                                <option value="">All Baths</option>
                                                <option value="2">2+</option>
                                                <option value="3">3+</option>
                                                <option value="4">4+</option>
                                                <option value="5">5+</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <select name="propertyType" class="form-control input-sm">
                                                <?php
                                                    if (isset($_GET['propertyType']) && $_GET['propertyType'] != ''){
                                                        echo "<option value='$propertyType'>$propertyType</option>";
                                                    }
                                                ?>
                                                <option value="">All Property Types</option>
                                                <option value="Single Family Home">Single Family Home</option>
                                                <option value="Condo">Condo</option>
                                                <option value="TownHome">Townhome</option>
                                                <option value="Apartment">Apartment</option>
                                                <option value="HouseBoat">Houseboat</option>
                                                <option value="Multi Family Home">Multi Family Home</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <?php $rentalChecked = isset($_GET['forRent']) ? 'checked' : ''; ?>
                                            <input name="forRent" type="checkbox" value="forRent" <?php echo $rentalChecked;?>> Rental
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <?php $petFriendlyChecked = isset($_GET['petsAllowed']) ? 'checked' : ''; ?>
                                            <input name="petsAllowed" type="checkbox" value="petsAllowed" <?php echo $petFriendlyChecked;?>> Pet Friendly
                                        </label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-default " href="listings.php<?php echo $resetQuery;?>">Reset</a>
                                    <button class="btn btn-primary " type="submit">Filter</button>
                                </div>
                            </form>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <!--End of Filter-->
                <!--start of Listings-->
                <?php
                    $num1 = $results->num_rows ? $start + 1 : 0;
                    $num2 = $start + $results->num_rows;
                    echo "<div style='height:22px; margin-bottom:5px;'class='textSmall'>";
                    echo "<div style='display:inline-block; margin-top:5px;'>Showing Listings $num1 - $num2 of $totalNumberOfListing</div>";
                    echo "<div class='visible-xs visible-sm' style='float:right;'><a class='btn btn-default btn-xs' data-toggle='modal' data-target='#filterModal' href='#'>Show Filter</a></div>";
                    echo "</div>";
                    if ($results->num_rows > 0) {
                        include 'includes/listItems.php';
                        // Make the links to other pages.
                        if ($pages > 1) {
                                echo '<nav style="text-align:center">';
                                echo '<ul class="pagination">';
                                $current_page = ($start/$display) + 1;

                                // If it's not the first page, make a Previous button:
                                if ($current_page != 1) {
                                    echo '<li><a href="listings.php?s=' . ($start - $display) . '&p=' . $pages . $lastSearch.'"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
                                }else{
                                    echo '<li class="disabled"><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
                                }

                                // Make all the numbered pages:
                                for ($i = 1; $i <= $pages; $i++) {
                                    if ($i != $current_page) {
                                        echo '<li><a href="listings.php?s=' . (($display * ($i - 1))) . '&p=' . $pages . $lastSearch. '">' . $i . '</a><li>';
                                    }  
                                    else {
                                        echo '<li class="disabled"><a href="#">' . $i . '</a><li>';
                                    }
                                } // End of FOR loop.

                                // If it's not the last page, make a Next button:
                                if ($current_page != $pages) {
                                    echo '<li><a href="listings.php?s=' . ($start + $display) . '&p=' . $pages . $lastSearch. '"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
                                }
                                else{
                                    echo '<li class="disabled"><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
                                }
                                echo '</ul>';
                                echo '</nav>'; // Close the paragraph.

                        } // End of links section.
                    }
                    else{
                        echo "<h1 style='text-align:center' class='TextTooBigForMobile'> No Listings found</h1>";
                    }
                ?><!--end of Listings-->
            </div><!--end of page container-->
            <div class="push"></div>   
        </div>
        <?php include 'includes/footer.html'; ?>
    </body>
</html>
