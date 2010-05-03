package com.gricai.common.message;

import java.nio.ByteBuffer;

public class JoinRoomResponseMessage implements Message {
	
	private boolean canJoin;

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

	public void setCanJoin(boolean canJoin) {
		this.canJoin = canJoin;
	}

	public boolean isCanJoin() {
		return canJoin;
	}

}
