package game.server;

import messages.Message;

public interface MessageReceiver {
	
    public abstract void receive(User from, Message msg);
    public abstract void connected(User user);
}
