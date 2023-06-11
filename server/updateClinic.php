<?php
$data = array();
$afm = $_GET["afm"];
$name = $_GET["name"];
$email = $_GET["email"];
$address = $_GET["address"];
$addressNumber = $_GET["addressNumber"];
$postcode = $_GET["postcode"];
$city = $_GET["city"];

$host = "localhost";
$uname = "root";
$pass = "";
$dbname = "myTherapy";

$dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
$dbh->set_charset("utf8");

mysqli_select_db($dbh, $dbname);

$sql = "UPDATE therapy SET name='$name', email='$email', address='$address', 
        addressNumber='$addressNumber', postcode='$postcode', city='$city' 
        WHERE afm='$afm'";

mysqli_query($dbh, $sql);

header("Content-Type: application/json");
mysqli_close($dbh);
?>
