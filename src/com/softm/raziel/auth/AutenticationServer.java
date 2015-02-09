package com.softm.raziel.auth;

import com.softm.raziel.Owner;
import com.softm.raziel.repo.OwnerRepository;

public class AutenticationServer {

	private final OwnerRepository ownerRepository;

	public AutenticationServer(final OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

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

	public void onSignOnRequest(final Owner owner) {
		// TODO: add checks on owner fields

		ownerRepository.store(owner);
	}

}
