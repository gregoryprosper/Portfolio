<?php
    $sql = "SELECT * FROM Listings WHERE Posted_By = '$id'";
    $result = $mysqli->query($sql);
    $rows = mysqli_num_rows($result);
    
    // Number of records to show per page:
    $display = 5;
    $lastSearch = "&id=$id";

    if (isset($_GET['p']) && is_numeric($_GET['p'])) { // Already been determined.
        $pages = $_GET['p'];
    } else { // Need to determine.
        // Count the number of records:
        $r = $mysqli->query($sql);
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
        
    if ($rows){
        
        $sql = "SELECT * FROM Listings WHERE Posted_By = '$id' ORDER by Post_Date DESC LIMIT $start, $display";
        $result = $mysqli->query($sql);
        
        $num1 = $start + 1;
        $num2 = $start + $result->num_rows;
        echo "<div class='textSmall'>Showing Listings $num1 - $num2 of $rows</div>";
        while ($property = $result->fetch_object()){
            $coverPic = substr($property->cover, 3);
            $date = substr($property->Post_Date,0,10);
            
            echo "<div class='row UserlistingRows'>"; //Row starts here
                //Mobile buttons start here
                echo "<div style='height:30px; margin-bottom:5px;' class='visible-xs visible-sm'>";
                    echo "<div style='text-align:center;' class='col-xs-6 col-sm-6'>";
                        echo "<form method='post' action='editMyListing.php'>";
                            echo "<input type='hidden' name='id' value='$property->id'>";
                            echo "<button class='btn btn-default btn-xs'>Edit</button>";
                        echo "</form>";
                    echo "</div>";
                    echo "<div style='text-align:center;' class='col-xs-6 col-sm-6'>";
                        echo "<form method='post' action='includes/deleteListing.php'>";
                            echo "<input type='hidden' name='id' value='$property->id'>";
                            echo "<button  onclick=\"return confirm('Are you sure you want to delete this listing?')\" class='btn btn-danger btn-xs'>Delete</button>";
                        echo "</form>";
                    echo "</div>";
                echo '</div>'; //Mobile buttons end here

                echo "<div>"; //Listing Details start here
                    echo "<div style='margin-left:5px' class='postDate'><span class='glyphicon glyphicon-time'></span> $date</div>";
                    echo "<div id='listImageArea'>";
                        echo "<img id='listImage' class='img-responsive img-thumbnail' src='$coverPic'/>";
                    echo "</div>";

                    echo '<div id="listDetailsArea">';
                            $price = number_format($property->Price);
                            $size = number_format($property->Size);
                            $forRentOrSale;

                            if ($property->For_Rent_Bool) {
                                $price = $price . '/mo';
                                $forRentOrSale = $property->Type_of_Home ." For Rent";
                            }
                            else{
                                $forRentOrSale = $property->Type_of_Home ." For Sale";
                            }

                            echo "<p id='ListingSaleRent'>$forRentOrSale</p>";
                            echo "<p id='ListingPrice'>$$price</p>";
                            echo "<p id='ListingDetailsBdBa'>$property->Beds bd | $property->Baths ba</p>";
                            echo "<p id='ListingAddress'>$property->Street $property->City, $property->State $property->zipCode</p>";
                            echo "<p id='ListingDetails'>$size Sq Ft</p>";
                    echo '</div>'; //Listing Details Area ends here
                    //Listing Edit Buttons starts here
                    echo "<div id='ListingEditButtons' class='hidden-xs hidden-sm'>";
                        echo "<div style='width:350px' class='outer'>";
                            echo "<div class='middle'>";
                                echo "<div class='inner'>";
                                    echo "<form style='float:left' method='post' action='editMyListing.php'>";
                                        echo "<input type='hidden' name='id' value='$property->id'>";
                                        echo "<button type='submit' style='margin-right:100px;' class='btn btn-default btn-lg'>Edit</button>";
                                    echo "</form>";
                                    echo "<form method='post' action='includes/deleteListing.php'>";
                                        echo "<input type='hidden' name='id' value='$property->id'>";
                                        echo "<button onclick=\"return confirm('Are you sure you want to delete this listing?')\" type='submit' class='btn btn-danger btn-lg'>Delete</button>";
                                    echo "</form>";
                                echo "</div>";
                            echo "</div>";
                        echo "</div>";
                    echo '</div>'; //Listing Edit Buttons ends here
                echo "</div>"; //Listing Details end here
            echo '</div>'; //Row end here
        }
    }
    else {
        echo "<h1 style='text-align:center' class='TextTooBigForMobile'>You have No Listings </h1>";
    }
?>
