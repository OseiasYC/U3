import React, { useState, useEffect } from 'react';
import { FaBookOpen } from "react-icons/fa";
import libraryFetch from '../../axios/config';
import './Biblioteca.css';

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [book, setBook] = useState(null);
  const [searchResults, setSearchResults] = useState([]);
  const [showResults, setShowResults] = useState(false);
  const [typingTimeout, setTypingTimeout] = useState(null);
  const [selectedBookInfo, setSelectedBookInfo] = useState(null);

  useEffect(() => {
    if (selectedBookInfo) {
      setBook(selectedBookInfo);
    }
  }, [selectedBookInfo]);

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
      setShowResults(false);
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
      setShowResults(true);
    } catch (error) {
      console.error('Erro ao buscar livro:', error);
      setSearchResults([]);
      setShowResults(false);
    }
  };

  const handleSelectBook = (selectedBook) => {
    setSelectedBookInfo(selectedBook);
    setSearchResults([]);
    setShowResults(false);
  };

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
          onFocus={() => setShowResults(true)}
        />
        {showResults && (
  <datalist id="book-list">
    {searchResults.map((result) => (
    <option key={result.id} value={`${result.title}`}><br />{`${result.author} - Disponível: ${result.amount}`}</option>
    ))}
  </datalist>
)}
      </div>
      {/* Exibe o livro selecionado (se existir) */}
      {book && (
        <div className="book-details">
          <h3>{book.title}</h3>
          <p>Autor: {book.author}</p>
          <p>Disponível: {book.amount}</p>
        </div>
      )}
    </div>
  );
}

export default Biblioteca;
