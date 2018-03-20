<?php
require "init.php";
$username = $_POST["username"];
$password = $_POST["password"];

$sql = "insert into Drivers_details (Username,Password) values ('$username','$password');";
if(mysqli_query($con,$sql))
{
	echo "<br><h3>User registered...</h3>";
}else{
	echo "Errror on registration...". mysqli_error($con);
}
?>