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

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class CofferKey.
 *
 * @author Pikard
 */
public abstract class CofferKey implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -4662067005874119024L;

    /**
     * Lock coffer.
     *
     * @param treasureBytes the treasure bytes
     * @return the byte[]
     */
    public abstract byte[] lockCoffer(byte[] treasureBytes);

    /**
     * Open coffer.
     *
     * @param encryptedBytes the encrypted bytes
     * @return the byte[]
     */
    public abstract byte[] openCoffer(byte[] encryptedBytes);

}
