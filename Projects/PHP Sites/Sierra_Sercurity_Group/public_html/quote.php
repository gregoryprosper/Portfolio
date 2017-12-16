<!-- 
    Created on : Jan 6, 2016, 1:35:59 AM
    Author     : Gregory Prosper
-->
<?php
require_once(dirname(__FILE__) . '/phpClasses/Util.php');

$succesful = false;
$postArray = filter_input_array(INPUT_POST);

if (Util::requiredFieldsSet($postArray)) {

    $email = $postArray["email"];
    $body = Util::getEmailBody($postArray);

    $headers = "From: " . $email . "\r\n";
    $headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";

    if (mail('vincemongio@sierrasecuritygroup.com', "Quote Request", $body, $headers)) {
        $succesful = true;
    }
}
?>

<html>
    <head>
        <title>Quote Confirmation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Sierra Security Group is a full-service security agency that specializes in facility security & surveillance, event protection, 
              and personal protection & transportation. Our licensed protective service professionals posess over 60 years of elite military, 
              law enforcement, and private security experience. We provide services within all 50 states of the United States and perform overseas assignments as needed.">

        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">

        <link rel="stylesheet" type="text/css" href="css/main.css"/>
        <link rel="stylesheet" type="text/css" href="css/quote.css"/>

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
            ga('create', 'UA-71617785-1', 'auto');
            ga('send', 'pageview');
        </script>
    </head>
    <body style="background-color: #F3F0F1">
        <!--Start of NavBar-->
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <img class="navbar-brand" src="images/logo_small.png"/>
                </div>

                <button id="lineButton" type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="facility_and_event_protection.html">Facility & Event Protection</a></li>
                        <li><a href="personal_protection_and_transportation.html">Personal Protection & Transportation</a></li>
                    </ul>
                </div>
            </div>
        </div><!--End of NavBar-->

        <div class="container">
            <div id="confirmation">
                <div style="display: <?php
                if (!$succesful) {
                    echo 'none';
                }
                ?>" class="confirmationText">
                    <h1>Thank You</h1>
                    <h4>One of our representatives will contact you as soon as possible with your quote.</h4>
                </div>
                <div style="display: <?php
                if ($succesful) {
                    echo 'none';
                }
                ?>" class="confirmationText">
                    <h1>Oops...</h1>
                    <h4>Something went wrong. Please try again.</h4>
                </div>
                <div id="backBanner">
                    <p><img id="backIcon" src="images/icons/back_icon.png"/><a href="index.html">Click here</a> to return to the site.</p>
                </div>
            </div>
        </div>
    </body>
</html>