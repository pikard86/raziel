package com.softm.secret.payload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Treasure implements Serializable {
	private static final long serialVersionUID = -7396530147151236165L;

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
	
	public static Treasure inflate(byte[] treasureBytes){
		InputStream inputStream= new ByteArrayInputStream(treasureBytes);
		try {
			ObjectInputStream objectInputStream= new ObjectInputStream(inputStream);
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
		//TODO: throw a custom exception
		return null;
	}
}
