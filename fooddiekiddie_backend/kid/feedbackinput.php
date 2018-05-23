<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$_SESSION['mnames'] = $data->momid;
$_SESSION['knames'] = $data->kidid;
$_SESSION['inames'] = $data->orderid;
$_SESSION['fnames'] = $data->feedid;
$_SESSION['rnames'] = $data->ratingid;

$sql = "INSERT INTO feedback (momID,kidID,orderID,feedID,ratingID) VALUES ('".$_SESSION['mnames']."','".$_SESSION['knames']."','".$_SESSION['inames']."','".$_SESSION['fnames']."','".$_SESSION['rnames']."');";

     if ($connection->query($sql) === TRUE) {
	echo json_encode(array("data"=>"success"));
     }else {
	echo json_encode(array("data"=>$connection->error));
     } 
?>