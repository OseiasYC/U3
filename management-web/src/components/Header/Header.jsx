import React, { useState } from 'react';
import './Header.css';
import ProfileMenu from '../ProfileMenu/ProfileMenu.jsx';

const Header = () => {
  const [logoSrc, setLogoSrc] = useState('./U3-logo.png');

  const handleMouseOver = () => {
    setLogoSrc('./U3-white-logo.png');
  };

  const handleMouseOut = () => {
    setLogoSrc('./U3-logo.png');
  };

  return (
    <header className="navbar">
      <div className="logo-section">
        <a href="/" className="logo-link">
          <img
            src={logoSrc}
            alt="Logo"
            className="logo-home"
            onMouseOver={handleMouseOver}
            onMouseOut={handleMouseOut}
          />
        </a>
      </div>
      <ul className="navbar-nav">
        <li className="nav-item">
          <a href="/dashboard" className="nav-link">Dashboard</a>
        </li>
        <li className="nav-item">
          <a href="/biblioteca" className="nav-link">Biblioteca</a>
        </li>
        <li className="nav-item">
          <a href="/atendimento" className="nav-link">Atendimento</a>
        </li>
        <li className="nav-item">
          <a href="/matricula" className="nav-link">Matrícula</a>
        </li>
        <li className="nav-item">
          <a href="/avaliacao" className="nav-link">Avaliação</a>
        </li>
        <li className="nav-item">
          <a href="/cursos" className="nav-link">Cursos</a>
        </li>
      </ul>
      <ProfileMenu />
    </header>
  );
}

export default Header;
