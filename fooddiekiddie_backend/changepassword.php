<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id = $data->userid; $role = $data->role;
$new = $data->newPassword; $old = $data->oldPassword;

$oldcheck = "SELECT * from `user` WHERE id='$id' AND password='$old' AND role='$role';";
$result = $connection->query($oldcheck);

if($result->num_rows > 0){
   $newinsert = "UPDATE `user` SET password='$new' WHERE id='$id'";
   if($connection->query($newinsert) === TRUE) { echo json_encode(array("data"=>"updated")); }
   else { echo json_encode(array("data"=>"user ERROR" . $connection->error)); }
}
else{
   echo json_encode(array("data"=>"Wrong old password"));
}

?>