var address = new Array();

var pravni = new Array("#missing_username", "#empty_username",
		"#alpha_username", "#missing_password", "#empty_password",
		"#missing_confirm", "#empty_confirm", "#wrong_confirm",
		"#wrong_confirm_pass", "#missing_name", "#empty_name",
		"#missing_address", "#empty_address", "#alphanumeric_address",
		"#missing_city", "#empty_city", "#alpha_city", "#missing_pib",
		"#empty_pib", "#numeric_pib", "#missing_maticni", "#empty_maticni",
		"#numeric_maticni", "#missing_telefon", "#empty_telefon",
		"#numeric_telefon", "#missing_email", "#empty_email", "#invalid_email",
		"#wrong_username", "#empty_address", "#invalid_address",
		"#alphanumeric_address", "#wrong_username", "#exist_user", "#success",
		"#missing", "#timeout");

var fizicki = new Array("#missing_f_username", "#empty_f_username",
		"#alpha_f_username", "#missing_f_password", "#empty_f_password",
		"#missing_f_confirm", "#empty_f_confirm", "#wrong_f_confirm",
		"#f_wrong_confirm_pass", "#missing_f_firstname", "#empty_f_firstname",
		"#alpha_f_firstname", "#missing_f_lastname", "#empty_f_lastname",
		"#alpha_f_lastname", "#missing_f_telefon", "#empty_f_telefon",
		"#numeric_f_telefon", "#missing_f_jmbg", "#empty_f_jmbg",
		"#numeric_f_jmbg", "#missing_f_email", "#empty_f_email",
		"#invalid_f_email", "#wrong_f_username", "#wrong_f_username",
		"#f_exist_user", "#f_success", "#f_missing", "#f_timeout");

function doChange() {
	var reg = $("input[@name=reg]:checked").attr('id');
	var unreg = "";
	switch (reg) {
	case "pravna":
		unreg = "fizicka";
		break;
	case "fizicka":
		unreg = "pravna";
		break;
	}
	$("#reg_" + reg).show();
	$("#reg_" + unreg).hide();
}

function addAddress() {
	var pom = true;
	for ( var i = 0; i < address.length; i++) {
		if (address[i].length == 0) {
			pom = false;
			break;
		}
	}
	if (pom) {
		address[address.length] = "";
		$.post("../templates/addresses.php", {
			addresses : address
		}, returnAddress);
	}
}

function returnAddress(data) {
	$("#addresses").html(data);
	$(".adrese").keyup(addText);
	$(".ukloni").click(removeText);
}

function addText() {
	var text = $(this).val();
	var id = $(this).attr("adresa");
	address[id] = text;
}

function removeText() {
	var pom = new Array();
	var id = $(this).attr("adresa");
	for ( var i = 0; i < address.length; i++) {
		if (i != id) {
			pom[pom.length] = address[i];
		}
	}
	address = pom;
	$.post("../templates/addresses.php", {
		addresses : address
	}, returnAddress);
}

function doRegisterPravna() {
	registerPravna(onReturnPravna);
}

function registerPravna(callback) {
	var username = $("#username").val();
	var password = $("#password").val();
	var confirm = $("#confirm").val();
	var name = $("#name").val();
	var addres = $("#address").val();
	var city = $("#city").val();
	var pib = $("#pib").val();
	var maticni = $("#maticni").val();
	var telefon = $("#telefon").val();
	var email = $("#email").val();

	$.postJSON("action.php", {
		register_pl : "submit",
		username : username,
		password : password,
		confirm : confirm,
		name : name,
		address : addres,
		city : city,
		pib : pib,
		maticni : maticni,
		telefon : telefon,
		email : email,
		addresses : address
	}, callback);
}

function onReturnPravna(data, textStatus, jqXHR) {
	var response = "";
	for (i in pravni) {
		$(pravni[i]).hide();
	}
	for (i in address) {
		$("#empty_address_" + i).hide();
		$("#alphanumeric_address_" + i).hide();
	}
	if (data['status']) {
		switch (data['status']) {
		case "exists_user":
			response = "#exist_user";
			$("#wrong_username").show();
			break;
		case "wrong_password":
			response = "#wrong_confirm_pass";
			$("#wrong_confirm").show();
			break;
		case "success":
			response = "#success";
			break;
		case "error":
			response = "#missing";
			for (error in data['errors']) {
				$("#" + data['errors'][error]['error']).show();
			}
			break;
		default:
			response = "#timeout";
			break;
		}
	}
	$(response).show();
}

function doRegisterFizicka() {
	registerFizicka(onReturnFizicka);
}

function registerFizicka(callback) {
	var username = $("#f_username").val();
	var password = $("#f_password").val();
	var confirm = $("#f_confirm").val();
	var firstname = $("#f_firstname").val();
	var lastname = $("#f_lastname").val();
	var telefon = $("#telefon").val();
	var jmbg = $("#f_jmbg").val();

	$.postJSON("action.php", {
		register_fl : "submit",
		f_username : username,
		f_password : password,
		f_confirm : confirm,
		f_firstname : firstname,
		f_lastname : lastname,
		f_telefon : telefon,
		email : email,
		f_jmbg : jmbg
	}, callback);
}

function onReturnFizicka(data, textStatus, jqXHR) {
	var response = "";
	for (i in fizicki) {
		$(fizicki[i]).hide();
	}
	if (data['status']) {
		switch (data['status']) {
		case "exists_user":
			response = "#f_exist_user";
			$("#wrong_f_username").show();
			break;
		case "wrong_password":
			response = "#f_wrong_confirm_pass";
			$("#wrong_f_confirm").show();
			break;
		case "success":
			response = "#f_success";
			break;
		case "error":
			response = "#f_missing";
			for (error in data['errors']) {
				$("#" + data['errors'][error]['error']).show();
			}
			break;
		default:
			response = "#f_timeout";
			break;
		}
	}
	$(response).show();
}

$(function() {
	$("#fizicka").click(doChange);
	$("#pravna").click(doChange);
	$("#add_address").click(addAddress);
	$("#register_pl").click(doRegisterPravna);
	$("#register_fl").click(doRegisterFizicka);
	doChange();
});