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

    // Check if a row with the given AFM exists
    $existingRowQuery = "SELECT * FROM therapy WHERE afm='$afm'";
    $result = mysqli_query($dbh, $existingRowQuery);

    if (mysqli_num_rows($result) > 0) {
        // Row already exists
        echo "AFM already exists in the database.";
    } else {
        // Row doesn't exist, create a new row
        $sql = "INSERT INTO therapy (afm, name, email, address, addressNumber, postcode, city) 
                VALUES ('$afm', '$name', '$email', '$address', '$addressNumber', '$postcode', '$city')";
    }

    mysqli_query($dbh, $sql);

    header("Content-Type: application/json");
    mysqli_close($dbh);
?>