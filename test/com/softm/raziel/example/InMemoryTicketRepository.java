package com.softm.raziel.example;

import java.util.HashMap;

import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.repo.TicketRepository;

public class InMemoryTicketRepository implements TicketRepository {
	Long ticketSequence = new Long(1);
	HashMap<Long, ContentTicket> tickets = new HashMap<Long, ContentTicket>();

	@Override
	public ContentTicket findTicket(final String ownerId, final long cofferId) {
		return tickets.get(cofferId);
	}

	@Override
	public long storeTicketForOwner(final String ownerId,
			final ContentTicket ticket) {
		ticket.setId(ticketSequence);
		tickets.put(ticket.getId(), ticket);
		ticketSequence++;
		return ticket.getId();
	}

}
