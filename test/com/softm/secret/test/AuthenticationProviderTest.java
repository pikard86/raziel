/*
 * Raziel
 * SofthMelody a Fishella Corporation Company
 */
package com.softm.secret.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.softm.AuthenticationProvider;
import com.softm.secret.Owner;
import com.softm.secret.OwnerFactory;
import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.crypt.CofferKey;

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
	private AuthenticationProvider authProvider;

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest() {
		final AuthCannelMock channelMock = new AuthCannelMock();

		final CofferKey ownerKey = new AESCofferKey(PASSWORD.getBytes());
		final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
		channelMock.owners.put(owner.getId(), owner);

		authProvider = new AuthenticationProvider(channelMock);
	}

	/**
	 * Sign in test.
	 */
	@Test
	public void signInTest() {
		final boolean result = authProvider.signIn(OWNER_ID, PASSWORD);
		Assert.assertTrue(result);
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
