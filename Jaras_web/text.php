<?php
$subject = fopen("countries.txt",r);

foreach(preg_split("/((\r?\n)|(\r\n?))/", $subject) as $line){
    // do stuff with $line
    $line = "'".$line."'";

}


?>
