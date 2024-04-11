import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Login from './pages/LoginPage/LoginPage.jsx';
import Home from './pages/HomePage/HomePage.jsx';
import Header from './components/Header/Header.jsx';
import Dashboard from './pages/FunctionPages/Dashboard';
import Biblioteca from './pages/FunctionPages/Biblioteca';
import Atendimento from './pages/FunctionPages/Atendimento';
import Matricula from './pages/FunctionPages/Matricula';
import Avaliacao from './pages/FunctionPages/Avaliacao';
import Cursos from './pages/FunctionPages/Cursos';
// import Home from './components/Home/home-tailwind.jsx';


/* function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Estado de autenticação

  // Função para fazer login
  const handleLogin = () => {
    // Adicionar lógica de autenticação
    // Por enquanto, apenas definimos isLoggedIn como true
    setIsLoggedIn(true);
  };

  // Função para fazer logout
  const handleLogout = () => {
    setIsLoggedIn(false);
  }; */

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/*" element={<AppLayout />} />
      </Routes>
    </Router>
  );
};

const AppLayout = () => {
  const location = useLocation();

  // Verifica se a rota atual é diferente de "/login"
  if (location.pathname !== "/login") {
    return (
      <>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/biblioteca" element={<Biblioteca />} />
          <Route path="/atendimento" element={<Atendimento />} />
          <Route path="/matricula" element={<Matricula />} />
          <Route path="/avaliacao" element={<Avaliacao />} />
          <Route path="/cursos" element={<Cursos />} />
        </Routes>
      </>
    );
  } else {
    return (
      <Routes>
        <Route path="/login" element={<Login />} />
      </Routes>
    );
  }
};

export default App;
