<?php
    $data = array();
    $code = $_POST["code"];
    $name = $_POST["name"];
    $price = $_POST["price"];
    $description = $_POST["description"];

    // Database connection details
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    $sql = "UPDATE service SET name='$name', price='$price', description='description' 
            WHERE code='$code'";

    mysqli_query($dbh, $sql);

    header("Content-Type: application/json");
    mysqli_close($dbh);
?>