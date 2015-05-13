package com.softm.raziel.example;

import java.util.List;

import com.softm.raziel.Owner;
import com.softm.raziel.client.AuthenticationChannel;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.server.AuthenticationService;

public class LocalAuthenticationChannel implements AuthenticationChannel {

	AuthenticationService service = new AuthenticationService(
			new InMemoryOwnerRepository());

	@Override
	public Owner doSignIn(final String ownerId, final String authenticationToken)
			throws UndefinedOwnerException, WrongOwnerCredentialException {
		return service.onSignInRequest(ownerId, authenticationToken);
	}

	@Override
	public boolean doSignOn(final Owner owner) {
		return service.onSignOnRequest(owner);
	}

	@Override
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(
			final String ownerId) throws UndefinedOwnerException {
		return service.getAuthenticationCoffer(ownerId);
	}

	@Override
	public List<Owner> getOwnersByIds(final List<String> recipientsIds) {
		return service.getOwnersByIds(recipientsIds);
	}

}