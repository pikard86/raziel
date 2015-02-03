package com.softm.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.softm.secret.crypt.CofferKey;
import com.softm.secret.crypt.RSACofferKey;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public class OwnerFacrory {

	public static Owner createOwner(String ownerId,CofferKey ownerKey) {
		Owner owner = new Owner();
		
		Coffer authCoffer = new Coffer();

		AuthenticationTreasure authTreasure = new AuthenticationTreasure();

		/*
		 * Generates the authentication token the only secret information shared
		 * with the server
		 */
		String authenticationToken = getAuthenticationToken();
		
		authTreasure.setAuthenticationToken(authenticationToken);

		/*
		 * Obtains the CofferKey
		 */
		CofferKey key = getCofferKey();
		/*
		 * Put the key into the treasure
		 */
		authTreasure.setBaseKey(key);
		/*
		 * Store the treasure into the coffer
		 */
		authCoffer.setTreasure(authTreasure);
		/*
		 * Lock the coffer with owner symmetric key
		 */
		authCoffer.lock(ownerKey);
		owner.setId(ownerId);
		owner.setAuthenticationCoffer(authCoffer);
		owner.setAuthenticationToken(authenticationToken);
		return owner;
	}

	protected static CofferKey getCofferKey() {
		try {

			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.generateKeyPair();
			// X.509
			byte[] publicKey = kp.getPublic().getEncoded();
			// PKCS#8
			byte[] privateKey = kp.getPrivate().getEncoded();

			RSACofferKey cofferKey = new RSACofferKey(privateKey, publicKey);

			return cofferKey;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected static String getAuthenticationToken() {
		return UUID.randomUUID().toString();
	}

	private Byte[] publicKey;
	private Byte[] privateKey;

	public Byte[] getPrivateKey() {
		return privateKey;
	}

	public Byte[] getPublicKey() {
		return publicKey;
	}

	public void setPrivateKey(Byte[] privateKey) {
		this.privateKey = privateKey;
	}

	public void setPublicKey(Byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
