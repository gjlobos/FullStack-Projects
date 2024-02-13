package com.gjlobos.notesspabackend.controller;

import com.gjlobos.notesspabackend.dto.NoteDTO;
import com.gjlobos.notesspabackend.service.NoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
//@Api( = "Note Management System", description = "Operations pertaining to notes in Note Management System")

public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "Create a new Note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new note"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO) {
        return new ResponseEntity<>(noteService.createNote(noteDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the note"),
            @ApiResponse(responseCode = "404", description = "Note not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> updateNote(@Parameter(description = "ID of the note to be updated", required = true) @PathVariable Long noteId,
                                              @RequestBody NoteDTO noteDTO) {
        NoteDTO updatedNote = noteService.updateNote(noteId, noteDTO);
        if (updatedNote != null) {
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an existing note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the note"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@Parameter(description = "ID of the note to be deleted", required = true) @PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Archive a note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully archived the note"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @PostMapping("/{noteId}/archive")
    public ResponseEntity<Void> archiveNote(@Parameter(description = "ID of the note to be archived", required = true) @PathVariable Long noteId) {
        noteService.archiveNote(noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Unarchive a note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully unarchived the note"),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @PostMapping("/{noteId}/unarchive")
    public ResponseEntity<Void> unarchiveNote(@Parameter(description = "ID of the note to be unarchived", required = true) @PathVariable Long noteId) {
        noteService.unarchiveNote(noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "View a list of active notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of active notes"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("/active")
    public ResponseEntity<List<NoteDTO>> getActiveNotes() {
        return new ResponseEntity<>(noteService.getActiveNotes(), HttpStatus.OK);
    }

    @Operation(summary = "View a list of archived notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of archived notes"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("/archived")
    public ResponseEntity<List<NoteDTO>> getArchivedNotes() {
        return new ResponseEntity<>(noteService.getArchivedNotes(), HttpStatus.OK);
    }

    @Operation(summary = "View a list of notes by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of notes by category"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<NoteDTO>> getNotesByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(noteService.getNotesByCategory(categoryId), HttpStatus.OK);
    }
}
