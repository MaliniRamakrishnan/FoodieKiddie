<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id = $data->id;

$sql1 = "SELECT * FROM `kitchenWorkers` WHERE chefID = '".$id."';";
$result1 = mysqli_query($connection, $sql1);
$outp1 = array();
if ($result1->num_rows == 0) { echo json_encode("kitchenWorkers details: ".$connection->err); }
else { $outp1 = $result1->fetch_all(MYSQLI_ASSOC); }

$sql2 = "SELECT * FROM `Addresses` WHERE locationID = (SELECT kitchenID FROM `kitchenWorkers` WHERE chefID = '".$id."');";
$result2 = mysqli_query($connection, $sql2);
$outp2 = array();
if ($result2->num_rows == 0) { echo json_encode("Kitchen Address details: ".$connection->err); }
else { $outp2 = $result2->fetch_all(MYSQLI_ASSOC); }

$output = array();
array_push($output,$outp1,$outp2);
echo json_encode($output);

?>