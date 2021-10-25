package com.disqo.demo.NotesApp.repository;

import com.disqo.demo.NotesApp.model.Note;
import com.disqo.demo.NotesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("SELECT n FROM Note n WHERE n.user = :user")
    public List<Note> findUserNotes(@Param("user") User user);
}
