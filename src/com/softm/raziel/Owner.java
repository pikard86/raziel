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
package com.softm.raziel;

import java.util.List;

import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class Owner.
 */
public class Owner {

	/** The authentication coffer. */
	private Coffer<AuthenticationTreasure> authenticationCoffer;

	/** The authentication token. */
	private String authenticationToken;

	/** The id. */
	private String id;

	/** The public key. */
	private byte[] publicKey;

	/** The owned coffers. */
	private List<Long> ownedCoffers;

	/**
	 * Gets the authentication coffer.
	 *
	 * @return the authentication coffer
	 */
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer() {
		return authenticationCoffer;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the owned coffers.
	 *
	 * @return the owned coffers
	 */
	public List<Long> getOwnedCoffers() {
		return ownedCoffers;
	}

	/**
	 * Gets the public key.
	 *
	 * @return the public key
	 */
	public byte[] getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the authentication coffer.
	 *
	 * @param authenticationCoffer
	 *            the new authentication coffer
	 */
	public void setAuthenticationCoffer(
			final Coffer<AuthenticationTreasure> authenticationCoffer) {
		this.authenticationCoffer = authenticationCoffer;
	}

	/**
	 * Sets the authentication token.
	 *
	 * @param authenticationToken
	 *            the new authentication token
	 */
	public void setAuthenticationToken(final String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Sets the owned coffers.
	 *
	 * @param ownedCoffers
	 *            the new owned coffers
	 */
	public void setOwnedCoffers(final List<Long> ownedCoffers) {
		this.ownedCoffers = ownedCoffers;
	}

	/**
	 * Sets the public key.
	 *
	 * @param publicKey
	 *            the new public key
	 */
	public void setPublicKey(final byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
