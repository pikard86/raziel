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
