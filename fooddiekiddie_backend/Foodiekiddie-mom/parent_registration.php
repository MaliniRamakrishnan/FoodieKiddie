<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

session_start();
require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);

$_SESSION['email'] = $data->email; 
$_SESSION['password'] = $data->password;
$_SESSION['role'] = $data->role;
$_SESSION['name'] = $data->name; 
$_SESSION['phone'] = $data->phone;

require "../email_check.php";
require "../ID_generator.php";

if($duplicate){ 
echo json_encode(array("data"=>"Exists")); 
}else{
 $sql1 = "INSERT INTO `user` VALUES('".$_SESSION['id']."','".$_SESSION['email']."','".$_SESSION['password']."','".$_SESSION['role']."');"; 
 if ($connection->query($sql1) === TRUE) {
     $sql2 = "INSERT INTO `mom` VALUES('".$_SESSION['id']."','".$_SESSION['name']."','".$_SESSION['phone']."');";	 
     if ($connection->query($sql2) === TRUE) {
	echo json_encode(array("data"=>$_SESSION['id']));
     }else {
	echo json_encode(array("data"=>"failed")); 
     }
 }
 else { echo json_encode(array("data"=>"failed")); }
}
?>