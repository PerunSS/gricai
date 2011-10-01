<?php 
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
?>
<html>
<head>
<link
	rel="stylesheet"
	type="text/css"
	href="css/shop.css"
	media="all"
/>
</head>
<body>
<div class="user_area">
<?php 
include '../templates/login.php';
?>
</div>
</body>
</html>