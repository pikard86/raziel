package com.softm;

import com.softm.secret.Owner;
import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public class AuthenticationProvider {
	private AuthenticationChannel channel;

	
	public AuthenticationProvider(AuthenticationChannel channel) {
		super();
		this.channel = channel;
	}

	public interface SignOnCallback {

		void onSuccess();

		void onFailure();
	}

	public boolean signOn(Owner owner) {
		//TODO move here the owner factory invocation
		return channel.doSignOn(owner);
	}

	public void signOn(Owner owner, SignOnCallback callBack) {
		callBack.onSuccess();
	}

	public boolean signIn(String ownerId, String password) {
		Coffer<AuthenticationTreasure> authCoffer = channel
				.getAuthenticationCoffer(ownerId);
		AESCofferKey key = new AESCofferKey(password.getBytes());
		authCoffer.open(key);
		String authenticationToken = authCoffer.getTreasure()
				.getAuthenticationToken();
		return channel.doSignIn(ownerId, authenticationToken);
	}

}
