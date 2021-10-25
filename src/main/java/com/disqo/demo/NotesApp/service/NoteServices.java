package com.disqo.demo.NotesApp.service;

import com.disqo.demo.NotesApp.model.Note;
import com.disqo.demo.NotesApp.model.User;
import com.disqo.demo.NotesApp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServices {
    @Autowired
    private NoteRepository repo;

    public List<Note> listAll() {
        return repo.findAll();
    }

    public List<Note> listUserNotes(User user) {
        return repo.findUserNotes(user);
    }

    public Note save(Note note) { return repo.save(note); }

    public void delete(Integer id) { repo.deleteById(id); };

    public Optional<Note> get(Integer id) { return repo.findById(id); };

}
