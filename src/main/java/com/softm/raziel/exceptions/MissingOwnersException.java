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
package com.softm.raziel.exceptions;

import java.util.List;


/**
 * The Class MissingOwnersException.
 */
public class MissingOwnersException extends Exception {

    /**
     * The Constant UNABLE_TO_RETRIEVE_OWNER_IDS.
     */
    private static final String UNABLE_TO_RETRIEVE_OWNER_IDS = "Unable to retrieve owner ids : ";
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1773861364233350166L;

    /**
     * Instantiates a new missing owners exception.
     *
     * @param recipientsIds the recipients ids
     */
    public MissingOwnersException(final List<String> recipientsIds) {
        super(UNABLE_TO_RETRIEVE_OWNER_IDS + recipientsIds.toString());
    }

}
