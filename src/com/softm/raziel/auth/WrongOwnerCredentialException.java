/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.auth;

public class WrongOwnerCredentialException extends Exception {

	private static final String WRONG_AUTHENTICATION_TOKEN_FOR_OWNRE_ID = "Wrong authentication token for ownre id : ";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8361751331403510256L;

	public WrongOwnerCredentialException(final String ownerId) {
		super(WRONG_AUTHENTICATION_TOKEN_FOR_OWNRE_ID + ownerId);
	}

}
