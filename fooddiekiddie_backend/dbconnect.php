<?php 
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: X-Requested-With');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Content-Type: application/json');

$dbhost = 'localhost';
$dbuser = 'id2745928_fkteam';
$dbpassword = 'foodie123';
$dbname = 'id2745928_foodiekiddie';

$connection = mysqli_connect($dbhost,$dbuser,$dbpassword,$dbname)
 or die(mysqli_error($connection));
?>