/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2020 Riccardo Pittiglio
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
package com.softm.raziel.client;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO: Auto-generated Javadoc

/**
 * The Class ContentServiceClientTest.
 */
public class ContentServiceClientTest {

    private static final String BOB_ID = "BOB";

    /**
     * The Constant PASSWORD.
     */
    private static final String PASSWORD = "password";

    /**
     * The Constant OWNER_ID.
     */
    private static final String ALICE_ID = "ALICE";

    private static final String BOB_PASSWORD = "bob_password";

    @Test
    public void shareContent() throws ContentException {
        final ContentChannel channel = Mockito.mock(ContentChannel.class);

        final Owner bob = OwnerFactory.createOwner(BOB_ID, new AESCofferKey(
                BOB_PASSWORD));

        final byte[] key = PASSWORD.getBytes();
        final CofferKey ownerKey = new AESCofferKey(key);
        final Owner alice = OwnerFactory.createOwner(ALICE_ID, ownerKey);
        final AuthenticatedSession aliceSession = new AuthenticatedSession(
                alice, PASSWORD);

        final ContentClient contentClient = new ContentClient(channel);
        final Message messageToBob = new Message("Hello bob", new Date());

        final List<String> recipientsIds = new ArrayList<String>();

        recipientsIds.add(BOB_ID);
        when(channel.storeCoffer(Mockito.any(Coffer.class))).thenReturn(
                (long) (Math.random() * 1000));

        final Map<String, Long> shareContent = contentClient.shareContent(
                messageToBob, aliceSession, Collections.singletonList(bob));

        final long bobContentId = shareContent.get(BOB_ID);

        final AuthenticatedSession bobSession = new AuthenticatedSession(bob,
                BOB_PASSWORD);

        final ArgumentCaptor<ContentTicket> ticketCaptor = forClass(ContentTicket.class);
        verify(channel).issueContentTicket(Mockito.eq(BOB_ID),
                ticketCaptor.capture());
        when(channel.getTicket(Mockito.eq(bobContentId), Mockito.eq(BOB_ID)))
                .thenReturn(ticketCaptor.getValue());

        final ArgumentCaptor<Coffer> cofferCaptor = forClass(Coffer.class);

        verify(channel, Mockito.atLeast(2)).storeCoffer(cofferCaptor.capture());
        when(channel.getCoffer(Mockito.eq(bobContentId))).thenReturn(
                cofferCaptor.getValue());

        final Message messageFromAlice = contentClient.getContent(bobContentId,
                bobSession);
        Assert.assertEquals(messageToBob, messageFromAlice);
    }

    /**
     * Store content test.
     */
    @Test
    public void storeContentTest() {
        final ContentChannel contentChannel = Mockito
                .mock(ContentChannel.class);
        final byte[] key = PASSWORD.getBytes();
        final CofferKey ownerKey = new AESCofferKey(key);
        final Owner owner = OwnerFactory.createOwner(ALICE_ID, ownerKey);
        final AuthenticatedSession session = new AuthenticatedSession(owner,
                PASSWORD);

        final ContentClient contentClient = new ContentClient(contentChannel);
        final Message message = new Message("Hello bob", new Date());

        contentClient.storeContent(message, session);
    }
}
