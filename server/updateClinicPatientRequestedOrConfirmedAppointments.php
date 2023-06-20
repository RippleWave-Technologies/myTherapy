<?php
	$data = array();
	$amka = $_POST["amka"];
	$afm = $_POST["afm"];
	$date = $_POST["date"];
	$act = $_POST["act"];
	$service = $_POST["service"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="myTherapy";

	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    $dbh->set_charset("utf8");

	mysqli_select_db($dbh, $dbname);

	if ($act='0') {
	$sql = "DELETE FROM appointment 
			WHERE amka='$amka' AND afm='$afm' AND date='$date'";
	} else {
		$sql = "UPDATE appointment 
			SET status=status+1, service='$service' 
			WHERE amka='$amka' AND afm='$afm' AND date='$date'";
		mysqli_query($dbh, $sql);
	}

	mysqli_close($dbh);
?>