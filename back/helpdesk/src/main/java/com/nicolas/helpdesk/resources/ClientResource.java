package com.nicolas.helpdesk.resources;

import com.nicolas.helpdesk.domain.Client;
import com.nicolas.helpdesk.domain.dtos.ClientDTO;
import com.nicolas.helpdesk.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    private final ClientService service;

    public ClientResource(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id) {
        Client obj = service.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> listOfClients = service.findAll();
        List<ClientDTO> listDTO = listOfClients.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO technicianDTO) {
        Client newClient = service.create(technicianDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{/id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> upadte(@PathVariable Integer id, @Valid @RequestBody ClientDTO technicianDTO) {
        Client obj = service.update(id, technicianDTO);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
