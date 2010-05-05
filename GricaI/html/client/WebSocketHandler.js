var ws = new WebSocket("ws://localhost:21121");
var recieve;
ws.onopen = function(event){
	
}

ws.onclose = function(event){
	
}

ws.onmessage = function(event){
	recieve = event.data;
}

function sendMessage(var message){
	ws.send(message);
}
function getRecieve(){
	return recieve;
}