package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.Client;
import com.nicolas.helpdesk.domain.Person;
import com.nicolas.helpdesk.domain.dtos.ClientDTO;
import com.nicolas.helpdesk.repositories.ClientRepository;
import com.nicolas.helpdesk.repositories.PersonRepository;
import com.nicolas.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.nicolas.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder encoder;

    public ClientService(ClientRepository clientRepository, PersonRepository personRepository, BCryptPasswordEncoder encoder) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    public Client findById(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object was not found! Id: " + id));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client create(ClientDTO clientDTO) {
        clientDTO.setId(null);
        clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
        validateByCpfAndEmail(clientDTO);
        Client newClient = new Client(clientDTO);
        return clientRepository.save(newClient);
    }

    public Client update(Integer id, ClientDTO clientDTO) {
        clientDTO.setId(id);
        Client oldObj = findById(id);
        validateByCpfAndEmail(clientDTO);
        oldObj = new Client(clientDTO);
        return clientRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Client obj = findById(id);
        if (obj.getTickets().size() > 0) {
            throw new DataIntegrityViolationException("This client has some tickets in his name!");
        }
        clientRepository.deleteById(id);
    }

    private void validateByCpfAndEmail(ClientDTO clientDTO) {
        Optional<Person> obj = personRepository.findByCpf(clientDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already exists in the system");
        }

        obj = personRepository.findByEmail(clientDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != clientDTO.getId()) {
            throw new DataIntegrityViolationException("Email already exists in the system");
        }
    }


}
