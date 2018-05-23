<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

//session_start();
require "dbconnect.php";

$sql = "SELECT * from `user` WHERE email='".$_SESSION['email']."' AND role='".$_SESSION['role']."';";
$result = mysqli_query($connection, $sql);
if(mysqli_num_rows($result) > 0){ $duplicate = TRUE; }
else{ $duplicate = FALSE; }

?>