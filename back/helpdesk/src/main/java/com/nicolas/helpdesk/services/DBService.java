package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Client;
import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.Ticket;
import com.nicolas.helpdesk.domain.enums.Priority;
import com.nicolas.helpdesk.domain.enums.Profile;
import com.nicolas.helpdesk.domain.enums.Status;
import com.nicolas.helpdesk.repositories.ClientRepository;
import com.nicolas.helpdesk.repositories.TechnicianRepository;
import com.nicolas.helpdesk.repositories.TicketRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    private final TechnicianRepository technicianRepository;

    private final ClientRepository clientRepository;

    private final TicketRepository ticketRepository;

    private final BCryptPasswordEncoder encoder;

    public DBService(TechnicianRepository technicianRepository, ClientRepository clientRepository, TicketRepository ticketRepository, BCryptPasswordEncoder encoder) {
        this.technicianRepository = technicianRepository;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
        this.encoder = encoder;
    }

    public void initDB() {
        Technician t1 = new Technician(null, "Valdir Cesar", "63653230268", "valdir@mail.com", encoder.encode("123456789098765432123456789098765432"));
        t1.addProfile(Profile.ADMIN);

        Client c1 = new Client(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", encoder.encode("123"));

        Ticket ticket = new Ticket(null, Priority.MEDIUM, Status.IN_PROGRESS, "Chamado 01", "Primeiro chamado", t1, c1);

        technicianRepository.saveAll(Arrays.asList(t1));

        clientRepository.saveAll(Arrays.asList(c1));

        ticketRepository.saveAll(Arrays.asList(ticket));
    }
}
