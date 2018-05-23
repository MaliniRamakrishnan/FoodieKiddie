<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$id = $data->userId;

$sql = "SELECT user.email, mom.phone, mom.name FROM `user` INNER JOIN `mom` WHERE mom.momID=user.id AND user.id='".$id."'";
$result = mysqli_query($connection, $sql);
$row=mysqli_fetch_array($result,MYSQLI_ASSOC);

if(mysqli_num_rows($result) > 0){
echo json_encode($row);
}
?>