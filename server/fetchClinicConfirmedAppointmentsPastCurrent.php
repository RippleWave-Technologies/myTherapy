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
	$sql = "SELECT * FROM appointment 
			 JOIN patient using(amka) 
			 WHERE afm='$afm' AND status=2 AND DATE(date)<=CURDATE()";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
		$amka = $row['amka'];
		$name = $row['name'];
		$surname = $row['surname'];
		$date = $row['date'];

		$arr = array(
			"name" => $name,
			"surname" => $surname
		);

		$data[$amka.$date] = $arr;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>
