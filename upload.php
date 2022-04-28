<?php

$name = strip_tags(addslashes($_POST['name']));

if (empty($name))

    die("please enter id");

    

$b64 = $_POST['file'];



// Specify the location where you want to save the image

$img_file = "./images/$name.jpg";

// check of file exists

if (file_exists($img_file))

    die("file exists");



// Obtain the original content (usually binary data)

$bin = base64_decode($b64);



// Load GD resource from binary data

$im = imageCreateFromString($bin);



// Make sure that the GD library was able to load the image

// This is important, because you should not miss corrupted or unsupported images

if (!$im) {

  die('Base64 value is not a valid image');

}







imagejpeg($im, $img_file);

imagedestroy($im);

echo "image uploaded successfully";

?>