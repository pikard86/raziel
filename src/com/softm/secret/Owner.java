package com.softm.secret;

import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class Owner.
 */
public class Owner {

	/** The authentication coffer. */
	private Coffer<AuthenticationTreasure> authenticationCoffer;

	/** The authentication token. */
	private String authenticationToken;

	/** The id. */
	private String id;

	/** The public key. */
	private byte[] publicKey;

	/**
	 * Gets the authentication coffer.
	 *
	 * @return the authentication coffer
	 */
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer() {
		return authenticationCoffer;
	}

	/**
	 * Gets the authentication token.
	 *
	 * @return the authentication token
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the public key.
	 *
	 * @return the public key
	 */
	public byte[] getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the authentication coffer.
	 *
	 * @param authenticationCoffer
	 *            the new authentication coffer
	 */
	public void setAuthenticationCoffer(
			final Coffer<AuthenticationTreasure> authenticationCoffer) {
		this.authenticationCoffer = authenticationCoffer;
	}

	/**
	 * Sets the authentication token.
	 *
	 * @param authenticationToken
	 *            the new authentication token
	 */
	public void setAuthenticationToken(final String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Sets the public key.
	 *
	 * @param publicKey
	 *            the new public key
	 */
	public void setPublicKey(final byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
