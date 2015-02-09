/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.repo;

import com.softm.raziel.Owner;

// TODO: Auto-generated Javadoc
/**
 * The Interface OwnerRepository.
 */
public interface OwnerRepository {

	/**
	 * Find owner by id.
	 *
	 * @param ownerId
	 *            the owner id
	 * @return the owner
	 */
	Owner findOwnerById(String ownerId);

	/**
	 * Store.
	 *
	 * @param owner
	 *            the owner
	 */
	void store(Owner owner);

}
