package com.gricai.common.message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gricai.common.message.server.LoginMessage;


public class JSONsomething {
	
	
	public static void main(String[] args) throws Exception{
		JSONObject testObject =  JSONObject.fromObject("{ \"LoginMessage\" : { \"username\" :  \"pera\" , \"password\" : \"1234\" } }");
		String something = "class=" + testObject.names().getString(0);
		JSONObject parameters = testObject.getJSONObject(testObject.names().getString(0));
		JSONArray parametersKeys = parameters.names();
		for (int i = 0; i < parametersKeys.size(); i ++){
			something =  something + "&" + parametersKeys.getString(i) + "=" + parameters.getString(parametersKeys.getString(i));
		}
		System.out.println(something);
		
		
		
		Message nesto = MessageFactory.createMessage(testObject, "username");
		LoginMessage lnesto = (LoginMessage)nesto;
		System.out.println(lnesto.getUsername());
		System.out.println(lnesto.getPassword());
		
		
	}
}


