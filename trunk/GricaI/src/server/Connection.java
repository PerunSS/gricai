package server;
import messages.Message;




public interface Connection {

    static public final int CLOSED=0;
    static public final int OPENING=1;
    static public final int OPENED=2;
    static public final int CLOSING=3;
    
    public void attach(ConnectionUser cu);
    public void send(Message msg);
    public void close();
    public int getState();
    public void setName(String name);
    public String getName();
}
