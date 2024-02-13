import React, { useState, useEffect } from 'react';
import axios from 'axios'; // You may need to install axios: npm install axios

function CreateEditModal({ onClose, onSave, categories }) {
  const [noteData, setNoteData] = useState({
    title: '',
    content: '',
    categoryIds: [],
  });

  useEffect(() => {
    // Any additional logic when the modal opens, if needed
  }, []);

  const handleInputChange = (e) => {
    setNoteData({
      ...noteData,
      [e.target.name]: e.target.value,
    });
  };

  const handleCategoryChange = (e) => {
    const categoryId = parseInt(e.target.value, 10);
    setNoteData({
      ...noteData,
      categoryIds: noteData.categoryIds.includes(categoryId)
        ? noteData.categoryIds.filter((id) => id !== categoryId)
        : [...noteData.categoryIds, categoryId],
    });
  };

  const handleSave = async () => {
    try {
      if (noteData.id) {
        // Edit existing note
        await axios.put(`http://localhost:8080/api/notes/${noteData.id}`, noteData);
      } else {
        // Create new note
        await axios.post('http://localhost:8080/api/notes', noteData);
      }

      // Close the modal and trigger a refetch of active notes
      onClose();
      onSave();
    } catch (error) {
      console.error('Error saving note:', error);
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h1>Create/Edit Notes</h1>
        <label>Title:</label>
        <input type="text" name="title" value={noteData.title} onChange={handleInputChange} />

        <label>Content:</label>
        <textarea name="content" value={noteData.content} onChange={handleInputChange} />

        <label>Categories:</label>
        <select multiple name="categoryIds" onChange={handleCategoryChange}>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>

        <button onClick={handleSave}>Save</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}

export default CreateEditModal;