package com.softm.raziel.example;

import com.softm.raziel.payload.ContentTicket;
import com.softm.raziel.repo.TicketRepository;

import java.util.HashMap;

public class InMemoryTicketRepository implements TicketRepository {
    final HashMap<Long, ContentTicket> tickets = new HashMap<Long, ContentTicket>();
    Long ticketSequence = Long.valueOf(1);

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
