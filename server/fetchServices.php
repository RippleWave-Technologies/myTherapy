<?php
	$data = array();

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT * FROM service";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
        $name = $row["name"];
        $price = $row["price"];

        $description = $row["description"];

		$arr = array(
			"name" => $name,
			"price" => $price,
			"description" => $description
		);

		$data[$row['code']] = $arr;

	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>