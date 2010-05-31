package com.gricai.game.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ServerUtil {

	private static ServerUtil instance = new ServerUtil();;

	public static synchronized ServerUtil getInstance() {
		return instance;
	}

	public static ByteBuffer serialize(Object obj) throws IOException {
		if (obj == null) {
			return null;
		} else if (obj instanceof byte[]) {
			return ByteBuffer.wrap((byte[]) obj);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		oos.writeObject(obj);
		oos.close();
		int len = baos.toByteArray().length;

		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos2);

		dos.writeInt(len);
		dos.flush();
		dos.write(baos.toByteArray());
		dos.flush();

		return ByteBuffer.wrap(baos2.toByteArray());

	}

	public static Object deserialize(byte[] array) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(array));
			return ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static byte[] convertIntToByteArray(int arrayLen, int number) {
		byte[] num = new byte[arrayLen];
		String tmpNum = number + "";
		byte[] tmpLen = tmpNum.getBytes();

		if (tmpNum.length() > arrayLen) {
			System.out
					.println("Number could not fit in to the array: arrayLen: "
							+ arrayLen + "; number: " + number + ";");
			return null;
		}

		for (int i = arrayLen - tmpLen.length, k = 0; i < arrayLen; i++, k++)
			num[i] = tmpLen[k];

		return num;
	}

	public static int convertByteArrayToInt(byte[] array) {
		if (array == null || array.length == 0)
			return -1;

		String tmpLen = "";

		for (int i = array.length - 1; i >= 0; i--)
			if (array[i] != 0)
				tmpLen = (char) array[i] + tmpLen;

		return Integer.parseInt(tmpLen);
	}

}
