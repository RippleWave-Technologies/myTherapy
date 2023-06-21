<?php
    $data = array();
    $afm = $_POST["afm"];
    $name = $_POST["name"];
    $email = $_POST["email"];
    $address = $_POST["address"];
    $addressNumber = $_POST["addressNumber"];
    $postcode = $_POST["postcode"];
    $city = $_POST["city"];

    // Database connection details
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

    mysqli_close($dbh);
?>