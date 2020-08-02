package com.example.authenticationsample.infra.http.rest;

import com.example.authenticationsample.domain.user.providers.SessionProvider;
import com.example.authenticationsample.infra.authentication.AuthenticationProvider;
import com.example.authenticationsample.infra.http.dtos.session.CreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionRestController {

    @Autowired
    private SessionProvider sessionProvider;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateDTO request) {

        String token = null;
        try {
            token = sessionProvider.generateToken(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(token);
    }

    @PutMapping("/refresh")
    public String refresh() {
        return "refreshed";
    }

    @DeleteMapping
    public String delete() {
        return "deleted";
    }
}
