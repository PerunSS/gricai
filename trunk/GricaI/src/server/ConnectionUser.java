package server;
import messages.Message;




public interface ConnectionUser
{
        public void receive(Message msg);
        public void stateChange(int state);
}
