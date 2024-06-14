package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Ticket;
import com.nicolas.helpdesk.repositories.TicketRepository;
import com.nicolas.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Ticket not found! Id: " + id));
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
