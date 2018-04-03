<?php
require "init.php";

$my_query=$con->prepare("SELECT Drivers_id,Username,Password from Drivers_details");
$my_query->execute();
$my_query->bind_result($Drivers_id,$Username,$Password);

$Drivers_details=array();

while($my_query->fetch()){
	$temp=array();
	$temp['Drivers_id']=$Drivers_id;
	$temp['Username']=$Username;
	$temp['Password']=$Password;
	array_push($Drivers_details,$temp);
}

echo json_encode($Drivers_details);
mysqli_close($con);

?>