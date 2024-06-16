package com.nicolas.helpdesk.services;

import com.nicolas.helpdesk.domain.dtos.CredencialsDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    public String getToken(CredencialsDTO credencialsDTO);

    public String validTokenJwt(String token);
}
