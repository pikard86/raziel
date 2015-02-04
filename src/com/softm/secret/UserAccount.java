/*
 * Raziel 
 * SofthMelody a Fiscella Corporation Company 
 */
package com.softm.secret;

import com.softm.secret.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAccount.
 */
public class UserAccount {
	
	/** The auth token. */
	private String authToken;
	
	/** The username. */
	private String username;
	
	/** The user secret coffer. */
	private Coffer userSecretCoffer;

	/**
	 * Gets the auth token.
	 *
	 * @return the auth token
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the user secret coffer.
	 *
	 * @return the user secret coffer
	 */
	public Coffer getUserSecretCoffer() {
		return userSecretCoffer;
	}

	/**
	 * Sets the auth token.
	 *
	 * @param authToken the new auth token
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the user secret coffer.
	 *
	 * @param userSecretCoffer the new user secret coffer
	 */
	public void setUserSecretCoffer(Coffer userSecretCoffer) {
		this.userSecretCoffer = userSecretCoffer;
	}

}
