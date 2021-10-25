package com.disqo.demo.NotesApp.controller;

import com.disqo.demo.NotesApp.model.User;
import com.disqo.demo.NotesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

}
