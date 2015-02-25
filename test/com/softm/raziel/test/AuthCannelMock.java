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
package com.softm.raziel.test;

import java.util.HashMap;

import com.softm.raziel.Owner;
import com.softm.raziel.client.AuthenticationChannel;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthCannelMock.
 */
public class AuthCannelMock implements AuthenticationChannel {

	/** The owners. */
	public HashMap<String, Owner> owners = new HashMap<String, Owner>();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.softm.AuthenticationChannel#doSignIn(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean doSignIn(final String ownerId,
			final String authenticationToken) {
		final Owner owner = owners.get(ownerId);
		return owner != null
				&& owner.getAuthenticationToken().equals(authenticationToken);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.softm.AuthenticationChannel#doSignOn(com.softm.secret.Owner)
	 */
	@Override
	public boolean doSignOn(final Owner owner) {
		owners.put(owner.getId(), owner);
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.softm.AuthenticationChannel#getAuthenticationCoffer(java.lang.String)
	 */
	@Override
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(
			final String ownerId) {
		final Owner owner = owners.get(ownerId);
		return owner.getAuthenticationCoffer();
	}

}
