<?php 

    $username = addslashes(strip_tags($_POST['username']));
    $password = addslashes(strip_tags($_POST['password']));
    $email = addslashes(strip_tags($_POST['email']));
    $key = addslashes(strip_tags($_POST['key']));

    

    if($key != "ADGJLKHFSwrtuYRQ" or trim($username) == "" or trim($email) == "" or trim($password) == "")
    {
        die("ACCESS DENIED");
    }


    $connection = mysqli_connect("localhost" , "id17996247_buildings" , "frankcastle_11A" , "id17996247_buildingsupplies");

    if(mysqli_connect_errno())
    {
        echo "Failed to connect to the database ".mysqli_connect_errno();
    }
    
    $result = "select * from USERS where username = '$username' and password = '$password' and email = '$email'";
    

    if(mysqli_fetch_row(mysqli_query($connection , $result))>=1){
        die("user already exists");
    }
    $query = "insert into USERS(username , password , email) values ('$username', '$password' , '$email')";

    mysqli_query($connection , $query) or die ("CAN'T ACCESS DATABASE");

    echo "User added";

    mysqli_close($connection);

?>