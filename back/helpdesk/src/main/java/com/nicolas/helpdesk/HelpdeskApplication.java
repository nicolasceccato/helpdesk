package com.nicolas.helpdesk;

import com.nicolas.helpdesk.domain.Client;
import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.Ticket;
import com.nicolas.helpdesk.domain.enums.Priority;
import com.nicolas.helpdesk.domain.enums.Profile;
import com.nicolas.helpdesk.domain.enums.Status;
import com.nicolas.helpdesk.repositories.ClientRepository;
import com.nicolas.helpdesk.repositories.TechnicianRepository;
import com.nicolas.helpdesk.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Technician t1 = new Technician(null, "Valdir Cesar", "63653230268", "valdir@mail.com", "123");
        t1.addProfile(Profile.ADMIN);

        Client c1 = new Client(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

        Ticket ticket = new Ticket(null, Priority.MEDIUM, Status.IN_PROGRESS, "Chamado 01", "Primeiro chamado", t1, c1);

        technicianRepository.saveAll(Arrays.asList(t1));

        clientRepository.saveAll(Arrays.asList(c1));

        ticketRepository.saveAll(Arrays.asList(ticket));
    }
}
