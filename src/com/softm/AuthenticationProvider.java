package com.softm;

import com.softm.secret.Owner;
import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationProvider.
 */
public class AuthenticationProvider {
	
	/**
	 * The Interface SignOnCallback.
	 */
	public interface SignOnCallback {

		/**
		 * On failure.
		 */
		void onFailure();

		/**
		 * On success.
		 */
		void onSuccess();
	}

	/** The channel. */
	private AuthenticationChannel channel;

	/**
	 * Instantiates a new authentication provider.
	 *
	 * @param channel the channel
	 */
	public AuthenticationProvider(AuthenticationChannel channel) {
		super();
		this.channel = channel;
	}

	/**
	 * Sign in.
	 *
	 * @param ownerId the owner id
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean signIn(String ownerId, String password) {
		Coffer<AuthenticationTreasure> authCoffer = channel
				.getAuthenticationCoffer(ownerId);
		AESCofferKey key = new AESCofferKey(password.getBytes());
		authCoffer.open(key);
		String authenticationToken = authCoffer.getTreasure()
				.getAuthenticationToken();
		return channel.doSignIn(ownerId, authenticationToken);
	}

	/**
	 * Sign on.
	 *
	 * @param owner the owner
	 * @return true, if successful
	 */
	public boolean signOn(Owner owner) {
		// TODO move here the owner factory invocation
		return channel.doSignOn(owner);
	}

	/**
	 * Sign on.
	 *
	 * @param owner the owner
	 * @param callBack the call back
	 */
	public void signOn(Owner owner, SignOnCallback callBack) {
		callBack.onSuccess();
	}

}
