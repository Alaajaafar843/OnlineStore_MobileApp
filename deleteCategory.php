<?php 

    $cid = addslashes(strip_tags($_POST['cid']));

    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    if(trim($cid)==""){
        echo("can't delete item");
    }
    $query1 = "DELETE FROM products WHERE cid='$cid'";
    $query = "DELETE FROM Categories WHERE cid='$cid'";
    
    if(mysqli_query($connection , $query1)){
        if(mysqli_query($connection , $query)){
        
            echo("Category deleted successfully");
    }
        
    }
    else{
        echo("could not execute query");
    }
    
    mysqli_close($connection);
    

?>