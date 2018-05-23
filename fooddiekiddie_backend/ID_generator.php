<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

//session_start();
require "dbconnect.php";
$result_count = 1;
$temp01 = strtoupper(substr($_SESSION['role'],0,2));
$temp23 = strtoupper(substr($_SESSION['name'],0,2));
$temp456 = substr($_SESSION['phone'],7,3);

while($result_count > 0){ 

$temp789 = mt_rand(100,999);
$tempID = $temp01.$temp23.$temp456.$temp789;

$sql = "SELECT * from `user` WHERE id='".$tempID."';";
$result = mysqli_query($connection, $sql);
$result_count = mysqli_num_rows($result);
}

$_SESSION['id'] = $tempID;

?>