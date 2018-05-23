<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$oid = $data->orderID;
  
$ois = "SELECT * FROM orders
    INNER JOIN orderDetails ON orders.orderID = orderDetails.orderID
    INNER JOIN orderItemDetails ON orderDetails.odID = orderItemDetails.odID
    WHERE orders.orderID = '$oid';";
    
$oiresult = $connection->query($ois);
$oi = array();
$oi = $oiresult->fetch_all(MYSQLI_ASSOC);
if($oiresult->num_rows > 0) { echo json_encode(array("data"=>$oi)); }
else { echo json_encode($connection->error); }

?>