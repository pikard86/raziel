package com.softm.secret.payload;

import com.softm.secret.crypt.CofferKey;

public class AuthenticationTreasure extends Treasure {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7710566999094551344L;
	private String authenticationToken;
	private CofferKey baseKey;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String autenticationToken) {
		this.authenticationToken = autenticationToken;
	}

	public CofferKey getBaseKey() {
		return baseKey;
	}

	public void setBaseKey(CofferKey baseKey) {
		this.baseKey = baseKey;
	}

}
