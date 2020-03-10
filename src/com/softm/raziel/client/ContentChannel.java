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

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Interface ContentChannel.
 */
public interface ContentChannel {

    /**
     * Gets the coffer.
     *
     * @param <T>       the generic type
     * @param contentId the content id
     * @return the coffer
     */
    <T extends Serializable> Coffer<T> getCoffer(long contentId);

    /**
     * Gets the ticket.
     *
     * @param contentId the content id
     * @param ownerId   the id
     * @return the ticket
     */
    ContentTicket getTicket(long contentId, String ownerId);

    /**
     * Store content ticket.
     *
     * @param ownerId      the owner id
     * @param ticketCoffer the ticket coffer
     * @return the long
     */
    long issueContentTicket(String ownerId, ContentTicket ticketCoffer);

    /**
     * Store.
     *
     * @param <T>           the generic type
     * @param contentCoffer the content coffer
     * @return the long
     */
    <T extends Serializable> long storeCoffer(Coffer<T> contentCoffer);
}
