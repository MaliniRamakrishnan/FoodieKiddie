<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$itmId = $data->fudId;
//$itmId = "IT101";
$sql = "SELECT ingredientsID FROM `alternatives` WHERE itemID = '".$itmId."';";
$result = $connection->query($sql);

if($result){
 $answer = array();
 while ($row = $result->fetch_object()) {
       array_push($answer,$row); 
      }
    
      echo json_encode(array("data"=>$answer));
 } 


else { echo json_encode(array("data"=>$connection->error)); }


// $sql = "SELECT ingredientsID FROM `items` WHERE id = '".$itmId."';";
// $result = $connection->query($sql);
// if ($result->num_rows > 0) {
//     $row = $result->fetch_assoc();
//     $inglist=explode(',', $row['ingredientsID']);
//     $ans=array();
//     $fine=array();
//     foreach($inglist as $out) {
       
//         $out = trim($out," ");
//         $sqln = "SELECT alternative_ing FROM `alternatives` WHERE ingredientsID = '".$out."' AND itemID = '".$itmId."';";
//         $resultn = $connection->query($sqln);
//         if ($resultn->num_rows > 0) {
//           array_push($ans,$out); 
//             $rown = $resultn->fetch_assoc();
//             $inglists=explode(',', $rown['alternative_ing']);
//              $anss=array();
//             foreach($inglists as $outs) {
//               $outs = trim($outs," ");
//             $sqlns = "SELECT ingredients.id,ingredients.name FROM `ingredients` WHERE id = '".$outs."';";
//             $resultns = $connection->query($sqlns);
//             if ($resultns->num_rows > 0) {
//             $rownss = $resultns->fetch_assoc();
//             array_push($anss,$rownss);}   
        
//         else { echo "0 results"; }
         
//     }
    
    
// }
//     echo json_encode(array("data"=>$anss)); 
//     }
   
// }
// else { echo json_encode(array("data"=>$connection->error)); }


?>