<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$names = $data->kidIds;
$sql = "DELETE FROM `cart` WHERE kidId = '".$names."'";
$result = $connection->query($sql);
  if ($connection->query($sql) === TRUE) {
    echo json_encode(array("data"=>"suess"));
}else {
    echo json_encode(array("data"=>"failed"));
} 
    
?>