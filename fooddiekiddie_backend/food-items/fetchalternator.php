<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$itmId = $data->itmId;
$ingId = $data->fudname;
//  $itmId = "IT102";
//  $names = "Sugar";
//  $ingId = "ING114";

$sqln = "SELECT alternative_ing FROM `alternatives` WHERE itemID = '".$itmId."' AND ingredientsID = '".$ingId."';";
$result = $connection->query($sqln);
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $inglist=explode(',', $row['alternative_ing']);
    $ans=array();
    foreach($inglist as $out) {
        $out = trim($out," ");
        $sqln = "SELECT id,name FROM `ingredients` WHERE id = '".$out."';";
        $resultn = $connection->query($sqln);
        if ($resultn->num_rows > 0) {
            $rown = $resultn->fetch_assoc();
            array_push($ans,$rown);
        }
        else { echo "0 results"; }
    }
    echo json_encode(array("data"=>$ans));
}



else { echo json_encode(array("data"=>$connection->error)); }


?>