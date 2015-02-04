/*
 * Raziel 
 * SofthMelody a Fishella Corporation Company 
 */
package com.softm.secret.crypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.softm.secret.payload.Coffer;
import com.softm.secret.payload.Treasure;

// TODO: Auto-generated Javadoc
/**
 * The Class RSACofferKey.
 */
public class RSACofferKey extends CofferKey {

	/** The Constant RSA. */
	private static final String RSA = "RSA";
	
	/** The Constant RSA_ECB_PKCS1_PADDING. */
	private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5219937819748791210L;
	
	/** The private key. */
	private byte[] privateKey;
	
	/** The public key. */
	private byte[] publicKey;

	/**
	 * Instantiates a new RSA coffer key.
	 */
	public RSACofferKey() {
		super();
	}

	/**
	 * Instantiates a new RSA coffer key.
	 *
	 * @param publicKey the public key
	 * @param privateKey the private key
	 */
	public RSACofferKey(byte[] publicKey, byte[] privateKey) {
		this();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	/**
	 * Gets the private key.
	 *
	 * @return the private key
	 */
	public byte[] getPrivateKey() {
		return privateKey;
	}

	/**
	 * Gets the public key.
	 *
	 * @return the public key
	 */
	public byte[] getPublicKey() {
		return publicKey;
	}

	/* (non-Javadoc)
	 * @see com.softm.secret.crypt.CofferKey#lockCoffer(com.softm.secret.payload.Coffer)
	 */
	@Override
	public void lockCoffer(Coffer coffer) {
		try {
			X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKey);
			KeyFactory kf = KeyFactory.getInstance(RSA);
			PublicKey publicKey = kf.generatePublic(ks);

			Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] enctipted_bytes = cipher.doFinal(coffer.getTreasure()
					.getBytes());
			coffer.setEncryptedBytes(enctipted_bytes);
			coffer.setTreasure(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.softm.secret.crypt.CofferKey#openCoffer(com.softm.secret.payload.Coffer)
	 */
	@Override
	public void openCoffer(Coffer closedCoffer) {
		try {
			PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKey);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kf.generatePrivate(ks);
			Cipher c = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
			c.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decrypted_bytes = c
					.doFinal(closedCoffer.getEncryptedBytes());
			closedCoffer.setTreasure(Treasure.inflate(decrypted_bytes));
			closedCoffer.setEncryptedBytes(null);
		} catch (Exception e) {
		}
	}

	/**
	 * Sets the private key.
	 *
	 * @param privateKey the new private key
	 */
	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Sets the public key.
	 *
	 * @param publicKey the new public key
	 */
	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
