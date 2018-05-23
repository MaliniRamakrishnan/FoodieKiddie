<?php

//fetch kitchen lat long $kitchenrow['locationID'];
$klat = $kitchenrow['latitude'];
$klon = $kitchenrow['longitude'];

$nearschools = "SELECT *,
(
    6371 * ACOS(
        (COS($klat*pi()/180) * COS(latitude*pi()/180) * COS(($klon*pi()/180)-(longitude*pi()/180))) + 
        (SIN($klat*pi()/180) * SIN(latitude*pi()/180))
    )
)
AS distance
FROM Addresses WHERE locationID LIKE 'SCH%'
HAVING distance <= 10
ORDER BY distance;";

$schresult = $connection->query($nearschools);
//number of schools near kitchen
$n = $schresult->num_rows;
//number of orders allowed from this school to this kitchen
$k = floor($capacity/$n);

?>