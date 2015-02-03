package com.softm;

import com.softm.secret.Owner;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public interface  AuthenticationChannel {

	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(String ownerId);

	public boolean doSignIn(String ownerId, String authenticationToken);

	public boolean doSignOn(Owner owner);
}
