// ActiveNote.js

import React from 'react';
import axios from 'axios'; // You may need to install axios: npm install axios

function ActiveNote({ note, onArchive, onEdit, onDelete }) {
  const handleArchive = async () => {
    try {
      await axios.post(`http://localhost:8080/api/notes/${note.id}/archive`);
      // Trigger the parent callback to refetch active notes
      onArchive();
    } catch (error) {
      console.error('Error archiving note:', error);
    }
  };

  const handleEdit = () => {
    // Trigger the parent callback to open the Create/Edit modal with note data
    onEdit(note);
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/notes/${note.id}`);
      // Trigger the parent callback to refetch active notes
      onDelete();
    } catch (error) {
      console.error('Error deleting note:', error);
    }
  };

  return (
    <div className="note">
      <img src={require('../images/vecteezy_notes-notepad-notebook-memo-diary-paper-line-icon_4853275.jpg')} alt="Note Image" />
      <h2>{note.title}</h2>
      <p>Last Edited: {note.dateLastEdit}</p>

      <div className="icons">
        <button onClick={handleArchive}>Archive</button>
        <button onClick={handleEdit}>Edit</button>
        <button onClick={handleDelete}>Delete</button>
      </div>
    </div>
  );
}

export default ActiveNote;