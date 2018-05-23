<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: X-Requested-With");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Content-Type: application/json");

require "dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$email = $data->email; $password = $data->password; $role = $data->role;

$sql = "SELECT id from `user` WHERE email = '".$email."' AND password = '".$password."' AND role = '".$role."'";

$result = $connection->query($sql);
$outp = array();
$outp = $result->fetch_all(MYSQLI_ASSOC);
if($result->num_rows > 0) { echo json_encode(array("data"=>$outp[0]['id'])); }
else { echo json_encode(array("data"=>"failed")); }


?>