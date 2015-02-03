package com.softm.secret.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.crypt.CofferKey;
import com.softm.secret.crypt.Encripter;
import com.softm.secret.payload.AuthenticationTreasure;
import com.softm.secret.payload.Coffer;
import com.softm.secret.payload.Treasure;

public class CofferTest {

	private Byte[] publicKey;
	private Byte[] privateKey;

	@Test
	public void test() {
		
		AuthenticationTreasure treasure= new AuthenticationTreasure();
		treasure.setAuthenticationToken("token");
		Byte[] privateKey={};
		Byte[] publicKey={};
		
		Coffer userCoffer= new Coffer();
		userCoffer.setTreasure(treasure);
		CofferKey key = new AESCofferKey();
		
		treasure.setBaseKey(key);
		
		userCoffer.lock(key);
		
		
		
	}

	public Byte[] getPrivateKey() {
		return privateKey;
	}

	public Byte[] getPublicKey() {
		return publicKey;
	}

	public void setPrivateKey(Byte[] privateKey) {
		this.privateKey = privateKey;
	}

	public void setPublicKey(Byte[] publicKey) {
		this.publicKey = publicKey;
	}

}
