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

import java.util.UUID;

import com.softm.raziel.Owner;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.CofferKey;
import com.softm.raziel.crypt.RSACofferKey;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Treasure;

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
	private Coffer<ContentTicket> generateTicket(final byte[] publicKey,
			final CofferKey contentKey, final long sharedCofferId) {
		final RSACofferKey tiketKey = new RSACofferKey();
		tiketKey.setPublicKey(publicKey);
		final ContentTicket ticket = new ContentTicket();
		ticket.setSharedCofferId(sharedCofferId);
		ticket.setKey(contentKey);
		final Coffer<ContentTicket> tiketCoffer = new Coffer<ContentTicket>();
		tiketCoffer.setTreasure(ticket);
		tiketCoffer.lock(tiketKey);
		return tiketCoffer;
	}

	public void storeContent(final Treasure plainContent) {

		final Owner owner = session.getOwner();
		final byte[] publicKey = owner.getPublicKey();

		final CofferKey contentKey = new AESCofferKey(UUID.randomUUID()
				.toString().getBytes());
		final Coffer<Treasure> contentCoffer = new Coffer<Treasure>();
		contentCoffer.setTreasure(plainContent);
		contentCoffer.lock(contentKey);

		final long sharedCofferId = contentChannel.storeCoffer(contentCoffer);
		final String ownerId = owner.getId();

		final Coffer<ContentTicket> tiketCoffer = generateTicket(publicKey,
				contentKey, sharedCofferId);
		final long tiketId = contentChannel.issueContentTicket(ownerId,
				tiketCoffer);

		owner.addTiket(tiketId);

	}

}
