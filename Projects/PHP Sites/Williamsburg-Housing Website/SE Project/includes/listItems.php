<?php
include 'misc.php';

while ($row = mysqli_fetch_array($results, MYSQLI_ASSOC))
{   
    $propId = $row['id'];
    $listingImage = substr($row['cover'], 3);
    $street = $row['Street'];
    $city = $row['City'];
    $state = $row['State'];
    $zip = $row['zipCode'];
    $baths = $row['Baths'];
    $beds = $row['Beds'];
    $size = $row['Size'];
    $price = $row['Price'];
    $petsAllowed = $row['Pets_Allowed_Bool'];
    $forRent = $row['For_Rent_Bool'];
    $description = $row['Description'];
    $typeOfHome = $row['Type_of_Home'];
    $postDate = $row['Post_Date'];
    
    $today = date_create(date("Y-m-d"));
    $date = substr($postDate,0,10);
    $timeDiff = date_diff(date_create($date),$today);
    $days = substr($timeDiff->format('%R%a'),1);
    
    echo '<div class="row listingRows">';
        echo '<div style="margin-left:5px" class="visible-xs visible-sm">';
            getDaysPostAgo($days);
        echo '</div>';
        echo '<div id="listImageArea">';
            echo "<img id='listImage' class='img-responsive img-thumbnail' src='$listingImage'/>";
            echo "<a href='property.php?id=$propId'></a>";
        echo '</div>';

        echo '<div id="listDetailsArea">';
                $price = number_format($price);
                $size = number_format($size);
                $forRentOrSale;
                
                if ($forRent) {
                    $price = $price . '/mo';
                    $forRentOrSale = $typeOfHome." For Rent";
                }
                else{
                    $forRentOrSale = $typeOfHome." For Sale";
                }
                
                echo "<p class='visible-xs visible-sm' id='ListingSaleRent'>$forRentOrSale</p>";
                echo "<p id='ListingPrice'>$$price</p>";
                echo "<p id='ListingDetailsBdBa'>$beds bd | $baths ba</p>";
                echo "<p class='hidden-xs hidden-sm' id='ListingDetails'>$typeOfHome</p>";
                echo "<p id='ListingAddress'>$street $city, $state $zip</p>";
                echo "<p id='ListingDetails'>$size Sq Ft<span></span></p>";
        echo '</div>';
        
        echo '<div id="listBulletsArea" class="hidden-xs hidden-sm">';
            echo "<div class='outer'>";
                echo "<div class='middle'>";
                    echo "<div class='inner'>";
                        if ($forRent) {
                                echo '<div style="padding-bottom:5px;"><img src="./images/forRent.png"></div>';
                        }
                        else{
                                echo '<div style="padding-bottom:5px;"><img src="./images/forSale.png"></div>';
                        }

                        if ($petsAllowed) {
                                echo '<div style="padding-bottom:5px;"><img src="./images/petsAllowed.png"></div>';
                        }
                    echo "</div>";
                echo "</div>";
            echo "</div>";	 
        echo '</div>';
		
        echo '<div id="listPostDateArea" class="hidden-xs hidden-sm">';

                echo "<div class='outer'>";
                echo "<div class='middle'>";
                        echo "<div class='inner'>";
                        getDaysPostAgo($days);
                        echo "</div>";
                echo "</div>";
        echo "</div>";
                
        echo '</div>';
        
    echo '</div>';
}

?>
    
    
