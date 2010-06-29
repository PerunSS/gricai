function GameCreator(){
	this.ffaRadio = this.createRadioButton("FFA", "Free for all","ffaEvent();");
	
	this.teamRadio = this.createRadioButton("teamGameType", "Team game","teamEvent();");
	/*this.ws = new WebSocket("ws://localhost:61616");
	this.ws.onopen = function(event){
	}
	this.ws.onclose = function(event){
	}
	this.ws.onmessage = function(event){
		recieve(event.data);
	}*/
	document.close();
	document.open();
	document.write("<h2>Create game</h2>");
	
	
	this.form = document.createElement("form");
	this.form.id="createGameForm";
	
	this.form.appendChild(this.ffaRadio);
	this.form.appendChild(document.createTextNode("Free for all"));
	this.form.appendChild(this.teamRadio);
	this.form.appendChild(document.createTextNode("Team game"));
	this.form.appendChild(document.createElement("br"));
	
	this.form.appendChild(document.createTextNode("map: "));
	
	this.select = document.createElement("select");
	this.select.size = 10;
	this.select.id="maps";
	
	this.form.appendChild(this.select);
	this.form.appendChild(document.createElement("br"));	
	
	this.submit = document.createElement("input");
	this.submit.type = "submit";
	this.submit.id = "submit";
	this.submit.value = "ok";	
	this.form.appendChild(this.submit);
	
	document.body.appendChild(this.form);
	
	document.write("<p id='prikaz'>Text</p>");
}

function ffaEvent(){
	
}

function teamEvent(){
	document.getElementById("prikaz").innerHtml="Team game";
	alert("team");
}

GameCreator.prototype.factory = new MessageFactory();

GameCreator.prototype.createRadioButton = function(name,value,eventFunction){
	var button = document.createElement("input");
	button.type = "radio";
	button.id = name;
	button.name = "gameType";
	button.value = value;
	button.setAttribute("onClick",eventFunction);
	
	return button;
}

GameCreator.prototype.createMapSelector = function(mapTypes, ws){
	var select = document.createElement("select");
	select.name = "map";
	select.size = 20;
	this.form.appendChild(document.createTextElement("Maps: "));
	this.form.appendChild(select);
	//this.ws.send(this.factory.createGetMapMessage(mapTypes));
	
}

GameCreator.prototype.recieve = function(data){
//	Message msg = factory.createMessageFromData(data);
//	if(msg.getMessageType == 'MapsMessage'){
//		var select = document.getElementsByTagName('select')[0];
//		
//	}
	
}