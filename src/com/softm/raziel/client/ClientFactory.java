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
package com.softm.raziel.client;

// TODO: Auto-generated Javadoc

/**
 * A factory for creating Client objects.
 */
public class ClientFactory {

    /**
     * The authentication channel.
     */
    private final AuthenticationChannel authenticationChannel;

    /**
     * The content channel.
     */
    private final ContentChannel contentChannel;

    /**
     * Instantiates a new client factory.
     *
     * @param authenticationChannel the authentication channel
     * @param contentChannel        the content channel
     */
    public ClientFactory(final AuthenticationChannel authenticationChannel,
                         final ContentChannel contentChannel) {
        this.authenticationChannel = authenticationChannel;
        this.contentChannel = contentChannel;
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    public RazielClient getClient() {
        final RazielClient razielClient = new RazielClient();
        razielClient.setAuthenticationClient(new AuthenticationClient(
                authenticationChannel));
        razielClient.setContentClient(new ContentClient(contentChannel));
        return razielClient;
    }
}
