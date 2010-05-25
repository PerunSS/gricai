function GricaI(){
	this.ws = new WebSocket("ws://localhost:21112");
	this.ws.onopen = function(event){
	}
	this.ws.onclose = function(event){
	}
	this.ws.onmessage = function(event){
		GricaI.recieve(event.data);
	}
	
	this.recieve = function(message){
		
		alert(message);
	}
	
	this.factory = new MessageFactory();
}

GricaI.prototype.send = function(message){
	this.ws.send(message);
}

GricaI.prototype.login = function(username,password){
	var values = [username,password];
	Message msg = this.factory.createLoginMessage(values);
	this.send(msg);
}
