package com.softm.raziel.crypt;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSACyperUtil {

	public static byte[] decriptRSA(final byte[] encriptedData,
			final byte[] privateKeyBytes) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

		final PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKeyBytes);
		final KeyFactory kf = KeyFactory.getInstance(RSA);
		final PrivateKey privateKey = kf.generatePrivate(ks);
		final Cipher c = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
		c.init(Cipher.DECRYPT_MODE, privateKey);
		final byte[] decrypted_bytes = c.doFinal(encriptedData);
		return decrypted_bytes;

	}

	public static byte[] encryptRSA(final byte[] data,
			final byte[] publicKeyBytes) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKeyBytes);
		final KeyFactory kf = KeyFactory.getInstance(RSA);
		final PublicKey publicKey = kf.generatePublic(ks);
		final Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		final byte[] enctipted_bytes = cipher.doFinal(data);
		return enctipted_bytes;
	}

	public static byte[] generateTicket(final AESCofferKey contentKey,
			final byte[] publicKeyBytes) {
		try {
			final byte[] secretKey = contentKey.getSecretKey();
			final byte[] enctipted_bytes = encryptRSA(secretKey, publicKeyBytes);
			return enctipted_bytes;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the coffer key.
	 *
	 * @return the coffer key
	 */
	public static AsymmetricKey getCofferKey() {
		try {

			final KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(1024);
			final KeyPair kp = kpg.generateKeyPair();
			// X.509
			final byte[] publicKey = kp.getPublic().getEncoded();
			// PKCS#8
			final byte[] privateKey = kp.getPrivate().getEncoded();

			final AsymmetricKey cofferKey = new AsymmetricKey(publicKey,
					privateKey);

			return cofferKey;

		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
	private static final String RSA = "RSA";

}
