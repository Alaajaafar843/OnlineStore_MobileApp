<?php 

    $productName = addslashes(strip_tags($_POST['productName']));
    $productID = addslashes(strip_tags($_POST['prodID']));
    $key = addslashes(strip_tags($_POST['key']));
    $catName = addslashes(strip_tags($_POST['catName']));
    $quantity = addslashes(strip_tags($_POST['quantity']));
    $price = addslashes(strip_tags($_POST['price']));
    
    

    if($key != "ADGJLKHFSwrtuYRQ" or trim($productName) == "" or trim($productID) == "" or trim($catName) == "" or trim($quantity) == "" or trim($price) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $cid = "SELECT cid FROM Categories WHERE name = '$catName'";
    $result = mysqli_query($connection , $cid);
    $categoryID;
    while ($row = mysqli_fetch_assoc($result)) {
        $categoryID = $row['cid'];
}
    
    $query = "UPDATE products SET name = '$productName', quantity = '$quantity' , price = '$price' , cid = '$categoryID' WHERE pid = '$productID' ";
    
    
    if(mysqli_query($connection , $query)){
        echo"Produt Updated";
    }
    else{
        echo"failed to update";
    }
    
    mysqli_close($connection);
    
    ?>