package com.example.authenticationsample.infra.http.rest;

import com.example.authenticationsample.domain.user.exceptions.InvalidParameters;
import com.example.authenticationsample.domain.user.exceptions.UserInvalidEmail;
import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.domain.user.providers.UserProvider;
import com.example.authenticationsample.infra.http.dtos.errors.Invalid;
import com.example.authenticationsample.infra.http.dtos.user.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserProvider userProvider;

    @GetMapping
    public String list(){
        return "listAll";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listOne(@PathVariable("id") String id){

        try {
            UUID uuid = UUID.fromString(id);

            if(uuid == null) {
                return ResponseEntity.badRequest().body(new Invalid("Id inválido"));
            }

            User user = this.userProvider.findById(uuid);

            return ResponseEntity.ok(user);

        } catch (UserNotFound e) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDTO request){

        if(!request.getPassword().equals(request.getPassword_confirm())) {
            return ResponseEntity.badRequest().body(new Invalid("Confirmação de senha inválida."));
        }

        User user =  request.parse();

        try {
            this.userProvider.saveOnDB(user);
        } catch (UserInvalidEmail e) {
            return ResponseEntity.badRequest().body(new Invalid(e.getMessage()));
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping
    public String update(){
        return "userUpdate";
    }

    @DeleteMapping
    public String delete() {
        return "Delete";
    }
}
