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
package com.softm.raziel.server;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Message;
import com.softm.raziel.repo.CofferRepository;
import com.softm.raziel.repo.TicketRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ContentServiceTest.
 */
public class ContentServiceTest {

	private static final String OWNER_ID = "BOB_ID";

	/** The Constant MSG_TXT. */
	private static final String MSG_TXT = "Hello Bob";

	/** The Constant ALICE_PSWD. */
	private static final String ALICE_PSWD = "ALICE_PSWD";

	private ContentService service;

	private CofferRepository cofferRepositoryMock;

	private TicketRepository ticketRepositoryMock;

	@Test
	public void issueTicketTest() {

		final ContentTicket ticket = new ContentTicket();
		final long sharedCofferId = 123;
		ticket.setSharedCofferId(sharedCofferId);
		ticket.setTicket(new byte[] { 'a', 'b', 'c' });

		final long ticketId = service.issueContentTicket(OWNER_ID, ticket);

		Mockito.when(ticketRepositoryMock.findTicket(OWNER_ID, sharedCofferId))
		.thenReturn(ticket);
		final ContentTicket retrievedTicket = service.getTicket(OWNER_ID,
				sharedCofferId);

		Assert.assertEquals(ticket, retrievedTicket);
	}

	@Before
	public void setUp() {
		cofferRepositoryMock = Mockito.mock(CofferRepository.class);
		ticketRepositoryMock = Mockito.mock(TicketRepository.class);
		service = new ContentService(cofferRepositoryMock, ticketRepositoryMock);
	}

	/**
	 * Store and retrieve coffer test.
	 */
	@Test
	public void storeAndRetrieveCofferTest() {
		setUp();

		Mockito.when(cofferRepositoryMock.store(Mockito.any(Coffer.class)))
		.thenReturn(new Long(5555));

		final Coffer<Message> coffer = new Coffer<Message>();
		final Date timestamp = new Date();
		coffer.setTreasure(new Message(MSG_TXT, timestamp));
		coffer.lock(new AESCofferKey(ALICE_PSWD));
		/*
		 * Store coffer on the service
		 */
		final long cofferId = service.storeCoffer(coffer);

		Mockito.when(cofferRepositoryMock.findById(new Long(cofferId)))
		.thenReturn(coffer);

		/*
		 * Retrieves coffer by id
		 */
		final Coffer<Message> retCoffer = service.getCoffer(cofferId);

		retCoffer.open(new AESCofferKey(ALICE_PSWD));
		final Message treasure = retCoffer.getTreasure();
		Assert.assertEquals(MSG_TXT, treasure.getMessageText());
		Assert.assertEquals(timestamp, treasure.getTimestamp());
	}

}
