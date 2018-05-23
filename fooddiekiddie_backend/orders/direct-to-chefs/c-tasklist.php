<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$date = $data->date;
$kitchenID = $data->kitchenID;
$chefID = $data->chefID;

$ordersString = "SELECT orders.orderID, orders.deliveryTime, orderDetails.odID, orderDetails.qty, items.id, items.name, orderDetails.preparation_status FROM orders 
    INNER JOIN orderDetails ON orders.orderID = orderDetails.orderID
    RIGHT JOIN items ON items.id = orderDetails.itemID
    WHERE orders.kitchenID = '$kitchenID' AND orders.deliveryDate = '$date' AND orderDetails.chefID = '$chefID';";

$ordersresult = $connection->query($ordersString);
$orders = array();
$orders = $ordersresult->fetch_all(MYSQLI_ASSOC);
if($ordersresult->num_rows > 0) { echo json_encode(array("data"=>$orders)); }
else { echo json_encode($connection->error); }

?>