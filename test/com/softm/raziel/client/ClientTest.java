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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

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
 * The Class ClientTest.
 */
public class ClientTest {

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
		final AuthenticationChannel authenticationChannel = Mockito
				.mock(AuthenticationChannel.class);

		final ContentChannel contentChannel = Mockito
				.mock(ContentChannel.class);
		final ClientFactory clientFactory = new ClientFactory(
				authenticationChannel, contentChannel);
		final RazielClient client = clientFactory.getClient();
		final String ownerId = "ownerId";
		final String password = "password";
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

		client.signIn(ownerId, password);

		final Message msg = new Message("Hello Bob", new Date());

		final Long mockContentID = new Long(112);
		Mockito.when(contentChannel.storeCoffer(Mockito.any(Coffer.class)))
				.thenReturn(mockContentID);

		final long contentId = client.storeContent(msg);
		final ArgumentCaptor<ContentTicket> ticketCaptor = forClass(ContentTicket.class);
		verify(contentChannel).issueContentTicket(Mockito.anyString(),
				ticketCaptor.capture());
		when(contentChannel.getTicket(mockContentID, ownerId)).thenReturn(
				ticketCaptor.getValue());

		final ArgumentCaptor<Coffer> cofferCaptor = forClass(Coffer.class);
		verify(contentChannel).storeCoffer(cofferCaptor.capture());
		when(contentChannel.getCoffer(mockContentID)).thenReturn(
				cofferCaptor.getValue());
		final Message retreivaedMsg = (Message) client.getContent(contentId);
		org.junit.Assert.assertEquals(msg, retreivaedMsg);

	}
}
