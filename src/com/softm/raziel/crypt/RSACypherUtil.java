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
package com.softm.raziel.crypt;

import com.softm.raziel.payload.ContentTicket;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

// TODO: Auto-generated Javadoc

/**
 * The Class RSACypherUtil.
 */
public class RSACypherUtil {

    /**
     * The Constant RSA_ECB_PKCS1_PADDING.
     */
    private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    /**
     * The Constant RSA.
     */
    private static final String RSA = "RSA";

    /**
     * Decrypt rsa.
     *
     * @param encryptedData   the encrypted data
     * @param privateKeyBytes the private key bytes
     * @return the byte[]
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws InvalidKeySpecException   the invalid key spec exception
     * @throws NoSuchPaddingException    the no such padding exception
     * @throws IllegalBlockSizeException the illegal block size exception
     * @throws BadPaddingException       the bad padding exception
     * @throws InvalidKeyException       the invalid key exception
     */
    public static byte[] decryptRSA(final byte[] encryptedData,
                                    final byte[] privateKeyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        final PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKeyBytes);
        final KeyFactory kf = KeyFactory.getInstance(RSA);
        final PrivateKey privateKey = kf.generatePrivate(ks);
        final Cipher c = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
        c.init(Cipher.DECRYPT_MODE, privateKey);
        return c.doFinal(encryptedData);

    }

    /**
     * Encrypt rsa.
     *
     * @param data           the data
     * @param publicKeyBytes the public key bytes
     * @return the byte[]
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws InvalidKeySpecException   the invalid key spec exception
     * @throws NoSuchPaddingException    the no such padding exception
     * @throws InvalidKeyException       the invalid key exception
     * @throws IllegalBlockSizeException the illegal block size exception
     * @throws BadPaddingException       the bad padding exception
     */
    public static byte[] encryptRSA(final byte[] data,
                                    final byte[] publicKeyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final X509EncodedKeySpec ks = new X509EncodedKeySpec(publicKeyBytes);
        final KeyFactory kf = KeyFactory.getInstance(RSA);
        final PublicKey publicKey = kf.generatePublic(ks);
        final Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * Generate ticket.
     *
     * @param contentKey     the content key
     * @param publicKeyBytes the public key bytes
     * @return the byte[]
     */
    public static byte[] generateTicket(final AESCofferKey contentKey,
                                        final byte[] publicKeyBytes) {
        try {
            final byte[] secretKey = contentKey.getSecretKey();
            return encryptRSA(secretKey, publicKeyBytes);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the coffer key.
     *
     * @return the coffer key
     */
    public static AsymmetricKey getCofferKey() {
        try {

            final KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(1024);
            final KeyPair kp = kpg.generateKeyPair();
            // X.509
            final byte[] publicKey = kp.getPublic().getEncoded();
            // PKCS#8
            final byte[] privateKey = kp.getPrivate().getEncoded();

            return new AsymmetricKey(publicKey,
                    privateKey);

        } catch (final NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the key from ticket.
     *
     * @param ticket          the ticket
     * @param privateKeyBytes the private key bytes
     * @return the key from ticket
     */
    public static AESCofferKey getKeyFromTicket(final ContentTicket ticket,
                                                final byte[] privateKeyBytes) {
        final byte[] encryptedKeyData = ticket.getTicket();
        byte[] secretKey;
        try {
            secretKey = decryptRSA(encryptedKeyData, privateKeyBytes);
            return new AESCofferKey(secretKey);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
