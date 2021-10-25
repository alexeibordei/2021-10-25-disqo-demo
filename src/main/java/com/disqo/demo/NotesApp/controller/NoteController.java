package com.disqo.demo.NotesApp.controller;

import com.disqo.demo.NotesApp.model.Note;
import com.disqo.demo.NotesApp.security.UserDetailsImpl;
import com.disqo.demo.NotesApp.service.NoteServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteServices service;

    Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping("/notes")
    public List<Note> list(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.listUserNotes(userDetails.getUser());
    }

    @GetMapping("notes/all")
    public List<Note> listAll() {
        return service.listAll();
    }

    @PostMapping("/notes/save")
    public Note addNode(@RequestBody Note note,
                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        note.setUser(userDetails.getUser());
        return service.save(note);
    }

    @PostMapping("/notes/update/{id}")
    public ResponseEntity<?> updateNote(@RequestBody Note note, @PathVariable Integer id,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Optional<Note> existNote = service.get(id);
            if (existNote.get().getUser().getId().equals(userDetails.getUser().getId())) {
                existNote.get().setTitle(note.getTitle());
                existNote.get().setContent(note.getContent());
                service.save(existNote.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Optional<Note> existNote = service.get(id);
            if (existNote.get().getUser().getId().equals(userDetails.getUser().getId())) {
                service.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
