/*
 * Raziel 
 * SofthMelody a Fiscella Corporation Company 
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7396530147151236165L;

	/**
	 * Inflate.
	 *
	 * @param treasureBytes the treasure bytes
	 * @return the treasure
	 */
	public static Treasure inflate(byte[] treasureBytes) {
		InputStream inputStream = new ByteArrayInputStream(treasureBytes);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					inputStream);
			try {
				Object obj = objectInputStream.readObject();
				if (obj instanceof Treasure) {
					Treasure treasure = (Treasure) obj;
					return treasure;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: throw a custom exception
		return null;
	}

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public byte[] getBytes() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = null;
		byte[] treasureBytes = null;
		try {
			objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(this);
			treasureBytes = out.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
			// TODO bubble the exception
		} finally {
			try {
				if (objectStream != null) {
					objectStream.close();
				}
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return treasureBytes;
	}
}
