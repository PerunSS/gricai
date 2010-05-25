
function MessageFactory(){
}

MessageFactory.prototype.createLoginMessage = function(values){
	var names = ["username","password"];
	var msg = new Message(names,values,"LoginMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createChatMessage = function(values){
	var names = ["username", "text"];
	var msg = new Message( names, values, "ChatMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createErrorMessage = function( values){
	var names = ["errorCode"];
	var msg = new Message( names, values, "ErrorMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createJoinRoomMessage = function( values){
	var names = ["username", "roomName"];
	var msg = new Message( names, values, "JoinRoomMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createJoinRoomResponseMessage = function( values){
	var names = ["roomName", "canJoin"];
	var msg = new Message( names, values, "JoinRoomResponseMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createLeaveRoomMessage = function( values){
	var names = [];
	var msg = new Message( names, values, "LeaveRoomMessage");
	return msg.toJSONString();
}

MessageFactory.prototype.createLoginResponseMessage = function( values){
	var names = ["logged", "username"];
	var msg = new Message( names, values, "LoginResponseMessage");
	return msg.toJSONString();
}
