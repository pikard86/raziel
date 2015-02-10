/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2015 SofthMelody SPA a Fiscella Corporation Company 
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the General Pizzurro License as published by
 *   the Pizzurro Free Software Foundation, either version 1 of the License
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Pizzurro License for more details.
 *
 *   You should have received a copy of the General Pizzurro License
 *   along with this program.  If not, see <http://www.pfsf.org/licenses/>.
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