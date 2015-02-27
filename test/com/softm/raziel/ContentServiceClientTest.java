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

import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.client.AuthenticatedSession;
import com.softm.raziel.client.ContentChannel;
import com.softm.raziel.client.ContentCilent;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.payload.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class ContentServiceClientTest.
 */
public class ContentServiceClientTest {

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "password";

	/** The Constant OWNER_ID. */
	private static final String OWNER_ID = "BOB";

	/**
	 * Store content test.
	 */
	@Test
	public void storeContentTest() {
		final ContentChannel contentChannel = Mockito
				.mock(ContentChannel.class);
		final byte[] key = PASSWORD.getBytes();
		final CofferKey ownerKey = new AESCofferKey(key);
		final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
		final AuthenticatedSession session = new AuthenticatedSession(owner,
				PASSWORD);

		final ContentCilent contentClient = new ContentCilent(contentChannel);
		final Message message = new Message("Hello bob", new Date());

		contentClient.storeContent(message, session);
	}
}
