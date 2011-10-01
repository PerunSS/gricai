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
<script
	type="text/javascript"
	src="js/jquery.js"
></script>
<script
	type="text/javascript"
	src="js/blockUI.js"
></script>
<script
	type="text/javascript"
	src="js/shop.js"
></script>
</head>
<body>
	<div>
	<?php
	include '../templates/login.php';
	include '../templates/search_items.php'
?>
	</div>
	<div id="search_list" class="search_list"></div>
	<div id="message_content" class="hidden"></div>
</body>
</html>
