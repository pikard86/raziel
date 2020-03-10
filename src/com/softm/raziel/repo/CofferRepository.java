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
package com.softm.raziel.repo;

import com.softm.raziel.payload.Coffer;

// TODO: Auto-generated Javadoc

/**
 * The Class CofferRepository.
 */
public interface CofferRepository {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the coffer
     */
    Coffer findById(long id);

    /**
     * Store.
     *
     * @param coffer the coffer
     * @return the long
     */
    long store(Coffer coffer);

}
