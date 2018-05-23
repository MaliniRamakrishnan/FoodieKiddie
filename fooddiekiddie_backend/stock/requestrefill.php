<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

session_start();

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$ingID = $data->ingID;
$kID = $data->kitchenID;
$chefID = $data->chefID;

$sql = "INSERT INTO `kitchenStock` VALUES('$kID','$ingID','$chefID');";
if ($connection->query($sql) === TRUE) { echo json_encode("requested"); }
else { echo json_encode("REQUEST ERROR" . $connection->error); }

?>