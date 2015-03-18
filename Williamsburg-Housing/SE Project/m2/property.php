<?php
    session_start();
    require_once 'includes/login.php';
    include_once 'includes/misc.php';
    
    $mysqli = new mysqli($db_hostname,$db_username,$db_password,$db_database);
    
    if (mysqli_connect_errno()) {
        printf("Connect failed: %s\n", mysqli_connect_error());
        exit();
    }
    
    $id = $_GET['id'];
    
    $sql = "SELECT * FROM Listings WHERE id = '$id'";
    $result = $mysqli->query($sql);
    $property = $result->fetch_object();
    $propertyCover = substr($property->cover,3);
    
    $sql = "SELECT * FROM Realtors WHERE id = '$property->Posted_By'";
    $result = $mysqli->query($sql);
    $realtor = $result->fetch_object();
    $realtorPic = substr($realtor->profilePic,3);
    
    $today = date_create(date("Y-m-d"));
    $date = substr($property->Post_Date,0,10);
    $timeDiff = date_diff(date_create($date),$today);
    $days = substr($timeDiff->format('%R%a'),1);
?>

<!DOCTYPE html>
<html>
    <head>
        <title><?php echo $property->Street .' '. $property->City .' '. $property->zipCode; ?></title>
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
        <script type="text/javascript" src="js/propertyJS.js"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>  
        <script type="text/javascript" src="js/jquery.gomap-1.3.2.min.js"></script> 
        <script type="text/javascript" src="js/jquery.fancybox.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="helpers/jquery.fancybox-buttons.css?v=1.0.5" />
	<script type="text/javascript" src="helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
    </head>
    <body>
        <div class="wrapper">
            <?php include 'includes/nav.php'; ?> <!--Nav bar inserted here-->
            <div style="margin-bottom: 20px;" class="container">
                <div><a href="javascript:history.go(-1);"><span style="color: black;" class="glyphicon glyphicon-chevron-left"></span> Back to Results</a></div>
                <br>
                <div class="row">
                    <div class="col-md-8">
                        <div id="postHeader">
                            <?php getDaysPostAgo($days); ?>
                            <div style="float: right; display: inline;"><p>Posted By: <a data-toggle="modal" data-target="#realtorModal" href="#"><?php echo $realtor->first_name . ' ' . $realtor->last_name; ?></a></p></div>
                        </div>
                        <!--start of carousel-->
                        <div id="myCarousel" class="carousel slide">
                            <div class="container">
                                <ol class="carousel-indicators">
                                    <li class="active hidden-xs" data-target="#myCarousel" data-slide-to="0"></li>
                                    <?php
                                            $tableName = 'Listing'.$property->id;
                                            $getImages = "SELECT * FROM $tableName";
                                            $result = $mysqli->query($getImages);
                                            $row_cnt = $result->num_rows;

                                            for ($i = 1; $i <= $row_cnt; $i++){
                                                    echo "<li class='hidden-xs' data-target='#myCarousel' data-slide-to='$i'></li> ";
                                            }
                                    ?>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <a class="fancybox-button hidden-sm hidden-xs" rel="pics" href="<?php echo $propertyCover ?>">
                                            <img src="<?php echo $propertyCover ?>">
                                        </a>
                                        <img class="visible-sm visible-xs" src="<?php echo $propertyCover ?>">
                                    </div>
                                    <?php
                                        while ($row = $result->fetch_assoc()){
                                                $link = substr($row['directory'],3);
                                                echo "<div class='item'>";
                                                        echo "<a class='fancybox-button hidden-sm hidden-xs' rel='pics' href='$link'>";
                                                                echo "<img src='$link'>";
                                                        echo "</a>";
                                                        echo "<img class='visible-sm visible-xs' src='$link'>";
                                                echo "</div>";
                                        }
                                    ?>
                                </div>
                            </div>
                            <a class="left carousel-control" href='#myCarousel' data-slide="prev" role="button">
                                <span class="icon-prev"></span>
                            </a>
                            <a class="right carousel-control" href='#myCarousel' data-slide="next" role="button">
                                <span class="icon-next"></span>
                            </a>
                        </div><!--end of carousel-->
                    </div>
                    <div class="col-md-4 hidden-xs hidden-sm">
                        <br><br>
                        <div class="iconRow">
                            <img class="icon" src="images/home_icon.png">
                            <span><?php echo $property->Type_of_Home; ?></span>
                        </div>
                        <div class="iconRow">
                            <img class="icon" src="images/bed_icon.png">
                            <span><?php echo $property->Beds . ' Bedroom(s)'; ?></span>
                        </div>
                        <div class="iconRow">
                            <img class="icon" src="images/shower_icon.png">
                            <span><?php echo $property->Baths . ' Bathroom(s)'; ?></span>
                        </div>
                        <div class="iconRow">
                            <img class="icon" src="images/ruler_icon.png">
                            <?php $size = number_format( $property->Size); ?>
                            <span><?php echo $size . ' Sq Ft'; ?></span>
                        </div>
                        <div class="iconRow" style="margin-bottom: 0px;">
                            <?php
                            if($property->Pets_Allowed_Bool == 1){
                                echo "<img class='icon' src='images/dog_icon.png'>";
                                echo "<span>Pets Allowed</span>";
                            }
							?>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div>
                            <h2 id="address" class="TextTooBigForMobile"><?php echo $property->Street .' '. $property->City .' '. $property->zipCode; ?></h2>
                            <h4 id="ListingPrice"><?php 
                                if ($property->For_Rent_Bool == 1) {
                                    echo 'For Rent: $' . number_format($property->Price) . '/mo';
                                }
                                else echo 'For Sale: $' . number_format($property->Price);
                                ?>
                            </h4>
                        </div>
                        <div class="visible-xs visible-sm">
                            <p><?php echo $property->Beds .' Bd' . ' | '. $property->Baths .' Ba' . ' | '. $property->Type_of_Home; ?></p>
                            <p><?php
                                    if ($property->Pets_Allowed_Bool == 1) {
                                        echo "<span><img style='vertical-align:middle' class='petIcon' src='images/petIcon.png'</span> Pets Allowed";
                                    }
                                ?>
                            </p>
                        </div>
                        <div>
                            <p><?php echo $property->Description ?></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div id="map"></div>
                    </div>
                </div>
                <div class="modal fade" id="realtorModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <img id="logo2" src="images/logo2.png">
                                <span style="font-size: large" class="modal-title"> <?php echo $realtor->first_name . ' ' . $realtor->last_name; ?></span>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div style="float: left;" class="col-sm-4 col-xs-4 col-md-3">
                                        <img class="realtorPic img-responsive" src="<?php echo $realtorPic?>">
                                    </div>
                                    <div style="float: left" class="col-sm-offset-1 col-sm-6 col-xs-8 col-md-offset-1 col-md-7">
                                        <p class="textXXLarge"><?php echo $realtor->email;?></p>
                                        <p class="textXLarge"><?php echo $realtor->phone;?></p>
                                        <p class="textLarge">Licence #: <?php echo $realtor->licence ;?></p>
                                        <p class="textLarge"><?php echo $realtor->experience;?> Years of Experience</p>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button data-dismiss="modal" class="btn btn-primary">Close</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div><!--Container ends here-->
            <div class="push"></div>
        </div><!--Wrapper ends here-->
        <?php include 'includes/footer.html'; ?><!--Footer inserted here-->
    </body>
</html>
