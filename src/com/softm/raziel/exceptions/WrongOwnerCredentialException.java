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
 * The Class WrongOwnerCredentialException.
 */
public class WrongOwnerCredentialException extends Exception {

	/** The Constant WRONG_AUTHENTICATION_TOKEN_FOR_OWNER_ID. */
	private static final String WRONG_AUTHENTICATION_TOKEN_FOR_OWNER_ID = "Wrong authentication token for ownre id : ";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8361751331403510256L;

	/**
	 * Instantiates a new wrong owner credential exception.
	 *
	 * @param ownerId
	 *            the owner id
	 */
	public WrongOwnerCredentialException(final String ownerId) {
		super(WRONG_AUTHENTICATION_TOKEN_FOR_OWNER_ID + ownerId);
	}

}
