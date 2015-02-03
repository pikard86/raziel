package com.softm.secret.test;

import java.util.HashMap;

import com.softm.AuthenticationChannel;
import com.softm.secret.Owner;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public class AuthCannelMock implements AuthenticationChannel{

	public HashMap<String, Owner> owners=new HashMap<String, Owner>();
	
	@Override
	public Coffer<AuthenticationTreasure> getAuthenticationCoffer(String ownerId) {
		Owner owner = owners.get(ownerId);
		return owner.getAuthenticationCoffer();
	}

	@Override
	public boolean doSignIn(String ownerId, String authenticationToken) {
		Owner owner = owners.get(ownerId);		
		return owner!=null && owner.getAuthenticationToken().equals(authenticationToken);
	}

	@Override
	public boolean doSignOn(Owner owner) {
		owners.put(owner.getId(), owner);
		return true;
	}

}
