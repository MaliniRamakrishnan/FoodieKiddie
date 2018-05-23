<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id=$data->id; $name=$data->name; $email=$data->email; $phone=$data->phone; $expertise=$data->expertise;

$updateSQL1 = "UPDATE `kitchenWorkers` SET name='$name', phone='$phone', expertise='$expertise' WHERE chefID='$id'";
$updateSQL2 = "UPDATE `user` SET email='$email' WHERE id='$id'";
if ($connection->query($updateSQL1) === TRUE) {
   if($connection->query($updateSQL2) === TRUE) { echo json_encode("updated"); }
   else { echo json_encode("user ERROR" . $connection->error); }
}
else { echo json_encode("kitchenWorkers ERROR" . $connection->error); }


?>