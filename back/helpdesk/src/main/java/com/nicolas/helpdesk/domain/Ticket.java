package com.nicolas.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nicolas.helpdesk.domain.enums.Priority;
import com.nicolas.helpdesk.domain.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closeDate;
    private Priority priority;
    private Status status;
    private String title;
    private String observations;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Ticket(Integer id, Priority priority, Status status, String title, String observations, Technician technician, Client client) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.observations = observations;
        this.technician = technician;
        this.client = client;
    }

    public Ticket() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
