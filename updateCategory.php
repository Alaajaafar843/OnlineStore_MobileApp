<?php 

    $name = addslashes(strip_tags($_POST['catName']));
    $cid = addslashes(strip_tags($_POST['cid']));
    $key = addslashes(strip_tags($_POST['key']));
    
    

    if($key != "ADGJLKHFSwrtuYRQ" or trim($name) == "" or trim($cid) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $query = "UPDATE Categories SET name = '$name'  WHERE cid = '$cid' ";
    
    
    if(mysqli_query($connection , $query)){
        echo"Category Updated";
    }
    else{
        echo"failed to update";
    }
    
    mysqli_close($connection);
    
    ?>