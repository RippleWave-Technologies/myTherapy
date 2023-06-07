<?php
	$data = array();

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT afm, GROUP_CONCAT(amka) AS grouped_amkas FROM session GROUP BY afm";

	$result = mysqli_query($dbh, $sql);


	while ($row = mysqli_fetch_array($result)) {

		$data[$row['afm']] = $row['grouped_amkas'];


	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>