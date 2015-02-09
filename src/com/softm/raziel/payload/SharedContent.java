/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.payload;

import com.softm.raziel.crypt.CofferKey;

// TODO: Auto-generated Javadoc
/**
 * The Class SharedContent.
 */
public class SharedContent extends Treasure {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7497300550265891449L;

	/** The shared coffer id. */
	private long sharedCofferId;

	/** The key. */
	private CofferKey key;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public CofferKey getKey() {
		return key;
	}

	/**
	 * Gets the shared coffer id.
	 *
	 * @return the shared coffer id
	 */
	public long getSharedCofferId() {
		return sharedCofferId;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(final CofferKey key) {
		this.key = key;
	}

	/**
	 * Sets the shared coffer id.
	 *
	 * @param sharedCofferId
	 *            the new shared coffer id
	 */
	public void setSharedCofferId(final long sharedCofferId) {
		this.sharedCofferId = sharedCofferId;
	}
}