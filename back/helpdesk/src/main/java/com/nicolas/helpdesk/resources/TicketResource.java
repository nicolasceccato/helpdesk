package com.nicolas.helpdesk.resources;

import com.nicolas.helpdesk.domain.Ticket;
import com.nicolas.helpdesk.domain.dtos.TicketDTO;
import com.nicolas.helpdesk.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tickets")
public class TicketResource {

    private final TicketService ticketService;

    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable Integer id) {
        Ticket obj = ticketService.findById(id);
        return ResponseEntity.ok().body(new TicketDTO(obj));
    }
}
