package old.game.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import old.messages.Message;


public class Server implements Runnable {

	private ServerSocketChannel serverSocketChannel;

	private Selector selector;

	private int PORT = 45453;

	private MessageReceiver messageReciver;

	public Server(int PORT, MessageReceiver messageReciver) {
		this.PORT = PORT;
		this.messageReciver = messageReciver;

		new CleanupThread(30000).start();
	}

	public void runServer() {
		Thread t = new Thread(this);
		t.run();
	}

	public void run() {
		try {
			openChannel();
			processRequests();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void openChannel() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

		while (!serverSocketChannel.isOpen()) {
		}
		System.out.println("Server started...");

		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void closeChannel() throws IOException {
		serverSocketChannel.close();

	}

	private void processRequests() throws IOException {

		while (true) {
			try {
				selector.select();
			} // TODO - should be selectNow()
			catch (Exception e) {
				e.printStackTrace();
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				int kro = key.isValid() ? key.readyOps() : -1;
				if ((kro & SelectionKey.OP_READ) == SelectionKey.OP_READ)
					doRead(key);
				if ((kro & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
					doAccept(key);
				if ((kro & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE)
					doWrite(key);
				iterator.remove();
			}

		}

	}

	private void doAccept(SelectionKey key) {
		try {
			ServerSocketChannel sc = (ServerSocketChannel) key.channel();
			SocketChannel usc = sc.accept();
			usc.configureBlocking(false);
			usc.socket().setKeepAlive(true);
			String nm = usc.socket().getInetAddress() + ":"
					+ usc.socket().getPort();
			SelectionKey dsk = usc.register(selector, SelectionKey.OP_READ
					| SelectionKey.OP_WRITE);
			Connection conn = new NIOConnection(dsk);
			conn.setName(nm);
			// new User(conn, userTable, mrec);
			messageReciver.connected(new User(conn, messageReciver));
			dsk.attach(conn);
		} catch (Exception e) {
			// Log.get().warn("******************************************************************************");
			// Log.get().warn(e.getMessage());
			// Log.get().warn("******************************************************************************");
		}

	}

	private void doRead(SelectionKey key) {
		NIOConnection conn = (NIOConnection) key.attachment();
		if (conn != null)
			conn.doRead();
		else {
			// Log.get().warn("******************************************************************************");
			// Log.get().warn("Error connection object is null!!!");
			// Log.get().warn("******************************************************************************");
		}
	}

	private void doWrite(SelectionKey key) {
		NIOConnection conn = (NIOConnection) key.attachment();
		if (conn != null)
			conn.doWrite();
		else {
			// Log.get().warn("******************************************************************************");
			// Log.get().warn("Error connection object is null!!!");
			// Log.get().warn("******************************************************************************");
		}
	}

	public void sendMessage(Message msg, NIOConnection conn) {
		if (conn != null)
			conn.send(msg);
		else {
			// Log.get().warn("******************************************************************************");
			// Log.get().warn("Error connection object is null!!!");
			// Log.get().warn("******************************************************************************");
		}

	}

	private class CleanupThread extends Thread {
		int sleepTime;
		boolean running;

		public CleanupThread(int sleepTime) {
			this.sleepTime = sleepTime;
		}

		public void run() {
			while (running) {

				try {
					sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

		public void start() {
			running = true;
			super.start();
		}
	}
}
