package com.nicolas.helpdesk.config;

import com.nicolas.helpdesk.domain.Person;
import com.nicolas.helpdesk.repositories.PersonRepository;
import com.nicolas.helpdesk.security.UserSS;
import com.nicolas.helpdesk.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private AuthenticationService authenticationService;
    private PersonRepository personRepository;

    public SecurityFilter(AuthenticationService authenticationService, PersonRepository personRepository) {
        this.authenticationService = authenticationService;
        this.personRepository = personRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenHeader(request);
        if (token != null) {
            String login = authenticationService.validTokenJwt(token);
            Optional<Person> person = personRepository.findByEmail(login);

            UserSS user = new UserSS(person.get().getId(), person.get().getEmail(), person.get().getPassword(), person.get().getProfiles());

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String getTokenHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }
        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return null;
        }
        return authHeader.split(" ")[1];
    }
}
