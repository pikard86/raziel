package com.softm.secret.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.softm.secret.Owner;
import com.softm.secret.OwnerFacrory;
import com.softm.secret.crypt.AESCofferKey;
import com.softm.secret.crypt.CofferKey;
import com.softm.secret.payload.Coffer;

public class OwnerFactoryTest {

	private static final String OWNER_ID = "ownerId";

	@Test
	public void testOwnerCreation() {		
		byte[] key="password".getBytes();
		CofferKey ownerKey= new  AESCofferKey(key);
		Owner owner= OwnerFacrory.createOwner(OWNER_ID,ownerKey);
		assertNotNull(owner);		
		assertNotNull(owner.getAuthenticationToken());
		assertNotNull(owner.getId());		
		assertEquals(OWNER_ID, owner.getId());
		assertNotNull(owner.getAuthenticationCoffer().getEncryptedBytes());
		owner.getAuthenticationCoffer().open(ownerKey);
		assertNotNull(owner.getAuthenticationCoffer().getTreasure());
		
		
		
		/**
		 * 
		 * 
		 * The owner can be sent to the server to store encrypted coffer, public key and
		 * shared auth token
		 * 
		 * on login the server will sent the authentication coffer 
		 * the client extracts the token from the coffer and sent it back to 
		 * the server using ssl 
		 * 
		 * now the client is logged
		 * 
		 * in order to share information using the server the client encode information 
		 * using the receiver public key 
		 * 
		 * to share with more than one receiver the information can be packed into a coffer
		 * using symmetric key and the key can be published to receivers using their public key
		 * 
		 * in this way the server can send contents to receivers if authorized
		 * and a receiver can read the content
		 * only if they have the coffer with the shared storage key
		 * 
		 */
	}

}
