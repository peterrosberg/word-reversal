import logo from './logo.svg';
import './App.css';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          World class word reversal
        </p>
      </header>
      <div className="Form-container">
        <p>
            This is awesome
        </p>
        <Form>
          <Form.Group controlId="formSentence">
            <Form.Control as="textarea" rows={3} placeholder="Enter sentence to reverse" />
          </Form.Group>
          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default App;
