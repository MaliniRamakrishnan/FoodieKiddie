<?php
$str = 'VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw==';
$val = base64_decode($str);
echo gettype($val);
?>