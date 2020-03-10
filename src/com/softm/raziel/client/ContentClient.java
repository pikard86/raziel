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

import com.softm.raziel.Owner;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.crypt.RSACypherUtil;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// TODO: Auto-generated Javadoc

/**
 * The Class ContentClient.
 */
public class ContentClient {

    /**
     * The content channel.
     */
    private final ContentChannel contentChannel;

    /**
     * Instantiates a new content client.
     *
     * @param contentChannel the content channel
     */
    public ContentClient(final ContentChannel contentChannel) {
        this.contentChannel = contentChannel;
    }

    /**
     * Gets the content.
     *
     * @param <T>       the generic type
     * @param contentId the content id
     * @param session   the session
     * @return the content
     * @throws ContentException the content exception
     */
    public <T extends Serializable> T getContent(final long contentId,
                                                 final AuthenticatedSession session) throws ContentException {
        final Owner owner = session.getOwner();
        final byte[] privateKey = session.getOwnerPrivateKey();
        final ContentTicket ticket = contentChannel.getTicket(contentId,
                owner.getId());
        if (ticket == null) {
            throw new ContentException(
                    "Unable to retrieve ticket for content id :" + contentId);
        }
        final Coffer<T> contentCoffer = contentChannel.getCoffer(contentId);
        if (contentCoffer == null) {
            throw new ContentException("Unable to find coffer id : "
                    + contentId);
        }

        final AESCofferKey key = RSACypherUtil.getKeyFromTicket(ticket,
                privateKey);
        contentCoffer.open(key);

        return contentCoffer.getTreasure();
    }

    /**
     * Issue content ticket.
     *
     * @param sharedCofferId     the shared coffer id
     * @param recipientId        the recipient id
     * @param recipientPublicKey the recipient public key
     * @param contentKey         the content key
     * @return the long
     */
    public long issueContentTicket(final long sharedCofferId,
                                   final String recipientId, final byte[] recipientPublicKey,
                                   final AESCofferKey contentKey) {
        final ContentTicket ticket = new ContentTicket();
        ticket.setSharedCofferId(sharedCofferId);
        ticket.setTicket(RSACypherUtil.generateTicket(contentKey,
                recipientPublicKey));
        return contentChannel.issueContentTicket(recipientId, ticket);
    }

    /**
     * Share content.
     *
     * @param <T>       the generic type
     * @param contentId the content id
     * @param session   the session
     * @return
     * @throws ContentException the content exception
     */
    public <T extends Serializable> Map<String, Long> shareContent(
            final long contentId, final AuthenticatedSession session,
            final List<Owner> owners) throws ContentException {
        final Map<String, Long> contentIds = new HashMap<String, Long>();

        final Serializable plainContent = getContent(contentId, session);
        for (final Owner recipient : owners) {
            final long storedContentId = storeContentForOwner(plainContent,
                    recipient.getId(), recipient.getPublicKey());
            contentIds.put(recipient.getId(), storedContentId);
        }
        return contentIds;
    }

    /**
     * Share content.
     *
     * @param <T>          the generic type
     * @param plainContent the plain content
     * @param session      the session
     * @param owners       the owners
     * @return the map
     */
    public <T extends Serializable> Map<String, Long> shareContent(
            final T plainContent, final AuthenticatedSession session,
            final List<Owner> owners) {

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
     * @param <T>          the generic type
     * @param plainContent the plain content
     * @param session      the session
     * @return the long
     */
    public <T extends Serializable> long storeContent(final T plainContent,
                                                      final AuthenticatedSession session) {

        final Owner owner = session.getOwner();
        final String ownerId = owner.getId();
        final byte[] publicKey = owner.getPublicKey();

        return storeContentForOwner(plainContent, ownerId,
                publicKey);
    }

    /**
     * Store content for owner.
     *
     * @param <T>          the generic type
     * @param plainContent the plain content
     * @param ownerId      the owner id
     * @param publicKey    the public key
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

        final long ticketId = issueContentTicket(sharedCofferId, ownerId,
                publicKey, contentKey);
        return sharedCofferId;
    }

}
