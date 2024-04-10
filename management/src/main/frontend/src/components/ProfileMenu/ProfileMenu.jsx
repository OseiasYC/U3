import React, { useState, useRef, useEffect } from 'react';

const ProfileMenu = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [animationState, setAnimationState] = useState('');
  const menuRef = useRef(null);
  const buttonRef = useRef(null);

  const toggleDropdown = () => {
    if (!isOpen) {
      setAnimationState('enter');
      setIsOpen(true);
    } else {
      setAnimationState('leave');
      setTimeout(() => {
        setIsOpen(false);
      }, 75); // Matches the duration of the leave animation
    }
  };

  const closeDropdown = e => {
    if (menuRef.current && !menuRef.current.contains(e.target)) {
      setAnimationState('leave');
      setTimeout(() => {
        setIsOpen(false);
      }, 75); // Timeout for the leave animation
    }
  };

  useEffect(() => {
    document.addEventListener('mousedown', closeDropdown);
    return () => document.removeEventListener('mousedown', closeDropdown);
  }, []);

  const getMenuStyle = () => {
    let style = {
      position: 'absolute',
      right: 0,
      width: '150px',
      backgroundColor: 'rgba(255, 255, 255, 0.2)',
      backdropFilter: 'blur(30px)',
      boxShadow: '0 0 10px rgba(0, 0, 0, .2)',
      borderRadius: '10px',
      border: '2px solid rgba(255, 255, 255, .2)',
      color: 'white',
      overflow: 'hidden',
    };
    if (animationState === 'enter') {
      style = { ...style, opacity: 1, transform: 'scale(1)', transition: 'opacity 100ms ease-out, transform 100ms ease-out' };
    } else if (animationState === 'leave') {
      style = { ...style, opacity: 0, transform: 'scale(0.95)', transition: 'opacity 75ms ease-in, transform 75ms ease-in' };
    }
    if (buttonRef.current) {
      const buttonHeight = buttonRef.current.offsetHeight;
      style = { ...style, top: `${buttonHeight + 8}px` }; // Adding 8px for some spacing
    }
    return style;
  };

  return (
    <div className="profile-section" style={{ position: 'relative' }} ref={menuRef}>
      <button ref={buttonRef} onClick={toggleDropdown} className="dropdown-button" style={{ background: 'none', border: 'none' }}>
        <img
          src="../profile-photo-example.jpg"
          alt="Perfil"
          style={{ width: '40px', height: '40px', borderRadius: '50%', border: 'none' }}
        />
      </button>
      {isOpen && (
        <div style={getMenuStyle()}>
          <a href="#perfil" style={{ display: 'block', padding: '8px 10px', textDecoration: 'none', color: 'white' }}>Editar perfil</a>
          <a href="#sair" style={{ display: 'block', padding: '8px 10px', textDecoration: 'none', color: 'white' }}>Sair</a>
        </div>
      )}
    </div>
  );
};

export default ProfileMenu;