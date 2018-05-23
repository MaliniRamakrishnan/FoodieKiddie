<?php

$orderingSchoolLocation = "SELECT latitude, longitude FROM Addresses WHERE locationID = '".$orderSchoolID."'";
$schoolLocationResult = $connection->query($orderingSchoolLocation);

$s = array();
$s = $schoolLocationResult->fetch_all(MYSQLI_ASSOC);

if($schoolLocationResult->num_rows > 0){
    foreach($s as $y){
        echo $y['latitude'] . "\t" . $y['longitude'] . "\n";
        $schoollat = $y['latitude'];
        $schoollon = $y['longitude'];
    }
}

else { echo json_encode(array("data"=>"school location fetch failed".$connection->error)); }

?>