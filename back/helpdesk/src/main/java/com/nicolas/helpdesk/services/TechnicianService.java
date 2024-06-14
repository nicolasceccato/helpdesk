package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Person;
import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.dtos.TechnicianDTO;
import com.nicolas.helpdesk.repositories.PersonRepository;
import com.nicolas.helpdesk.repositories.TechnicianRepository;
import com.nicolas.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.nicolas.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    private final PersonRepository personRepository;

    public TechnicianService(TechnicianRepository technicianRepository, PersonRepository personRepository) {
        this.technicianRepository = technicianRepository;
        this.personRepository = personRepository;
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
        validateByCpfAndEmail(technicianDTO);
        Technician newTechnician = new Technician(technicianDTO);
        return technicianRepository.save(newTechnician);
    }

    public Technician update(Integer id, TechnicianDTO technicianDTO) {
        technicianDTO.setId(id);
        Technician oldObj = findById(id);
        validateByCpfAndEmail(technicianDTO);
        oldObj = new Technician(technicianDTO);
        return technicianRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Technician obj = findById(id);
        if (obj.getTickets().size() > 0) {
            throw new DataIntegrityViolationException("This technician has some tickets in his name!");
        }
        technicianRepository.deleteById(id);
    }

    private void validateByCpfAndEmail(TechnicianDTO technicianDTO) {
        Optional<Person> obj = personRepository.findByCpf(technicianDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already exists in the system");
        }

        obj = personRepository.findByEmail(technicianDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("Email already exists in the system");
        }
    }


}
