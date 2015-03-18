<?php
    session_start();
    require_once 'includes/login.php';
    
    if (!isset($_SESSION['username'])){
        header('Location:index.php');
    }
    
    $username = $_SESSION['username'];
    
    if (!isset($_SESSION['accountUpdated'])) {
        $_SESSION['accountUpdated'] = 0;
    }
    
    $db = mysql_connect($db_hostname,$db_username,$db_password);
    if(!$db) die("Unable to connect to MySQL: ".mysql_error());
    mysql_select_db($db_database) or die("Unable to select database: ".mysql_error());
    
    $sql = "SELECT * FROM Realtors WHERE username = '$username'";
    $result = mysql_query($sql) or die("<b>Error: ". mysql_error());
    
    $profilePic = substr(mysql_result($result,0,'profilePic'),3);
    $firstname = mysql_result($result,0,'first_name');
    $lastname = mysql_result($result,0,'last_name');
    $phone = mysql_result($result,0,'phone');
    $email = mysql_result($result,0,'email');
    $experience = mysql_result($result,0,'experience');
    $licence = mysql_result($result,0,'licence');
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Profile</title>
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
                        <h1 class="TextTooBigForMobile"><span class="glyphicon glyphicon-pencil"></span> Edit Profile</h1>
                        <hr>
                    </div>
                    <div>
                        <div>
                            <?php
                                if ($_SESSION['accountUpdated'] == 1) {
                                    echo "<h4 style='text-align:center;'> Profile Updated </h4><br>";
                                    $_SESSION['accountUpdated'] = 0;
                                }
                            ?>
                        </div>
                        <div class="row">
                            <div id="profilePic" class="col-sm-4">
                                <img class="img-responsive" src="<?php echo $profilePic?>">
                            </div>
                            <div class="col-sm-8">
                                <form id="changeAccountForm" class="form-horizontal changeForm" method="post" action="includes/editAccount.php" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="firstname">First Name</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="firstname" name="firstname" type="text" class="form-control" placeholder="<?php echo $firstname ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="lastname">Last Name</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="lastname" name="lastname" type="text" class="form-control" placeholder="<?php echo $lastname ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="phone">Phone Number</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="phone" name="phone" class="form-control" placeholder="<?php echo $phone ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="email">Email</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="email" name="email" type="email" class="form-control" placeholder="<?php echo $email ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="experience">Years of Experience</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="experience" name="experience" type="number" class="form-control" placeholder="<?php echo $experience ?>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label  for="licence">Licence Number</label>
                                        </div>
                                        <div class="col-sm-7">
                                            <input id="licence" name="licence" type="text" class="form-control" placeholder="<?php echo $licence ?>">
                                        </div>
                                    </div>
                                    
                                    </br>
                                    
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label for="profilePic">Profile Picture</label></br>
                                        </div>
                                        <div class="col-sm-7">
                                            <input name="uploadFile" type="file"></br>
                                        </div>
                                    </div>
                                    
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
                    </div>
                </div> <!--Container ends here-->
            <div class="push"></div>
        </div>
        <?php include 'includes/footer.html'; ?><!--Footer inserted here-->
    </body>
</html>