package com.disqo.demo.NotesApp.service;

import java.util.List;

import javax.transaction.Transactional;

import com.disqo.demo.NotesApp.model.User;
import com.disqo.demo.NotesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }
}
