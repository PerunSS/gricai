function Game(){
}

Game.prototype.getCreateGamePage = function(){
//	document.close();
//	document.open();
	document.write("<h1>Create game</h1>")
	//form for game creation
	var form = document.createElement("form");
	form.method = "post";
	
	//game type radio buttons
	var ffaGameType = document.createElement("input");
	ffaGameType.type = "radio";
	ffaGameType.id = "ffa";
	ffaGameType.name = "gameType";
	ffaGameType.value = "FFA";
	
	var teamGameType = document.createElement("input")
	teamGameType.type = "radio";
	teamGameType.id = "team";
	teamGameType.name = "gameType";
	teamGameType.value = "TEAM GAME";
	
	form.appendChild(ffaGameType);
	form.appendChild(document.createTextNode("Free for all"));
	form.appendChild(teamGameType);
	form.appendChild(document.createTextNode("Team game"));
	
	var numTeams = document.createElement("input");
	numTeams.type = "text";
	numTeams.id = "numTeams";
	var numPlayersPerTeam = document.createElement("input");
	numPlayersPerTeam.type = "text";
	numPlayersPerTeam.id = "numPlayersPerTeam";
	
	form.appendChild(document.createTextNode("number of teams: "));
	form.appendChild(numTeams);
	form.appendChild(document.createTextNode("players per team: "));
	form.appendChild(numPlayersPerTeam);
	document.body.appendChild(form);
}