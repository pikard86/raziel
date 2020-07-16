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
package com.softm.raziel.crypt;

import java.io.Serializable;


/**
 * The Class AsymmetricKey.
 */
public class AsymmetricKey implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5219937819748791210L;

    /**
     * The private key.
     */
    private byte[] privateKey;

    /**
     * The public key.
     */
    private byte[] publicKey;

    /**
     * Instantiates a new RSA coffer key.
     */
    public AsymmetricKey() {
        super();
    }

    /**
     * Instantiates a new RSA coffer key.
     *
     * @param publicKey  the public key
     * @param privateKey the private key
     */
    public AsymmetricKey(final byte[] publicKey, final byte[] privateKey) {
        this();
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Gets the private key.
     *
     * @return the private key
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets the private key.
     *
     * @param privateKey the new private key
     */
    public void setPrivateKey(final byte[] privateKey) {
        this.privateKey = privateKey;
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
