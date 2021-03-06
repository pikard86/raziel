/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2020 Riccardo Pittiglio
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.softm.raziel.payload;

import com.softm.raziel.crypt.CofferKey;

import java.io.*;


/**
 * The Class Coffer.
 *
 * @param <T> the generic type
 */
public class Coffer<T extends Serializable> {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7396530147151236165L;
    /**
     * The id.
     */
    private long id;
    /**
     * The encrypted bytes.
     */
    private byte[] encryptedBytes;
    /**
     * The treasure.
     */
    private T treasure;

    /**
     * Gets the encrypted bytes.
     *
     * @return the encrypted bytes
     */
    public byte[] getEncryptedBytes() {
        return encryptedBytes;
    }

    /**
     * Sets the encrypted bytes.
     *
     * @param encryptedBytes the new encrypted bytes
     */
    public void setEncryptedBytes(final byte[] encryptedBytes) {
        this.encryptedBytes = encryptedBytes;
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets the treasure.
     *
     * @return the treasure
     */
    public T getTreasure() {
        return treasure;
    }

    /**
     * Sets the treasure.
     *
     * @param treasure the new treasure
     */
    public void setTreasure(final T treasure) {
        this.treasure = treasure;
    }

    /**
     * Gets the bytes.
     *
     * @return the bytes
     */
    private byte[] getTreasureBytes() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = null;
        byte[] treasureBytes = null;
        try {
            objectStream = new ObjectOutputStream(out);
            objectStream.writeObject(treasure);
            treasureBytes = out.toByteArray();

        } catch (final IOException e) {
            throw new RuntimeException(e);
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

    /**
     * Inflate treasure.
     *
     * @param treasureBytes the treasure bytes
     * @return the t
     */
    private T inflateTreasure(final byte[] treasureBytes) {
        final InputStream inputStream = new ByteArrayInputStream(treasureBytes);
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(
                    inputStream);
            try {
                final Object obj = objectInputStream.readObject();
                return (T) obj;
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

    /**
     * Lock.
     *
     * @param key the key
     */
    public void lock(final CofferKey key) {
        setEncryptedBytes(key.lockCoffer(this.getTreasureBytes()));
        setTreasure(null);
    }

    /**
     * Open.
     *
     * @param key the key
     */
    public void open(final CofferKey key) {
        if (this.getEncryptedBytes() != null) {
            final byte[] decryptedBytes = key.openCoffer(this
                    .getEncryptedBytes());
            final T inflatedTreasure = inflateTreasure(decryptedBytes);
            setTreasure(inflatedTreasure);
            setEncryptedBytes(null);
        }
    }

}
