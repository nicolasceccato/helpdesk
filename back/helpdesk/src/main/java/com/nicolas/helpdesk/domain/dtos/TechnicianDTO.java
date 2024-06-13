package com.nicolas.helpdesk.domain.dtos;

import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.enums.Profile;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotBlank(message = "The field Name is required")
    protected String name;

    @NotBlank(message = "The field Cpf is required")
    protected String cpf;

    @NotBlank(message = "The field Email is required")
    protected String email;

    @NotBlank(message = "The field Password is required")
    protected String password;
    private Set<Integer> profiles = new HashSet<>();
    protected LocalDate criationDate = LocalDate.now();

    public TechnicianDTO() {
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.CLIENT);
    }

    public TechnicianDTO(Technician technician) {
        this.id = technician.getId();
        this.name = technician.getName();
        this.cpf = technician.getCpf();
        this.email = technician.getEmail();
        this.password = technician.getPassword();
        this.profiles = technician.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.criationDate = technician.getCriationDate();
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.CLIENT);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCode());
    }

    public LocalDate getCriationDate() {
        return criationDate;
    }

    public void setCriationDate(LocalDate criationDate) {
        this.criationDate = criationDate;
    }
}
