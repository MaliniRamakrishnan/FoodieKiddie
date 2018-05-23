<?php

$orderingSchoolLocation = "SELECT latitude, longitude FROM Addresses WHERE locationID = '".$orderSchoolID."'";
$schoolLocationResult = $connection->query($orderingSchoolLocation);
$s = array();
$s = $schoolLocationResult->fetch_all(MYSQLI_ASSOC);

if($schoolLocationResult->num_rows > 0){
    foreach($s as $y){
        $schoollat = $y['latitude'];
        $schoollon = $y['longitude'];
    }
}

else { echo json_encode(array("order school location fetch error"=>$connection->error)); }

$nearkitchens = "SELECT *,
(
    6371 * ACOS(
        (COS($schoollat*pi()/180) * COS(latitude*pi()/180) * COS(($schoollon*pi()/180)-(longitude*pi()/180))) + 
        (SIN($schoollat*pi()/180) * SIN(latitude*pi()/180))
    )
)
AS distance
FROM Addresses WHERE locationID LIKE 'K%'
HAVING distance <= 10
ORDER BY distance;";

$kresult = $connection->query($nearkitchens);
$kit = array();
$kit = $kresult->fetch_all(MYSQLI_ASSOC);
$kitchensetflag = false;

if($kresult->num_rows > 0){
    $capacity = 100;
    foreach($kit as $kitchenrow){
        if($kitchensetflag) break;
        else{
            //code to check if kitchen has capacity
            $checkCapacity = "SELECT COUNT(*) AS kcap FROM orders WHERE kitchenID='".$kitchenrow['locationID']."';";
                //"' AND DATE='".$data->orderPart->deliveryDate."';";
            $kcapres = $connection->query($checkCapacity);
            if($kcapres->num_rows > 0){
                $kcap = $kcapres->fetch_all(MYSQLI_ASSOC);
                foreach($kcap as $kcaprow) $kcapacity = $kcaprow['kcap'];
                if($kcapacity < $capacity){
                    //get number of nearby schools and define n, k.
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
                    //check how many orders have already been placed from this school to this kitchen
                    $placedSchoolOrders = "SELECT COUNT(*) AS countOfOrders FROM orders WHERE kitchenID='".$kitchenrow['locationID'] .
                        //"'AND DATE='".$orderPart['deliveryDate'].
                        "' AND schoolID='" . $orderSchoolID . "';";
                    $pscres = $connection->query($placedSchoolOrders);
                    $pscrow = $pscres->fetch_all(MYSQLI_ASSOC);
                    foreach($pscrow as $psc){
                        if($psc['countOfOrders'] < $k && !$kitchensetflag) {
                            //proceed to allot this kitchen
                            $setKitchen = "UPDATE orders SET kitchenID='" . $kitchenrow['locationID'] . "' WHERE orderID='".$orderID."'";
                            if ($connection->query($setKitchen) === TRUE) {
                                //echo json_encode(array("data"=>"kitchen selected"));
                                echo json_encode(array("data"=>"kitchen set: " . $kitchenrow['locationID']));
                                $kitchensetflag = true;
                            }
                            else { 
                                echo json_encode(array("kitchen set error"=>$connection->error));
                                $kitchenselectedflag = false; 
                            }
                        }
                        else
                        echo json_encode(array("kitchen count of orders from same school error"=>$connection->error));
                    }
                }
                else echo json_encode(array("data"=>"kitchen capacity exceeded 100"));
            }
            else echo json_encode(array("data"=>"capacity check no rows"));
        }
    }
}
else echo json_encode(array("kitchens near school list error"=>$connection->error));

if(!$kitchensetflag) {
    echo json_encode(array("data"=>"no more kitchens: all kitchens busy: order cancelled"));
    $orderCancellationSQL = "UPDATE order SET status = 'CANCELLED' WHERE orderID = '".$orderID."';"; 
    if ($connection->query($orderCancellationSQL) === TRUE) {
        echo json_encode(array("order cancellation error"=>"success"));
    }
    else { echo json_encode(array("order cancellation error"=>$connection->error)); }
}

?>