/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.payload;

import com.softm.raziel.crypt.CofferKey;

// TODO: Auto-generated Javadoc
/**
 * The Class Coffer.
 *
 * @param <T>
 *            the generic type
 */
public class Coffer<T extends Treasure> {

	/** The id. */
	private long id;

	/** The encrypted bytes. */
	private byte[] encryptedBytes;

	/** The treasure. */
	private T treasure;

	/**
	 * Gets the encrypted bytes.
	 *
	 * @return the encrypted bytes
	 */
	public byte[] getEncryptedBytes() {
		return encryptedBytes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the treasure.
	 *
	 * @return the treasure
	 */
	public T getTreasure() {
		return treasure;
	}

	/**
	 * Lock.
	 *
	 * @param key
	 *            the key
	 */
	public void lock(final CofferKey key) {
		key.lockCoffer(this);
	}

	/**
	 * Open.
	 *
	 * @param key
	 *            the key
	 */
	public void open(final CofferKey key) {
		key.openCoffer(this);
	}

	/**
	 * Sets the encrypted bytes.
	 *
	 * @param encryptedBytes
	 *            the new encrypted bytes
	 */
	public void setEncryptedBytes(final byte[] encryptedBytes) {
		this.encryptedBytes = encryptedBytes;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final long id) {
		this.id = id;
	};

	/**
	 * Sets the treasure.
	 *
	 * @param treasure
	 *            the new treasure
	 */
	public void setTreasure(final T treasure) {
		this.treasure = treasure;
	}

}
