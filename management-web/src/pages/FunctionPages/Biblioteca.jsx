import React, { useState, useEffect } from 'react';
import { FaBookOpen } from "react-icons/fa";
import libraryFetch from '../../axios/config';
import './Biblioteca.css';

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [selectedBook, setSelectedBook] = useState(null);
  const [typingTimeout, setTypingTimeout] = useState(null);

  useEffect(() => {
    if (typingTimeout) {
      clearTimeout(typingTimeout);
    }

    if (searchTerm.trim() !== '') {
      const timeoutId = setTimeout(() => {
        searchBook(searchTerm.trim());
      }, 1000);

      setTypingTimeout(timeoutId);
    } else {
      setSearchResults([]);
    }

    return () => {
      if (typingTimeout) {
        clearTimeout(typingTimeout);
      }
    };
  }, [searchTerm]);

  const searchBook = async (title) => {
    try {
      const response = await libraryFetch.get(`/books/title/${title}`);
      const data = response.data;
      setSearchResults(data);
    } catch (error) {
      console.error('Erro ao buscar livro:', error);
      setSearchResults([]);
    }
  };

  const handleSelectBook = (selectedBook) => {
    setSelectedBook(selectedBook);
  };

  const handleLendBook = () => {
    // TODO: Adicionar lógica de empréstimo relacionando ao db
  }

  const handleReturnBook = () => {
    // TODO: Adicionar lógica de devolução relacionando ao db
  }

  return (
    <div className='library-div'>
      <h1>Biblioteca</h1>
      <div className='book-input'>
        <FaBookOpen className='book-icon' />
        <input
          type="text"
          list="book-list"
          placeholder="Pesquise por título do livro"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <datalist id="book-list">
          {searchResults.map((result) => (
            <option key={result.id} value={`${result.title}`} onClick={(result) => handleSelectBook(result)}><br />{`${result.author} - Disponível: ${result.amount}`}</option>
          ))}
        </datalist>
      </div>
        <button onClick={handleLendBook}>Emprestar</button>
        <button onClick={handleReturnBook}>Devolver</button>
    </div>
  );
}

export default Biblioteca;
