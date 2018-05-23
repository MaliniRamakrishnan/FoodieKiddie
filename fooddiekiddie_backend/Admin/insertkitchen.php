
<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "FooddieKiddie";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
}



$kid = mysqli_real_escape_string($conn,$_POST['id']);
$kidname = mysqli_real_escape_string($conn,$_POST['name']);
$door = mysqli_real_escape_string($conn,$_POST['door']);
$landmark = mysqli_real_escape_string($conn,$_POST['landmark']);
$street = mysqli_real_escape_string($conn,$_POST['street']);
$city = mysqli_real_escape_string($conn,$_POST['city']);
$state = mysqli_real_escape_string($conn,$_POST['state']);
$country = mysqli_real_escape_string($conn,$_POST['country']);
$pin = mysqli_real_escape_string($conn,$_POST['pin']);



$address = $street . ', ' . $city . ', ' . $state . ', ' . $pin;
$prepAddr = str_replace(' ','+',$address);
$geocode=file_get_contents('https://maps.google.com/maps/api/geocode/json?address='.$prepAddr.'&sensor=false');
$output= json_decode($geocode);

$latitude = $output->results[0]->geometry->location->lat;
$longitude = $output->results[0]->geometry->location->lng;




$sql = "INSERT INTO `Addresses` (locationID, nameOfLocation, typeOfLocation, 
	locationDoorNumber,locationLandmark, locationStreet, locationCity, locationState, 
	locationCountry, locationPIN, latitude, longitude)VALUES ('$kid', '$kidname', 'kitchen', '$door', '$landmark',
	'$street', '$city', '$state', '$country', '$pin', '$latitude', '$longitude')";

$sql1 = "INSERT INTO `kitchen` (kitchenID, kitchenName, locationID) VALUES ('$kid', '$kidname','$kid')";

mysqli_query($conn, $sql);

mysqli_query($conn, $sql1);

header("location: editacc.php");
$conn->close();
?>