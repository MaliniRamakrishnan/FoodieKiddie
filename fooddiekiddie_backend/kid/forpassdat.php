<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$names = $data->mailid;
//$knames = $data->kidId;
//$names ="pilotvikash1997@gmail.com";
// $knames = "SCHOOLERO123976";

$sql = "SELECT password FROM `user` WHERE email = '".$names."'";
 $result = $connection->query($sql);
$outp = array();
$outp = $result->fetch_all(MYSQLI_ASSOC);
if($result->num_rows > 0) { 
    echo json_encode(array("data"=>$outp[0]['password']));
    // echo json_encode(array("data"=>"success"));
    }
else { 
    echo json_encode(array("data"=>"failed"));
    //echo json_encode($connection->error);
    }
?>