<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
//$names = $data->fudName;
$sql = "SELECT id, name, price, category, typeOfFood, cuisine FROM `items`";
$result = $connection->query($sql);
if($result){
 $answer = array();
 while ($row = $result->fetch_object()) { array_push($answer,$row); } 
 echo json_encode(array("data"=>$answer));}
?>