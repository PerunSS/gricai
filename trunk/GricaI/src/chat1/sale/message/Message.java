package chat1.sale.message;

import net.sf.json.JSONObject;


public abstract class Message {

	public abstract String toJsonString();
	public abstract void fillMessage(JSONObject jsonMessage);
}
