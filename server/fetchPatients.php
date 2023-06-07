<?php
	$data = array();

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT * FROM patient";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
        $name = $row["name"];
        $surname = $row["surname"];

        $city = $row["city"];
        $address = $row["address"];
        $addressNumber = $row["addressNumber"];
        $postcode = $row["postcode"];

		$arr = array(
			"name" => $name,
			"surname" => $surname,
			"city" => $city,
			"address" => $address,
			"addressNumber" => $addressNumber,
			"postcode" => $postcode
		);

		$data[$row['amka']] = $arr;

	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>