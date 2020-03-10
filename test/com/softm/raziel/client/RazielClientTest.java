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
package com.softm.raziel.client;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.exceptions.AuthenticationRequiredException;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationClientTest.
 */
public class RazielClientTest {

	private static final String BOB_PASSWORD = "bobpassword";
	private static final String BOB_ID = "bob id";
	private static final String ALICE_PASSWORD = "password";
	private static final String ALICE_ID = "ownerId";
	private AuthenticationChannel authenticationChannel;
	private ContentChannel contentChannel;
	private RazielClient razielClient;

	/**
	 * Client sign in test.
	 *
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 * @throws ContentException
	 *             the content exception
	 */
	@Test
	public void clientSignInTest() throws WrongOwnerCredentialException,
			AuthenticationRequiredException, UndefinedOwnerException,
			ContentException {

		final String ownerId = ALICE_ID;
		final String password = ALICE_PASSWORD;
		fillAuthenticationMock(ownerId, password);

		razielClient.signIn(ownerId, password);

	}

	public void fillAuthenticationMock(final String ownerId,
			final String password) throws UndefinedOwnerException,
			WrongOwnerCredentialException {
		final AESCofferKey ownerKey = new AESCofferKey(password);
		final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);
		final Coffer<AuthenticationTreasure> coffer = owner
				.getAuthenticationCoffer();
		coffer.open(ownerKey);
		final String authenticationToken = coffer.getTreasure()
				.getAuthenticationToken();
		coffer.lock(ownerKey);

		Mockito.when(
				authenticationChannel.doSignIn(ownerId, authenticationToken))
				.thenReturn(owner);
		Mockito.when(authenticationChannel.getAuthenticationCoffer(ownerId))
				.thenReturn(owner.getAuthenticationCoffer());
	}

	public void fillMockWithRecipients(final String ownerId,
			final String password) throws UndefinedOwnerException {
		final AESCofferKey boobKey = new AESCofferKey(ownerId);
		final Owner owner = OwnerFactory.createOwner(ownerId, boobKey);
		Mockito.when(authenticationChannel.getAuthenticationCoffer(ownerId))
				.thenReturn(owner.getAuthenticationCoffer());
		Mockito.when(
				authenticationChannel.getOwnersByIds(Collections.singletonList(ownerId)))
				.thenReturn(Collections.singletonList(owner));
	}

	@Before
	public void setUp() {
		authenticationChannel = mock(AuthenticationChannel.class);
		contentChannel = Mockito.mock(ContentChannel.class);
		final ClientFactory clientFactory = new ClientFactory(
				authenticationChannel, contentChannel);
		razielClient = clientFactory.getClient();
	}

	@Test
	public void storeContentTest() throws AuthenticationRequiredException,
	ContentException, WrongOwnerCredentialException,
			UndefinedOwnerException {

		fillAuthenticationMock(ALICE_ID, ALICE_PASSWORD);
		razielClient.signIn(ALICE_ID, ALICE_PASSWORD);

		final Message msg = new Message("Hello Bob", new Date());

		final Long mockContentID = Long.valueOf(112);
		Mockito.when(contentChannel.storeCoffer(Mockito.any(Coffer.class)))
				.thenReturn(mockContentID);

		final long contentId = razielClient.storeContent(msg);
		final ArgumentCaptor<ContentTicket> ticketCaptor = forClass(ContentTicket.class);
		verify(contentChannel).issueContentTicket(Mockito.anyString(),
				ticketCaptor.capture());
		when(contentChannel.getTicket(mockContentID, ALICE_ID)).thenReturn(
				ticketCaptor.getValue());

		final ArgumentCaptor<Coffer> cofferCaptor = forClass(Coffer.class);
		verify(contentChannel).storeCoffer(cofferCaptor.capture());
		when(contentChannel.getCoffer(mockContentID)).thenReturn(
				cofferCaptor.getValue());
		final Message retrievedMsg = razielClient
				.getContent(contentId);
		org.junit.Assert.assertEquals(msg, retrievedMsg);
	}
}
