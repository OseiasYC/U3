import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
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


function App() {
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
  };

  return (
    <>
      <Header />
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" Component={Dashboard} />
          <Route path="/biblioteca" Component={Biblioteca} />
          <Route path="/atendimento" Component={Atendimento} />
          <Route path="/matricula" Component={Matricula} />
          <Route path="/avaliacao" Component={Avaliacao} />
          <Route path="/cursos" Component={Cursos} />
          {/* <Route path="/login" element={<Login onLogin={handleLogin} />} />
        {isLoggedIn ? (
          <Route path="/home" element={<Home onLogout={handleLogout} />} />
        ) : (
          // Se não estiver logado, redireciona para a página de login
          <Route path="*" element={<Navigate to="/login" replace />} />
        )} */}
        </Routes>
      </Router>
    </>
  );
}

export default App;
