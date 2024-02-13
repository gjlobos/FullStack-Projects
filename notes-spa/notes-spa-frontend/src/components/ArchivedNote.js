import React from 'react';
import axios from 'axios'; // You may need to install axios: npm install axios

function ArchivedNote({ note, onUnarchive }) {
  const handleUnarchive = async () => {
    try {
      await axios.post(`http://localhost:8080/api/notes/${note.id}/unarchive`);
      // Trigger the parent callback to refetch archived notes
      onUnarchive();
    } catch (error) {
      console.error('Error unarchiving note:', error);
    }
  };

  return (
    <div className="note">
      <img src={require('../images/vecteezy_notes-notepad-notebook-memo-diary-paper-line-icon_4853275.jpg')} alt="Note Image" />
      <h2>{note.title}</h2>
      <p>Last Edited: {note.dateLastEdit}</p>

      <div className="icons">
        <button onClick={handleUnarchive}>Unarchive</button>
        {/* You can add additional options if needed */}
      </div>
    </div>
  );
}

export default ArchivedNote;