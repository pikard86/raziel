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
package com.softm.raziel.server;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.repo.OwnerRepository;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * The Class AuthenticationServerTest.
 */
public class AuthenticationServiceTest {

    /**
     * The Constant OWNER_PASSWORD.
     */
    private static final String OWNER_PASSWORD = "owner:password";

    /**
     * The Constant OWNER_ID.
     */
    private static final String OWNER_ID = "#test:owner";

    /**
     * Test sign in.
     *
     * @throws UndefinedOwnerException       the undefined owner exception
     * @throws WrongOwnerCredentialException the wrong owner credential exception
     */
    @Test
    public void testSignIn() throws UndefinedOwnerException,
            WrongOwnerCredentialException {
        final String ownerId = OWNER_ID;

        final OwnerRepository ownerRepository = Mockito
                .mock(OwnerRepository.class);

        final CofferKey ownerKey = new AESCofferKey(OWNER_PASSWORD.getBytes());
        final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);

        Mockito.when(ownerRepository.findOwnerById(Mockito.matches(OWNER_ID)))
                .thenReturn(owner);

        final String authenticationToken = owner.getAuthenticationToken();
        final AuthenticationService authServer = new AuthenticationService(
                ownerRepository);

        authServer.onSignInRequest(ownerId, authenticationToken);
    }

    /**
     * Test sign on.
     */
    @Test
    public void testSignOn() {

        final OwnerRepository ownerRepository = Mockito
                .mock(OwnerRepository.class);

        final CofferKey ownerKey = new AESCofferKey(OWNER_PASSWORD.getBytes());
        final Owner owner = OwnerFactory.createOwner(OWNER_ID, ownerKey);

        final AuthenticationService authServer = new AuthenticationService(
                ownerRepository);

        authServer.onSignOnRequest(owner);
    }
}
