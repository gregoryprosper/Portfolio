<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Confirmation Page</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<?php
      if (isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['email']) && isset($_POST['carMake']) && isset($_POST['carModel']) && isset($_POST['carColor']))
      {
          $firstName = $_POST['firstName'];
          $lastName = $_POST['lastName'];
		  $email = $_POST['email'];
		  $carMake = $_POST['carMake'];
		  $carModel = $_POST['carModel'];
		  $carColor = $_POST['carColor'];
      }
      
      
?>
</head>

<body>


<div id="wrapper">
  <div id="message">
   	<img src="images/car.gif" id="confirmationPic"/>
  	<div>
      <h1>That's All, Thank You <?php echo "$firstName $lastName";?></h1>
    </div>
  </div>
  	<div style="text-align:center">
    	<table id="confirmationTable">
        	<tr>
            	<td width="468">
                	<h3 id="centerText"> Car Make: <?php echo $carMake;?> </h3>
                </td>
                <td width="520">
                	<h3 id="centerText"> Car Model: <?php echo $carModel;?> </h3>
                </td>
            </tr>
            <tr>
            	<td>
                	<h3 id="centerText"> Car Color: <?php echo $carColor;?> </h3>
                </td>
                <td>
                      <h3 id="optionTextStyle"> Options: </h3>
                      
                      <div id="optionList"><p> <?php foreach($_POST['carOptions'] as 				$value){echo $value . '<br/>';}?></p>
                      </div>
                </td>
            </tr>
        </table>
    </div>
    <div id="subMessage">
    	<h2>We appreciate all your time and doing business with you!</h2>
    </div>
</div>
</body>
</html>
