<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$imgsql = "SELECT category, pic FROM `items`"; // WHERE id='IT101'";
$result = mysqli_query($connection,$imgsql);
$images = array();
if(mysqli_num_rows($result)) {
  while($image = mysqli_fetch_assoc($result)) {
    $image['pic'] = base64_encode($image['pic']);
    array_push($images,$image);
  }
}
echo json_encode(array('pics'=>$images));

?>