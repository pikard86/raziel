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
import com.softm.raziel.crypt.RSACyperUtil;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;

// TODO: Auto-generated Javadoc
/**
 * The Class ContentCilent.
 */
public class ContentCilent {

	/** The content channel. */
	private final ContentChannel contentChannel;

	/**
	 * Instantiates a new content cilent.
	 *
	 * @param contentChannel
	 *            the content channel
	 * @param session
	 *            the session
	 */
	public ContentCilent(final ContentChannel contentChannel) {
		this.contentChannel = contentChannel;
	}

	/**
	 * Store content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param plainContent
	 *            the plain content
	 */
	public <T extends Serializable> void storeContent(final T plainContent,
			final AuthenticatedSession session) {

		final Owner owner = session.getOwner();
		final String ownerId = owner.getId();
		final byte[] publicKey = owner.getPublicKey();

		final String randomPassword = UUID.randomUUID().toString();
		final AESCofferKey contentKey = new AESCofferKey(randomPassword);

		final Coffer<T> contentCoffer = new Coffer<T>();
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
