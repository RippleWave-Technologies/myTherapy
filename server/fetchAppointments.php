<?php
	$data = array();

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT * FROM appointment";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
        $amka = $row["amka"];
        $afm = $row["afm"];

        $status = $row["status"];
        $service= $row["service"];

		$arr = array(
			"amka" => $amka,
			"afm" => $afm,
			"status" => $status,
			"service" => $service
		);

		$data[$row['date']] = $arr;

	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>