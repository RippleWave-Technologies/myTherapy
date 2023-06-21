<?php
    $code = $_POST["code"];

    // Database connection details
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    // Prepare the SQL query
    $sql = "DELETE FROM service WHERE code ='$code'";
    
    mysqli_query($dbh, $sql);

    if (mysqli_errno($dbh) == 1451) {
        $response = 0; // Foreing key error occurred
    } else {
        $response = 1; // Foreing key error did not occur
    }

    echo $response;

    mysqli_close($dbh);
?>