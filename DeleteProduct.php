<?php 

    $pid = addslashes(strip_tags($_POST['pid']));

    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    if(trim($pid)==""){
        echo("can't delete item");
    }
    
    $query = "DELETE FROM products WHERE pid='$pid'";
    
    if(mysqli_query($connection , $query)){
        echo("product deleted successfully");
    }
    else{
        echo("could not execute query");
    }
    
    mysqli_close($connection);
    

?>