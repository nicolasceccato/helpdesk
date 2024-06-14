package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Client;
import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.Ticket;
import com.nicolas.helpdesk.domain.dtos.TicketDTO;
import com.nicolas.helpdesk.domain.enums.Priority;
import com.nicolas.helpdesk.domain.enums.Status;
import com.nicolas.helpdesk.repositories.TicketRepository;
import com.nicolas.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TechnicianService technicianService;
    private final ClientService clientService;

    public TicketService(TicketRepository ticketRepository, TechnicianService technicianService, ClientService clientService) {
        this.ticketRepository = ticketRepository;
        this.technicianService = technicianService;
        this.clientService = clientService;
    }

    public Ticket findById(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Ticket not found! Id: " + id));
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket create(TicketDTO ticketDTO) {
        return ticketRepository.save(newTicket(ticketDTO));
    }

    private Ticket newTicket(TicketDTO obj) {
        Technician technician = technicianService.findById(obj.getTechnician());
        Client client = clientService.findById(obj.getClient());

        Ticket ticket = new Ticket();
        if (obj.getId() != null) {
            ticket.setId(obj.getId());
        }
        ticket.setTechnician(technician);
        ticket.setClient(client);
        ticket.setPriority(Priority.toEnum(obj.getPriority()));
        ticket.setStatus(Status.toEnum(obj.getStatus()));
        ticket.setTitle(obj.getTitle());
        ticket.setObservations(obj.getObservations());
        return ticket;
    }

}
