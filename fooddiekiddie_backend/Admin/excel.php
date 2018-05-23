<?php
require_once "Classes/PHPExcel.php";
	
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

$sql = "CREATE TABLE IF NOT EXISTS `excel_data` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `phone` int(11) NOT NULL,
  `city` VARCHAR(30) NOT NULL) ";


mysqli_query($conn, $sql);

if (mysqli_query($conn, $sql)) {
    echo "Table items created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}


$path = $_POST["name"];
$ext = pathinfo($path, PATHINFO_EXTENSION);
if($ext == 'xlsx'){
$tmpfname = $_POST["name"];
        echo $tmpfname . "<br><br>";
		$excelReader = PHPExcel_IOFactory::createReaderForFile($tmpfname);
		$excelObj = $excelReader->load($tmpfname);
		$worksheet = $excelObj->getSheet(0);
		$lastRow = $worksheet->getHighestRow();
		
		for ($row = 2; $row <= $lastRow; $row++) {
			
			 $id = $worksheet->getCell('A'.$row)->getValue();
			 $name = $worksheet->getCell('B'.$row)->getValue();
            $email = $worksheet->getCell('C'.$row)->getValue();
			 $phone = $worksheet->getCell('D'.$row)->getValue();
            $city = $worksheet->getCell('E'.$row)->getValue();
            $sql = "INSERT INTO excel_data (id, name, email, phone, city)
            VALUES ('$id', '$name', '$email', '$phone', '$city')";

       if ($conn->query($sql) === TRUE) {
            header("location: uploadmenu.html");
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
                }
}
            else{
                 echo "Please Select Valid Excel File";
            }
        $conn->close();
        ?>