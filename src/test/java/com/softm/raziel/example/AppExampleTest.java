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
package com.softm.raziel.example;

import com.softm.raziel.client.ClientFactory;
import com.softm.raziel.client.RazielClient;
import com.softm.raziel.exceptions.AuthenticationRequiredException;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.payload.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * The Class AppExampleTest.
 */
public class AppExampleTest {

    private static final String TRUDY_PASSWORD = "trudy password";
    private static final String TRUDY_ID = "trudy id";
    private static final String BOB_PASSWORD = "bob password";
    private static final String BOB_ID = "bob id";
    private static final String ALICE_PASSWORD = "alice password";
    private static final String ALICE_ID = "alice id";
    private final LocalAuthenticationChannel authenticationChannel = new LocalAuthenticationChannel();
    private final LocalContentChannel contentChannel = new LocalContentChannel();
    private RazielClient bob;
    private RazielClient alice;
    private RazielClient trudy;

    @Test
    public void basicInteractionTest() throws
            AuthenticationRequiredException,
            ContentException {
        bob.signOn(BOB_ID, BOB_PASSWORD);

        alice.signOn(ALICE_ID, ALICE_PASSWORD);

        final Message messageToAlice = new Message(
                "Hi alice I'm bob how are you?", new Date());
        final Map<String, Long> sharedContents = bob.shareContent(
                messageToAlice, ALICE_ID);
        final Message messageFromBob = alice.getContent(sharedContents
                .get(ALICE_ID));

        // TODO: add method to retrieve content by user
        assertEquals(messageToAlice, messageFromBob);

        trudy.signOn(TRUDY_ID, TRUDY_PASSWORD);
        try {
            final Message stolenMessage = trudy.getContent(sharedContents
                    .get(ALICE_ID));
            Assert.assertNotEquals(messageToAlice, stolenMessage);
        } catch (final RuntimeException ignored) {
        }

        final Map<String, Long> contentSharedWithTrudy = alice
                .shareExistingContent(sharedContents.get(ALICE_ID), TRUDY_ID);
        final Message fromAliceToTrudy = trudy.getContent(contentSharedWithTrudy
                .get(TRUDY_ID));
        assertEquals(messageToAlice, fromAliceToTrudy);

    }

    @Before
    public void setUp() {
        final ClientFactory clientFactory = new ClientFactory(
                authenticationChannel, contentChannel);
        bob = clientFactory.getClient();
        alice = clientFactory.getClient();
        trudy = clientFactory.getClient();
    }
}
