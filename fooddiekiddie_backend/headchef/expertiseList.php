<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$expertiseSQL = "SELECT * FROM `cuisinesList`";
$result = $connection->query($expertiseSQL);
$outp = array();
$outp = $result->fetch_all(MYSQLI_ASSOC);
if($result->num_rows > 0) { echo json_encode($outp); }
else { echo json_encode($connection->error); }

?>