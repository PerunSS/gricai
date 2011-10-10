<div class="login_area">
<?php
if (!SecurityChecker::isLogged()){?>
	<fieldset>
	<?php
	if (isset($_SESSION['login_error'])){
		$message = "";
		$error = json_decode($_SESSION['login_error']);
		switch ($error->status){
			case "banned":
				$message="Profil je banovan";
				break;
			case "not_activated":
				$message="Profil nije aktiviran";
				break;
			case "wrong":
				$message="Pogresan korisnik ili sifra";
				break;
			case "error":
				$message="Upisite korisnicko ime i sifru";
				break;
			default:
				$message="Greska u procesu, pokusajte kasnije";
				break;
		}?>
		<table>
			<tr>
				<td><label class="error_tekst"><?php echo $message;?> </label></td>
			</tr>
		</table>
		<?php
		unset($_SESSION['login_error']);
	}
	?>
		<form
			action="action.php"
			method="post"
		>
			<table>
				<tr>
					<td><input
						type="text"
						id="username"
						name="username"
						placeholder="korisnik"
						class="tekst"
					>
					</td>
					</td>
					<td><input
						type="password"
						id="password"
						name="password"
						placeholder="sifra"
						class="tekst"
					>
					</td>
					<td><input
						type="submit"
						name="u_login"
						id="u_login"
						value="Login"
						class="dugmici"
					>
					</td>
				</tr>
				<tr>
					<td><a href="registruj.php">Registruj se</a>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<?php }
	else {
		$user=json_decode($_SESSION['user']);
		?>
	<fieldset>
		<table>
			<tr>
				<td><label>Korisnik: </label>
				</td>
				<td><label class="labela"><?php echo (isset($user->ime)?$user->ime:$user->ime_firme);?>
				</label></td>
				<td><input
					type="submit"
					name="shopping_cart"
					id="shopping_cart"
					value="Korpa"
					class="dugmici"
				>
				</td>
				<td>
					<form
						action="action.php"
						method="post"
					>
						<input
							type="submit"
							name="u_logout"
							id="u_logout"
							value="Logout"
							class="dugmici"
						>
					</form></td>
			</tr>
		</table>
	</fieldset>
	<?php }
	?>
</div>
