/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.test;

import java.util.HashMap;

import com.softm.raziel.Owner;
import com.softm.raziel.auth.AuthenticationChannel;
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
