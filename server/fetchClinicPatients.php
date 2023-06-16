<?php
	$data = array();
	$afm = $_POST["afm"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
  	$dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	$sql = "SELECT * FROM session JOIN patient using(amka) WHERE afm='$afm' ORDER BY surname";

	$result = mysqli_query($dbh, $sql);


	while ($row = mysqli_fetch_array($result)) {
		$nested_data = array();       
        	$nested_data['name'] = $row['name'];
        	$nested_data['surname'] = $row['surname'];
		$nested_data['address'] = $row['address'];        
        	$nested_data['addressNumber'] = $row['addressNumber'];
        	$nested_data['city'] = $row['city'];
		$nested_data['postcode'] = $row['postcode'];

		$data[$row['amka']] = $nested_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>