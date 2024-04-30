import React, { useState } from 'react';
import { FaBookOpen } from "react-icons/fa";
import libraryFetch from '../../axios/config';
import './Biblioteca.css';

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [book, setBook] = useState(null);
  

  const searchBook = async (title) => {
    try {
      const response = await libraryFetch.get(`/books/title/${title}`);
      const data = response.data;

      // Se a busca retornar um livro, define o livro encontrado no estado `book`
      if (data.length > 0) {
        setBook(data[0]); // Considera apenas o primeiro livro encontrado
      } else {
        setBook(null); // Reseta o estado `book` se nenhum livro for encontrado
      }
    } catch (error) {
      console.error('Erro ao buscar livro:', error);
      setBook(null); // Limpa o estado `book` em caso de erro
    }
  };

  const handleSearch = async (title) => {
    const trimmedTitle = title.trim();

    if (trimmedTitle !== '') {
      setSearchTerm(trimmedTitle);
      await searchBook(trimmedTitle); // Realiza a busca pelo título
    } else {
      setBook(null); // Reseta o estado `book` se o título estiver vazio
    }
  };

  return (
    <div className='library-div'>
      <div className='book-input'>
        <FaBookOpen className='book-icon' />
        <input
          type="text"
          placeholder="Pesquise por título do livro"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onBlur={(e) => handleSearch(e.target.value)}
        />
      </div>
      {/* Exibe o livro encontrado (se existir) */}
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
