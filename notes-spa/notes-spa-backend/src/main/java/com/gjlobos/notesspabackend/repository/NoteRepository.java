package com.gjlobos.notesspabackend.repository;

import com.gjlobos.notesspabackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByIsArchivedFalse();

    List<Note> findByIsArchivedTrue();

    @Query("SELECT n FROM Note n JOIN n.categories c WHERE c.id = :categoryId")
    List<Note> findByCategoryId(@Param("categoryId") Long categoryId);
}
