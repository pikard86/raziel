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
package com.softm.raziel.payload;

import java.io.Serializable;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class SharedContent.
 */
public class ContentTicket implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7497300550265891449L;

	/** The id. */
	private long id;

	/** The shared coffer id. */
	private long sharedCofferId;

	/** The ticket. */
	private byte[] ticket;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ContentTicket other = (ContentTicket) obj;
		if (id != other.id) {
			return false;
		}
		if (sharedCofferId != other.sharedCofferId) {
			return false;
		}
		if (!Arrays.equals(ticket, other.ticket)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the shared coffer id.
	 *
	 * @return the shared coffer id
	 */
	public long getSharedCofferId() {
		return sharedCofferId;
	}

	/**
	 * Gets the ticket.
	 *
	 * @return the ticket
	 */
	public byte[] getTicket() {
		return ticket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ id >>> 32);
		result = prime * result
				+ (int) (sharedCofferId ^ sharedCofferId >>> 32);
		result = prime * result + Arrays.hashCode(ticket);
		return result;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Sets the shared coffer id.
	 *
	 * @param sharedCofferId
	 *            the new shared coffer id
	 */
	public void setSharedCofferId(final long sharedCofferId) {
		this.sharedCofferId = sharedCofferId;
	}

	/**
	 * Sets the ticket.
	 *
	 * @param ticket
	 *            the new ticket
	 */
	public void setTicket(final byte[] ticket) {
		this.ticket = ticket;
	}

}