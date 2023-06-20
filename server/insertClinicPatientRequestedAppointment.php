<?php
$data = array();
$date = $_POST["date"];
$amka = $_POST["amka"];
$afm = $_POST["afm"];

$host = "localhost";
$uname = "root";
$pass = "";
$dbname = "myTherapy";

$dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
$dbh->set_charset("utf8");

mysqli_select_db($dbh, $dbname);

// Check if a row with the given time exists 
$existingRowQuery = "SELECT * FROM appointment WHERE date='$date' AND afm='$afm'";
$result = mysqli_query($dbh, $existingRowQuery);

if (mysqli_num_rows($result) > 0) {
    // Row already exists
    $response = 0;
    echo " Time you have chosen already has a scheduled appointment.";
} else {
    // Row doesn't exist, create a new row
    $response = 1;
    $sql = "INSERT INTO appointment (amka, afm, date, status, service) 
            VALUES ('$amka', '$afm', '$date', 1, 'uAS')";
}


mysqli_query($dbh, $sql);

echo $response;

mysqli_close($dbh);
?>