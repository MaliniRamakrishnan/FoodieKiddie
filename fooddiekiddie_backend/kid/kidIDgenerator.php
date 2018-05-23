<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

//session_start();
require "../dbconnect.php";
$result_count = 1;
$temp01 = "KID";
$temp23 = strtoupper("ERO");
$temp456 = "123";

//while($result_count > 0){ 
$temp789 = mt_rand(100,999);
$tempID = $temp01.$temp23.$temp456.$temp789;
//}

$_SESSION['kid'] = $tempID;

?>