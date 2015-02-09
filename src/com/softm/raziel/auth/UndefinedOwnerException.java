/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.auth;

// TODO: Auto-generated Javadoc
/**
 * The Class UndefinedOwnerException.
 */
public class UndefinedOwnerException extends Exception {

	/** The Constant UNABLE_TO_RETREIVE_OWNER_ID. */
	private static final String UNABLE_TO_RETREIVE_OWNER_ID = "Unable to retreive owner id : ";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4113268056849915971L;

	/**
	 * Instantiates a new undefined owner exception.
	 *
	 * @param ownerId
	 *            the owner id
	 */
	public UndefinedOwnerException(final String ownerId) {
		super(UNABLE_TO_RETREIVE_OWNER_ID + ownerId);
	}

}
