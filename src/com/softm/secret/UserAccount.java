package com.softm.secret;

import com.softm.secret.payload.Coffer;

public class UserAccount {
	private String username;
	private String authToken;
	private Coffer userSecretCoffer;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public Coffer getUserSecretCoffer() {
		return userSecretCoffer;
	}
	public void setUserSecretCoffer(Coffer userSecretCoffer) {
		this.userSecretCoffer = userSecretCoffer;
	}
	
}
