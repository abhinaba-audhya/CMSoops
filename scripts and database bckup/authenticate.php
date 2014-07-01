<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['Teacher_Id']) && isset($_POST['Password'])) {
 
    $Teacher_Id = $_POST['Teacher_Id'];
    $Password = $_POST['Password'];
    //$description = $_POST['description'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("SELECT * FROM Faculty WHERE Teacher_Id='$Teacher_Id' and Password='$Password'");
 
    // check if row inserted or not
    if ($result) {if(mysql_num_rows($result) > 0) {
        // successfully retrieved into database
	$rslt = mysql_fetch_assoc($result);
	if($rslt['Password']==$Password)
        $response["success"] = 1;
        $response["message"] = "Successfully logged in";
 
        // echoing JSON response
        echo json_encode($response);}
     else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Wrong user name or password";
 
        // echoing JSON response
        echo json_encode($response);
    }
}
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>