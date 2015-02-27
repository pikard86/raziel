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
package com.softm.raziel.repo;

import com.softm.raziel.payload.ContentTicket;

// TODO: Auto-generated Javadoc
/**
 * The Interface TicketRepository.
 */
public interface TicketRepository {

	/**
	 * Find tiket.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param cofferId
	 *            the coffer id
	 * @return the content ticket
	 */
	ContentTicket findTiket(String ownerId, long cofferId);

	/**
	 * Store ticket for owner.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param ticket
	 *            the ticket
	 * @return the long
	 */
	long storeTicketForOwner(String ownerId, ContentTicket ticket);
}