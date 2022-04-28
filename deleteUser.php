<?php 

    $id = addslashes(strip_tags($_POST['id']));
    $username = addslashes(strip_tags($_POST['username']));
    $email = addslashes(strip_tags($_POST['email']));

    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    if(trim($id)=="" or trim($username)=="" or trim($email)==""){
        echo("can't delete item");
    }
    
    $query = "DELETE FROM USERS WHERE id='$id' AND username='$username' AND email='$email'";
    
    if(mysqli_query($connection , $query)){
        echo("user deleted successfully");
    }
    else{
        echo("could not execute query");
    }
    
    mysqli_close($connection);
    

?>