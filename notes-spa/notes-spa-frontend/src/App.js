import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ActiveNotesPage from './pages/ActiveNotesPage';
//import ArchivedNotesPage from './pages/ArchivedNotesPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" exact component={ActiveNotesPage} />
        {/*<Route path="/archived" component={ArchivedNotesPage} />*/}
      </Routes>
    </Router>
  );
}

export default App;
