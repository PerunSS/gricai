var page = 1;
var pretraga = true;

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
	$.get("dispatch.php", {
		dispatch : "category_options",
		id : id
	}, returnSub);
}

function returnSub(data) {
	$("#categorry_options").html(data);
}

function buttonSmaller() {
	page = 1;
	doSmallerSearch();
}

function buttonAdvanced() {
	page = 1;
	doAdvancedSearch();
}

function doSmallerSearch() {
	pretraga = true;
	var name = $("#search_name").val();
	$.get("dispatch.php", {
		dispatch : "items_search",
		name : name,
		cat : "0",
		sub : "0",
		page : page
	}, returnSearch);
}

function doAdvancedSearch() {
	pretraga = false;
	var name = $("#search_name").val();
	var cat = $("#categorry option:selected").val();
	var sub = $("#sub_categorry option:selected").val();
	$.get("dispatch.php", {
		dispatch : "items_search",
		name : name,
		cat : cat,
		sub : sub,
		page : page
	}, returnSearch);
}

function returnSearch(data) {
	$("#search_list").html(data);
	$(".set_cart").click(addToCart);
	$(".stranica").click(doPage);
}

function doPage() {
	page = $(this).attr("strana");
	if (pretraga){
		doSmallerSearch();
	}
	else {
		doAdvancedSearch();
	}
}

function addToCart() {
	var item_id = $(this).attr("item_id");
	var item_name = $(this).attr("item_name");
	var item_currency = $(this).attr("item_currency");
	$.post("action.php", {
		add_to_cart : "submit",
		item_id : item_id,
		item_name : item_name,
		item_currency : item_currency
	}, returnAdd);
}

function returnAdd(data) {
	$("#message_content").html(data);
	$("#close").click(removeMessage);
	showDialog("#message");
}

function showDialog(message) {
	$.blockUI({
		message : $(message)
	});
}

function removeMessage() {
	$.unblockUI();
}

function showCart() {
	$.get("dispatch.php", {
		dispatch : "shopping_cart"
	}, returnCart)
}

function returnCart(data) {
	$("#message_content").html(data);
	$("#close").click(removeMessage);
	$("#empty").click(emptyCart);
	$("#buy").click(buyCart);
	$(".remove").click(removeFromCart);
	showDialog("#show_cart");
}

function removeFromCart() {
	removeMessage();
	var item_id = $(this).attr("item_id");
	$.post("action.php", {
		remove_from_cart : "submit",
		item_id : item_id
	}, returnRemove);
}

function emptyCart() {
	removeMessage();
	$.post("action.php", {
		empty_cart : "submit"
	}, returnRemove);
}

function buyCart() {
	removeMessage();
	$.post("action.php", {
		buy_cart : "submit"
	}, returnBuy);
}

function returnBuy(data) {
	$("#message_content").html(data);
	$("#close").click(removeMessage);
	showDialog("#message");
}

function returnRemove(data) {
	$("#message_content").html(data);
	showCart();
}

$(function() {
	$.blockUI.defaults.css = {};
	$("#advanced_search").click(doAdvanced);
	$("#smaller_search").click(doSmaller);
	$("#categorry").change(doSub);
	$("#s_search_items").click(buttonSmaller);
	$("#a_search_items").click(buttonAdvanced);
	$("#shopping_cart").click(showCart);
	doSmallerSearch();
});