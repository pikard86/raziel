/*
 * Raziel
 * SofthMelody a Fiscella Corporation Company
 */
package com.softm.raziel.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.Treasure;
import com.softm.raziel.repo.CofferRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CofferRepositoryTest.
 */
public class CofferRepositoryTest {

	private class TestTreasure extends Treasure {
		/**
		 *
		 */
		private static final long serialVersionUID = 1681023468744262900L;
		private String message;

		/**
		 * Gets the message.
		 *
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		public void setMessage(final String message) {
			this.message = message;
		}

	}

	/**
	 * Test.
	 */
	@Test
	public void storeAndRetreiveTest() {

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
