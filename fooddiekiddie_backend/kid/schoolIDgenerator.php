<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$result_count = 1;
//$ts123 = "SCH";
//$ts456 = strtoupper(substr("coimbatore",0,3));

//while($result_count > 0){ 
  //  $ts789 = mt_rand(10000,99999);
  //  $tsID = $ts123.$ts456.$ts789;
  $tsID = "SCHALV123"
//}

?>