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

import com.softm.raziel.Owner;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface OwnerRepository.
 */
public interface OwnerRepository {

    /**
     * Find owner by id.
     *
     * @param ownerId the owner id
     * @return the owner
     */
    Owner findOwnerById(String ownerId);

    /**
     * Find owners by ids.
     *
     * @param ownerIds the owner ids
     * @return the list
     */
    List<Owner> findOwnersByIds(List<String> ownerIds);

    /**
     * Store.
     *
     * @param owner the owner
     */
    void store(Owner owner);

}
