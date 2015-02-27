package com.softm.raziel.client;

import java.util.Date;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.softm.raziel.Owner;
import com.softm.raziel.OwnerFactory;
import com.softm.raziel.crypt.AESCofferKey;
import com.softm.raziel.exceptions.AuthenticationRequiredException;
import com.softm.raziel.exceptions.ContentException;
import com.softm.raziel.exceptions.UndefinedOwnerException;
import com.softm.raziel.exceptions.WrongOwnerCredentialException;
import com.softm.raziel.payload.AuthenticationTreasure;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.payload.Message;

public class ClientTest {

	@Test
	public void clientSignInTest() throws WrongOwnerCredentialException,
	AuthenticationRequiredException, UndefinedOwnerException,
	ContentException {
		final AuthenticationChannel authenticationChannel = Mockito
				.mock(AuthenticationChannel.class);

		final ContentChannel contentChannel = Mockito
				.mock(ContentChannel.class);
		final ClientFactory clientFactory = new ClientFactory(
				authenticationChannel, contentChannel);
		final RazielClient client = clientFactory.getClient();
		final String ownerId = "ownerId";
		final String password = "password";
		final AESCofferKey ownerKey = new AESCofferKey(password);
		final Owner owner = OwnerFactory.createOwner(ownerId, ownerKey);
		final Coffer<AuthenticationTreasure> coffer = owner
				.getAuthenticationCoffer();
		coffer.open(ownerKey);
		final String authenticationToken = coffer.getTreasure()
				.getAuthenticationToken();
		coffer.lock(ownerKey);

		Mockito.when(
				authenticationChannel.doSignIn(ownerId, authenticationToken))
				.thenReturn(owner);
		Mockito.when(authenticationChannel.getAuthenticationCoffer(ownerId))
		.thenReturn(owner.getAuthenticationCoffer());

		client.signIn(ownerId, password);

		final Message msg = new Message("Hello Bob", new Date());

		final Long mockContentID = new Long(112);
		Mockito.when(contentChannel.storeCoffer(Mockito.any(Coffer.class)))
		.thenReturn(mockContentID);
		final ArgumentCaptor<ContentTicket> ticketCaptor = ArgumentCaptor
				.forClass(ContentTicket.class);

		final ArgumentCaptor<Coffer> cofferCaptor = ArgumentCaptor
				.forClass(Coffer.class);

		final long contentId = client.storeContent(msg);
		Mockito.verify(contentChannel).issueContentTicket(Mockito.anyString(),
				ticketCaptor.capture());
		Mockito.when(contentChannel.getTicket(mockContentID, ownerId))
		.thenReturn(ticketCaptor.getValue());

		Mockito.verify(contentChannel).storeCoffer(cofferCaptor.capture());
		Mockito.when(contentChannel.getCoffer(mockContentID)).thenReturn(
				cofferCaptor.getValue());
		final Message retreivaedMsg = (Message) client.getContent(contentId);
		org.junit.Assert.assertEquals(msg, retreivaedMsg);

	}
}
