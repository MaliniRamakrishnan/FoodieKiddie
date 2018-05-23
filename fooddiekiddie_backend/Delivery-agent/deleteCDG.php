<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id=$data->id; $role=$data->role;

if($role == "chef") { $deleteSQL1 = "DELETE FROM `kitchenWorkers` WHERE chefID='$id'"; }
else { $deleteSQL1 = "DELETE FROM `deliveryGuy` WHERE dgID='$id'"; }
$deleteSQL2 = "DELETE FROM `user` WHERE id='$id'";

if ($connection->query($deleteSQL1) === TRUE) {
   if($connection->query($deleteSQL2) === TRUE) { echo json_encode("deleted"); }
   else { echo json_encode("user ERROR" . $connection->error); }
}
else { echo json_encode($role . " ERROR" . $connection->error); }

?>