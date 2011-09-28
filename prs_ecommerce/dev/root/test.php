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
		action="TODO register action"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Register</legend>
			Ime: <input
				type="text"
				name="ime"
				id="ime"
			><br />Prezime: <input
				type="text"
				name="prezime"
				id="prezime"
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
				name="sub"
				id="sub"
				value="Register"
			>
		</fieldset>
	</form>

	<form
		action="TODO register pravnog lica action"
		method="post"
		target="myFrame"
	>
		<fieldset>
			<legend>Register</legend>
			Ime firme: <input
				type="text"
				name="ime"
				id="ime"
			><br />Adresa: <input
				type="text"
				name="adresa"
				id="adresa"
			><br />Grad: <input
				type="text"
				name="grad"
				id="grad"
			><br />PIB: <input
				type="text"
				name="pib"
				id="pib"
			><br />Maticni broj: <input
				type="text"
				name="maticni_broj"
				id="maticni_broj"
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
				name="sub"
				id="sub"
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
	
</body>
</html>
