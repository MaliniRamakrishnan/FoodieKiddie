
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



$id = mysqli_real_escape_string($conn,$_POST['id']);
$name = mysqli_real_escape_string($conn,$_POST['name']);
$email = mysqli_real_escape_string($conn,$_POST['email']);
$password = mysqli_real_escape_string($conn,$_POST['password']);
$kid = mysqli_real_escape_string($conn,$_POST['kid']);
$phone = mysqli_real_escape_string($conn,$_POST['phone']);

$first = "INSERT INTO `user` (id, email, password, role) VALUES ('$id', '$email', '$password', 'headchef')";

$sql = "INSERT INTO `kitchenWorkers` (chefID, kitchenID, name, phone, expertiseCuisine) VALUES ('$id', '$kid', '$name', '$phone', 'All')";


mysqli_query($conn, $first);
mysqli_query($conn, $sql);
header("location: editacc.php");

$conn->close();
?>