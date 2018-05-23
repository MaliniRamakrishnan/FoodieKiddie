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
<html>
<head>
  <title>
  </title>
  <link href="https://fonts.googleapis.com/css?family=Raleway:200,300,400,700" rel="stylesheet">
  <link rel="stylesheet" href="../CSS/global.css">
  <link rel="stylesheet" href="../CSS/animate.css">
  <link rel="stylesheet" href="../CSS/owl.theme.default.min.css">
  <link rel="stylesheet" href="../CSS/style.css">
<link rel="stylesheet" href="../CSS/viewacc.css">
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

<div class = "content">
<div class="main">

<main>
  
  <input id="tab1" type="radio" name="tabs" checked>
    <label for="tab1">Users</label>
    
    <input id="tab2" type="radio" name="tabs">
    <label for="tab2">Chef</label>
    
    <input id="tab3" type="radio" name="tabs">
    <label for="tab3">Delivery Person</label>


<!-- =======================================================
FETCHING USERS
* ======================================================= -->
    
  <section id="content1">
    <div class="main">

        <table class="users">
         
          
          <?php

          
          $sql = "SELECT * FROM user where role='parent'";

          $result = $conn->query($sql);

          if ($result->num_rows > 0) {
          // output data of each row
            echo "<tbody><tr><th>ID</th><th>Email</th></tr>";
          // output data of each row
            while($row = $result->fetch_assoc()) {
              echo "<tr>
              <td>".$row["id"]."</td>
              <td>".$row["email"]."</td></tr>";
              
            }
            echo "</tbody>";
          } else {
            echo "0 results";
          }
          ?>

        </table>

      </div>
  </section>
    
  <section id="content2">
       <div class="main">
      <table class="users">
       <?php

       $sql2 = "SELECT * FROM kitchenWorkers where expertiseCuisine!='all'";

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
    
  <section id="content3">
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
</div>






</body>
</html>

