<?php 
    
    
    $cid = addslashes(strip_tags($_POST['cid']));
    $name = addslashes(strip_tags($_POST['name']));
    $key = addslashes(strip_tags($_POST['key']));
    
    if($key != "ADGJLKHFSwrtuYRQ" or trim($name) == "")
        die("access denied");
    
    
    $con = mysqli_connect("localhost","id17996247_buildings", "frankcastle_11A","id17996247_buildingsupplies");
    
    if(mysqli_connect_errno())
    {
        echo "Failed to connect to database MYSQL: ".mysqli_connect_errno();
    }
    
    $sql = "INSERT INTO Categories VALUES ($cid , '$name')";
    
    mysqli_query($con , $sql) or die ("can't access database");
    
    echo " Category added";
    
    mysqli_close($con);
    

?>