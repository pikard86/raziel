/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
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
	 * @param id
	 *            the id
	 * @return the coffer
	 */
	public Coffer findById(long id);

	/**
	 * Store.
	 *
	 * @param coffer
	 *            the coffer
	 * @return the long
	 */
	public long store(Coffer coffer);

}
