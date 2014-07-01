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
 
// get all students from students table
if (isset($_GET["Sub_Code"]))
{
$code=$_GET["Sub_Code"];
$result = mysql_query("SELECT * FROM Student where Roll in (select Roll from Stud_Sub where Sub_Code='$code')") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // students node
    $response["students"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["Roll"] = $row["Roll"];
        $product["Firstname"] = $row["Firstname"];
        $product["Lastname"] = $row["Lastname"];
        $product["Mobile"] = $row["Mobile"];
        $product["Email"] = $row["Email"];
		$product["Branch"] = $row["Branch"];
		$product["Year"] = $row["Year"];
 
        // push single product into final response array
        array_push($response["students"], $product);
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