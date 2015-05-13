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
package com.softm.raziel.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class UndefinedOwnerException.
 */
public class UndefinedOwnerException extends Exception {

	/** The Constant UNABLE_TO_RETREIVE_OWNER_ID. */
	private static final String UNABLE_TO_RETREIVE_OWNER_ID = "Unable to retreive owner id : ";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4113268056849915971L;
	private final String ownerId;

	/**
	 * Instantiates a new undefined owner exception.
	 *
	 * @param ownerId
	 *            the owner id
	 */
	public UndefinedOwnerException(final String ownerId) {
		super(UNABLE_TO_RETREIVE_OWNER_ID + ownerId);
		this.ownerId = ownerId;
	}

	public String getOwnerId() {
		return ownerId;
	}

}
