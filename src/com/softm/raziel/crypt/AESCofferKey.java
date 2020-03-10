/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2020  Riccardo Pittiglio
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
package com.softm.raziel.crypt;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

// TODO: Auto-generated Javadoc

/**
 * The Class AESCofferKey.
 */
public class AESCofferKey extends CofferKey {

    /**
     * The Constant AES.
     */
    private static final String AES = "AES";

    /**
     * The Constant AES_ECB_PKCS5_PADDING.
     */
    private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * The Constant PBKDF2_WITH_HMAC_SHA1.
     */
    private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3663336776189514516L;

    /**
     * The iterations count.
     */
    private final int ITERATIONS_COUNT = 1000;

    /**
     * The salt.
     */
    private byte[] salt;

    /**
     * The secret key.
     */
    private byte[] secretKey;

    /**
     * Instantiates a new AES coffer key.
     */
    public AESCofferKey() {
        super();
    }

    /**
     * Instantiates a new AES coffer key.
     *
     * @param secretKey the secret key
     */
    public AESCofferKey(final byte[] secretKey) {
        this();
        this.secretKey = secretKey;
    }

    /**
     * Instantiates a new AES coffer key.
     *
     * @param password the password
     */
    public AESCofferKey(final String password) {
        this(password.getBytes());
    }

    /**
     * Gets the secret key.
     *
     * @return the secret key
     */
    public byte[] getSecretKey() {
        return secretKey;
    }

    /**
     * Sets the secret key.
     *
     * @param secretKey the new secret key
     */
    public void setSecretKey(final byte[] secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Initializes cipher.
     *
     * @param encryptMode the encrypt mode
     * @return the cipher
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     * @throws NoSuchPaddingException   the no such padding exception
     * @throws InvalidKeyException      the invalid key exception
     */
    private Cipher initCipher(final int encryptMode)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException {
        // TODO: extract secret key generation
        final char[] password = new String(secretKey).toCharArray();
        salt = "salt".getBytes();

        final SecretKeyFactory factoryKeyEncrypt = SecretKeyFactory
                .getInstance(PBKDF2_WITH_HMAC_SHA1);
        final KeySpec keySpec = new PBEKeySpec(password, salt, ITERATIONS_COUNT,
                128);

        final SecretKey secretKey = factoryKeyEncrypt.generateSecret(keySpec);
        final SecretKeySpec encryptKey = new SecretKeySpec(
                secretKey.getEncoded(), AES);

        final Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
        cipher.init(encryptMode, encryptKey);
        return cipher;
    }

    /* (non-Javadoc)
     * @see com.softm.raziel.crypt.CofferKey#lockCoffer(byte[])
     */
    @Override
    public byte[] lockCoffer(final byte[] treasureBytes) {
        try {
            final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            return cipher.doFinal(treasureBytes);
        } catch (final Exception e) {
            e.printStackTrace();
            // TODO : handle exceptions
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.softm.raziel.crypt.CofferKey#openCoffer(byte[])
     */
    @Override
    public byte[] openCoffer(final byte[] encryptedBytes) {
        try {
            final Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
            return cipher.doFinal(encryptedBytes);
        } catch (final Exception e) {
            e.printStackTrace();
            // TODO : handle exceptions
        }
        return null;
    }
}
