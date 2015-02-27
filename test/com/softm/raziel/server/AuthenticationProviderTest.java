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
package com.softm.raziel.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.client.AuthCannelMock;
import com.softm.raziel.client.AuthenticationClient;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.exceptions.UndefinedOwnerException;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationProviderTest.
 */
public class AuthenticationProviderTest {

	/** The Constant OWNER_ID. */
	private static final String OWNER_ID = "ownerId";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "password";

	/** The auth provider. */
	private AuthenticationClient authProvider;

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest() {
		final AuthCannelMock channelMock = new AuthCannelMock();

		final CofferKey ownerKey = new AESCofferKey(PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
		channelMock.owners.put(owner.getId(), owner);

		authProvider = new AuthenticationClient(channelMock);
	}

	/**
	 * Sign in test.
	 *
	 * @throws UndefinedOwnerException the undefined owner exception
	 */
	@Test
	public void signInTest() throws UndefinedOwnerException {
		final Owner result = authProvider.signIn(OWNER_ID, PASSWORD);
		Assert.assertNotNull(result);
	}

	/**
	 * Sign on test.
	 */
	@Test
	public void signOnTest() {
		final CofferKey ownerKey = new AESCofferKey(PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
		final boolean signOn = authProvider.signOn(owner);
		Assert.assertTrue(signOn);
	}
}