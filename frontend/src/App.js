import React from 'react';
import logo from './logo.svg';
import { Reversal } from './features/reversal/Reversal';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Wordsmith inc.</h1>
        <p>Reverse the words of any sentence</p>
        <Reversal />
      </header>
    </div>
  );
}

export default App;
