<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$names = $data->momID;
//$names = "PAJO660768";


$sql = "SELECT kidID FROM `kid` WHERE momID = '".$names."'";
$result = $connection->query($sql);
if($result){
 $answer = array();
 while ($row = $result->fetch_object()) { 

     array_push($answer,$row); 
     } 
 echo json_encode(array("data"=>$answer));}
 else
 {
     echo json_encode(array("data"=>"failed"));
 }
 
?>