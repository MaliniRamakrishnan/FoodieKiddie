<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$k = $data->kitchenID;
$d = $data->date;

$hcolist = "SELECT orders.orderID, items.name, items.cuisine, orderDetails.chefID, orderDetails.odID, orderDetails.preparation_status FROM orders INNER JOIN orderDetails ON orderDetails.orderID = orders.orderID RIGHT JOIN items ON items.id = orderDetails.itemID WHERE orders.kitchenID = '$k' AND orders.deliveryDate = '$d';";
//$hcolist = "SELECT * FROM orders";
$hcoresult = $connection->query($hcolist);
$hco = array();
$hco = $hcoresult->fetch_all(MYSQLI_ASSOC);

if($hcoresult->num_rows > 0) { echo json_encode(array("data"=>$hco)); }
else { echo json_encode(array("data"=>$connection->error)); }

?>