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

import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.repo.OwnerRepository;
import com.softm.raziel.server.AutenticationService;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationServerTest.
 */
public class AuthenticationServiceTest {

	/** The Constant OWNER_PASSWORD. */
	private static final String OWNER_PASSWORD = "owner:password";

	/** The Constant OWNER_ID. */
	private static final String OWNER_ID = "#test:owner";

	/**
	 * Test sign in.
	 *
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 */
	@Test
	public void testSignIn() throws UndefinedOwnerException,
			WrongOwnerCredentialException {
		final String ownerId = OWNER_ID;

		final OwnerRepository ownerRepository = Mockito
				.mock(OwnerRepository.class);

		final CofferKey ownerKey = new AESCofferKey(OWNER_PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);

		Mockito.when(ownerRepository.findOwnerById(Mockito.matches(OWNER_ID)))
				.thenReturn(owner);

		final String authenticationToken = owner.getAuthenticationToken();
		final AutenticationService authServer = new AutenticationService(
				ownerRepository);

		authServer.onSignInRequest(ownerId, authenticationToken);
	}

	/**
	 * Test sign on.
	 *
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 */
	@Test
	public void testSignOn() throws UndefinedOwnerException,
			WrongOwnerCredentialException {
		final String ownerId = OWNER_ID;

		final OwnerRepository ownerRepository = Mockito
				.mock(OwnerRepository.class);

		final CofferKey ownerKey = new AESCofferKey(OWNER_PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);

		final AutenticationService authServer = new AutenticationService(
				ownerRepository);

		authServer.onSignOnRequest(owner);
	}
}
