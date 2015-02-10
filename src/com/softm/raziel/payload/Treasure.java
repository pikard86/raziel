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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Treasure.
 */
public abstract class Treasure implements Serializable {

	/**
	 * Inflate.
	 *
	 * @param treasureBytes
	 *            the treasure bytes
	 * @return the treasure
	 */
	public static Treasure inflate(final byte[] treasureBytes) {
		final InputStream inputStream = new ByteArrayInputStream(treasureBytes);
		try {
			final ObjectInputStream objectInputStream = new ObjectInputStream(
					inputStream);
			try {
				final Object obj = objectInputStream.readObject();
				if (obj instanceof Treasure) {
					final Treasure treasure = (Treasure) obj;
					return treasure;
				}
			} catch (final ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: throw a custom exception
		return null;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7396530147151236165L;

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public byte[] getBytes() {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = null;
		byte[] treasureBytes = null;
		try {
			objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(this);
			treasureBytes = out.toByteArray();

		} catch (final IOException e) {
			e.printStackTrace();
			// TODO bubble the exception
		} finally {
			try {
				if (objectStream != null) {
					objectStream.close();
				}
				out.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return treasureBytes;
	}
}
