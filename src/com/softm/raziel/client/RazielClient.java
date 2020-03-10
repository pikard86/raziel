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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.exceptions.AuthenticationRequiredException;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;

// TODO: Auto-generated Javadoc
/**
 * The Class RazielClient.
 */
public class RazielClient {

	/** The session. */
	private AuthenticatedSession session;

	/** The authentication client. */
	private AuthenticationClient authenticationClient;

	/** The content client. */
	private ContentClient contentClient;

	/**
	 * Check session.
	 *
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 */
	private void checkSession() throws AuthenticationRequiredException {
		if (session == null) {
			throw new AuthenticationRequiredException(
					"Unable to store content authentication required do SignIn/SignOn before");
		}
	}

	/**
	 * Gets the authentication client.
	 *
	 * @return the authentication client
	 */
	public AuthenticationClient getAuthenticationClient() {
		return authenticationClient;
	}

	/**
	 * Gets the content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param contentId
	 *            the content id
	 * @return the content
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 * @throws ContentException
	 *             the content exception
	 */
	public <T extends Serializable> T getContent(final long contentId)
			throws AuthenticationRequiredException, ContentException {
		checkSession();
		return contentClient.getContent(contentId, session);
	}

	/**
	 * Gets the content client.
	 *
	 * @return the content client
	 */
	public ContentClient getContentClient() {
		return contentClient;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public AuthenticatedSession getSession() {
		return session;
	}

	/**
	 * Sets the authentication client.
	 *
	 * @param authenticationClient
	 *            the new authentication client
	 */
	public void setAuthenticationClient(
			final AuthenticationClient authenticationClient) {
		this.authenticationClient = authenticationClient;
	}

	/**
	 * Sets the content client.
	 *
	 * @param contentClient
	 *            the new content client
	 */
	public void setContentClient(final ContentClient contentClient) {
		this.contentClient = contentClient;
	}

	/**
	 * Sets the session.
	 *
	 * @param session
	 *            the new session
	 */
	public void setSession(final AuthenticatedSession session) {
		this.session = session;
	}

	/**
	 * Share content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param content
	 *            the content
	 * @param recipientsIds
	 *            the recipients ids
	 * @return the map
	 */
	public <T extends Serializable> Map<String, Long> shareContent(
			final T content, final List<String> recipientsIds) {
		final List<Owner> owners = authenticationClient
				.getOwnersByIds(recipientsIds);
		return contentClient.shareContent(content, session, owners);
	}

	/**
	 * Share content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param content
	 *            the content
	 * @param recipientId
	 *            the recipient id
	 * @return the map
	 */
	public <T extends Serializable> Map<String, Long> shareContent(
			final T content, final String recipientId) {
		return shareContent(content, Arrays.asList(recipientId));
	}

	/**
	 * Share content.
	 *
	 * @param contentId
	 *            the content id
	 * @param recipientsIds
	 *            the recipients ids
	 * @return the map
	 * @throws ContentException
	 *             the content exception
	 */
	public Map<String, Long> shareExistingContent(final long contentId,
			final List<String> recipientsIds) throws ContentException {
		final List<Owner> owners = authenticationClient
				.getOwnersByIds(recipientsIds);
		return contentClient.shareContent(contentId, session, owners);
	}

	/**
	 * Share existing content.
	 *
	 * @param contentId
	 *            the content id
	 * @param recipientId
	 *            the recipient id
	 * @return the map
	 * @throws ContentException
	 *             the content exception
	 */
	public Map<String, Long> shareExistingContent(final long contentId,
			final String recipientId) throws ContentException {

		return shareExistingContent(contentId, Arrays.asList(recipientId));
	}

	/**
	 * Sign in.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param password
	 *            the password
	 * @throws WrongOwnerCredentialException
	 *             the wrong owner credential exception
	 * @throws UndefinedOwnerException
	 *             the undefined owner exception
	 */
	public void signIn(final String ownerId, final String password)
			throws WrongOwnerCredentialException, UndefinedOwnerException {
		final Owner owner = authenticationClient.signIn(ownerId, password);
		if (owner != null) {
			session = new AuthenticatedSession(owner, password);
		} else {
			throw new WrongOwnerCredentialException(ownerId);
		}
	}

	/**
	 * Sign on.
	 *
	 * @param ownerId
	 *            the owner id
	 * @param password
	 *            the password
	 */
	public void signOn(final String ownerId, final String password) {
		final AESCofferKey key = new AESCofferKey(password);
		final Owner owner = OwnerFactory.createOwner(ownerId, key);
		final boolean signOn = authenticationClient.signOn(owner);
		if (signOn) {
			session = new AuthenticatedSession(owner, password);
		}
	}

	/**
	 * Store content.
	 *
	 * @param <T>
	 *            the generic type
	 * @param content
	 *            the content
	 * @return the long
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 */
	public <T extends Serializable> long storeContent(final T content)
			throws AuthenticationRequiredException {
		checkSession();
		return contentClient.storeContent(content, session);
	}

}
