<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);

$tempID = mt_rand(1000,9999);
$img = $data->imageStr;

$sql = "INSERT INTO `images` VALUES('".$tempID."','".$img."');";	 
     if ($connection->query($sql) === TRUE) {
	echo json_encode(array("data"=>"success"));
    }else {
	echo json_encode(array("data"=>"failed")); 
     }
     

?>