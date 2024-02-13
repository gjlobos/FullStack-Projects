import React, { useState, useEffect } from 'react';
import ActiveNote from '../components/ActiveNote';
import CreateEditModal from '../components/CreateEditModal';
import NotesFilter from '../components/NotesFilter';
import axios from 'axios'; // You may need to install axios: npm install axios

function ActiveNotesPage() {
  const [activeNotes, setActiveNotes] = useState([]);
  const [categories, setCategories] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedCategoryId, setSelectedCategoryId] = useState(null);

  useEffect(() => {
    // Fetch active notes and categories from the backend API
    fetchActiveNotes();
    fetchCategories();
  }, []);

  const fetchActiveNotes = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/notes/active');
      setActiveNotes(response.data);
    } catch (error) {
      console.error('Error fetching active notes:', error);
    }
  };

  const fetchCategories = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/categories');
      setCategories(response.data);
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };

  const handleCreateNote = () => {
    // Logic to show Create/Edit modal
    setShowModal(true);
  };

  const handleCategoryChange = (categoryId) => {
    // Logic to filter notes by category
    setSelectedCategoryId(categoryId);
  };

  return (
    <div>
      <h1>Active Notes</h1>
      <button onClick={handleCreateNote}>Create new Note</button>

      {/* Filter by Category */}
      <NotesFilter
        categories={categories}
        selectedCategoryId={selectedCategoryId}
        onCategoryChange={handleCategoryChange}
      />

      {/* Render active notes, applying category filter */}
      {activeNotes
        .filter((note) => !selectedCategoryId || note.categoryIds.includes(selectedCategoryId))
        .map((note) => (
          <ActiveNote key={note.id} note={note} />
        ))}

      {/* Create/Edit Modal */}
      {showModal && (
        <CreateEditModal
          onClose={() => setShowModal(false)}
          onSave={fetchActiveNotes} // Refetch active notes after saving
          categories={categories}
        />
      )}
    </div>
  );
}

export default ActiveNotesPage;