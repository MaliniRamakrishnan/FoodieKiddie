<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$odID = $data->odID;

$ing = "SELECT * FROM orderItemDetails WHERE odID = '$odID';";
$ingresult = $connection->query($ing);
$ingarr = array();
$ingarr = $ingresult->fetch_all(MYSQLI_ASSOC);

if($ingresult->num_rows > 0) { echo json_encode(array("data"=>$ingarr)); }
else { echo json_encode(array("data"=>$connection->error)); }

?>