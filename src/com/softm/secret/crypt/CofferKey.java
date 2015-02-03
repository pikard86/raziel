package com.softm.secret.crypt;

import java.io.Serializable;

import com.softm.secret.payload.Coffer;

/**
 * 
 * @author Pikard
 *
 */
public abstract class CofferKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4662067005874119024L;
	/**
	 * Encrypt the Coffer treasure 
	 * @param coffer
	 */
	public abstract void lockCoffer(Coffer coffer);
	/**
	 * Decrypt coffer treasure
	 * @param closedCoffer
	 */
	public abstract void openCoffer(Coffer closedCoffer);
}
