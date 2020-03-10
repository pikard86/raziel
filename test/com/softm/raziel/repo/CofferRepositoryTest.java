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
package com.softm.raziel.repo;

import com.softm.raziel.payload.Coffer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.Serializable;


/**
 * The Class CofferRepositoryTest.
 */
public class CofferRepositoryTest {

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

    /**
     * The Class TestTreasure.
     */
    private static class TestTreasure implements Serializable {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1681023468744262900L;

        /**
         * The message.
         */
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
         * @param message the new message
         */
        public void setMessage(final String message) {
            this.message = message;
        }

    }

}
