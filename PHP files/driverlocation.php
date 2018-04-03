<?php
require "init.php";

$driver_id = $_POST["driver_id"];
$drivers_route= $_POST["drivers_route"];
$geolat=$_POST["geolat"];
$geolng=$_POST["geolng"];

$sql = "update Drivers_location set Drivers_route='$drivers_route',Geolat='$geolat',Geolng='$geolng',Time_tracker=now() WHERE Drivers_id='$driver_id';";

if(mysqli_query($con,$sql))
{
	
	echo "<br><h3>Data Updated...</h3>";
}else{
	echo "Errror while updating data...". mysqli_error($con);
}
mysqli_close($con); 

?>
