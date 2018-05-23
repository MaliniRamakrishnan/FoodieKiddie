<?php
require "ordercreation.php";
$done;
$odd = array();
$odd = $data->orderDetailsPart;

foreach($odd as $oddrow){
    //echo json_encode(array("data"=>$x->itemID));
    $orderdetailsql = "INSERT INTO orderDetails(orderID,itemID,qty) VALUES('$orderID','" . $oddrow->itemID . "','" . $oddrow->quantity . "');"; 
    if ($connection->query($orderdetailsql) === TRUE) { $done=true; }
    else { echo json_encode(array("error"=>$connection->error)); }
}
if($done){ }
else { echo json_encode(array("error"=>"orderdetails table failed")); }

?>