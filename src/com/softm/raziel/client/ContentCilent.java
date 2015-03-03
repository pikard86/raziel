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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.softm.raziel.Owner;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.RSACyperUtil;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.exceptions.MissingOwnersException;
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
	 */
	public ContentCilent(final ContentChannel contentChannel) {
		this.contentChannel = contentChannel;
	}

	/**
	 * Gets the content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param contentId
	 *            the content id
	 * @param session
	 *            the session
	 * @return the content
	 * @throws ContentException
	 *             the content exception
	 */
	public <T extends Serializable> T getContent(final long contentId,
			final AuthenticatedSession session) throws ContentException {
		final Owner owner = session.getOwner();
		final byte[] privateKey = session.getOwnerPriveteKey();
		final ContentTicket ticket = contentChannel.getTicket(contentId,
				owner.getId());
		if (ticket == null) {
			throw new ContentException(
					"Unable to retreive ticket for content id :" + contentId);
		}
		final Coffer<T> contentCoffer = contentChannel.getCoffer(contentId);
		if (contentCoffer == null) {
			throw new ContentException("Unable to find coffer id : "
					+ contentId);
		}

		final AESCofferKey key = RSACyperUtil.getKeyFromTicket(ticket,
				privateKey);
		contentCoffer.open(key);

		return contentCoffer.getTreasure();
	}

	/**
	 * Issue content ticket.
	 *
	 * @param sharedCofferId
	 *            the shared coffer id
	 * @param recypientId
	 *            the recypient id
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param contentKey
	 *            the content key
	 * @return the long
	 */
	public long issueContentTicket(final long sharedCofferId,
			final String recypientId, final byte[] recipientPublicKey,
			final AESCofferKey contentKey) {
		final ContentTicket tiket = new ContentTicket();
		tiket.setSharedCofferId(sharedCofferId);
		tiket.setTicket(RSACyperUtil.generateTicket(contentKey,
				recipientPublicKey));
		return contentChannel.issueContentTicket(recypientId, tiket);
	}

	/**
	 * Share content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param contentId
	 *            the content id
	 * @param session
	 *            the session
	 * @param recipientsIds
	 *            the recipients ids
	 * @throws ContentException
	 *             the content exception
	 */
	public <T extends Serializable> void shareContent(final long contentId,
			final AuthenticatedSession session, final List<String> recipientsIds)
			throws ContentException {
		final Serializable plainContent = getContent(contentId, session);
		final List<Owner> owners = contentChannel.getOwnersByIds(recipientsIds);
		for (final Owner recipient : owners) {
			storeContentForOwner(plainContent, recipient.getId(),
					recipient.getPublicKey());
		}
	}

	/**
	 * Share content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param plainContent
	 *            the plain content
	 * @param session
	 *            the session
	 * @param recipientsIds
	 *            the recipients ids
	 * @throws MissingOwnersException
	 */
	public <T extends Serializable> Map<String, Long> shareContent(
			final T plainContent, final AuthenticatedSession session,
			final List<String> recipientsIds) throws MissingOwnersException {

		final List<Owner> owners = contentChannel.getOwnersByIds(recipientsIds);
		if (owners == null || owners.isEmpty()) {
			throw new MissingOwnersException(recipientsIds);
		}

		final Map<String, Long> contentIds = new HashMap<String, Long>();

		/*
		 * Stores content for the owner
		 */
		final long ownerContentId = storeContent(plainContent, session);
		final String ownerId = session.getOwner().getId();
		contentIds.put(ownerId, ownerContentId);
		/*
		 * Stores content for the recipient
		 */
		for (final Owner recipient : owners) {
			final long storeContentForOwner = storeContentForOwner(
					plainContent, recipient.getId(), recipient.getPublicKey());
			contentIds.put(recipient.getId(), storeContentForOwner);
		}
		return contentIds;
	}

	/**
	 * Store content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param plainContent
	 *            the plain content
	 * @param session
	 *            the session
	 * @return the long
	 */
	public <T extends Serializable> long storeContent(final T plainContent,
			final AuthenticatedSession session) {

		final Owner owner = session.getOwner();
		final String ownerId = owner.getId();
		final byte[] publicKey = owner.getPublicKey();

		final long sharedCofferId = storeContentForOwner(plainContent, ownerId,
				publicKey);
		return sharedCofferId;
	}

	/**
	 * Store content for owner.
	 *
	 * @param <T>
	 *            the generic type
	 * @param plainContent
	 *            the plain content
	 * @param ownerId
	 *            the owner id
	 * @param publicKey
	 *            the public key
	 * @return the long
	 */
	public <T extends Serializable> long storeContentForOwner(
			final T plainContent, final String ownerId, final byte[] publicKey) {
		final String randomPassword = UUID.randomUUID().toString();
		final AESCofferKey contentKey = new AESCofferKey(randomPassword);

		final Coffer<T> contentCoffer = new Coffer<T>();
		contentCoffer.setTreasure(plainContent);
		contentCoffer.lock(contentKey);

		final long sharedCofferId = contentChannel.storeCoffer(contentCoffer);

		final long tiketId = issueContentTicket(sharedCofferId, ownerId,
				publicKey, contentKey);
		return sharedCofferId;
	}

}
