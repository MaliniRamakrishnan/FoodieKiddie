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
echo $id;
$name = mysqli_real_escape_string($conn,$_POST['name']);
$description = mysqli_real_escape_string($conn,$_POST['description']);
$price = mysqli_real_escape_string($conn,$_POST['price']);
$category = mysqli_real_escape_string($conn,$_POST['category']);
$ingredientsID = mysqli_real_escape_string($conn,$_POST['ingredientsID']);
$cuisine = mysqli_real_escape_string($conn,$_POST['cuisine']);
$typeOfFood = mysqli_real_escape_string($conn,$_POST['typeOfFood']);
$tags = mysqli_real_escape_string($conn,$_POST['tags']);

$first = "INSERT INTO `items` (id, name, description, price, category, ingredientsID, cuisine, 
	typeOfFood, tags ) VALUES ('$id', '$name', '$description', '$price', '$category', '$ingredientsID', '$cuisine', '$typeOfFood', '$tags')";


mysqli_query($conn, $first);

//header("location: updatemenu.php");

$conn->close();
?>