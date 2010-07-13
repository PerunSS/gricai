package com.gricai.nio.framework.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import com.gricai.common.message.MessageFactory;
import com.gricai.nio.framework.ClientServerObject;
import com.gricai.nio.framework.Connection;

public class ConnectionImpl implements Connection {
	
	private SelectionKey selectinKey;
	private SocketChannel socketChannel;
	
	private List<ByteBuffer> sendQueue;
	private ByteBuffer readBuffer, writeBuffer;
	private ClientServerObject clientServerObject;
	
	private static final int sizeOfMessageLength = 4;
	private ByteBuffer sizeOfMessageBuffer;
	private ByteBuffer sendBuffer;
	
	private int objectSize = -1;
	private boolean writeReady;

	public ConnectionImpl(SelectionKey selectionKey, ClientServerObject clientServerObject) {
		this.clientServerObject = clientServerObject;
		socketChannel = (SocketChannel) selectionKey.channel();
		selectionKey.attach(this);
		this.selectinKey = selectionKey;
		sendQueue = new LinkedList<ByteBuffer>();
		sizeOfMessageBuffer = ByteBuffer.allocate(sizeOfMessageLength);
	}

	@Override
	public void close() {
		if (socketChannel.isOpen()) {
			try {
				clientServerObject.close(socketChannel);
				socketChannel.close();
				selectinKey.selector().wakeup();
				selectinKey.attach(null);
			} catch (IOException ce) {
				ce.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void send(ByteBuffer msg) throws IOException {
		sendQueue.add(msg);

		if (selectinKey.isValid()){
			selectinKey.interestOps(SelectionKey.OP_READ
					| SelectionKey.OP_WRITE);
		}
		//selectinKey.selector().wakeup();
	}

	public void doRead() {
		try {
			if (objectSize == -1) {
				int read = 0;
				try {
					read = socketChannel.read(sizeOfMessageBuffer);

				} catch (IOException e) {
					this.close();
					return;
				}

				if (read == -1) {
					this.close();
					return;
				}

				if (sizeOfMessageBuffer.remaining() == 0) {
					sizeOfMessageBuffer.flip();
					objectSize = sizeOfMessageBuffer.getInt();
					if (objectSize <= 0) {
						throw new IOException(
								"Found message of improper number of bytes - " + objectSize
										+ " bytes");
					}
					readBuffer = ByteBuffer.allocate(objectSize);
				} else {
					return;
				}
			}
			if (objectSize != -1) {
				int read = 0;
				try {
					read = socketChannel.read(readBuffer);
				} catch (IOException e) {
					this.close();
					return;
				}

				if (read == -1) {
					this.close();
					return;
				}

				if (readBuffer.remaining() == 0) {
					readBuffer.flip();

					final byte[] objectArray = new byte[objectSize];
					readBuffer.get(objectArray);
					ByteBuffer buffer = ByteBuffer.wrap(objectArray);
					clientServerObject.onMessage(socketChannel, buffer);
					
					reset();

//					Object obj = ServerUtil.deserialize(objectArray);
//					if (obj != null && obj instanceof Message)
//						connectionUser.receive((Message) obj);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void doWrite() {
		System.out.println("do write");
		selectinKey.interestOps(SelectionKey.OP_READ);
		writeReady = true;
		if (sendBuffer != null)
			write(sendBuffer);
		while (writeReady) {
			if (sendQueue.size() > 0) {
				writeBuffer = (ByteBuffer) sendQueue.remove(0);
				try {
					System.out.println(MessageFactory.createMessage(writeBuffer).getClass());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (writeBuffer == null)
					return;
				write(writeBuffer);
			} else if (sendQueue.size() == 0) {
				break;
			}
		}
	}
	
	private void write(ByteBuffer buffer) {
//		buffer.flip();
		if (!socketChannel.isOpen()) {
			try {
				System.err.println("connection not opened!!!");
			} catch (Exception e) {
				e.getMessage();
			}
			return;
		}
		buffer.position(0);
		System.out.println(buffer.limit()+" "+buffer.position());
		if (buffer.hasRemaining()) {
			try {
				System.out.println("WRITE "+buffer.limit());
				socketChannel.write(buffer);
				System.out.println("WRITTEN");
			} catch (IOException e) {
				this.close();
			}
		}

		if (buffer.hasRemaining()) {
			writeReady = false;
			if (selectinKey.isValid())
				selectinKey.interestOps(SelectionKey.OP_READ
						| SelectionKey.OP_WRITE);
			sendBuffer = buffer;
		} else {
			sendBuffer = null;
		}
	}
	
	private void reset() {
		objectSize = -1;
		readBuffer = null;
		sizeOfMessageBuffer.clear();
	}

}
