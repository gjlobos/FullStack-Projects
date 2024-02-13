import React, { useState } from 'react';

function NotesFilter({ categories, selectedCategoryId, onCategoryChange }) {
  const [tempSelectedCategoryId, setTempSelectedCategoryId] = useState(selectedCategoryId);

  const handleCategoryChange = (e) => {
    const categoryId = parseInt(e.target.value, 10);
    setTempSelectedCategoryId(categoryId);
  };

  const handleConfirm = () => {
    // Confirm the category selection and update the parent state
    onCategoryChange(tempSelectedCategoryId);
  };

  const handleCancel = () => {
    // Cancel the category selection and reset to the previous state
    setTempSelectedCategoryId(selectedCategoryId);
    onCategoryChange(selectedCategoryId);
  };

  return (
    <div>
      <label>Filter by Category:</label>
      <select value={tempSelectedCategoryId || ''} onChange={handleCategoryChange}>
        <option value="">All Categories</option>
        {categories.map((category) => (
          <option key={category.id} value={category.id}>
            {category.name}
          </option>
        ))}
      </select>

      <button onClick={handleConfirm}>Confirm</button>
      <button onClick={handleCancel}>Cancel</button>
    </div>
  );
}

export default NotesFilter;