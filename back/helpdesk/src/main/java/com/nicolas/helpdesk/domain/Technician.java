package com.nicolas.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicolas.helpdesk.domain.dtos.TechnicianDTO;
import com.nicolas.helpdesk.domain.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Technician extends Person {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician() {
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.CLIENT);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.CLIENT);
    }

    public Technician(TechnicianDTO technicianDTO) {
        this.id = technicianDTO.getId();
        this.name = technicianDTO.getName();
        this.cpf = technicianDTO.getCpf();
        this.email = technicianDTO.getEmail();
        this.password = technicianDTO.getPassword();
        this.profiles = technicianDTO.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.criationDate = technicianDTO.getCriationDate();
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.CLIENT);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
