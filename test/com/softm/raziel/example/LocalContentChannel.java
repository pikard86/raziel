package com.softm.raziel.example;

import java.io.Serializable;

import com.softm.raziel.client.ContentChannel;
import com.softm.raziel.payload.Coffer;
import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.server.ContentService;

public class LocalContentChannel implements ContentChannel {

	private final ContentService service = new ContentService(
			new InMemoryCofferReopsitory(), new InMemoryTicketRepository());

	@Override
	public <T extends Serializable> Coffer<T> getCoffer(final long contentId) {

		return service.getCoffer(contentId);
	}

	@Override
	public ContentTicket getTicket(final long contentId, final String ownerId) {

		return service.getTicket(ownerId, contentId);
	}

	@Override
	public long issueContentTicket(final String ownerId,
			final ContentTicket tiketCoffer) {
		return service.issueContentTicket(ownerId, tiketCoffer);
	}

	@Override
	public <T extends Serializable> long storeCoffer(
			final Coffer<T> contentCoffer) {
		return service.storeCoffer(contentCoffer);
	}

}
