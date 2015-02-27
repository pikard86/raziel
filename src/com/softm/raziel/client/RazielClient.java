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

	/** The content cilent. */
	private ContentCilent contentCilent;

	/**
	 * Check session.
	 *
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 */
	private void checkSession() throws AuthenticationRequiredException {
		if (session == null) {
			throw new AuthenticationRequiredException(
					"Unable to store content autentication required do SignIn/SignOn before");
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
	 * @param contentId
	 *            the content id
	 * @return the content
	 * @throws AuthenticationRequiredException
	 *             the authentication required exception
	 * @throws ContentException
	 *             the content exception
	 */
	public Serializable getContent(final long contentId)
			throws AuthenticationRequiredException, ContentException {
		checkSession();
		return contentCilent.getContent(contentId, session);
	}

	/**
	 * Gets the content cilent.
	 *
	 * @return the content cilent
	 */
	public ContentCilent getContentCilent() {
		return contentCilent;
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
	 * Sets the content cilent.
	 *
	 * @param contentCilent
	 *            the new content cilent
	 */
	public void setContentCilent(final ContentCilent contentCilent) {
		this.contentCilent = contentCilent;
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
		return contentCilent.storeContent(content, session);
	}
}