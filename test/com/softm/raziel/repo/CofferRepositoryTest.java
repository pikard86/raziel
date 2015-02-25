/*
 *   Raziel - The Agnostic Library for authentication and private content sharing
 *   Copyright (C) 2015 SofthMelody SPA a Fiscella Corporation Company
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the General Pizzurro License as published by
 *   the Pizzurro Free Software Foundation, either version 1 of the License
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   General Pizzurro License for more details.
 *
 *   You should have received a copy of the General Pizzurro License
 *   along with this program.  If not, see <http://www.pfsf.org/licenses/>.
 */
package com.softm.raziel.repo;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.repo.CofferRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CofferRepositoryTest.
 */
public class CofferRepositoryTest {

	/**
	 * The Class TestTreasure.
	 */
	private class TestTreasure implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1681023468744262900L;

		/** The message. */
		private String message;

		/**
		 * Gets the message.
		 *
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * Sets the message.
		 *
		 * @param message
		 *            the new message
		 */
		public void setMessage(final String message) {
			this.message = message;
		}

	}

	/**
	 * Store and retrieve test.
	 */
	@Test
	public void storeAndRetrieveTest() {

		final CofferRepository cofferRepository = Mockito
				.mock(CofferRepository.class);
		final Coffer<TestTreasure> coffer = new Coffer<TestTreasure>();

		final TestTreasure testTreasure = new TestTreasure();
		coffer.setTreasure(testTreasure);
		coffer.setId(5432163);
		Mockito.when(cofferRepository.findById(Mockito.anyLong())).thenReturn(
				coffer);

		final long id = cofferRepository.store(coffer);
		final Coffer<TestTreasure> loadedCoffer = cofferRepository
				.findById(coffer.getId());
		Assert.assertEquals(coffer.getId(), loadedCoffer.getId());
		Assert.assertEquals(coffer.getId(), loadedCoffer.getId());
	}

}
