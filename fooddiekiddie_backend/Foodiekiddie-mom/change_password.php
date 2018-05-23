<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$old = $data->oldPassword;
$new = $data->newPassword;
$id = $data->userId;


$sql = "UPDATE user SET password = '".$new."' WHERE id = '".$id."' and password = '".$old."' ";
if ($connection->query($sql) === TRUE) {
echo json_encode(array("data"=>"success"));
}else {
echo json_encode(array("data"=>"failed")); 
}

?>