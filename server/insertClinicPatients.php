<?php
    $amka = $_POST["amka"];
    $afm = $_POST["afm"];

    // Database connection details
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("Cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    // Check if the therapy and patient entries exist
    $therapyQuery = "SELECT * FROM therapy WHERE afm='$afm'";
    $patientQuery = "SELECT * FROM patient WHERE amka='$amka'";

    $therapyResult = mysqli_query($dbh, $therapyQuery);
    $patientResult = mysqli_query($dbh, $patientQuery);

    if (mysqli_num_rows($therapyResult) > 0 && mysqli_num_rows($patientResult) > 0) {
        // Both therapy and patient entries exist, insert into the session table
        $sql = "INSERT INTO session (amka, afm) VALUES ('$amka', '$afm')";

        if (mysqli_query($dbh, $sql)) {
            echo "Session entry added successfully.";
        } else {
            echo "Error: " . mysqli_error($dbh);
        }
    } else {
        // Either therapy or patient entry doesn't exist
        echo "Therapy or patient entry not found.";
    }

    mysqli_close($dbh);
?>
