package com.softm.secret.payload;

import com.softm.secret.crypt.CofferKey;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationTreasure.
 */
public class AuthenticationTreasure extends Treasure {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7710566999094551344L;

	/** The authentication token. */
	private String authenticationToken;

	/** The base key. */
	private CofferKey baseKey;

	/**
	 * Gets the authentication token.
	 *
	 * @return the authentication token
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * Gets the base key.
	 *
	 * @return the base key
	 */
	public CofferKey getBaseKey() {
		return baseKey;
	}

	/**
	 * Sets the authentication token.
	 *
	 * @param autenticationToken
	 *            the new authentication token
	 */
	public void setAuthenticationToken(final String autenticationToken) {
		authenticationToken = autenticationToken;
	}

	/**
	 * Sets the base key.
	 *
	 * @param baseKey
	 *            the new base key
	 */
	public void setBaseKey(final CofferKey baseKey) {
		this.baseKey = baseKey;
	}

}
