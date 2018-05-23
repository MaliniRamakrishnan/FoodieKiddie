<?php

   $db = mysqli_connect("localhost","root","","FooddieKiddie");
   session_start();
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($db,$_POST['uname']);
      $mypassword = mysqli_real_escape_string($db,$_POST['psw']); 
      
      $sql = "SELECT email FROM user WHERE email = '$myusername' and password = '$mypassword'";
      $result = mysqli_query($db,$sql);



      $row = mysqli_fetch_array($result,MYSQLI_ASSOC); 
      
      $count = mysqli_num_rows($result);

      echo $count;
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
    if($count==0){
         $error = "Your Login Name or Password is invalid";
         echo $error;
      }

    else{
    	header("location: editacc.php");
    }
   }

?>