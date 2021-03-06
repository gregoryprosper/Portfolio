<?php 
    session_start();
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Williamsburg-Housing</title>
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
            <!--start of Jumbotron-->
            <div id="homePageJumbotron" class="jumbotron">
                <!--start of Search Area-->
                <div id="searchArea">
                    <div>
                        <h1 style="text-align: center;" class="TextTooBigForMobile">Search Locations</h1>
                    </div>
                    <!--Start of Search Bar-->
                    <div id="searchBar">
                        <form class="hidden-xs" action="listings.php" method="get"><!--Form for Mobile-->
                            <div class="input-group input-group-lg">
                                <input type="search" name="search" class="form-control" placeholder="Use 'Boca Raton' to Test">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"> <b class="glyphicon glyphicon-search"></b> </button>
                                </span>
                            </div>
                            <br>
                        </form>

                        <form class="visible-xs" action="listings.php" method="get"> <!--Form for Mobile-->
                            <div class="input-group">
                                <input type="search" name="search" class="form-control" placeholder="Use 'Boca Raton' to Test">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"> <b class="glyphicon glyphicon-search"></b> </button>
                                </span>
                            </div>
                            <br>
                        </form>
                    </div><!--End of Search Bar-->
                </div><!--End of Search Area-->
            </div><!--End of Jumbotron-->

            <!--start of first row-->
            <div class="container">
                <div class="row" id="mainRowContent">
                    <div class="col-md-5 hidden-xs hidden-sm">
                        <img style="height: 250px; width: 250px;" class="img-circle" src="images/workers.jpg">
                    </div>
                    <div class="col-md-7">
                        <h1 class="TextTooBigForMobile">Best Network of Realtors</h1>
                        <p>Here at Williamsburg-Housing, we will partner you with the best real estate agents available.</p>
                    </div>
                </div>
            </div><!--end of first row-->
            <!--start of second row-->
            <div style="height: auto; color: white; background:url('./images/hardwood.jpg')repeat-x center center;">
                <div class="container">
                    <div id="mainRowContent" class="row">
                        <div class="col-md-7">
                            <h1 class="TextTooBigForMobile">Always Up-to-date</h1>
                            <p>Browse more than 300,000 photos of kitchens, bathrooms, outdoor spaces and more. At Williamsburg-Housing, you
                               can be sure that your dream home won't pass you by.
                            </p>
                        </div>
                        <div class="col-md-5 hidden-xs hidden-sm">
                            <img style="height: 250px; width: 250px; float: right;" class="img-responsive img-circle" src="images/room.jpg">
                        </div>
                    </div>
                </div>
            </div><!--end of second row-->
            <!--start of third row-->
            <div class="container">
                <div class="row" id="mainRowContent">
                    <div class="col-md-5 hidden-xs hidden-sm">
                        <img style="height: 250px; width: 250px;" class="img-circle" src="images/cellphone.jpg">
                    </div>
                    <div class="col-md-7">
                        <h1 class="TextTooBigForMobile">Accessible from Anywhere</h1>
                        <p>You can keep track of that home you've been eyeing, no matter where you are. Our services are compatible with all devices
                           from smartphones to tablets.
                        </p>
                    </div>
                </div>
            </div><!--end of third row-->
            <div class="push"></div>
        </div>
        <?php include 'includes/footer.html'; ?>
    </body>
</html>
