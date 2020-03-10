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
package com.softm.raziel;

import com.softm.raziel.crypt.AsymmetricKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.crypt.RSACypherUtil;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

import java.util.UUID;

// TODO: Auto-generated Javadoc

/**
 * A factory for creating Owner objects.
 */
public class OwnerFactory {

    /**
     * Creates the owner. with encrypted authentication treasures
     *
     * @param ownerId  the owner id
     * @param ownerKey the owner key
     * @return the owner
     */
    public static Owner createOwner(final String ownerId,
                                    final CofferKey ownerKey) {
        final Owner owner = new Owner();

        final Coffer authCoffer = new Coffer();

        final AuthenticationTreasure authTreasure = new AuthenticationTreasure();

        /*
         * Generates the authentication token the only secret information shared
         * with the server
         */
        final String authenticationToken = getAuthenticationToken();

        authTreasure.setAuthenticationToken(authenticationToken);

        /*
         * Obtains the asymmetric key
         */
        final AsymmetricKey asymmetricKey = RSACypherUtil.getCofferKey();
        /*
         * Put the key into the treasure
         */
        authTreasure.setAsymmetricKey(asymmetricKey);
        /*
         * Store the treasure into the coffer
         */
        authCoffer.setTreasure(authTreasure);
        /*
         * Lock the coffer with owner symmetric key
         */
        authCoffer.lock(ownerKey);
        owner.setId(ownerId);
        owner.setPublicKey(asymmetricKey.getPublicKey());
        owner.setAuthenticationCoffer(authCoffer);
        owner.setAuthenticationToken(authenticationToken);
        return owner;
    }

    /**
     * Gets the authentication token.
     *
     * @return the authentication token
     */
    protected static String getAuthenticationToken() {
        return UUID.randomUUID().toString();
    }

}
