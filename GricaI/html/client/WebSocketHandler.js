function WebSocketHandler(){

	this.ws = new WebSocket("ws://localhost:21112");
	this.recieve;
	this.ws.onopen = function(event){
	
	}

	this.ws.onclose = function(event){
		
	}

	this.ws.onmessage = function(event){
		this.recieve = event.data;
	}
}
	
WebSocketHandler.prototype.sendMessage = function (message){
	this.ws.send(message);
}
WeboSocketHandler.prototype.getRecieve = function(){
	return recieve;
}