package com.gricai.central.server.nioMark2.impl;

import java.nio.ByteBuffer;

import com.gricai.central.server.nioMark2.BufferFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Mar 19, 2007
 * Time: 2:59:03 PM
 */
public class DumbBufferFactory implements BufferFactory
{
	private int capacity;

	public DumbBufferFactory (int capacity)
	{
		this.capacity = capacity;
	}
	@Override
	public ByteBuffer newBuffer()
	{
		return (ByteBuffer.allocate (capacity));
	}
	@Override
	public void returnBuffer (ByteBuffer buffer)
	{
		// do nothing
	}
}
