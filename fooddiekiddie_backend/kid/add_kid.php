<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$_SESSION['momId'] = $data->momId;
$_SESSION['kidName'] = $data->kidName;
$_SESSION['regNum'] = $data->kidRegNo;
$_SESSION['kidsect'] = $data->kidsection;
$_SESSION['dob'] = $data->kiddob;
$_SESSION['city'] = $data->locCity;
$_SESSION['imageData'] = $data->imageStr;


require "./kidIDgenerator.php"; 
require "./schoolIDgenerator.php";
$schoolAddressId = "SCHALV123";

$sql = "INSERT INTO kid (kidID,momID,name,kidPic,dob,schoolAddressID,kidClassSection,kidRegisterNumber) VALUES ('".$_SESSION['kid']."','".$_SESSION['momId']."','".$_SESSION['kidName']."','".$_SESSION['imageData']."','".$_SESSION['dob']."','.$schoolAddressId.','".$_SESSION['kidsect']."','".$_SESSION['regNum']."');";

     if ($connection->query($sql) === TRUE) {
	echo json_encode(array("data"=>$_SESSION['kid']));
     }else {
	echo json_encode(array("data"=>"failed"));
     } 
     

?>