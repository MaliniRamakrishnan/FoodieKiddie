<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$_SESSION['mnames'] = $data->momID;
$_SESSION['knames'] = $data->kidID;
$_SESSION['inames'] = $data->itemID;

$sql = "INSERT INTO wish (momid,kidid,itemid) VALUES ('".$_SESSION['mnames']."','".$_SESSION['knames']."','".$_SESSION['inames']."');";

     if ($connection->query($sql) === TRUE) {
	echo json_encode(array("data"=>"success"));
     }else {
	echo json_encode(array("data"=>$connection->error));
     } 
?>