<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$_SESSION['momid'] = $data->momID;
$_SESSION['kidid'] = $data->kidID;
$_SESSION['itemid'] = $data->itemID;
$_SESSION['ingid'] = $data->ingID;
$_SESSION['choseningid'] = $data->chosenIng;


if(is_array($_SESSION['ingid'])){

    foreach (array_combine($_SESSION['ingid'], $_SESSION['choseningid']) as $code => $name) {
  
       $sql = "INSERT INTO cart (momId,kidId,ItemId,IngId,chosenIngId) VALUES ('".$_SESSION['momid']."','".$_SESSION['kidid']."','".$_SESSION['itemid']."','".$code."','".$name."');";
       if ($connection->query($sql) === TRUE) {
    echo json_encode(array("data"=>$data));
}else {
    echo json_encode(array("data"=>"failed"));
} 
    }
}

// $sql = "INSERT INTO cart (momId,kidId,ItemId,IngId,chosenIngId) VALUES ('".$_SESSION['momid']."','".$_SESSION['kidid']."','".$_SESSION['itemid']."','".$_SESSION['ingid']."','".$_SESSION['choseningid']."');";

// if ($connection->query($sql) === TRUE) {
//     echo json_encode(array("data"=>$data));
// }else {
//     echo json_encode(array("data"=>"failed"));
// } 
     

?>