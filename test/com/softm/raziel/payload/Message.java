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
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1194816913795414569L;

	/** The message text. */
	private String messageText;

	/** The timestamp. */
	private Date timestamp;

	/**
	 * Instantiates a new message.
	 *
	 * @param messageText
	 *            the message text
	 * @param timestamp
	 *            the timestamp
	 */
	public Message(final String messageText, final Date timestamp) {
		super();
		this.messageText = messageText;
		this.timestamp = timestamp;
	}

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
		final Message other = (Message) obj;
		if (messageText == null) {
			if (other.messageText != null) {
				return false;
			}
		} else if (!messageText.equals(other.messageText)) {
			return false;
		}
		if (timestamp == null) {
            return other.timestamp == null;
		} else return timestamp.equals(other.timestamp);
    }

	/**
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
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
		result = prime * result
				+ (messageText == null ? 0 : messageText.hashCode());
		result = prime * result
				+ (timestamp == null ? 0 : timestamp.hashCode());
		return result;
	}

	/**
	 * Sets the message text.
	 *
	 * @param messageText
	 *            the new message text
	 */
	public void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the new timestamp
	 */
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

}