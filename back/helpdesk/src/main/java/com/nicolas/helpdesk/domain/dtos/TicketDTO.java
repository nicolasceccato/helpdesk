package com.nicolas.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nicolas.helpdesk.domain.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closeDate;
    @NotNull(message = "The field Priority is required")
    private Integer priority;
    @NotNull(message = "The field Status is required")
    private Integer status;
    @NotBlank(message = "The field Title is required")
    private String title;
    @NotBlank(message = "The field Observations is required")
    private String observations;
    @NotNull(message = "The field Technician is required")
    private Integer technician;
    @NotNull(message = "The field Client is required")
    private Integer client;
    private String technicianName;
    private String clientName;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.openDate = ticket.getOpenDate();
        this.closeDate = ticket.getCloseDate();
        this.priority = ticket.getPriority().getCode();
        this.status = ticket.getStatus().getCode();
        this.title = ticket.getTitle();
        this.observations = ticket.getObservations();
        this.technician = ticket.getTechnician().getId();
        this.client = ticket.getClient().getId();
        this.technicianName = ticket.getTechnician().getName();
        this.clientName = ticket.getClient().getName();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
