/*
 * Raziel 
 * SofthMelody a Fiscella Corporation Company 
 */
package com.softm.raziel.crypt;

import java.io.Serializable;

import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class CofferKey.
 *
 * @author Pikard
 */
public abstract class CofferKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4662067005874119024L;

	/**
	 * Encrypt the Coffer treasure.
	 *
	 * @param coffer the coffer
	 */
	public abstract void lockCoffer(Coffer coffer);

	/**
	 * Decrypt coffer treasure.
	 *
	 * @param closedCoffer the closed coffer
	 */
	public abstract void openCoffer(Coffer closedCoffer);
}