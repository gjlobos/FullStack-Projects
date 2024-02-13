package com.gjlobos.notesspabackend.service;

import com.gjlobos.notesspabackend.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO createNote (NoteDTO noteDTO);
    NoteDTO updateNote(Long noteId, NoteDTO noteDTO);

    void deleteNote(Long noteId);

    void archiveNote(Long noteId);

    void unarchiveNote(Long noteId);

    List<NoteDTO> getActiveNotes();

    List<NoteDTO> getArchivedNotes();

    List<NoteDTO> getNotesByCategory(Long categoryId);

}
