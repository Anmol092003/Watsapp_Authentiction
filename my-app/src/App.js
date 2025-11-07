import React, { useState } from 'react';
import axios from 'axios';

function App() {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [status, setStatus] = useState('Not authenticated');
  const phoneNumber = '918851611801'; // Static for now

  const handleLogin = async () => {
    if (name !== password) {
      alert('Credentials are invalid');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/send-message', null, {
        params: { to: phoneNumber }
      });

      if (response.data.includes('messages')) {
        setStatus('Authenticated via WhatsApp');
      } else {
        setStatus('Message sent, awaiting confirmation');
      }
    } catch (error) {
      console.error('Error:', error);
      setStatus('Authentication failed');
    }
  };

  return (
    <div style={{ maxWidth: '300px', margin: 'auto' }}>
      <h3>Login</h3>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        style={{ display: 'block', marginBottom: '10px', width: '100%' }}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={{ display: 'block', marginBottom: '10px', width: '100%' }}
      />
      <button onClick={handleLogin}>Login</button>
      <p>Status: {status}</p>
    </div>
  );
}
export default App;
