package com.nicolas.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicolas.helpdesk.domain.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
