package com.nicolas.helpdesk.resources;

import com.nicolas.helpdesk.domain.Technician;
import com.nicolas.helpdesk.domain.dtos.TechnicianDTO;
import com.nicolas.helpdesk.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianResource {

    private final TechnicianService service;

    public TechnicianResource(TechnicianService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        Technician obj = service.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        List<Technician> listOfTechnicians = service.findAll();
        List<TechnicianDTO> listDTO = listOfTechnicians.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO technicianDTO) {
        Technician newTechnician = service.create(technicianDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{/id}").buildAndExpand(newTechnician.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> upadte(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO technicianDTO) {
        Technician obj = service.update(id, technicianDTO);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
