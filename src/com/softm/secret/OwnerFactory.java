package com.softm.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.softm.secret.crypt.CofferKey;
import com.softm.secret.crypt.RSACofferKey;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

// TODO: Auto-generated Javadoc

/**
 * A factory for creating Owner objects.
 */
public class OwnerFactory {

	/**
	 * Creates the owner.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param ownerKey
	 *            the owner key
	 * @return the owner
	 */
	public static Owner createOwner(final String ownerId,
			final CofferKey ownerKey) {
		final Owner owner = new Owner();

		final Coffer authCoffer = new Coffer();

		final AuthenticationTreasure authTreasure = new AuthenticationTreasure();

		/*
		 * Generates the authentication token the only secret information shared
		 * with the server
		 */
		final String authenticationToken = getAuthenticationToken();

		authTreasure.setAuthenticationToken(authenticationToken);

		/*
		 * Obtains the CofferKey
		 */
		final CofferKey key = getCofferKey();
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

	/**
	 * Gets the authentication token.
	 *
	 * @return the authentication token
	 */
	protected static String getAuthenticationToken() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Gets the coffer key.
	 *
	 * @return the coffer key
	 */
	protected static CofferKey getCofferKey() {
		try {

			final KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			final KeyPair kp = kpg.generateKeyPair();
			// X.509
			final byte[] publicKey = kp.getPublic().getEncoded();
			// PKCS#8
			final byte[] privateKey = kp.getPrivate().getEncoded();

			final RSACofferKey cofferKey = new RSACofferKey(privateKey,
					publicKey);

			return cofferKey;

		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/** The private key. */
	private Byte[] privateKey;

	/** The public key. */
	private Byte[] publicKey;

	/**
	 * Gets the private key.
	 *
	 * @return the private key
	 */
	public Byte[] getPrivateKey() {
		return privateKey;
	}

	/**
	 * Gets the public key.
	 *
	 * @return the public key
	 */
	public Byte[] getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the private key.
	 *
	 * @param privateKey
	 *            the new private key
	 */
	public void setPrivateKey(final Byte[] privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Sets the public key.
	 *
	 * @param publicKey
	 *            the new public key
	 */
	public void setPublicKey(final Byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
