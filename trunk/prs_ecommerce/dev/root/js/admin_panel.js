function doUserList() {
	$.get("../dispatch.php", {
		dispatch : "view_users"
	}, returnList);
}

function returnList(data) {
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
	}, returnSearch);
}

function returnSearch(data) {
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

function doProductList() {
	$.get("../dispatch.php", {
		dispatch : "admin_search_items"
	}, returnProduct);
}

function returnProduct(data) {
	$("#details").html(data);
	$("#advanced_search").click(doAdvanced);
	$("#smaller_search").click(doSmaller);
	$("#categorry").change(doSub);
	$("#s_search_items").click(doSmallerSearch);
	$("#a_search_items").click(doAdvancedSearch);
	doBuySearch();
}

function doAdvanced() {
	$("#advanced_div").show();
	$("#s_search_items").hide();
	$("#advanced_search").hide();
	doSub();
}

function doSmaller() {
	$("#advanced_div").hide();
	$("#s_search_items").show();
	$("#advanced_search").show();
}

function doSub() {
	var id = $("#categorry option:selected").val();
	$.get("../dispatch.php", {
		dispatch : "category_options",
		id : id
	}, returnSub);
}

function returnSub(data) {
	$("#categorry_options").html(data);
}

function doSmallerSearch() {
	var name = $("#search_name").val();
	$.get("../dispatch.php", {
		dispatch : "admin_items_search",
		name : name,
		cat : "0",
		sub : "0"
	}, returnSearch);
}

function doAdvancedSearch() {
	var name = $("#search_name").val();
	var cat = $("#categorry option:selected").val();
	var sub = $("#sub_categorry option:selected").val();
	$.get("../dispatch.php", {
		dispatch : "admin_items_search",
		name : name,
		cat : cat,
		sub : sub
	}, returnSearch);
}

function returnSearch(data) {
	$("#search_list").html(data);
}

$(function() {
	$("#user_list").click(doUserList)
	$("#shopping_list").click(doShoppingList);
	$("#product_list").click(doProductList);
	doUserList();
});