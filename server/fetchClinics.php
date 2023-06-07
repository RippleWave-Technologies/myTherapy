<?php
	$data = array();

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT * FROM therapy";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
        $name = $row["name"];
        $email = $row["email"];

        $address = $row["address"];

		$addressNumber = $row["addressNumber"];
		$postcode = $row["postcode"];
		$city = $row["city"];

		$arr = array(
			"name" => $name,
			"email" => $email,
			"address" => $address,
			"addressNumber" => $addressNumber,
			"postcode" => $postcode,
			"city" => $city
		);

		$data[$row['afm']] = $arr;

	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>