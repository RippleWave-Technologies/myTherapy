<?php
    $data = array();
    $code = $_POST["code"];
    $name = $_POST["name"];
    $price = $_POST["price"];
    $description = $_POST["description"];

    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    // Check if a row with the given code exists
    $existingRowQuery = "SELECT * FROM service WHERE code='$code'";
    $result = mysqli_query($dbh, $existingRowQuery);

    if (mysqli_num_rows($result) > 0) {
        // Row already exists
        $response = 0;
    } else {
        // Row doesn't exist, create a new row
        $response = 1;
        $sql = "INSERT INTO service (code, name, price, description) 
                VALUES ('$code', '$name', '$price', '$description')";
    }

    mysqli_query($dbh, $sql);

    echo $response;

    mysqli_close($dbh);
?>