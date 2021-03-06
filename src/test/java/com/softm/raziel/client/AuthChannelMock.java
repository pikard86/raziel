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
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The Class AuthChannelMock.
 */
public class AuthChannelMock implements AuthenticationChannel {

    /**
     * The owners.
     */
    public final HashMap<String, Owner> owners = new HashMap<String, Owner>();

    /*
     * (non-Javadoc)
     *
     * @see com.softm.AuthenticationChannel#doSignIn(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Owner doSignIn(final String ownerId, final String authenticationToken) {
        final Owner owner = owners.get(ownerId);
        if (owner != null
                && owner.getAuthenticationToken().equals(authenticationToken)) {
            return owner;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.softm.AuthenticationChannel#doSignOn(com.softm.secret.Owner)
     */
    @Override
    public boolean doSignOn(final Owner owner) {
        owners.put(owner.getId(), owner);
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.softm.AuthenticationChannel#getAuthenticationCoffer(java.lang.String)
     */
    @Override
    public Coffer<AuthenticationTreasure> getAuthenticationCoffer(
            final String ownerId) {
        final Owner owner = owners.get(ownerId);
        return owner.getAuthenticationCoffer();
    }

    @Override
    public List<Owner> getOwnersByIds(final List<String> recipientsIds) {
        final List<Owner> ownersByIds = new ArrayList<Owner>();

        for (final String id : recipientsIds) {
            final Owner owner = owners.get(id);
            if (owner != null) {
                ownersByIds.add(owner);
            }
        }
        return ownersByIds;
    }

}
