package com.softm.secret.crypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.softm.secret.payload.Coffer;
import com.softm.secret.payload.Treasure;

public class AESCofferKey extends CofferKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3663336776189514516L;
	private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";
	private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";
	private static final String AES = "AES";
	private byte[] secretKey;
	private int ITERATIONSCOUNT=1000;
	private byte[] salt;

	public byte[] getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(byte[] secretKey) {
		this.secretKey = secretKey;
	}

	public AESCofferKey() {
		super();
	}

	public AESCofferKey(byte[] secretKey) {
		this();
		this.secretKey = secretKey;
	}

	@Override
	public void lockCoffer(Coffer coffer) {

		try {
			Cipher cipher = initChipher(Cipher.ENCRYPT_MODE);			
			byte[] encrypted = cipher.doFinal(coffer.getTreasure().getBytes());
			coffer.setEncryptedBytes(encrypted);
			coffer.setTreasure(null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO : handle exceptions
		}
	}

	private Cipher initChipher(int encryptMode) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException {
		//TODO: extract secret key generation
		char[] password = new String(secretKey).toCharArray();
		   salt = "salt".getBytes();
		
		SecretKeyFactory factoryKeyEncrypt = SecretKeyFactory
				.getInstance(PBKDF2_WITH_HMAC_SHA1);
		KeySpec keySpec = new PBEKeySpec(password,salt,ITERATIONSCOUNT,128);
		
		SecretKey secretKey = factoryKeyEncrypt.generateSecret(keySpec); 
		SecretKeySpec encryptKey = new SecretKeySpec(secretKey.getEncoded(), AES);

		Cipher cipher = Cipher
				.getInstance(AES_ECB_PKCS5_PADDING);	  
		cipher.init(encryptMode, encryptKey);
		return cipher;
	}

	@Override
	public void openCoffer(Coffer closedCoffer) {
		try {
			Cipher cipher = initChipher(Cipher.DECRYPT_MODE);			
			byte[] decrypted_bytes = cipher.doFinal(closedCoffer.getEncryptedBytes());
			closedCoffer.setTreasure(Treasure.inflate(decrypted_bytes));
			closedCoffer.setEncryptedBytes(null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO : handle exceptions
		}

	}
}
