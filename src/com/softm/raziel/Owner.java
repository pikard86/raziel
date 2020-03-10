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

import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;


/**
 * The Class Owner.
 */
public class Owner {

    /**
     * The authentication coffer.
     */
    private Coffer<AuthenticationTreasure> authenticationCoffer;

    /**
     * The authentication token.
     */
    private String authenticationToken;

    /**
     * The id.
     */
    private String id;

    /**
     * The public key.
     */
    private byte[] publicKey;

    /**
     * Gets the authentication coffer.
     *
     * @return the authentication coffer
     */
    public Coffer<AuthenticationTreasure> getAuthenticationCoffer() {
        return authenticationCoffer;
    }

    /**
     * Sets the authentication coffer.
     *
     * @param authenticationCoffer the new authentication coffer
     */
    public void setAuthenticationCoffer(
            final Coffer<AuthenticationTreasure> authenticationCoffer) {
        this.authenticationCoffer = authenticationCoffer;
    }

    /**
     * Gets the authentication token.
     *
     * @return the authentication token
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Sets the authentication token.
     *
     * @param authenticationToken the new authentication token
     */
    public void setAuthenticationToken(final String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets the public key.
     *
     * @return the public key
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the public key.
     *
     * @param publicKey the new public key
     */
    public void setPublicKey(final byte[] publicKey) {
        this.publicKey = publicKey;
    }

}
