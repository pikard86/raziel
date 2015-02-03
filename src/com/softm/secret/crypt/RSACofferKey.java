package com.softm.secret.crypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.softm.secret.payload.Coffer;
import com.softm.secret.payload.Treasure;

public class RSACofferKey extends CofferKey {

	private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
	private static final String RSA = "RSA";
	/**
	 * 
	 */
	private static final long serialVersionUID = 5219937819748791210L;
	private byte[] publicKey;
	private byte[] privateKey;

	@Override
	public void lockCoffer(Coffer coffer) {
		try {
			X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKey);
			KeyFactory kf = KeyFactory.getInstance(RSA);
			PublicKey publicKey = kf.generatePublic(ks);
			
			Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			byte[] enctipted_bytes = cipher.doFinal(coffer
					.getTreasure().getBytes());
			coffer.setEncryptedBytes(enctipted_bytes);
			coffer.setTreasure(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RSACofferKey() {
		super();
	}

	public RSACofferKey(byte[] publicKey, byte[] privateKey) {
		this();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	@Override
	public void openCoffer(Coffer closedCoffer) {
		try {
			 PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKey);
             KeyFactory kf = KeyFactory.getInstance("RSA");
             PrivateKey privateKey = kf.generatePrivate(ks);
             Cipher c = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
             c.init(Cipher.DECRYPT_MODE, privateKey);
             byte[] decrypted_bytes = c.doFinal(closedCoffer.getEncryptedBytes());
             closedCoffer.setTreasure(Treasure.inflate(decrypted_bytes));
             closedCoffer.setEncryptedBytes(null);			
		} catch (Exception e) {
		}
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	public byte[] getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

}
