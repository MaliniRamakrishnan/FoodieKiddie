<?php
require "../../dbconnect.php";
//include("config.php");
require "./schoollocationfetcher.php";

$nearkitchens = "SELECT *,
(
    6371 * ACOS(
        (COS($schoollat*pi()/180) * COS(latitude*pi()/180) * COS(($schoollon*pi()/180)-(longitude*pi()/180))) + 
        (SIN($schoollat*pi()/180) * SIN(latitude*pi()/180))
    )
)
AS distance
FROM Addresses WHERE locationID LIKE 'K%'

ORDER BY distance;";

$kresult = $connection->query($nearkitchens);
$k = array();
$k = $kresult->fetch_all(MYSQLI_ASSOC);

if($kresult->num_rows > 0){
    foreach($k as $x){
        echo $x['locationID'] . "\t" . $x['latitude'] . "\t" . $x['longitude'] . "\t" . $x['distance'] . "\n";
    }
}
else echo "error. pocha!";

require "";

?>