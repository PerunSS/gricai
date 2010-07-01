function GameTypeChooser(){
	this.gameType = document.createElement("select");
	this.gameType.id = "gameType";
	this.gameType.attributes.setAttribute("onChange","changeAll();");
	
	var ffaType = document.createElement("option");
	ffaType.value = "ffaType";
	ffaType.innerHtml = "Free for all game";
	var teamType = document.createElement("option");
	teamType.value = "teamType";
	teamType.innerHtml = "Team game";
	
	this.gameType.appendChild(ffaType);
	this.gameType.appendChild(teamType);
}

GameTypeChooser.prototype.getSelect = function(){
	return this.gameType;
}

function changeAll(){
	alert(document.getElementById("gameType").value);
}