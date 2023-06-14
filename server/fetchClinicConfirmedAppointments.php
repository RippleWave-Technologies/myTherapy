<?php
    $date = $_POST['date'];
    $afm = $_POST['afm'];
    $status = $_POST['status'];

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
        WHERE DATE(appointment.date) = '$date' AND appointment.afm = '$afm' AND appointment.status = $status";

    $result = mysqli_query($dbh, $query);

    // Check if any rows were returned
    if (mysqli_num_rows($result) > 0) {
        // Fetch the rows and store them in an array
        $data = array();
        while ($row = mysqli_fetch_assoc($result)) {
            $data[] = array(
                'amka' => $row['amka'],
                'name' => $row['name'],
                'surname' => $row['surname'],
                'serviceCode' => $row['code'],
                'serviceName' => $row['service_name']
            );
        }

        // Convert the array to JSON
        $json = json_encode($data);

        // Output the JSON response
        header('Content-Type: application/json');
        echo $json;
    } else {
        // No appointments found
        $data = array('message' => 'No appointments found');
        $json = json_encode($data);
        header('Content-Type: application/json');
        echo $json;
    }

    // Close the database connection
    mysqli_close($dbh);
?>