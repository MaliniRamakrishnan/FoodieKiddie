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

?>


<!DOCTYPE html><html class="no-js"> 
<head>
	
	<link href="https://fonts.googleapis.com/css?family=Raleway:200,300,400,700" rel="stylesheet">

	<link rel="stylesheet" href="../CSS/global.css">
	
	<link rel="stylesheet" href="../CSS/edit.css">
	
	<link rel="stylesheet" href="../CSS/animate.css">
	
	<link rel="stylesheet" href="../CSS/owl.theme.default.min.css">
	
	<link rel="stylesheet" href="../CSS/style.css">



</head>
<body>

	<header id="fh5co-header" role="banner">

		<div class="header-inner">

			<nav role="navigation">
				<ul>
					<li><a href="editacc.php">Edit Accounts</a></li>
          <li><a href="viewacc.php">View Accounts</a></li>
          <li><a href="uploadmenu.html">Upload Menu</a></li>
          <li><a href="updatemenu.php">Update Menu</a></li>
          <li><a href="viewstats.php">View Statistics</a></li>

				</ul>
			</nav>
		</div>

	</header>
  <div class="container">
   <div class = "left-box"> 

    <h1><a href="index.html"><font color="white">Fooddie Kiddie</a></h1>


  </div>


<!-- Inserting delivery agent -->
  <div id="modal" class="modal">
    <div class="modal-content">

      <span class="close2">&times;</span>

      <form action="insertmenu.php" method="post">
        <input type="text" placeholder="Item ID" name="id" required>
        <br><br>

        <input type="text" placeholder="Item Name" name="name" required>
        <br><br>      
        <input type="text" placeholder="Description" name="description" required>
        <br><br> 
        <input type="text" placeholder="Price" name="price" required>
        <br><br> 
        <input type="text" placeholder="Category" name="category" required>
        <br><br> 
        <input type="text" placeholder="Ingredients" name="ingredientsID" required>
        <br><br>
        <input type="text" placeholder="Cuisine" name="cuisine" required>
        <br><br> 
        <input type="text" placeholder="Type Of Food" name="typeOfFood" required>
        <br><br> 
        <input type="text" placeholder="Tags" name="tags" required>
        <br><br>


        <button type="submit">ADD</button>

      </form>
    </div>
  </div>

  <div id="deleteit" class="modal">
    <div class="modal-content">
      <form action="deleteitem.php" method="post">
       <font color="Black" size="3px">Please enter the ID of the record you want to delete.</font>
       <input type="text" placeholder="ID" name="id" required>
       <br><br>
       <button type="submit">DELETE</button>
     </form>
   </div>
 </div>

</div>





<div class = "content">
   <header id="fh5co-header" role="banner">
    <div class="header-inner">
      <nav role="navigation">
        <ul><font color="#ed1849">
         <li id="addmenu">ADD |</li>
         <li id="editmenu">EDIT |</li>
         <li id="deletemenu">DELETE </li></font>
       </ul>
     </nav>
   </div>
 </header>

        <table class="users">
         
          
          <?php

          
          $sql = "SELECT * FROM items";

          $result = $conn->query($sql);

          if ($result->num_rows > 0) {
          // output data of each row
            echo "<tbody>
            <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Cuisine</th>
            <th>Type Of Food</th>
            <th>Tags</th>
            </tr>";
          // output data of each row
            while($row = $result->fetch_assoc()) {
              echo "<tr>
              <td>".$row["id"]."</td>
              <td>".$row["name"]."</td>
              <td>".$row["description"]."</td>
              <td>".$row["price"]."</td>
              <td>".$row["category"]."</td>
              <td>".$row["cuisine"]."</td>
              <td>".$row["typeOfFood"]."</td>
              <td>".$row["tags"]."</td></tr>";
              
            }
            echo "</tbody>";
          } else {
            echo "0 results";
          }

          $conn->close();
          ?>

        </table>

   
    



</div>


<script>
// Get the modal
var modal = document.getElementById('modal');

var deleteit = document.getElementById('deleteit');


// Get the button that opens the modal

var addmenu = document.getElementById("addmenu");
var editmenu = document.getElementById("editmenu");
var deletemenu = document.getElementById("deletemenu");

var deletedelivery = document.getElementById("deletedelivery");


// When the user clicks the button, open the modal 
addmenu.onclick = function(){
  modal.style.display = "block";
} 
editmenu.onclick = function(){
  modal.style.display = "block";
}  
deletemenu.onclick = function(){
  deleteit.style.display = "block";
}                                          


// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
  else if(event.target == deleteit){
    deleteit.style.display = "none";
  }
  
}
</script>



</body>
</html>

