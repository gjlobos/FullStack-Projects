package com.gjlobos.notesspabackend.service;

import com.gjlobos.notesspabackend.dto.NoteDTO;
import com.gjlobos.notesspabackend.model.Category;
import com.gjlobos.notesspabackend.model.Note;
import com.gjlobos.notesspabackend.repository.CategoryRepository;
import com.gjlobos.notesspabackend.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
        Note note = new Note();
        updateNoteFromDTO(note, noteDTO);
        return convertToDTO(noteRepository.save(note));
    }

    @Override
    public NoteDTO updateNote(Long noteId, NoteDTO noteDTO) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if (optionalNote.isPresent()) {
            Note existingNote = optionalNote.get();
            updateNoteFromDTO(existingNote, noteDTO);
            return convertToDTO(noteRepository.save(existingNote));
        } else {
            throw new NoSuchElementException("Note with ID " + noteId + " not found");
        }
    }

    @Override
    public void deleteNote(Long noteId) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public void archiveNote(Long noteId) {
        updateArchiveState(noteId, true);
    }

    @Override
    public void unarchiveNote(Long noteId) {
        updateArchiveState(noteId, false);
    }

    @Override
    public List<NoteDTO> getActiveNotes() {
        return noteRepository.findByIsArchivedFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getArchivedNotes() {
        return noteRepository.findByIsArchivedTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getNotesByCategory(Long categoryId) {
        return noteRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void updateNoteFromDTO(Note note, NoteDTO noteDTO) {
        BeanUtils.copyProperties(noteDTO, note, "id", "dateLastEdit", "isArchived");
        note.setDateLastEdit(new Date());
        note.setArchived(noteDTO.isArchived());

        Set<Category> categories = noteDTO.getCategoryIds().stream()
                .map(categoryId -> categoryRepository.findById(categoryId).orElse(null))
                .filter(category -> category != null)
                .collect(Collectors.toSet());

        note.setCategories(categories);
    }

    private void updateArchiveState(Long noteId, boolean isArchived) {
        noteRepository.findById(noteId).ifPresent(note -> {
            note.setArchived(isArchived);
            noteRepository.save(note);
        });
    }

    private NoteDTO convertToDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO, "categories");
        noteDTO.setCategoryIds(note.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
        return noteDTO;
    }
}
