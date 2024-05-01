import React, { useState, useEffect } from 'react';
import { FaBookOpen } from "react-icons/fa";
import libraryFetch from '../../axios/config';
import './Biblioteca.css';

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [selectedBook, setSelectedBook] = useState(null);
  const [formData, setFormData] = useState({
    title: "",
  });
  const [studentRM, setStudentRM] = useState(""); // Adiciona o estado para o número de registro do estudante
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
    setFormData({
      title: selectedBook.title // assuming you want to send only the title
    });
  };

  const handleLendBook = async () => {
    try {
      if (!selectedBook) {
        console.error('Nenhum livro selecionado para empréstimo');
        return;
      }

      const loanData = {
        book_id: selectedBook.id,
        loan_date: new Date().toISOString(),
        return_date: "", // O usuário fornecerá isso
        loan_status: "BORROWED", // Pode ser "BORROWED" por padrão
        student_rm: studentRM // Usa o valor de studentRM
      };

      const response = await fetch('http://localhost:8084/loans/save', {
        method: 'POST',
        body: JSON.stringify(loanData),
        headers: {
          'Content-Type': 'application/json'
        }
      });

      const json = await response.json();
      console.log(response);
      console.log(json);
    } catch (error) {
      console.error('Erro ao emprestar livro:', error);
    }
  };


  const handleReturnBook = async () => {
    try {
      const returnData = {
        book_id: selectedBook.id,
        return_date: "", // O usuário fornecerá isso
        loan_status: "RETURNED" // Define o status como "RETURNED" ao devolver
      };

      const response = await fetch(`http://localhost:8084/loans/update/${selectedBook.id}`, {
        method: 'PUT',
        body: JSON.stringify(returnData),
        headers: {
          'Content-Type': 'application/json'
        }
      });

      const json = await response.json();
      console.log(response);
      console.log(json);
    } catch (error) {
      console.error('Erro ao devolver livro:', error);
    }
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
          onSelect={(e) => {
            const selectedTitle = e.target.value;
            const selectedBook = searchResults.find(result => result.title === selectedTitle);
            if (selectedBook) {
              handleSelectBook(selectedBook);
            }
          }}
        />
        <datalist id="book-list">
          {searchResults.map((result) => (
            <option key={result.id} value={`${result.title}`} onClick={() => handleSelectBook(result)}><br />{`${result.author} - Disponível: ${result.amount}`}</option>
          ))}
        </datalist>
      </div>
      {/* Adiciona o campo de entrada para o número de registro do estudante */}
      <div className='student-input'>
        <FaBookOpen className='student-icon' />
        <input
          type="text"
          placeholder="Número de registro do estudante"
          value={studentRM}
          onChange={(e) => setStudentRM(e.target.value)}
          maxLength={9} // Limita o input a 9 caracteres
        />
      </div>
      <button onClick={handleLendBook}>Emprestar</button>
      <button onClick={handleReturnBook}>Devolver</button>
    </div>
  );
};

export default Biblioteca;
