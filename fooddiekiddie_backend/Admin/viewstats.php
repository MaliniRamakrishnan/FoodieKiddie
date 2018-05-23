<!DOCTYPE html><html class="no-js"> 
<head>
	
	<link href="https://fonts.googleapis.com/css?family=Raleway:200,300,400,700" rel="stylesheet">
	<link rel="stylesheet" href="../CSS/global.css"
	
	<link rel="stylesheet" href="../CSS/animate.css">
	<link rel="stylesheet" href="../CSS/owl.theme.default.min.css">
	
	<link rel="stylesheet" href="../CSS/style.css">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    
</head>
<body>
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

$sql1 = "SELECT * FROM kitchen";
$sql2 = "SELECT count(*) FROM orders;";
$result1 = $conn->query($sql1);
$result2 = $conn->query($sql2);
if ($result1->num_rows > 0 || $result2->num_rows > 0) {
    // output data of each row
    $ids= array();
    $numb = array();
    $i=0; $j=0;
    while($row1 = ($result1->fetch_assoc()){ 
        $ids[$i]=$row1["kitchenID"];
        
        $i++;
    }
    while($row1 = ($result1->fetch_assoc()){ 
    $numb[$j]=$row2["orderID"];
    $j++;
  }
} else {
    echo "0 results";
}
$conn->close();
?>
	<header id="fh5co-header" role="banner">

		<div class="header-inner">

			<nav role="navigation">
				<ul>
					<li><a href="editaccounts.html">Edit Accounts</a></li>
					<li><a href="viewaccounts.html">View Accounts</a></li>
					<li><a href="puploadmenu.html">Upload Menu</a></li>
					<li><a href="updatemenu.html">Update Menu</a></li>
					<li><a href="viewstats.html">View Statistics</a></li>

				</ul>
			</nav>
		</div>

	</header>

	<div class = "left-box"> 

		<h1><a href="index.html"><font color="white">Fooddie Kiddie</a></h1>

			

	</div>
	
	<div class = "content">
		<div class="main">
<br><br><br><br><br><br><br><br>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
</div>
	</div>
<script type="text/javascript">

window.onload = function () {
    //abarna
 
    //end
   var i=<?php echo $i ?>;
   console.log(i);
    var data1=[];
   <?php for($iter=0;$iter<$i;$iter++){ ?>
    var obj={label:"<?=$ids[$iter]?>",y:<?=$numb[$iter]?>}
    data1.push(obj);

  var chart = new CanvasJS.Chart("chartContainer", {
    title:{
      text: "Kitchen Statistics"              
    },
    data: [              
    {
      // Change type to "doughnut", "line", "splineArea", etc.
      type: "splineArea",
      dataPoints: data1
        
      
    }
    ]
  });
  chart.render();
}
</script>
</body>
</html>
