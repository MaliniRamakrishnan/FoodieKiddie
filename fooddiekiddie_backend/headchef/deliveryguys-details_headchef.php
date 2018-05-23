<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$kitchenid = $data->kitchenID;

$joinedSQL = "SELECT user.id, deliveryGuy.name, user.email, deliveryGuy.phone, deliveryGuy.currentLocationLatitude, deliveryGuy.currentLocationLongitude, deliveryGuy.orderAssignment FROM `user` INNER JOIN `deliveryGuy` ON user.id = deliveryGuy.dgID AND deliveryGuy.kitchenID = '".$kitchenid."'";
$result = $connection->query($joinedSQL);
$outp = array();
$outp = $result->fetch_all(MYSQLI_ASSOC);
if($result->num_rows > 0) { echo json_encode($outp); }
else { echo json_encode("Zero results"); }

?>