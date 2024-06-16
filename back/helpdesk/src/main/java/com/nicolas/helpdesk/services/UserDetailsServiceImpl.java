package com.nicolas.helpdesk.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nicolas.helpdesk.domain.Person;
import com.nicolas.helpdesk.domain.dtos.CredencialsDTO;
import com.nicolas.helpdesk.repositories.PersonRepository;
import com.nicolas.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements AuthenticationService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
    private PersonRepository repository;

    public UserDetailsServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = repository.findByEmail(email);

        if (user.isPresent()) {
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }

    @Override
    public String getToken(CredencialsDTO credencialsDTO) {
        Optional<Person> person = repository.findByEmail(credencialsDTO.getEmail());
        return generateTokenJwt(person);
    }

    public String generateTokenJwt(Optional<Person> person) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("helpdesk")
                    .withSubject(person.get().getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error on creating token" + e.getMessage());
        }
    }

    public String validTokenJwt(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("helpdesk")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
