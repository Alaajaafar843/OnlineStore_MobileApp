<?php 

    $username = addslashes(strip_tags($_POST['username']));
    $password = addslashes(strip_tags($_POST['password']));
    $key = addslashes(strip_tags($_POST['key']));
    $response = array();
    $USER = array();

    

    if($key != "ADGJLKHFSwrtuYRQ" or trim($username) == "" or trim($password) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $query = "select id , username , password , email , ROLE from USERS where username = '$username' and password = '$password'";
    $result = mysqli_query($connection , $query);
    if($row = mysqli_fetch_assoc($result)){

        $USER['id'] = $row['id'];
        $USER['username'] = $row['username'];
        $USER['email'] = $row['email'];
        $USER['ROLE'] = $row['ROLE'];
        $response['success'] = "logged in";
        $response['user'] = $USER;
        
        echo(json_encode($response));
        mysqli_close($connection);
    }
    else {
    $response['success'] = "error";
    echo(json_encode($response));
    mysqli_close($connection);
}

?>