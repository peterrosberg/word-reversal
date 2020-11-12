import React from 'react';
import { Reversal } from './features/reversal/Reversal';
import { History } from './features/history/History';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Wordsmith inc.</h1>
        <p>Reverse the words of any sentence</p>
        <Reversal />
        <History />
      </header>
    </div>
  );
}

export default App;
