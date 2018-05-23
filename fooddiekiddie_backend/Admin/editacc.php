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


<!DOCTYPE html>
<html class="no-js"> 
<head>
	<link href="https://fonts.googleapis.com/css?family=Raleway:200,300,400,700" rel="stylesheet">
  <link rel="stylesheet" href="../CSS/global.css">
  <link rel="stylesheet" href="../CSS/viewacc.css">
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

  <!-- deleteing -->
  <div id="deletekit" class="modal">
    <div class="modal-content">
      <form action="delete.php" method="post">
       <font color="Black" size="3px">Please enter the ID of the record you want to delete.</font>
       <input type="text" placeholder="ID" name="id" required>
       <br><br>
       <button type="submit">DELETE</button>
     </form>
   </div>
 </div>

 <!-- deleteing -->
  <div id="deletehead" class="modal">
    <div class="modal-content">
      <form action="deletehead.php" method="post">
       <font color="Black" size="3px">Please enter the ID of the record you want to delete.</font>
       <input type="text" placeholder="ID" name="headid" required>
       <br><br>
       <button type="submit">DELETE</button>
     </form>
   </div>
 </div>

 <!-- deleteing -->
  <div id="deleteagent" class="modal">
    <div class="modal-content">
      <form action="deleteagent.php" method="post">
       <font color="Black" size="3px">Please enter the ID of the record you want to delete.</font>
       <input type="text" placeholder="ID" name="agentid" required>
       <br><br>
       <button type="submit">DELETE</button>
     </form>
   </div>
 </div>


 <!-- Inserting Kitchen -->
 <div id="kitchen" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <form action="insertkitchen.php" method="post">
      <input type="text" placeholder="Kitchen ID" name="id" required>
      <br><br>
      <input type="text" placeholder="Kitchen Name" name="name" required>
      <br><br>      
      <h3> Address </h3><br>
      <input type="text" placeholder="Door Number" name="door">
      <br><br> 
      <input type="text" placeholder="Landmark" name="landmark">
      <br><br> 
      <input type="text" placeholder="Street" name="street">
      <br><br> 
      <input type="text" placeholder="City" name="city" >
      <br><br>
      <input type="text" placeholder="State" name="state" >
      <br><br>  
      <input type="text" placeholder="Country" name="country">
      <br><br> 
      <input type="text" placeholder="PIN" name="pin" required>
      <br><br>
      <button type="submit">ADD</button>
    </form>
  </div>
</div>

<!-- Inserting Chef -->
<div id="chef" class="modal">
  <div class="modal-content">
    <span class="close1">&times;</span>
    <form action="insertheadchef.php" method="post">
      <input type="text" placeholder="HeadChef ID" name="id" required>
      <br><br>
            <input type="text" placeholder="Kitchen ID" name="kid" required>

      <br><br>      
      <h3> Personal Information </h3><br>
      <input type="text" placeholder="Name" name="name" required>
      <br><br> 
      <input type="text" placeholder="E-Mail" name="email" required>
      <br><br> 
      <input type="text" placeholder="Password" name="password" required>
      <br><br> 
      <input type="text" placeholder="Phone" name="phone" required>
      <br><br> 
      
      <button type="submit">ADD</button>
    </form>
  </div>
</div>

<!-- Inserting delivery agent -->
<div id="agent" class="modal">
  <div class="modal-content">
    <span class="close2">&times;</span>
     <form action="insertdeliveryagent.php" method="post">
      <input type="text" placeholder="DeliveryAgent ID" name="id" required>
      <br><br>
      <input type="text" placeholder="Zone" name="zone" required>

      <br><br>      
      <h3> Personal Information </h3><br>
      <input type="text" placeholder="Name" name="name" required>
      <br><br> 
      <input type="text" placeholder="E-Mail" name="email" required>
      <br><br> 
      <input type="text" placeholder="Password" name="password" required>
      <br><br> 
      <input type="text" placeholder="Phone" name="phone" required>
      <br><br> 
      
      <button type="submit">ADD</button>
    </form>
  </div>
</div>
</div>

<div class = "content">
  <main>
    <input id="tab1" type="radio" name="tabs" checked>
    <label for="tab1">Kitchen</label>
    <input id="tab2" type="radio" name="tabs">
    <label for="tab2">HeadChef</label>
    <input id="tab3" type="radio" name="tabs">
    <label for="tab3">Delivery Agent</label>
    
<!-- =======================================================
FETCHING KITCHEN
* ======================================================= -->
<section id="content1">
  <header id="fh5co-header" role="banner">
    <div class="header-inner">
      <nav role="navigation">
        <ul><font color="#ed1849">
         <li id="addkitchen">ADD |</li>
         <li id="editkitchen">EDIT |</li>
         <li id="deletekitchen">DELETE </li></font>
       </ul>
     </nav>
   </div>
 </header>
 <div class="main">
   <table class="users">          
    <?php         
    $sql = "SELECT * FROM kitchen INNER JOIN Addresses ON kitchen.kitchenID=Addresses.locationID";

    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
          // output data of each row
      echo "<tbody><tr><th>ID</th><th>Name</th><th>Address</th></tr>";
          // output data of each row
      while($row = $result->fetch_assoc()) {
        echo "<tr>
        <td>".$row["kitchenID"]."</td>
        <td>".$row["kitchenName"]."</td>
        <td> ".$row["locationDoorNumber"].",".$row["locationLandmark"].","
        .$row["locationStreet"].",".$row["locationCity"].",".$row["locationState"].",".$row["locationCountry"].",".$row["locationPIN"]."</td>
        </tr>";

      }
      echo "</tbody>";
    } else {
      echo "0 results";
    }

    ?>

  </table>

</div>
</section>

<!-- =======================================================
FETCHING HEAD CHEF
* ======================================================= -->

<section id="content2">
  <header id="fh5co-header" role="banner">
    <div class="header-inner">
      <nav role="navigation">
        <ul><font color="#ed1849">
         <li id="addhead">ADD |</li>
         <li id="edithead">EDIT |</li>
         <li id="deleteheadchef">DELETE </li></font>
       </ul>
     </nav>
   </div>
 </header>

 <div class="main">
  <table class="users">
   <?php

   $sql2 = "SELECT * FROM kitchenWorkers where expertiseCuisine='all'";

   $result2 = $conn->query($sql2);

   if ($result2->num_rows > 0){
          // output data of each row
    echo "<tbody><tr><th>ID</th><th>Kitchen</th><th>Name</th><th>Phone</th></tr>";
          // output data of each row
    while($row2 = $result2->fetch_assoc())  {
      echo "<tr>
      <td>".$row2["chefID"]."</td>
      <td>".$row2["kitchenID"]."</td>
      <td> ".$row2["name"]."</td>
      <td> ".$row2["phone"].
      "</td></tr>";

    }
    echo "</tbody>";
  } else {
    echo "0 results";
  }

  ?>
</table>
</div>
</section>

<!-- =======================================================
FETCHING DELIVERY AGENT
* ======================================================= -->

<section id="content3">
   <header id="fh5co-header" role="banner">
    <div class="header-inner">
      <nav role="navigation">
        <ul><font color="#ed1849">
         <li id="addagent">ADD |</li>
         <li id="editagent">EDIT |</li>
         <li id="deletedelivery">DELETE </li></font>
       </ul>
     </nav>
   </div>
 </header>
  <div class="main">
    <table class="users">
     <?php

     $sql2 = "SELECT * FROM deliveryAgent";

     $result2 = $conn->query($sql2);

     if ($result2->num_rows > 0){
          // output data of each row
      echo "<tbody><tr><th>ID</th><th>Name</th><th>Contact</th><th>Zone</th></tr>";
          // output data of each row
      while($row2 = $result2->fetch_assoc())  {
        echo "<tr>
        <td>".$row2["deliveryAgentID"]."</td>
        <td>".$row2["name"]."</td>
        <td> ".$row2["contact"]."</td>
        <td> ".$row2["zone"].
        "</td></tr>";

      }
      echo "</tbody>";
    } else {
      echo "0 results";
    }
    ?>
  </table>
</div>
</section>
</main>


</div>




<script>
// Get the modal
var modal = document.getElementById('kitchen');
var chef = document.getElementById('chef');
var deletekit = document.getElementById('deletekit');


// Get the button that opens the modal
var addkitchen = document.getElementById("addkitchen");
var editkitchen = document.getElementById("editkitchen");
var deletekitchen = document.getElementById("deletekitchen");


var addhead = document.getElementById("addhead");
var edithead = document.getElementById("edithead");
var deletehead = document.getElementById("deletehead");

var addagent = document.getElementById("addagent");
var editagent = document.getElementById("editagent");
var deleteagent = document.getElementById("deleteagent");

var deletedelivery = document.getElementById("deletedelivery");
var btn1 = document.getElementById("add1");
var btn2 = document.getElementById("add2");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close1")[0];
var span2 = document.getElementsByClassName("close2")[0];


// When the user clicks the button, open the modal 
addkitchen.onclick = function(){
  modal.style.display = "block";
} 
editkitchen.onclick = function(){
  modal.style.display = "block";
}  
deletekitchen.onclick = function(){
  deletekit.style.display = "block";
}  
     
addhead.onclick = function(){
  chef.style.display = "block";
} 
edithead.onclick = function(){
  chef.style.display = "block";
}  
deleteheadchef.onclick = function(){
  deletehead.style.display = "block";
}  

addagent.onclick = function(){
  agent.style.display = "block";
} 
editagent.onclick = function(){
  agent.style.display = "block";
}  
deletedelivery.onclick = function(){
  deleteagent.style.display = "block";
}                                          


// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
  else if(event.target == deletekit){
    deletekit.style.display = "none";
  }
  else if(event.target == deletehead){
    deletehead.style.display = "none";
  }
  else if(event.target == deleteagent){
    deleteagent.style.display = "none";
  }
  else if(event.target == chef){
    chef.style.display = "none";
  }
  else if(event.target == agent){
    agent.style.display = "none";
  }
}
</script>



</body>
</html>

