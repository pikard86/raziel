/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2015 SofthMelody SPA a Fiscella Corporation Company
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the General Pizzurro License as published by
 *   the Pizzurro Free Software Foundation, either version 1 of the License
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   General Pizzurro License for more details.
 *
 *   You should have received a copy of the General Pizzurro License
 *   along with this program.  If not, see <http://www.pfsf.org/licenses/>.
 */
package com.softm.raziel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;

// TODO: Auto-generated Javadoc
/**
 * The Class OwnerFactoryTest.
 */
public class OwnerFactoryTest {

	/** The Constant OWNER_ID. */
	private static final String OWNER_ID = "ownerId";

	/**
	 * Test owner creation.
	 */
	@Test
	public void testOwnerCreation() {
		final byte[] key = "password".getBytes();
		final CofferKey ownerKey = new AESCofferKey(key);
		final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
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
		 * The owner can be sent to the server to store encrypted coffer, public
		 * key and shared auth token
		 *
		 * on login the server will send the authentication coffer the client
		 * extracts the token from the coffer and sent it back to the server
		 * using ssl
		 *
		 * now the client is logged
		 *
		 * in order to share information using the server the client encode
		 * information using the receiver public key
		 *
		 * to share with more than one receiver the information can be packed
		 * into a coffer using symmetric key and the key can be published to
		 * receivers using their public key
		 *
		 * in this way the server can send contents to receivers if authorized
		 * and a receiver can read the content only if they have the coffer with
		 * the shared storage key
		 *
		 */
	}

}