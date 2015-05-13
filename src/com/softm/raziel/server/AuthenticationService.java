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

import java.util.List;

import com.softm.raziel.Owner;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.repo.OwnerRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class .
 */
public class AuthenticationService {

	/** The owner repository. */
	private final OwnerRepository ownerRepository;

	/**
	 * Instantiates a new autentication server.
	 *
	 * @param ownerRepository
	 *            the owner repository
	 */
	public AuthenticationService(final OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	/**
	 * Gets the authentication coffer.
	 *
	 * @param ownerId
	 *            the owner id
	 * @return the authentication coffer
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 */
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(
			final String ownerId) throws UndefinedOwnerException {
		final Owner owner = ownerRepository.findOwnerById(ownerId);
		if (owner == null) {
			throw new UndefinedOwnerException(ownerId);
		}
		return owner.getAuthenticationCoffer();
	}

	/**
	 * Gets the owner repository.
	 *
	 * @return the owner repository
	 */
	public OwnerRepository getOwnerRepository() {
		return ownerRepository;
	}

	public List<Owner> getOwnersByIds(final List<String> ownerIds) {

		return ownerRepository.findOwnersByIds(ownerIds);
	}

	/**
	 * On sign in request.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param authenticationToken
	 *            the authentication token
	 * @return the owner
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 */
	public Owner onSignInRequest(final String ownerId,
			final String authenticationToken) throws UndefinedOwnerException,
			WrongOwnerCredentialException {
		final Owner owner = ownerRepository.findOwnerById(ownerId);
		if (owner == null) {
			throw new UndefinedOwnerException(ownerId);
		}
		if (!authenticationToken.equals(owner.getAuthenticationToken())) {
			throw new WrongOwnerCredentialException(ownerId);
		}
		return owner;
	}

	/**
	 * On sign on request.
	 *
	 * @param owner
	 *            the owner
	 * @return true, if successful
	 */
	public boolean onSignOnRequest(final Owner owner) {
		ownerRepository.store(owner);
		// TODO: check store success
		return true;
	}

}
