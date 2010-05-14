
function MessageFactory(){
}

MessageFactory.prototype.createLoginMessage = function(values){
	var names = ["username","password"];
	var msg = new Message(names,values,"LoginMessage");
	return msg;
}

MessageFactory.prototype.createChatMessage = function(values){
	
}