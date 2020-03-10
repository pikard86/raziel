/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2020  Riccardo Pittiglio
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.softm.raziel.server;

import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Message;
import com.softm.raziel.repo.CofferRepository;
import com.softm.raziel.repo.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

// TODO: Auto-generated Javadoc

/**
 * The Class ContentServiceTest.
 */
public class ContentServiceTest {

    private static final String OWNER_ID = "BOB_ID";

    /**
     * The Constant MSG_TXT.
     */
    private static final String MSG_TXT = "Hello Bob";

    /**
     * The Constant ALICE PASSWORD.
     */
    private static final String ALICE_PASSWORD = "ALICE_PASSWORD";

    private ContentService service;

    private CofferRepository cofferRepositoryMock;

    private TicketRepository ticketRepositoryMock;

    @Test
    public void issueTicketTest() {

        final ContentTicket ticket = new ContentTicket();
        final long sharedCofferId = 123;
        ticket.setSharedCofferId(sharedCofferId);
        ticket.setTicket(new byte[]{'a', 'b', 'c'});

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
                .thenReturn(Long.valueOf(5555));

        final Coffer<Message> coffer = new Coffer<Message>();
        final Date timestamp = new Date();
        coffer.setTreasure(new Message(MSG_TXT, timestamp));
        coffer.lock(new AESCofferKey(ALICE_PASSWORD));
        /*
         * Store coffer on the service
         */
        final long cofferId = service.storeCoffer(coffer);

        Mockito.when(cofferRepositoryMock.findById(Long.valueOf(cofferId)))
                .thenReturn(coffer);

        /*
         * Retrieves coffer by id
         */
        final Coffer<Message> retCoffer = service.getCoffer(cofferId);

        retCoffer.open(new AESCofferKey(ALICE_PASSWORD));
        final Message treasure = retCoffer.getTreasure();
        Assert.assertEquals(MSG_TXT, treasure.getMessageText());
        Assert.assertEquals(timestamp, treasure.getTimestamp());
    }

}
