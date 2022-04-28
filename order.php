<?php 

    $uid = addslashes(strip_tags($_POST['uid']));
    $pid = addslashes(strip_tags($_POST['pid']));
    $address = addslashes(strip_tags($_POST['Address']));
    $quantity = addslashes(strip_tags($_POST['quantity']));
    $price = addslashes(strip_tags($_POST['CheckPrice']));
    

    if(trim($uid) == "" or trim($pid) == "" or trim($address) == "" or trim($quantity) == ""  or trim($price) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $query = "INSERT INTO orders (pid , Address , uid , Quantity , checkPrice) VALUES ('$pid' , '$address' , '$uid' , '$quantity' , '$price')";
    $query1 = "UPDATE products SET quantity = quantity-$quantity WHERE pid = '$pid'";
    
    if(mysqli_query($connection , $query) and mysqli_query($connection , $query1)){
        echo"Order placed";
    }
    else{
        echo"failed to order";
    }
    
    mysqli_close($connection);
    
    ?>