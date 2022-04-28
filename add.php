<?php 
    
    
    $cid = addslashes(strip_tags($_POST['cid']));
    $pid = addslashes(strip_tags($_POST['pid']));
    $name = addslashes(strip_tags($_POST['name']));
    $quantity = addslashes(strip_tags($_POST['quantity']));
    $price = addslashes(strip_tags($_POST['price']));
    $key = addslashes(strip_tags($_POST['key']));
    
    if($key != "ADGJLKHFSwrtuYRQ" or trim($name) == "")
        die("access denied");
    
    
    $con = mysqli_connect("localhost","id17996247_buildings", "frankcastle_11A","id17996247_buildingsupplies");
    
    if(mysqli_connect_errno())
    {
        echo "Failed to connect to database MYSQL: ".mysqli_connect_errno();
    }
    $query = "SELECT * FROM Categories WHERE cid=$cid";
    $result = mysqli_query($con , $query);
    if($row = mysqli_fetch_assoc($result)){
    
    $sql = "INSERT INTO products VALUES ($pid , '$name' , $quantity , $price , $cid)";
    
    mysqli_query($con , $sql) or die ("can't access database");
    
    echo " Product added";
    }
    
    else{
        echo "category doesn't exist";
    }
    mysqli_close($con);
    

?>