package com.softm.secret.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.softm.AuthenticationChannel;
import com.softm.AuthenticationProvider;
import com.softm.secret.Owner;
import com.softm.secret.OwnerFacrory;
import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.crypt.CofferKey;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;

public class AuthenticationProviderTest {


	private static final String OWNER_ID = "ownerId";
	private static final String PASSWORD = "password";
	private AuthenticationProvider authProvider;

	@Before
	public void setupTest(){
		AuthCannelMock channelMock=new AuthCannelMock();

		CofferKey ownerKey = new AESCofferKey(PASSWORD.getBytes());
		Owner owner = OwnerFacrory.createOwner(OWNER_ID,ownerKey);
		channelMock.owners.put(owner.getId(), owner);
		
		authProvider = new AuthenticationProvider(channelMock);
	}
	@Test
	public void signOnTest() {
		CofferKey ownerKey = new AESCofferKey(PASSWORD.getBytes());
		Owner owner = OwnerFacrory.createOwner(OWNER_ID,ownerKey);
		boolean signOn = authProvider.signOn(owner);
		Assert.assertTrue(signOn);
	}

	@Test
	public void signInTest() {
	 	boolean result = authProvider.signIn(OWNER_ID,PASSWORD);
		Assert.assertTrue(result);		
	}
}
