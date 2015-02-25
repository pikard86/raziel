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
package com.softm.raziel.crypt;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CofferKey.
 *
 * @author Pikard
 */
public abstract class CofferKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4662067005874119024L;

	/**
	 * Lock coffer.
	 *
	 * @param treasureBytes
	 *            the treasure bytes
	 * @return the byte[]
	 */
	public abstract byte[] lockCoffer(byte[] treasureBytes);

	/**
	 * Open coffer.
	 *
	 * @param encryptedBytes
	 *            the encrypted bytes
	 * @return the byte[]
	 */
	public abstract byte[] openCoffer(byte[] encryptedBytes);

}
