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
package com.softm.raziel.client;

import com.softm.raziel.Owner;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthenticationChannel.
 */
public interface AuthenticationChannel {

	/**
	 * Do sign in.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param authenticationToken
	 *            the authentication token
	 * @return true, if successful
	 */
	public boolean doSignIn(String ownerId, String authenticationToken);

	/**
	 * Do sign on.
	 *
	 * @param owner
	 *            the owner
	 * @return true, if successful
	 */
	public boolean doSignOn(Owner owner);

	/**
	 * Gets the authentication coffer.
	 *
	 * @param ownerId
	 *            the owner id
	 * @return the authentication coffer
	 */
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(String ownerId);
}
