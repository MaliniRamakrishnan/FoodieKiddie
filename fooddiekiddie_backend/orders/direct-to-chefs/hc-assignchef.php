<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$o = $data->odID;
$c = $data->chefID;

$assignmentSQL = "UPDATE orderDetails SET chefID = '".$c."' WHERE odID = '".$o."';"; 

if ($connection->query($assignmentSQL) === TRUE) {
    echo json_encode(array("data"=>"success"));
}
else { echo json_encode(array("data"=>$connection->error)); }

?>