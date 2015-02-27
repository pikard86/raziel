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
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationProvider.
 */
public class AuthenticationClient {

	/**
	 * The Interface SignOnCallback.
	 */
	public interface SignOnCallback {

		/**
		 * On failure.
		 */
		void onFailure();

		/**
		 * On success.
		 */
		void onSuccess();
	}

	/** The channel. */
	private final AuthenticationChannel channel;

	/**
	 * Instantiates a new authentication provider.
	 *
	 * @param channel
	 *            the channel
	 */
	public AuthenticationClient(final AuthenticationChannel channel) {
		super();
		this.channel = channel;
	}

	/**
	 * Sign in.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param password
	 *            the password
	 * @return true, if successful
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 */
	public Owner signIn(final String ownerId, final String password)
			throws UndefinedOwnerException {
		final Coffer<AuthenticationTreasure> authCoffer = channel
				.getAuthenticationCoffer(ownerId);
		if (authCoffer == null) {
			throw new UndefinedOwnerException(ownerId);
		}
		final AESCofferKey key = new AESCofferKey(password);
		authCoffer.open(key);
		final String authenticationToken = authCoffer.getTreasure()
				.getAuthenticationToken();
		return channel.doSignIn(ownerId, authenticationToken);
	}

	/**
	 * Sign on.
	 *
	 * @param owner
	 *            the owner
	 * @return true, if successful
	 */
	public boolean signOn(final Owner owner) {
		// TODO move here the owner factory invocation
		return channel.doSignOn(owner);
	}

	/**
	 * Sign on.
	 *
	 * @param owner
	 *            the owner
	 * @param callBack
	 *            the call back
	 */
	public void signOn(final Owner owner, final SignOnCallback callBack) {
		callBack.onSuccess();
	}

}
