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
 *   General Pizzurro License for more details.
 *
 *   You should have received a copy of the General Pizzurro License
 *   along with this program.  If not, see <http://www.pfsf.org/licenses/>.
 */
package com.softm.raziel.payload;

import java.io.Serializable;

import com.softm.raziel.crypt.AsymmetricKey;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationTreasure.
 */
public class AuthenticationTreasure implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7710566999094551344L;

	/** The authentication token. */
	private String authenticationToken;

	/** The base key. */
	private AsymmetricKey asymmetricKey;

	/**
	 * Gets the asymmetric key.
	 *
	 * @return the asymmetric key
	 */
	public AsymmetricKey getAsymmetricKey() {
		return asymmetricKey;
	}

	/**
	 * Gets the authentication token.
	 *
	 * @return the authentication token
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * Sets the asymmetric key.
	 *
	 * @param asymmetricKey
	 *            the new asymmetric key
	 */
	public void setAsymmetricKey(final AsymmetricKey asymmetricKey) {
		this.asymmetricKey = asymmetricKey;
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

}
