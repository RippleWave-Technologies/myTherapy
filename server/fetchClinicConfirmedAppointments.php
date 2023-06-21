<?php
    $data = array();
    $date = $_POST['date'];
    $afm = $_POST['afm'];

    // Database connection details
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "myTherapy";

    $dbh = mysqli_connect($host, $uname, $pass) or die("Cannot connect");
    $dbh->set_charset("utf8");

    mysqli_select_db($dbh, $dbname);

    // Prepare the SQL query
    $query = "SELECT appointment.*, patient.name, patient.surname, service.code, service.name AS service_name 
        FROM appointment 
        INNER JOIN patient ON appointment.amka = patient.amka 
        INNER JOIN service ON appointment.service = service.code 
        WHERE DATE(appointment.date) = '$date' AND appointment.afm = '$afm' AND (appointment.status = 2 OR appointment.status = 3)";

    $result = mysqli_query($dbh, $query);
    while ($row = mysqli_fetch_assoc($result)) {

        $amka = $row['amka'];
        $name = $row['name'];
        $surname = $row['surname'];
        $code = $row['code'];
        $serviceName = $row['service_name'];

        $arr = array(
            "name" => $name,
            "surname" => $surname,
            "code" => $code,
            "service_name" => $serviceName
        );

        $data[$amka.$row['date']] = $arr;
    }

    header("Content-Type: application/json");
    echo json_encode($data);

    mysqli_close($dbh);
?>