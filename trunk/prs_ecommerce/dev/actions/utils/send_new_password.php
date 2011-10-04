<?php
    $to=$_REQUEST['email'];
	// subject
	$subject = 'PRS: Forgot password';
	
	// message
	$message = '
	<html>
	<head>
	  <title>Forgot password</title>
	</head>
	<body>
	  <p>click this link to recover your password</p><br/>
	  <a href="http://www.w3schools.com">Visit W3Schools</a>
	</body>
	</html>
	';
	
	// To send HTML mail, the Content-type header must be set
	$headers  = 'MIME-Version: 1.0' . "\r\n";
	$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
	
	// Additional headers
	$headers .= 'To: '.$to . "\r\n";
	$headers .= 'From: PRS <noreply@PRS.com>' . "\r\n";
	
	// Mail it
	mail($to, $subject, $message, $headers);
	echo "Prosledjen vam je link na email, klikom na njega dobicete novi password." 

?>