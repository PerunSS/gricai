package com.gricai.common.message;

import java.nio.ByteBuffer;

public class LoginResponseMessage implements Message {
	
	private boolean logged;

	@Override
	public void fillMessage(ByteBuffer data) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isLogged() {
		return logged;
	}

}
