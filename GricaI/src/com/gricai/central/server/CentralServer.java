package com.gricai.central.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.gricai.central.server.dbManager.SQLSelect;
import com.gricai.common.message.Message;
import com.gricai.common.message.MessageFactory;
import com.gricai.common.message.exception.WrongMessageTypeException;
import com.gricai.common.message.server.LoginMessage;
import com.gricai.common.message.server.LoginResponseMessage;
import com.gricai.nio.framework.impl.ServerImpl;

public class CentralServer extends ServerImpl {

	@Override
	public void onMessage(SocketChannel socketChannel, ByteBuffer message)
			throws IOException {
		try {
			Message msg = MessageFactory.createMessage(message);
			if(msg instanceof LoginMessage){
				LoginMessage lmsg = (LoginMessage)msg;
				String username = lmsg.getUsername();
				String password = lmsg.getPassword();
				boolean exists = false;
				try {
					SQLSelect select = SQLSelect.getInstance();
					select.select("username").select("password").from("users").where("username=:username").parameter("username", username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//TODO check in database
				if(exists){
					LoginResponseMessage lrm = new LoginResponseMessage(username);
					sendMessage(socketChannel, lrm.toByteBuffer());
				}else{
					
				}
			}
		} catch (WrongMessageTypeException e) {
			e.printStackTrace();
		}
	}

}
