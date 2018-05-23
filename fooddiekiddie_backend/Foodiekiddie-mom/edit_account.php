<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');
session_start();
require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id = $data->userId;
$contact = $data->contact;
$name = $data->name;
$flag = filter_var($data->flag, FILTER_VALIDATE_BOOLEAN);; 
$_SESSION['email'] = $data->email; 
$_SESSION['role'] = "parent";

require "../email_check.php";
       
if($duplicate and $flag){ 
echo json_encode(array("data"=>"Exists")); 
}else{
$sql = "UPDATE mom INNER JOIN user ON (mom.momID = user.id) SET mom.phone = '".$contact."', mom.name = '".$name."', user.email = '".$_SESSION['email']."' WHERE user.id = '".$id."'";
if ($connection->query($sql) === TRUE) {
echo json_encode(array("data"=>"success"));
}else {
echo json_encode(array("data"=>"failed")); 
}
}

?>