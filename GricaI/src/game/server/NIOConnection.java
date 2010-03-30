package game.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

import messages.Message;

public class NIOConnection implements Connection {

	private SelectionKey selectinKey;
	private SocketChannel socketChannel;
	private ConnectionUser connectionUser;
	private int state;
	private boolean writeReady;
	private LinkedList<ByteBuffer> sendQueue, chatQueue;
	private final int sizeBufferLen = 4;

	private ByteBuffer readBuffer, writeBuffer, sizeBuffer, sendBuffer;
	private int objectSize = -1;

	private String name = "";

	public NIOConnection(SelectionKey selectionKey) {
		state = Connection.OPENED;
		this.selectinKey = selectionKey;
		socketChannel = (SocketChannel) selectionKey.channel();
		selectionKey.attach(this);
		sizeBuffer = ByteBuffer.allocateDirect(sizeBufferLen);
		sendQueue = new LinkedList<ByteBuffer>();
		chatQueue = new LinkedList<ByteBuffer>();
	}

	public void attach(ConnectionUser cu) {
		this.connectionUser = cu;
	}

	public void doRead() {
		try {
			read(socketChannel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doWrite() {
		selectinKey.interestOps(SelectionKey.OP_READ);
		writeReady = true;
		if (sendBuffer != null)
			write(sendBuffer);
		writeQueued();
	}

	public synchronized void send(Message msg) {
		try {
			sendQueue.add(ServerUtil.serialize(msg));

			if (selectinKey.isValid())
				selectinKey.interestOps(SelectionKey.OP_READ
						| SelectionKey.OP_WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeQueued() {
		while (writeReady) {
			if (sendQueue.size() > 0) {
				writeBuffer = (ByteBuffer) sendQueue.remove(0);
				if (writeBuffer == null)
					return;
				write(writeBuffer);
			} else if (sendQueue.size() == 0) {
				break;
			}
		}

		if (writeReady && chatQueue.size() > 0) {
			writeBuffer = (ByteBuffer) chatQueue.remove(0);
			if (writeBuffer == null)
				return;
			write(writeBuffer);
			if (writeReady && (sendQueue.size() > 0 || chatQueue.size() > 0)
					&& selectinKey.isValid()) {
				selectinKey.interestOps(SelectionKey.OP_READ
						| SelectionKey.OP_WRITE);
			}
		}
	}

	private void write(ByteBuffer buffer) {
		if (!socketChannel.isOpen()) {
			try {
				System.out.println("connection not opened! "
						+ ((User) connectionUser));
			} catch (Exception e) {
				e.getMessage();
			}
			return;
		}

		if (buffer.hasRemaining()) {
			try {
				socketChannel.write(buffer);
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

	public void read(SocketChannel sc) throws IOException {
		if (objectSize == -1) {
			int read = 0;
			try {
				read = sc.read(sizeBuffer);

			} catch (IOException e) {
				this.close();
				return;
			}

			if (read == -1) {
				this.close();
				return;
			}

			if (sizeBuffer.remaining() == 0) {
				initializeObjectBuffer();
			} else {
				return;
			}
		}

		if (objectSize != -1) {
			int read = 0;
			try {
				read = sc.read(readBuffer);
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
				reset();

				Object obj = ServerUtil.deserialize(objectArray);
				if (obj != null && obj instanceof Message)
					connectionUser.receive((Message) obj);
			}

		}

	}

	private void initializeObjectBuffer() throws IOException {
		sizeBuffer.flip();

		byte[] tmpArray = new byte[sizeBufferLen];
		for (int i = 0; i < sizeBufferLen; i++)
			tmpArray[i] = sizeBuffer.get(i);
		objectSize = sizeBuffer.getInt();

		if (objectSize <= 0) {
			throw new IOException(
					"Found message of improper number of bytes - " + objectSize
							+ " bytes");
		}

		readBuffer = ByteBuffer.allocate(objectSize);
	}

	public void reset() {
		objectSize = -1;
		readBuffer = null;
		sizeBuffer.clear();
	}

	public void close() {
		if (state != Connection.CLOSED) {
			if (socketChannel.isOpen()) {
				try {
					socketChannel.close();
					selectinKey.selector().wakeup();
					selectinKey.attach(null);
				} catch (IOException ce) {
					ce.printStackTrace();
				}
			}

			if (connectionUser != null)
				connectionUser.stateChange(Connection.CLOSED);

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public ConnectionUser getConnectionUser() {
		return connectionUser;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public void setSocketChannel(SocketChannel sc) {
		this.socketChannel = sc;
	}

}
