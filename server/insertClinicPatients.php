<?php
    $amka = $_POST["amka"];
    $name = $_POST["name"];
    $surname = $_POST["surname"];
    $address = $_POST["address"];
    $addressNumber = $_POST["addressNumber"];
    $city = $_POST["city"];
    $postcode = $_POST["postcode"];
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
    $patientQuery = "SELECT * FROM patient 
		     WHERE amka='$amka' AND name='$name' AND surname='$surname' AND
		     address='$address' AND addressNumber='$addressNumber' AND
		     city='$city' AND postcode='$postcode'";

    $therapyResult = mysqli_query($dbh, $therapyQuery);
    $patientResult = mysqli_query($dbh, $patientQuery);

    if (mysqli_num_rows($therapyResult) > 0 && mysqli_num_rows($patientResult) > 0) {

        // Both therapy and patient entries exist
        $sql = "SELECT * FROM session WHERE afm='$afm' AND amka='$amka'";
        $result = mysqli_query($dbh, $sql);

	if (mysqli_num_rows($result) > 0) {
        // Row already exists
		$response = 1;
	}
	else{
		// Row doesn't exist, create a new row
        $response = 2;
    	$sqlinsert = "INSERT INTO session (amka, afm) VALUES ('$amka', '$afm')";
		mysqli_query($dbh, $sqlinsert);
	}

    } else {
        // Either therapy or patient entry doesn't exist
	    $response = 0;
    }

    echo $response;
    mysqli_close($dbh);
?>
