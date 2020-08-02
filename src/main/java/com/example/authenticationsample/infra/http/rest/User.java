package com.example.authenticationsample.infra.http.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class User {
    @GetMapping
    public String list(){
        return "listAll";
    }

    @GetMapping("/{id}")
    public String listOne(@PathVariable("id") String id){
        return "listOne "+id;
    }

    @PostMapping
    public String create(){
        return "userCreated";
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
