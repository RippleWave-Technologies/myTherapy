<?php
    $data = array();
    $afm = $_POST["afm"];

    // Database connection details
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    // Prepare the SQL query
    $sql = "DELETE FROM therapy WHERE afm ='$afm'";

    mysqli_query($dbh, $sql);

    mysqli_close($dbh);
?>