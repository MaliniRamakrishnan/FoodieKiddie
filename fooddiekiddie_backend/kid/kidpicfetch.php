<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";
$names = "PAJO660768";
$imgsql = "SELECT kidPic FROM `kid` WHERE momID = '".$names."'"; 


$result = $connection->query($imgsql);
if($result){
 $answer = array();
 while ($row = $result->fetch_object()) { array_push($answer,$row); } 
 echo json_encode(array("data"=>$answer));}





// // $result = mysqli_query($connection,$imgsql);
// // $images = array();
// // if(mysqli_num_rows($result)) {
// //   while($image = mysqli_fetch_assoc($result)) {
// //     $image['pic'] = base64_encode($image['pic']);
// //     array_push($images,$image);
// //   }
// }
// echo json_encode(array('pics'=>$images));

?>