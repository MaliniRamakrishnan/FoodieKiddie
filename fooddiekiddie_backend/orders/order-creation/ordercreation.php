<?php
session_start();
$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);

$orderingSchoolID = "SELECT schoolAddressID FROM kid WHERE kidID = '" . $data->orderPart->kidID . "';";

$schoolLocationResult = $connection->query($orderingSchoolID);
$sla = array();
$sla = $schoolLocationResult->fetch_all(MYSQLI_ASSOC);

if($schoolLocationResult->num_rows > 0) $orderSchoolID=$sla[0]['schoolAddressID'];

$ordersql = "INSERT INTO orders(status,deliveryDate,deliveryTime,kidID,schoolID) VALUES('PLACED','" . $data->orderPart->deliveryDate . "','" . $data->orderPart->deliveryTime . "','" . $data->orderPart->kidID . "','" . $orderSchoolID . "');"; 

if ($connection->query($ordersql) === TRUE) {
 $getorderid = "SELECT orderID FROM orders WHERE kidID = '". $data->orderPart->kidID ."' AND deliveryDate = '" . $data->orderPart->deliveryDate . "' AND deliveryTime = '" . $data->orderPart->deliveryTime . "';";
 $orderIDresult = $connection->query($getorderid);
 if($orderIDresult->num_rows > 0) {
     $orderID = $orderIDresult->fetch_assoc()["orderID"];
     //echo json_encode(array("data"=>$orderID));
 }
 else { echo json_encode(array("error"=>$connection->error)); }
}
else { echo json_encode(array("error"=>$connection->error)); }

?>