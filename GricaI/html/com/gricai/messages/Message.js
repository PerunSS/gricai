function Message(names,values,msgType){
	this.names = names;
	this.values = values;
	this.msgType = msgType;
}

Message.prototype.getJSONMessage = function(){
	var jsonString = "{ \""+this.msgType+"\" : {";
	for(var i=0;i<this.names.length;i++){
		jsonString += " \""+this.names[i]+"\" : \""+this.values[i]+"\",";
	}
	jsonString = jsonString.substring(0,jsonString.length-1);
	jsonString+="}}";
	alert(jsonString);
	return jsonString;
}

Message.prototype.getMsgType = function (){
	return msgType;
}