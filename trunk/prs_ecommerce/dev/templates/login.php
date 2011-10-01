<?php
if (!SecurityChecker::isLogged()){?>
<fieldset>
	<legend>Login</legend>
	<form
		action="action.php"
		method="post"
	>
		<label for="username"></label> <input
			type="text"
			id="username"
			name="username"
		><br /> <label for="password"></label> <input
			type="password"
			id="password"
			name="password"
		><br /> <input
			type="submit"
			name="u_login"
			id="u_login"
			value="Login"
		>
	</form>
</fieldset>
<?php }
else {?>

<?php }
?>