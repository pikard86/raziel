/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm;

import com.softm.secret.Owner;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthenticationChannel.
 */
public interface AuthenticationChannel {

	/**
	 * Do sign in.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param authenticationToken
	 *            the authentication token
	 * @return true, if successful
	 */
	public boolean doSignIn(String ownerId, String authenticationToken);

	/**
	 * Do sign on.
	 *
	 * @param owner
	 *            the owner
	 * @return true, if successful
	 */
	public boolean doSignOn(Owner owner);

	/**
	 * Gets the authentication coffer.
	 *
	 * @param ownerId
	 *            the owner id
	 * @return the authentication coffer
	 */
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(String ownerId);
}
