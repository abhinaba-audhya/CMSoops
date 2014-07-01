<?php
 
/*
 * Following code will list all the students
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all subjects from subjects table
if (isset($_GET["Teacher_Id"]))
{
$T_Id=$_GET["Teacher_Id"];
$result = mysql_query("SELECT * FROM Subject where Sub_Code in (select Sub_Code from Faculty_Sub where Teacher_Id= '$T_Id')  ") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // students node
    $response["subjects"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["Sub_Code"] = $row["Sub_Code"];
        $product["Sub_Name"] = $row["Sub_Name"];
        // push single product into final response array
        array_push($response["subjects"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no students found
    $response["success"] = 0;
    $response["message"] = "No students found";
 
    // echo no users JSON
    echo json_encode($response);
}
}
?>