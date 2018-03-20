<?php
require "init.php";


$mysql_qry="select * from Drivers_details ;";
$result=mysqli_query($con,$mysql_qry);
	
		while($row=mysqli_fetch_array($result)){
			$data[]=$row;
		}
		print(json_encode($data));
}
mysqli_close($con);

?>