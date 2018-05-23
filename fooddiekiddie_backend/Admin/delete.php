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
$id = $_POST["id"];
echo $id;
$sql1 = "DELETE FROM kitchen where kitchenID='$id'";
$sql2 = "DELETE FROM Addresses where locationID='$id'";

mysqli_query($conn, $sql2);
mysqli_query($conn, $sql1);

header("location: editacc.php");

$conn->close();
?>