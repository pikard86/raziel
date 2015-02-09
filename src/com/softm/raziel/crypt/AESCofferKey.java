/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.crypt;

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

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.Treasure;

// TODO: Auto-generated Javadoc
/**
 * The Class AESCofferKey.
 */
public class AESCofferKey extends CofferKey {

	/** The Constant AES. */
	private static final String AES = "AES";

	/** The Constant AES_ECB_PKCS5_PADDING. */
	private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";

	/** The Constant PBKDF2_WITH_HMAC_SHA1. */
	private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3663336776189514516L;

	/** The iterationscount. */
	private final int ITERATIONSCOUNT = 1000;

	/** The salt. */
	private byte[] salt;

	/** The secret key. */
	private byte[] secretKey;

	/**
	 * Instantiates a new AES coffer key.
	 */
	public AESCofferKey() {
		super();
	}

	/**
	 * Instantiates a new AES coffer key.
	 *
	 * @param secretKey
	 *            the secret key
	 */
	public AESCofferKey(final byte[] secretKey) {
		this();
		this.secretKey = secretKey;
	}

	/**
	 * Gets the secret key.
	 *
	 * @return the secret key
	 */
	public byte[] getSecretKey() {
		return secretKey;
	}

	/**
	 * Initializes cipher.
	 *
	 * @param encryptMode
	 *            the encrypt mode
	 * @return the cipher
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws InvalidKeySpecException
	 *             the invalid key spec exception
	 * @throws NoSuchPaddingException
	 *             the no such padding exception
	 * @throws InvalidKeyException
	 *             the invalid key exception
	 */
	private Cipher initCipher(final int encryptMode)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException {
		// TODO: extract secret key generation
		final char[] password = new String(secretKey).toCharArray();
		salt = "salt".getBytes();

		final SecretKeyFactory factoryKeyEncrypt = SecretKeyFactory
				.getInstance(PBKDF2_WITH_HMAC_SHA1);
		final KeySpec keySpec = new PBEKeySpec(password, salt, ITERATIONSCOUNT,
				128);

		final SecretKey secretKey = factoryKeyEncrypt.generateSecret(keySpec);
		final SecretKeySpec encryptKey = new SecretKeySpec(
				secretKey.getEncoded(), AES);

		final Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
		cipher.init(encryptMode, encryptKey);
		return cipher;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.softm.secret.crypt.CofferKey#lockCoffer(com.softm.secret.payload.
	 * Coffer)
	 */
	@Override
	public void lockCoffer(final Coffer coffer) {

		try {
			final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
			final byte[] encrypted = cipher.doFinal(coffer.getTreasure()
					.getBytes());
			coffer.setEncryptedBytes(encrypted);
			coffer.setTreasure(null);
		} catch (final Exception e) {
			e.printStackTrace();
			// TODO : handle exceptions
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.softm.secret.crypt.CofferKey#openCoffer(com.softm.secret.payload.
	 * Coffer)
	 */
	@Override
	public void openCoffer(final Coffer closedCoffer) {
		try {
			final Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
			final byte[] decrypted_bytes = cipher.doFinal(closedCoffer
					.getEncryptedBytes());
			closedCoffer.setTreasure(Treasure.inflate(decrypted_bytes));
			closedCoffer.setEncryptedBytes(null);
		} catch (final Exception e) {
			e.printStackTrace();
			// TODO : handle exceptions
		}

	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey
	 *            the new secret key
	 */
	public void setSecretKey(final byte[] secretKey) {
		this.secretKey = secretKey;
	}
}
