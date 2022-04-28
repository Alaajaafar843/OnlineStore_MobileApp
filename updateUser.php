<?php 

    $name = addslashes(strip_tags($_POST['username']));
    $id = addslashes(strip_tags($_POST['id']));
    $key = addslashes(strip_tags($_POST['key']));
    $email = addslashes(strip_tags($_POST['email']));
    
    

    if($key != "ADGJLKHFSwrtuYRQ" or trim($name) == "" or trim($id) == "" or trim($email) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $query = "UPDATE USERS SET username = '$name' , email = '$email' WHERE id = '$id' ";
    
    
    if(mysqli_query($connection , $query)){
        echo"User Updated";
    }
    else{
        echo"failed to update";
    }
    
    mysqli_close($connection);
    
    ?>