package com.softm.secret;

import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public class Owner {
	private Coffer<AuthenticationTreasure> authenticationCoffer;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String authenticationToken;

	public Coffer<AuthenticationTreasure> getAuthenticationCoffer() {
		return authenticationCoffer;
	}

	public void setAuthenticationCoffer(
			Coffer<AuthenticationTreasure> authenticationCoffer) {
		this.authenticationCoffer = authenticationCoffer;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

}
