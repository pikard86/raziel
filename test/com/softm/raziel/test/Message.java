package com.softm.raziel.test;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1194816913795414569L;
	private String messageText;
	private Date timestamp;

	public Message(final String messageText, final Date timestamp) {
		super();
		this.messageText = messageText;
		this.timestamp = timestamp;
	}

	public String getMessageText() {
		return messageText;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

}