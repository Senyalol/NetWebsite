import React from 'react';
import ReactDOM from 'react-dom/client';
import AutPage from './aut';
import StartPage from './start';
import DevPage from './device';
import LogPage from './log';
import ConPage from './connect';
import UserPage from './users';
import RegPage from './reg';
import SessionsPage from './sessions';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
  <Routes>
  <Route path="/" element={<AutPage />}/>
  <Route path="/register" element={<RegPage />}/>
  <Route path="/start/:id" element={<StartPage />}/>
  <Route path="/devices/:id" element={<DevPage />}/>
  <Route path="/logs/:id" element={<LogPage />}/>
  {/* <Route path="/connections/:id" element={<ConPage />}/> */}
  <Route path="/sessions/:id" element={<SessionsPage />}/>
  <Route path="/users/:id" element={<UserPage />}/>
  </Routes>
  </BrowserRouter>
);


reportWebVitals();
