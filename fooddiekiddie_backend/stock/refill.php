<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$kitchenid = $data->kitchenID;
$ingredientid = $data->ingID;

$sql = "DELETE FROM `kitchenStock` WHERE kitchenID='$kitchenid' AND ingID = '$ingredientid'";

if($connection->query($sql) === TRUE) { echo json_encode("refilled"); }
else { echo json_encode("ERROR"); }

?>