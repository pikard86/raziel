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

import com.softm.raziel.Owner;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.repo.OwnerRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class .
 */
public class AutenticationService {

	/** The owner repository. */
	private final OwnerRepository ownerRepository;

	/**
	 * Instantiates a new autentication server.
	 *
	 * @param ownerRepository
	 *            the owner repository
	 */
	public AutenticationService(final OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	/**
	 * Gets the owner repository.
	 *
	 * @return the owner repository
	 */
	public OwnerRepository getOwnerRepository() {
		return ownerRepository;
	}

	/**
	 * On sign in request.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param authenticationToken
	 *            the authentication token
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 */
	public void onSignInRequest(final String ownerId,
			final String authenticationToken) throws UndefinedOwnerException,
			WrongOwnerCredentialException {
		final Owner owner = ownerRepository.findOwnerById(ownerId);
		if (owner == null) {
			throw new UndefinedOwnerException(ownerId);
		}
		if (!authenticationToken.equals(owner.getAuthenticationToken())) {
			throw new WrongOwnerCredentialException(ownerId);
		}
	}

	/**
	 * On sign on request.
	 *
	 * @param owner
	 *            the owner
	 */
	public void onSignOnRequest(final Owner owner) {
		ownerRepository.store(owner);
	}

}
