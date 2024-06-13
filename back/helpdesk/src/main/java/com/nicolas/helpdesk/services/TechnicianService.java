package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.dtos.TechnicianDTO;
import com.nicolas.helpdesk.repositories.TechnicianRepository;
import com.nicolas.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public Technician findById(Integer id) {
        Optional<Technician> obj = technicianRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object was not found! Id: " + id));
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        technicianDTO.setId(null);
        Technician newTechnician = new Technician(technicianDTO);
        return technicianRepository.save(newTechnician);

    }
}
