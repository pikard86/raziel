/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2015 SofthMelody SPA a Fiscella Corporation Company
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the General Pizzurro License as published by
 *   the Pizzurro Free Software Foundation, either version 1 of the License
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   General Pizzurro License for more details.
 *
 *   You should have received a copy of the General Pizzurro License
 *   along with this program.  If not, see <http://www.pfsf.org/licenses/>.
 */
package com.softm.raziel.server;

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.repo.CofferRepository;
import com.softm.raziel.repo.TicketRepository;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class ContentService.
 */
public class ContentService {

    /**
     * The coffer repository.
     */
    private CofferRepository cofferRepository;

    /**
     * The ticket repository.
     */
    private TicketRepository ticketRepository;

    public ContentService(final CofferRepository cofferRepository,
                          final TicketRepository ticketRepository) {
        super();
        this.cofferRepository = cofferRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * Gets the coffer.
     *
     * @param <T>      the generic type
     * @param cofferId the coffer id
     * @return the coffer
     */
    public <T extends Serializable> Coffer<T> getCoffer(final long cofferId) {
        return cofferRepository.findById(cofferId);
    }

    /**
     * Gets the coffer repository.
     *
     * @return the coffer repository
     */
    public CofferRepository getCofferRepository() {
        return cofferRepository;
    }

    /**
     * Sets the coffer repository.
     *
     * @param cofferRepository the new coffer repository
     */
    public void setCofferRepository(final CofferRepository cofferRepository) {
        this.cofferRepository = cofferRepository;
    }

    /**
     * Gets the ticket.
     *
     * @param ownerId  the owner id
     * @param cofferId the coffer id
     * @return the ticket
     */
    public ContentTicket getTicket(final String ownerId, final long cofferId) {

        return ticketRepository.findTicket(ownerId, cofferId);
    }

    /**
     * Gets the ticket repository.
     *
     * @return the ticket repository
     */
    public TicketRepository getTicketRepository() {
        return ticketRepository;
    }

    /**
     * Sets the ticket repository.
     *
     * @param ticketRepository the new ticket repository
     */
    public void setTicketRepository(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Issue content ticket.
     *
     * @param ownerId the owner id
     * @param ticket  the ticket
     * @return the long
     */
    public long issueContentTicket(final String ownerId,
                                   final ContentTicket ticket) {
        // TODO: add check on content and owner ids
        return ticketRepository.storeTicketForOwner(ownerId, ticket);
    }

    /**
     * Store coffer.
     *
     * @param <T>    the generic type
     * @param coffer the coffer
     * @return the long
     */
    public <T extends Serializable> long storeCoffer(final Coffer<T> coffer) {
        return cofferRepository.store(coffer);
    }

}
