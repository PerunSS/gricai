function doUserList() {
	$.get("../dispatch.php", {
		dispatch : "view_users"
	}, returnUserList);
}

function returnUserList(data) {
	$("#details").html(data);
	$("#searchUsers").click(doSearchUser);
	doSearchUser();
}
function doSearchUser() {
	var a_korisnik_un_search = $("#a_korisnik_un_search").val();
	var a_korisnik_tip_search = $("#a_korisnik_tip_search").val();
	var a_korisnik_aktivan_search = "";
	if ($("#a_korisnik_aktivan_search").attr("checked") == true) {
		a_korisnik_aktivan_search = "checked";
	}
	var a_korisnik_banovan_search = "";
	if ($("#a_korisnik_banovan_search").attr("checked") == true) {
		a_korisnik_banovan_search = "checked";
	}
	$.get("../dispatch.php", {
		dispatch : "user_list",
		a_korisnik_un_search : a_korisnik_un_search,
		a_korisnik_tip_search : a_korisnik_tip_search,
		a_korisnik_aktivan_search : a_korisnik_aktivan_search,
		a_korisnik_banovan_search : a_korisnik_banovan_search
	}, returnSearchUser);
}

function returnSearchUser(data) {
	$("#list_of_user").html(data);
	$(".un_ban").click(doBan);
	$(".detail_user").click(doDetail);
}

function doBan() {
	var ban = $(this).val();
	var user_id = $(this).attr("user_id");
	$.post("../action.php", {
		ban_action : ban,
		user_id : user_id
	}, returnBan);
}

function returnBan(data) {
	$("#list_of_user").html(data);
	doSearchUser();
}

function doDetail() {
	var user_id = $(this).attr("user_id");
	$.post("../dispatch.php", {
		dispatch : "user_details",
		user_id : user_id
	}, returnDetail);
}

function returnDetail(data) {
	$("#load_html").html(data);
	$("#close").click(removeMessage);
	showDialog("#message");
}

function showDialog(message){
	$.blockUI({
		message : $(message)
	});
}

function removeMessage(){
	$.unblockUI();
}


function doShoppingList() {
	$.get("../dispatch.php", {
		dispatch : "view_porudzbenice"
	}, returnShopping);
}

function returnShopping(data) {
	$("#details").html(data);
	$("#porudzbenica_search").click(doBuySearch);
	doBuySearch();
}

function doBuySearch() {
	var a_porudzbenica_vreme_search = $("#a_porudzbenica_vreme_search").val();
	var a_porudzbenica_cena_min = $("#a_porudzbenica_cena_min").val();
	var a_porudzbenica_cena_max = $("#a_porudzbenica_cena_max").val();
	$.get("../dispatch.php", {
		dispatch : "porudzbenica_list",
		a_porudzbenica_vreme_search : a_porudzbenica_vreme_search,
		a_porudzbenica_cena_max : a_porudzbenica_cena_max,
		a_porudzbenica_cena_min : a_porudzbenica_cena_min
	}, returnBuySearch);
}

function returnBuySearch(data) {
	$("#list_of_buy").html(data);
	$(".detail_por").click(doDetailPor);
}

function doDetailPor() {
	var por_id = $(this).attr("por_id");
	$.post("../dispatch.php", {
		dispatch : "porudzbenica_details",
		por_id : por_id
	}, returnDetailPor);
}

function returnDetailPor(data) {
	$("#load_html").html(data);
	$("#close").click(removeMessage);
	showDialog("#message");
}

$(function() {
	$("#user_list").click(doUserList)
	$("#shopping_list").click(doShoppingList);
	doUserList();
});