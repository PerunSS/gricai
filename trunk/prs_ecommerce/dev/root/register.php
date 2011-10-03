<?php
require_once '../classes/AutoloadClasses.inc';
SecurityChecker::sessionStart();
?>
<html>
<head>
<link
	rel="stylesheet"
	type="text/css"
	href="css/register.css"
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
	src="js/register.js"
></script>
</head>
<body>
	<div class="register">
		<fieldset>
			<table align="center">
				<tr>
					<td><label for="fizicka">Fizicka lica: </label><input
						type="radio"
						id="fizicka"
						name="reg"
						checked="checked"
					></td>
					<td><label for="pravna">Pravna lica: </label><input
						type="radio"
						id="pravna"
						name="reg"
					></td>
				</tr>
			</table>
			<div id="register">
				<div
					id="reg_pravna"
					class="hidden"
				>
					<table align="center">
						<tr>
							<td>Username:</td>
							<td><input
								type="text"
								name="username"
								id="username"
							></td>

							<td><div
									class="hidden warning"
									id="missing_username"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_username"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alpha_username"
								>Pogresan unos</div>
								<div
									class="hidden warning"
									id="wrong_username"
								>Postoji korisnik</div>
							</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input
								type="text"
								name="email"
								id="email"
							></td>
							<td><div
									class="hidden warning"
									id="missing_email"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_email"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="invalid_email"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input
								type="password"
								name="password"
								id="password"
							></td>
							<td><div
									class="hidden warning"
									id="missing_password"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_password"
								>Nedostaje unos</div>
							</td>
						</tr>
						<tr>
							<td>Confirm:</td>
							<td><input
								type="password"
								name="confirm"
								id="confirm"
							></td>

							<td><div
									class="hidden warning"
									id="missing_confirm"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_confirm"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="wrong_confirm"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Ime firme:</td>
							<td><input
								type="text"
								name="name"
								id="name"
							></td>

							<td><div
									class="hidden warning"
									id="missing_name"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_name"
								>Nedostaje unos</div>
							</td>
						</tr>
						<tr>
							<td>Adresa:</td>
							<td><input
								type="text"
								name="address"
								id="address"
							></td>

							<td><div
									class="hidden warning"
									id="missing_address"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_address"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alphanumeric_address"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Grad:</td>
							<td><input
								type="text"
								name="city"
								id="city"
							></td>

							<td><div
									class="hidden warning"
									id="missing_city"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_city"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alpha_city"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>PIB:</td>
							<td><input
								type="text"
								name="pib"
								id="pib"
							></td>
							<td><div
									class="hidden warning"
									id="missing_pib"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_pib"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="numeric_pib"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Maticni broj:</td>
							<td><input
								type="text"
								name="maticni"
								id="maticni"
							></td>
							<td><div
									class="hidden warning"
									id="missing_maticni"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_maticni"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="numeric_maticni"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Kontakt telefon:</td>
							<td><input
								type="text"
								name="telefon"
								id="telefon"
							></td>
							<td><div
									class="hidden warning"
									id="missing_telefon"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_telefon"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="numeric_telefon"
								>Pogresan unos</div>
							</td>
						</tr>
					</table>
					<div id="addresses"></div>
					<table align="center">
						<tr>
							<td><input
								type="submit"
								name="add_address"
								id="add_address"
								value="Dodaj adresu"
							></td>
						</tr>
					</table>
					<table align="right">
						<tr>
							<td><input
								type="submit"
								name="register_pl"
								id="register_pl"
								value="Register"
							></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<div
									class="hidden warning"
									id="missing"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="timeout"
								>Greska u procesu</div>
								<div
									class="hidden warning"
									id="exist_user"
								>Postoji korisnik</div>
								<div
									class="hidden warning"
									id="wrong_confirm_pass"
								>Pogresna sifra</div>
								<div
									class="hidden success"
									id="success"
								>Uspesna registracija</div>
							</td>
						</tr>
					</table>
				</div>
				<div
					id="reg_fizicka"
					class="hidden"
				>
					<table align="center">
						<tr>
							<td>Username:</td>
							<td><input
								type="text"
								name="f_username"
								id="f_username"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_username"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_username"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alpha_f_username"
								>Pogresan unos</div>
								<div
									class="hidden warning"
									id="wrong_f_username"
								>Postoji korisnik</div>
							</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input
								type="text"
								name="f_email"
								id="f_email"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_email"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_email"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="invalid_f_email"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input
								type="password"
								name="f_password"
								id="f_password"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_password"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_password"
								>Nedostaje unos</div>
							</td>
						</tr>
						<tr>
							<td>Confirm:</td>
							<td><input
								type="password"
								name="f_confirm"
								id="f_confirm"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_confirm"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_confirm"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="wrong_f_confirm"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Ime:</td>
							<td><input
								type="text"
								name="f_firstname"
								id="f_firstname"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_firstname"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_firstname"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alpha_f_firstname"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Prezime:</td>
							<td><input
								type="text"
								name="f_lastname"
								id="f_lastname"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_lastname"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_lastname"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="alpha_f_lastname"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>JMBG:</td>
							<td><input
								type="text"
								name="f_jmbg"
								id="f_jmbg"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_jmbg"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_jmbg"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="numeric_f_jmbg"
								>Pogresan unos</div>
							</td>
						</tr>
						<tr>
							<td>Kontakt telefon:</td>
							<td><input
								type="text"
								name="f_telefon"
								id="f_telefon"
							></td>
							<td><div
									class="hidden warning"
									id="missing_f_telefon"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="empty_f_telefon"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="numeric_f_telefon"
								>Pogresan unos</div>
							</td>
						</tr>
					</table>
					<table align="right">
						<tr>
							<td><input
								type="submit"
								name="register_fl"
								id="register_fl"
								value="Register"
							></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<div
									class="hidden warning"
									id="f_missing"
								>Nedostaje unos</div>
								<div
									class="hidden warning"
									id="f_timeout"
								>Greska u procesu</div>
								<div
									class="hidden warning"
									id="f_exist_user"
								>Postoji korisnik</div>
								<div
									class="hidden warning"
									id="f_wrong_confirm_pass"
								>Pogresna sifra</div>
								<div
									class="hidden success"
									id="f_success"
								>Uspesna registracija</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</fieldset>
	</div>
</body>
</html>
