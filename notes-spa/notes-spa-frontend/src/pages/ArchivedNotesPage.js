import React, { useState, useEffect } from 'react';
import ArchivedNote from '../components/ArchivedNote';
import axios from 'axios'; // You may need to install axios: npm install axios

function ArchivedNotesPage() {
  const [archivedNotes, setArchivedNotes] = useState([]);

  useEffect(() => {
    // Fetch archived notes from the backend API
    fetchArchivedNotes();
  }, []);

  const fetchArchivedNotes = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/notes/archived');
      setArchivedNotes(response.data);
    } catch (error) {
      console.error('Error fetching archived notes:', error);
    }
  };

  const handleUnarchiveNote = async (noteId) => {
    try {
      await axios.post(`http://localhost:8080/api/notes/${noteId}/unarchive`);
      // Refetch archived notes after unarchiving
      fetchArchivedNotes();
    } catch (error) {
      console.error('Error unarchiving note:', error);
    }
  };

  return (
    <div>
      <h1>Archived Notes</h1>

      {/* Render archived notes */}
      {archivedNotes.map((note) => (
        <ArchivedNote
          key={note.id}
          note={note}
          onUnarchive={() => handleUnarchiveNote(note.id)}
        />
      ))}
    </div>
  );
}

export default ArchivedNotesPage;