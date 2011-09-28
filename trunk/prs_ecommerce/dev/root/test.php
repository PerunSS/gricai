<html>
<head>
</head>
<body>
	<iframe
		width="800px"
		height="600px"
		id="myFrame"
		name="myFrame"
	></iframe>
	<form
		action="action.php"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Register za fizicka lica</legend>
			Username: <input
				type="text"
				name="username"
				id="username"
			><br />
			Password:
			<input
				type="password"
				name="password"
				id="password"
			><br />
			Confirm:
			<input
				type="password"
				name="confirm"
				id="confirm"
			><br />
			Ime: <input
				type="text"
				name="firstname"
				id="firstname"
			><br />Prezime: <input
				type="text"
				name="lastname"
				id="lastname"
			><br />JMBG: <input
				type="text"
				name="jmbg"
				id="jmbg"
			><br />Kontakt telefon: <input
				type="text"
				name="telefon"
				id="telefon"
			><br />Email: <input
				type="text"
				name="email"
				id="email"
			><br /> <input
				type="submit"
				name="register_fl"
				id="register_fl"
				value="Register"
			>
		</fieldset>
	</form>

	<form
		action="action.php"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Register za pravna lica</legend>
			Username: <input
				type="text"
				name="username"
				id="username"
			><br />
			Password:
			<input
				type="password"
				name="password"
				id="password"
			><br />
			Confirm:
			<input
				type="password"
				name="confirm"
				id="confirm"
			><br />
			Ime firme: <input
				type="text"
				name="name"
				id="name"
			><br />Adresa: <input
				type="text"
				name="address"
				id="address"
			><br />Grad: <input
				type="text"
				name="city"
				id="city"
			><br />PIB: <input
				type="text"
				name="pib"
				id="pib"
			><br />Maticni broj: <input
				type="text"
				name="maticni"
				id="maticni"
			><br />Kontakt telefon: <input
				type="text"
				name="telefon"
				id="telefon"
			><br />Email: <input
				type="text"
				name="email"
				id="email"
			><br />Adresa za dostavu: <input
				type="text"
				name="adresa_za_dostavu"
				id="adresa_za_dostavu"
			><br /> <input
				type="submit"
				name="register_pl"
				id="register_pl"
				value="Register"
			>
		</fieldset>
	</form>
	<form
		action="action.php"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Login</legend>
			Korisnicko ime: <input
				type="text"
				name="username"
				id="username"
			><br />Sifra: <input
				type="text"
				name="password"
				id="password"
			><br /> <input
				type="submit"
				name="u_login"
				id="u_login"
				value="Login"
			>
		</fieldset>
	</form>
		<form
		action="TODO forgot password action"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Zaboravljeni password</legend>
			Email: <input
				type="text"
				name="email"
				id="email"
			><br /> <input
				type="submit"
				name="forgot"
				id="forgot"
				value="Forgot"
			>
		</fieldset>
	</form>
		<form
		action="TODO update za fizicka lica action"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Update za fizicka lica</legend>
			Novi telefon<input
				type="text"
				name="telefon"
				id="telefon"
			><br /> <input
				type="submit"
				name="update_pl"
				id="update_pl"
				value="Update"
			>
		</fieldset>
	</form>
	<form
		action="TODO update za fizicka lica action"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Update za fizicka lica</legend>
			Novi telefon<input
				type="text"
				name="telefon"
				id="telefon"
			><br /> <div id="input1" style="margin-bottom:4px;" class="clonedInput">
        		Dodaj adresu za dostavu: <input type="text" name="adresa1" id="adresa1" />
    		</div>
			<br/><input
				type="submit"
				name="update_fl"
				id="update_fl"
				value="Update"
			>
		</fieldset>
	</form>
</body>
</html>
