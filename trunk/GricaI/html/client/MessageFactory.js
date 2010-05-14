
function MessageFactory(){
}

MessageFactory.prototype.createLoginMessage = function(values){
	var names = ["username","password"];
	var msg = new Message(names,values,"LoginMessage");
	return msg;
}

MessageFactory.prototype.createChatMessage = function(values){
	var names = ["username", "text"];
	var msg = new Message( names, values, "ChatMessage");
	return msg;
}

MessageFactory.prototype.createErrorMessage = function( values){
	var names = ["errorCode"];
	var msg = new Message( names, values, "ErrorMessage");
	return msg;
}

MessageFactory.prototype.createJoinRoomMessage = function( values){
	var names = ["username", "roomName"];
	var msg = new Message( names, values, "JoinRoomMessage");
	return msg;
}

MessageFactory.prototype.createJoinRoomResponseMessage = function( values){
	var names = ["roomName", "canJoin"];
	var msg = new Message( names, values, "JoinRoomResponseMessage");
	return msg;
}

MessageFactory.prototype.createLeaveRoomMessage = function( values){
	var names = [];
	var msg = new Message( names, values, "LeaveRoomMessage");
	return msg;
}

MessageFactory.prototype.createLoginResponseMessage = function( values){
	var names = ["logged", "username"];
	var msg = new Message( names, values, "LoginResponseMessage");
	return msg;
}
