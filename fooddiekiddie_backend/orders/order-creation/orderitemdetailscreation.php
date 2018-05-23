<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

$finished = false;

require "../../dbconnect.php";
require "orderdetailscreation.php";

$oidd = array();
$oidd = $data->orderItemDetailsPart;

foreach($oidd as $item){
    $getOdID = "SELECT * FROM orderDetails WHERE orderID = '$orderID' AND itemID='" . $item->ItemId . "';";
    $odIDresult = $connection->query($getOdID);
    $odIdArray = array();
    $odIdArray = $orderIDresult->fetch_all(MYSQLI_ASSOC);
    if($odIDresult->num_rows > 0){
        foreach($odIDresult as $odIDrow){
            $orderitemdetails = "INSERT INTO orderItemDetails(odID,ingID,chosenIngID,ingQty) VALUES('" . $odIDrow["odID"] . "','" . $item->ingId . "','" . $item->chosenIngId . "',NULL);";
            if($connection->query($orderitemdetails) === TRUE) { 
                $finished = true;
            }
            else { echo json_encode(array("data"=>$connection->error)); }
        }
    }
    //else { echo json_encode(array("odID data"=>"od id fetch failed")); }
}

if($finished) {
    require "../kitchen-allocation/kitchenselection.php";
    echo json_encode(array("data"=>"order creation success")); 
}

?>