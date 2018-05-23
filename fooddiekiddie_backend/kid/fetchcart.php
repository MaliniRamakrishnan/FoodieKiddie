<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$names = $data->momId;
$knames = $data->kidId;
// $names ="PAMU589988";
// $knames = "SCHOOLERO123976";

$sql = "SELECT cart.momId,cart.kidId,items.price,items.name FROM `cart` RIGHT JOIN items ON cart.ItemId = items.id WHERE cart.momId = '".$names."' AND cart.kidId = '".$knames."'";

$result = $connection->query($sql);
if($result){
 $answer = array();
 while ($row = $result->fetch_object()) { array_push($answer,$row); } 
 echo json_encode(array("data"=>$answer));}
?>