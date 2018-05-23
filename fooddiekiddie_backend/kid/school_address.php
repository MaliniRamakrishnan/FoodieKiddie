<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

require "../dbconnect.php";

$raw_data = file_get_contents("php://input");
$data = json_decode($raw_data);
$schoolName = strtoupper($data->school);
$schoolCity = $data->schoolCity;
//similarly get for door number, landmark, street, state, country, pin.
$locationtype = "SCHOOL";
require "./schoolIDgenerator.php";

$checkSchoolID = "SELECT * FROM `Addresses` WHERE locationID = '" . $tsID . "';";
//check for num of rows. if>0, again generate ID.
$sql = "INSERT INTO Addresses(nameOfLocation,typeOfLocation,locationID) VALUES('".$schoolName."','".$locationtype."','".$tsID."');";
if ($connection->query($sql) === TRUE) {
    echo json_encode(array("data"=>"success"));
}
else {
	echo json_encode(array("data"=>$connection->error));
} 

?>