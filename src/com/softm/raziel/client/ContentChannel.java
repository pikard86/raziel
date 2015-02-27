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
package com.softm.raziel.client;

import java.io.Serializable;

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContentChannel.
 */
public interface ContentChannel {

	/**
	 * Gets the coffer.
	 *
	 * @param contentId
	 *            the content id
	 * @return the coffer
	 */
	<T extends Serializable> Coffer<T> getCoffer(long contentId);

	/**
	 * Gets the ticket.
	 *
	 * @param contentId
	 *            the content id
	 * @param id
	 *            the id
	 * @return the ticket
	 */
	ContentTicket getTicket(long contentId, String id);

	/**
	 * Store content ticket.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param tiketCoffer
	 *            the tiket coffer
	 * @return the long
	 */
	long issueContentTicket(String ownerId, ContentTicket tiketCoffer);

	/**
	 * On content share.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param contentTicket
	 *            the content ticket
	 * @return true, if successful
	 */
	boolean onContentShare(String ownerId, ContentTicket contentTicket);

	/**
	 * Store.
	 *
	 * @param contentCoffer
	 *            the content coffer
	 * @return the long
	 */
	<T extends Serializable> long storeCoffer(Coffer<T> contentCoffer);
}
