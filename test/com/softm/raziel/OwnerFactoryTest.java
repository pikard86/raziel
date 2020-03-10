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

import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class OwnerFactoryTest.
 * <p>
 * <p>
 * The owner can be sent to the server to store encrypted coffer, public
 * key and shared auth token
 * <p>
 * on login the server will send the authentication coffer the client
 * extracts the token from the coffer and sent it back to the server
 * using ssl
 * <p>
 * now the client is logged
 * <p>
 * in order to share information using the server the client encode
 * information using the receiver public key
 * <p>
 * to share with more than one receiver the information can be packed
 * into a coffer using symmetric key and the key can be published to
 * receivers using their public key
 * <p>
 * in this way the server can send contents to receivers if authorized
 * and a receiver can read the content only if they have the coffer with
 * the shared storage key
 */
public class OwnerFactoryTest {

    /**
     * The Constant OWNER_ID.
     */
    private static final String OWNER_ID = "ownerId";

    /**
     * Test owner creation.
     */
    @Test
    public void testOwnerCreation() {
        final byte[] key = "password".getBytes();
        final CofferKey ownerKey = new AESCofferKey(key);
        final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);
        assertNotNull(owner);
        assertNotNull(owner.getAuthenticationToken());
        assertNotNull(owner.getId());
        assertEquals(OWNER_ID, owner.getId());
        assertNotNull(owner.getAuthenticationCoffer().getEncryptedBytes());
        owner.getAuthenticationCoffer().open(ownerKey);
        assertNotNull(owner.getAuthenticationCoffer().getTreasure());
    }

}
