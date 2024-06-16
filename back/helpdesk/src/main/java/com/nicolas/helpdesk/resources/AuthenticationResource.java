package com.nicolas.helpdesk.resources;

import com.nicolas.helpdesk.domain.dtos.CredencialsDTO;
import com.nicolas.helpdesk.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    public AuthenticationResource(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody CredencialsDTO credencialsDTO) {

        var userAuthToken = new UsernamePasswordAuthenticationToken(credencialsDTO.getEmail(), credencialsDTO.getPassword());
        return authenticationService.getToken(credencialsDTO);
    }


}
