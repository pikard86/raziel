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
package com.softm.raziel.payload;

import com.softm.raziel.crypt.AsymmetricKey;

import java.io.Serializable;


/**
 * The Class AuthenticationTreasure.
 */
public class AuthenticationTreasure implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 7710566999094551344L;

    /**
     * The authentication token.
     */
    private String authenticationToken;

    /**
     * The base key.
     */
    private AsymmetricKey asymmetricKey;

    /**
     * Gets the asymmetric key.
     *
     * @return the asymmetric key
     */
    public AsymmetricKey getAsymmetricKey() {
        return asymmetricKey;
    }

    /**
     * Sets the asymmetric key.
     *
     * @param asymmetricKey the new asymmetric key
     */
    public void setAsymmetricKey(final AsymmetricKey asymmetricKey) {
        this.asymmetricKey = asymmetricKey;
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

}
