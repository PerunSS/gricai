package com.gricai.central.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Properties;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ch.unifr.nio.framework.AbstractChannelHandler;
import ch.unifr.nio.framework.Dispatcher;
import ch.unifr.nio.framework.transform.ByteBufferToStringTransformer;
import ch.unifr.nio.framework.transform.StringToByteBufferTransformer;

public class MainClient extends AbstractChannelHandler {

	private final Lock lock = new ReentrantLock();
	private final Condition inputArrived = lock.newCondition();
	private static final String propertiesFile = "server.properties";

	public MainClient() {
		ByteBufferToStringTransformer byteBufferToStringTransformer = new ByteBufferToStringTransformer();
		reader.setNextForwarder(byteBufferToStringTransformer);
		MainClientForwarder echoTransformer = new MainClientForwarder(this);
		byteBufferToStringTransformer.setNextForwarder(echoTransformer);

		// setup output chain
		StringToByteBufferTransformer stringToByteBufferTransformer = new StringToByteBufferTransformer();
		stringToByteBufferTransformer.setNextForwarder(writer);

		try {
			// connect to server

			Properties props = new Properties();
			try {
				props.load(new FileReader(propertiesFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int port = Integer.parseInt(props.getProperty("port"));
			String host = props.getProperty("host");
			InetSocketAddress socketAddress = new InetSocketAddress(host, port);
			SocketChannel channel = SocketChannel.open(socketAddress);
			channel.configureBlocking(false);

			// start NIO Framework
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();
			dispatcher.registerChannel(channel, this);

			// send all user input to echo server
			System.out.println("[CLIENT] " + "EchoClient is running...");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader stdIn = new BufferedReader(streamReader);
			while (true) {
				System.out.print("[CLIENT] " + "Your input: ");
				String userInput = stdIn.readLine();
				if (userInput.length() == 0) {
					continue;
				}
				System.out.println("[CLIENT] " + "sending \"" + userInput + "\"");
				stringToByteBufferTransformer.forward(userInput);
				// wait until we get an echo from the server...
				lock.lock();
				try {
					inputArrived.await();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void channelException(Exception exception) {
		System.out.println("[CLIENT] " + "Exception on channel: " + exception);
		System.exit(1);
	}

	@Override
	public void inputClosed() {
		System.out.println("[CLIENT] " + "EchoServer closed the connection");
		System.exit(1);

	}

	public Lock getLock() {
		return lock;
	}

	public Condition getInputArrived() {
		return inputArrived;
	}

	public static void main(String[] args) {
		new MainClient();
	}
}
