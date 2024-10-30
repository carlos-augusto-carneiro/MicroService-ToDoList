package com.microservice.todo.portfolio.list.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.todo.portfolio.list.dto.LoginDTOReponse;
import com.microservice.todo.portfolio.list.dto.LoginDTORequest;
import com.microservice.todo.portfolio.list.service.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<LoginDTOReponse> login(@RequestBody LoginDTORequest loginDTO){
        var user = userService.findByEmail(loginDTO.email());

        if(user.isEmpty() || !user.get().isLoginCorrect(loginDTO, passwordEncoder)){
            throw new BadCredentialsException("Email ou senha não encontrado");
        }
        var now = Instant.now();
        var expiresIn = 3000L;
        var claims = JwtClaimsSet.builder()
                                .issuer("API USER")
                                .subject(user.get().getId().toString())
                                .claim("Role", user.get().getRole().toString())
                                .claim("id", user.get().getId())
                                .expiresAt(now.plusSeconds(expiresIn))
                                .issuedAt(now)
                                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginDTOReponse(jwtValue, expiresIn));
    }   
}
