function Game(){
	inc("com/gricai/messages/Message.js");
	inc("com/gricai/messages/MessageFactory.js");
	inc("com/gricai/game/GameCreator.js");
	inc("com/gricai/game/GameTypeChooser.js");
}

function inc(filename){
	script = document.createElement('script');
	script.src = filename;
	script.type = 'text/javascript';
	document.head.appendChild(script);
}

Game.prototype.getGameCreator = function(){
	this.creator = new GameCreator();
}