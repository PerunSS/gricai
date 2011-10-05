var item_id = '';
var current = false;

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
	current = false;
	doSmallerSearch();
}

function doAdvanced() {
	$("#advanced_div").show();
	$("#s_search_items").hide();
	$("#advanced_search").hide();
	current = true;
	doSub();
}

function doSmaller() {
	$("#advanced_div").hide();
	$("#s_search_items").show();
	$("#advanced_search").show();
	current = false;
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
	$(".product_change").click(doEditUpdate);
	$(".remove_cancel").click(doRemoveCancel);
	$("#add_new").click(doAddNew);
	$("#cancel_new").click(doCancelNew);
}

function doEditUpdate() {
	if ($(this).val()=="Edit"){
		doEdit(this);
	}
	else if ($(this).val()=="Izmeni"){
		doUpdate(this);
	}
}

function doEdit(item) {
	item_id = $(item).attr("proiz_id");
	$("#kat_"+item_id).removeAttr("disabled");
	$("#kat_"+item_id).removeClass();
	$("#pod_"+item_id).removeAttr("disabled");
	$("#pod_"+item_id).removeClass();
	$("#naz_"+item_id).removeAttr("disabled");
	$("#naz_"+item_id).removeClass();
	$("#opi_"+item_id).removeAttr("disabled");
	$("#opi_"+item_id).removeClass();
	$("#cen_"+item_id).removeAttr("disabled");
	$("#cen_"+item_id).removeClass();
	$("#por_"+item_id).removeAttr("disabled");
	$("#por_"+item_id).removeClass();
	$("#pak_"+item_id).removeAttr("disabled");
	$("#pak_"+item_id).removeClass();
	$("#sta_"+item_id).removeAttr("disabled");
	$("#sta_"+item_id).removeClass();
	$("#min_"+item_id).removeAttr("disabled");
	$("#min_"+item_id).removeClass();
	$(item).val("Izmeni");
	$("#prekini_ukloni_"+item_id).val("Odustani");
	$("#kat_"+item_id).change(doSubId);	
}

function doRemoveCancel() {
	if ($(this).val()=="Odustani"){
		doCancel(this);
	}
	else if ($(this).val()=="Obrisi"){
		doDelete(this);
	}
}

function doCancel(item) {
	item_id = $(item).attr("proiz_id");
	$("#kat_"+item_id).attr("disabled","disabled");
	$("#kat_"+item_id).addClass("non_editable");
	$("#pod_"+item_id).attr("disabled","disabled");
	$("#pod_"+item_id).addClass("non_editable");
	$("#naz_"+item_id).attr("disabled","disabled");
	$("#naz_"+item_id).addClass("non_editable");
	$("#opi_"+item_id).attr("disabled","disabled");
	$("#opi_"+item_id).addClass("non_editable");
	$("#cen_"+item_id).attr("disabled","disabled");
	$("#cen_"+item_id).addClass("non_editable");
	$("#por_"+item_id).attr("disabled","disabled");
	$("#por_"+item_id).addClass("non_editable");
	$("#pak_"+item_id).attr("disabled","disabled");
	$("#pak_"+item_id).addClass("non_editable");
	$("#sta_"+item_id).attr("disabled","disabled");
	$("#sta_"+item_id).addClass("non_editable");
	$("#min_"+item_id).attr("disabled","disabled");
	$("#min_"+item_id).addClass("non_editable");
	$(item).val("Obrisi");
	$("#edit_potvrdi_"+item_id).val("Edit");
	$("#kat_"+item_id).change(doSubId);
}

function doSubId() {
	item_id = $(this).attr("proiz_id");
	var id = $("#kat_"+item_id+" option:selected").val();
	$.get("../dispatch.php", {
		dispatch : "admin_category_options",
		id : id,
		item_id : item_id
	}, returnSubId);
}

function returnSubId(data) {
	$("#category_"+item_id).html(data);
}

function doDelete(item) {
	id = $(item).attr("proiz_id");
	$.post("../action.php", {
		delete_proizvod : "submit",
		id : id
	}, returnDelete);
}

function returnDelete(data) {
	$("#load_html").html(data);
	if (current){
		doAdvancedSearch();
	}
	else {
		doSmallerSearch();
	}
	$("#close").click(removeMessage);
	showDialog("#message");
}

function doUpdate(item) {
	item_id = $(item).attr("proiz_id");
	var id_kat = $("#kat_"+item_id+" option:selected").val();
	var id_pod = $("#pod_"+item_id+" option:selected").val();
	var naziv = $("#naz_"+item_id).val();
	var poreklo = $("#por_"+item_id).val();
	var opis = $("#opi_"+item_id).val();
	var pakovanje = $("#pak_"+item_id).val();
	var cena = $("#cen_"+item_id).val();
	var stanje = $("#sta_"+item_id).val();
	var minimum = $("#min_"+item_id).val();	
	$.post("../action.php", {
		update_proizvod : "submit",
		id : item_id,
		id_kat : id_kat,
		id_pod : id_pod,
		naziv : naziv,
		poreklo : poreklo,
		opis : opis,
		pakovanje : pakovanje,
		cena : cena,
		stanje : stanje,
		minimum : minimum
	}, returnUpdate);
}

function returnUpdate(data) {
	$("#load_html").html(data);
	if (current){
		doAdvancedSearch();
	}
	else {
		doSmallerSearch();
	}
	$("#close").click(removeMessage);
	showDialog("#message");
}

function doAddNew() {
	if ($(this).val()=="Dodaj"){
		doAdd(this);
	}
	else if ($(this).val()=="Snimi"){
		doSave(this);
	}
}

function doAdd(item) {
	item_id = 0;
	$("#kat_"+item_id).removeAttr("disabled");
	$("#kat_"+item_id).removeClass();
	$("#pod_"+item_id).removeAttr("disabled");
	$("#pod_"+item_id).removeClass();
	$("#naz_"+item_id).removeAttr("disabled");
	$("#naz_"+item_id).removeClass();
	$("#opi_"+item_id).removeAttr("disabled");
	$("#opi_"+item_id).removeClass();
	$("#cen_"+item_id).removeAttr("disabled");
	$("#cen_"+item_id).removeClass();
	$("#por_"+item_id).removeAttr("disabled");
	$("#por_"+item_id).removeClass();
	$("#pak_"+item_id).removeAttr("disabled");
	$("#pak_"+item_id).removeClass();
	$("#sta_"+item_id).removeAttr("disabled");
	$("#sta_"+item_id).removeClass();
	$("#min_"+item_id).removeAttr("disabled");
	$("#min_"+item_id).removeClass();
	$(item).val("Snimi");
	$("#div_cancel_new").removeClass();
	$("#kat_"+item_id).change(doSubId);	
}

function doSave(item) {
	item_id = 0;
	var id_kat = $("#kat_"+item_id+" option:selected").val();
	var id_pod = $("#pod_"+item_id+" option:selected").val();
	var naziv = $("#naz_"+item_id).val();
	var poreklo = $("#por_"+item_id).val();
	var opis = $("#opi_"+item_id).val();
	var pakovanje = $("#pak_"+item_id).val();
	var cena = $("#cen_"+item_id).val();
	var stanje = $("#sta_"+item_id).val();
	var minimum = $("#min_"+item_id).val();	
	$.post("../action.php", {
		add_proizvod : "submit",
		id_kat : id_kat,
		id_pod : id_pod,
		naziv : naziv,
		poreklo : poreklo,
		opis : opis,
		pakovanje : pakovanje,
		cena : cena,
		stanje : stanje,
		minimum : minimum
	}, returnSave);
}

function returnSave(data) {
	$("#load_html").html(data);
	if (current){
		doAdvancedSearch();
	}
	else {
		doSmallerSearch();
	}
	$("#close").click(removeMessage);
	showDialog("#message");
}

function doCancelNew() {
	item_id = 0;
	$("#kat_"+item_id).attr("disabled","disabled");
	$("#kat_"+item_id).addClass("non_editable");
	$("#pod_"+item_id).attr("disabled","disabled");
	$("#pod_"+item_id).addClass("non_editable");
	$("#naz_"+item_id).attr("disabled","disabled");
	$("#naz_"+item_id).addClass("non_editable");
	$("#opi_"+item_id).attr("disabled","disabled");
	$("#opi_"+item_id).addClass("non_editable");
	$("#cen_"+item_id).attr("disabled","disabled");
	$("#cen_"+item_id).addClass("non_editable");
	$("#por_"+item_id).attr("disabled","disabled");
	$("#por_"+item_id).addClass("non_editable");
	$("#pak_"+item_id).attr("disabled","disabled");
	$("#pak_"+item_id).addClass("non_editable");
	$("#sta_"+item_id).attr("disabled","disabled");
	$("#sta_"+item_id).addClass("non_editable");
	$("#min_"+item_id).attr("disabled","disabled");
	$("#min_"+item_id).addClass("non_editable");
	$("#add_new").val("Dodaj");
	$("#div_cancel_new").addClass("hidden");
	$("#kat_"+item_id).change(doSubId);
}

$(function() {
	$("#product_list").click(doProductList);
});