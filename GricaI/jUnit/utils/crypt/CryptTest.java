package utils.crypt;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class CryptTest {

	@Test
	public void testFile() {
		String alias = "testKey1";
		/*
		 * If You want to make new key, remove comment from code below, but be
		 * sure that alias don't exists in keys.dat file
		 */
//		try {
//			Crypt.generateAndSaveKey(alias);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		byte data[] = "test message".getBytes();
		try {
			byte crypted[] = Crypt.cryptData(data, alias);
			byte decrypted[] = Crypt.deCryptData(crypted, alias);
			assertEquals(new String(data), new String(decrypted));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
