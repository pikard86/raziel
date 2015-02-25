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
package com.softm.raziel.client;

import java.io.Serializable;
import java.util.UUID;

import com.softm.raziel.Owner;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.crypt.RSACyperUtil;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;

/**
 * The Class ContentCilent.
 */
public class ContentCilent {

	private final ContentChannel contentChannel;
	private final AuthenticatedSession session;

	/**
	 * Instantiates a new content cilent.
	 *
	 * @param contentChannel
	 *            the content channel
	 */
	public ContentCilent(final ContentChannel contentChannel,
			final AuthenticatedSession session) {
		this.contentChannel = contentChannel;
		this.session = session;
	}

	/**
	 * Generate ticket.
	 *
	 * @param publicKey
	 *            the public key
	 * @param contentKey
	 *            the content key
	 * @param sharedCofferId
	 *            the shared coffer id
	 * @return the coffer
	 */
	private ContentTicket generateTicket(final byte[] publicKey,
			final CofferKey contentKey, final long sharedCofferId) {

		final ContentTicket ticket = new ContentTicket();

		return ticket;
	}

	public void storeContent(final Serializable plainContent) {

		final Owner owner = session.getOwner();
		final String ownerId = owner.getId();
		final byte[] publicKey = owner.getPublicKey();

		final String randomPassword = UUID.randomUUID().toString();
		final AESCofferKey contentKey = new AESCofferKey(randomPassword);

		final Coffer contentCoffer = new Coffer();
		contentCoffer.setTreasure(plainContent);
		contentCoffer.lock(contentKey);

		final long sharedCofferId = contentChannel.storeCoffer(contentCoffer);

		final ContentTicket tiket = new ContentTicket();
		tiket.setSharedCofferId(sharedCofferId);
		tiket.setTicket(RSACyperUtil.generateTicket(contentKey, publicKey));
		final long tiketId = contentChannel.issueContentTicket(ownerId, tiket);

		owner.addTiket(tiketId);
	}

}
