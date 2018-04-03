
<?php
require "init.php";

$my_query=$con->prepare("SELECT Geolat,Geolng,TIMESTAMPDIFF(minute,Time_tracker,NOW()) from Drivers_location;");
$my_query->execute();
$my_query->bind_result($geolat,$geolng,$time);

$Drivers_location=array();

while($my_query->fetch()){
	$temp=array();
	$temp['geolat']=$geolat;
	$temp['geolng']=$geolng;
	$temp['time']=$time;
	array_push($Drivers_location,$temp);
}

echo json_encode($Drivers_location);
mysqli_close($con);

?>