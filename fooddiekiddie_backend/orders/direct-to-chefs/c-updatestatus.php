<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$odID = $data->odID;

$updateSQL = "UPDATE `orderDetails` SET preparation_status = 1 WHERE odID = '$odID';";

if ($connection->query($updateSQL) === TRUE) { echo json_encode(array("data"=>"updated")); }
else { echo json_encode(array("data"=>$connection->error)); }

?>