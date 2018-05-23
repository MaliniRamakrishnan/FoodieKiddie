<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

//$raw_data = file_get_contents("php://input");
//$data = json_decode($raw_data);
// $dgID = $data->dgid;

$dgID = "DGFA268001";

//$sql = "SELECT orders.orderID,kid.name,kid.kidClassSection,school.schoolName FROM `orders`,`kid`,`school` WHERE orders.schoolID = school.schoolID AND orders.kidID = kid.kidID AND orders.dgID = '".$dgID."'";

$sql = "SELECT orders.orderID,kid.name,kid.kidClassSection,school.schoolName FROM `orders`,`kid`,`school` WHERE orders.schoolID = school.locationID  AND orders.kidID = kid.kidID AND orders.dgID = '".$dgID."'";
$result = $connection->query($sql);

if($result){
 $answer = array();
 while ($row = $result->fetch_object()) { array_push($answer,$row); } 
 echo json_encode(array("data"=>$answer));}
?>
