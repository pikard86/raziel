package com.softm.raziel.test;

import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.auth.AutenticationServer;
import com.softm.raziel.auth.UndefinedOwnerException;
import com.softm.raziel.auth.WrongOwnerCredentialException;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.repo.OwnerRepository;

public class AuthenticationServerTest {

	private static final String OWNER_PASSWORD = "owner:password";
	private static final String OWNER_ID = "#test:owner";

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
		final AutenticationServer authServer = new AutenticationServer(
				ownerRepository);

		authServer.onSignInRequest(ownerId, authenticationToken);
	}

	@Test
	public void testSignOn() throws UndefinedOwnerException,
	WrongOwnerCredentialException {
		final String ownerId = OWNER_ID;

		final OwnerRepository ownerRepository = Mockito
				.mock(OwnerRepository.class);

		final CofferKey ownerKey = new AESCofferKey(OWNER_PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);

		final AutenticationServer authServer = new AutenticationServer(
				ownerRepository);

		authServer.onSignOnRequest(owner);
	}
}
