<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

session_start();

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);

$_SESSION['email'] = $data->email; $_SESSION['password'] = $data->password;
$_SESSION['role'] = $data->role; $_SESSION['name'] = $data->name; $_SESSION['phone'] = $data->phone;
$_SESSION['expertise'] = $data->expertise; $_SESSION['kitchenID'] = $data->kitchenID;

require "../email_check.php";
require "../ID_generator.php";

if($duplicate){ echo json_encode("Duplicate Present"); }
else{
 $sql1 = "INSERT INTO `user` VALUES('".$_SESSION['id']."','".$_SESSION['email']."','".$_SESSION['password']."','".$_SESSION['role']."');";
 if ($connection->query($sql1) === TRUE) {
     //INSERTED INTO USERS, NOW INTO kitchenWorkers
     $sql2 = "INSERT INTO `kitchenWorkers` VALUES('".$_SESSION['id']."','".$_SESSION['kitchenID']."','".$_SESSION['name']."','".$_SESSION['phone']."','".$_SESSION['expertise']."');";
     if($connection->query($sql2) === TRUE) { echo json_encode("inserted"); }
     else { echo json_encode("Error in kitchenWorkers insertion: ". $sql2 . "<br>" . $connection->error); }
 }
 else { echo json_encode("Error in USER insertion: " . $sql1 . "<br>" . $connection->error); }
}

?>