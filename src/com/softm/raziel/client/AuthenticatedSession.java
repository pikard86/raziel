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
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.AsymmetricKey;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc

/**
 * The Class AuthenticatedSession.
 */
public class AuthenticatedSession {

    /**
     * The owner.
     */
    private final Owner owner;

    /**
     * The password.
     */
    private final String password;

    /**
     * The asymmetric key.
     */
    private AsymmetricKey asymmetricKey;

    /**
     * Instantiates a new authenticated session.
     *
     * @param owner    the owner
     * @param password the password
     */
    public AuthenticatedSession(final Owner owner, final String password) {
        super();
        this.owner = owner;
        this.password = password;
        extractAsymmetricKey();
    }

    /**
     * Extract asymmetric key.
     */
    private void extractAsymmetricKey() {
        final Coffer<AuthenticationTreasure> authenticationCoffer = owner
                .getAuthenticationCoffer();
        if (authenticationCoffer.getTreasure() == null) {
            authenticationCoffer.open(new AESCofferKey(password));
        }
        asymmetricKey = authenticationCoffer.getTreasure().getAsymmetricKey();
    }

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Gets the owner private key.
     *
     * @return the owner private key
     */
    public byte[] getOwnerPrivateKey() {
        return asymmetricKey.getPrivateKey();
    }

}
