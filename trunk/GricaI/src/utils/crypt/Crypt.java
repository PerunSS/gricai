package utils.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import utils.log.LogFactory;

public class Crypt {

	private static final String filename = "keys.dat";
	private static Map<String,SecretKeySpec> keys = new HashMap<String, SecretKeySpec>();

	public static void generateAndSaveKey(String alias)
			throws NoSuchAlgorithmException, IOException {
		KeyGenerator kgen;
		try {
			kgen = KeyGenerator.getInstance("Blowfish");
		} catch (NoSuchAlgorithmException e) {
			LogFactory.getLog("crypt").logException(e);
			throw e;
		}
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
		byte[] encodedKeySpec = skeySpec.getEncoded();
		File file = new File(filename);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				LogFactory.getLog("crypt").logException(e);
				throw e;
			}
		FileOutputStream out;
		try {
			out = new FileOutputStream(file, true);
			out.write(alias.getBytes());
			out.write(";".getBytes());
			out.write(encodedKeySpec);
			out.write("\r\n".getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			LogFactory.getLog("crypt").logException(e);
			throw e;
		} catch (IOException e) {
			LogFactory.getLog("crypt").logException(e);
			throw e;
		}

	}

	// TODO modify
	public static byte[] cryptData(byte[] data, String alias)
			throws IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		readKeys();
		SecretKeySpec skeySpec = keys.get(alias);
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		// Encrypt the data
		byte[] encrypted = cipher.doFinal(data);

		return encrypted;
	}
	
	public static byte[] deCryptData(byte[] data, String alias)
			throws IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException{
		readKeys();
		SecretKeySpec skeySpec = keys.get(alias);
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		
		byte[] decrypted = cipher.doFinal(data);
		return decrypted;
	}

	private static void readKeys() throws IOException{
		File file = new File(filename);
		if (!file.exists())
			throw new FileNotFoundException();
		FileInputStream in = new FileInputStream(file);
		byte[] temp = new byte[(int) file.length()];
		try {
			int bytesRead = in.read(temp);
			if(bytesRead == -1)
				return;
			in.close();
			String fileString = new String(temp);
			String lines[] = fileString.split("\r\n");
			for(String line:lines){
				String lineParts[] = line.split(";");
				String alias = lineParts[0];
				byte[] encodedKeySpec = lineParts[1].getBytes();
				SecretKeySpec skeySpec = new SecretKeySpec(encodedKeySpec, "Blowfish");
				keys.put(alias, skeySpec);
			}
		} catch (IOException e) {
			LogFactory.getLog("crypt").logException(e);
			throw e;
		}
		
	}
	
}
