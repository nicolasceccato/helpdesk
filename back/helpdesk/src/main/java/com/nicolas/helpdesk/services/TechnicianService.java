package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public Technician findById(Integer id){
        Optional<Technician> obj = technicianRepository.findById(id);
        return obj.orElse(null);
    }
}