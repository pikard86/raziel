package com.softm.raziel.test;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import com.softm.raziel.payload.Coffer;

public class RsaCofferKeyTest {

	@Test
	public void rsaKeyLockOpenTest() throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		final KeyPair kp = kpg.generateKeyPair();
		// X.509
		final byte[] publicKey = kp.getPublic().getEncoded();
		// PKCS#8
		final byte[] privateKey = kp.getPrivate().getEncoded();

		final Coffer<Message> messageCoffer = new Coffer<Message>();
		messageCoffer.setTreasure(new Message("Hello Bob", new Date()));

		final KeyFactory kf = KeyFactory.getInstance("RSA");
		final X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKey);
		final PublicKey publicKeyGen = kf.generatePublic(ks);// FIXME:
		// exception
		// thrown

		final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKeyGen);

		final byte[] bytes = messageCoffer.getTreasure().getBytes();
		final byte[] enctipted_bytes = cipher.doFinal(bytes);
		messageCoffer.setEncryptedBytes(enctipted_bytes);
		messageCoffer.setTreasure(null);

	}
}
