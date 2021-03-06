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
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

import java.util.List;


/**
 * The Interface AuthenticationChannel.
 */
public interface AuthenticationChannel {

    /**
     * Do sign in.
     *
     * @param ownerId             the owner id
     * @param authenticationToken the authentication token
     * @return true, if successful
     * @throws WrongOwnerCredentialException
     * @throws UndefinedOwnerException
     */
    Owner doSignIn(String ownerId, String authenticationToken)
            throws UndefinedOwnerException, WrongOwnerCredentialException;

    /**
     * Do sign on.
     *
     * @param owner the owner
     * @return true, if successful
     */
    boolean doSignOn(Owner owner);

    /**
     * Gets the authentication coffer.
     *
     * @param ownerId the owner id
     * @return the authentication coffer
     * @throws UndefinedOwnerException
     */
    Coffer<AuthenticationTreasure> getAuthenticationCoffer(String ownerId)
            throws UndefinedOwnerException;

    /**
     * Gets the owners by ids.
     *
     * @param recipientsIds the recipients ids
     * @return the owners by ids
     */
    List<Owner> getOwnersByIds(List<String> recipientsIds);
}
