import React, { useState, useEffect } from "react";
import { FaBookOpen } from "react-icons/fa";
import libraryFetch from "../../axios/LibraryFetch";
import "./Biblioteca.css";

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [showResults, setShowResults] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const [confirmationVisible, setConfirmationVisible] = useState(false);

  useEffect(() => {
    if (searchTerm.trim() !== "") {
      const timeoutId = setTimeout(() => {
        searchBook(searchTerm.trim());
      }, 1000);

      return () => clearTimeout(timeoutId);
    }
  }, [searchTerm]);

  const searchBook = async (title) => {
    try {
      const response = await libraryFetch.get(`/books/title/${title}`);
      setSearchResults(response.data);
      setShowResults(true);
    } catch (error) {
      console.error("Erro ao buscar livro:", error);
      setSearchResults([]);
      setShowResults(false);
    }
  };

  const handleSelectBook = (book) => {
    setSelectedBook(book);
    setShowResults(false);
    setConfirmationVisible(true);
    console.log("Livro selecionado:", book);
  };

  const createLoan = async () => {
    try {
      if (!selectedBook) {
        console.error("Nenhum livro selecionado.");
        return;
      }
  
      const currentDate = new Date();
      const loanDate = currentDate.toISOString();
  
      const returnDate = new Date(currentDate);
      returnDate.setDate(returnDate.getDate() + 7);
      const formattedReturnDate = returnDate.toISOString();
  
      const response = await libraryFetch.post(`/loans/save`, {
        studentRm: "20000001",
        bookId: selectedBook.id,
        loanStatus: "BORROWED",
        loanDate: loanDate,
        returnDate: formattedReturnDate,
      });

      console.log("Response from createLoan:", response);
  
      console.log("Empréstimo criado com sucesso!");
    } catch (error) {
      console.error("Erro ao criar empréstimo:", error);
    }
  };

  const createLoan = async () => {
    try {
      if (!selectedBook) {
        console.error("Nenhum livro selecionado.");
        return;
      }
  
      const currentDate = new Date();
      const loanDate = currentDate.toISOString();
  
      const returnDate = new Date(currentDate);
      returnDate.setDate(returnDate.getDate() + 7);
      const formattedReturnDate = returnDate.toISOString();
  
      const response = await libraryFetch.post(`/loans/save`, {
        studentRm: "20000001",
        bookId: selectedBook.id,
        loanStatus: "BORROWED",
        loanDate: loanDate,
        returnDate: formattedReturnDate,
      });
  
      console.log("Empréstimo criado com sucesso!");
    } catch (error) {
      console.error("Erro ao criar empréstimo:", error);
    }
  };
  
  const handleSelectBook = (selectedBook) => {
    setSelectedBook(selectedBook);
    setShowResults(false); // Esconde a lista de resultados após selecionar um livro
  };
  

  return (
    <div className="library-div">
      <h1>Biblioteca</h1>
      <div className="book-input">
        <FaBookOpen className="book-icon" />
        <input
          type="text"
          placeholder="Pesquise por título do livro"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onFocus={() => setShowResults(true)}
        />
        {showResults && (
          <ul className="book-results">
            {searchResults.map((book) => (
              <li key={book.id} onClick={() => handleSelectBook(book)}>
                <span>{book.title}</span>
                <span>{book.author}</span>
                <span>Disponível: {book.amount}</span>
              </li>
            ))}
          </ul>
        )}
      </div>
      {confirmationVisible && selectedBook && (
        <div className="confirmation">
          <p>Confirme o empréstimo do livro:</p>
          <p>Título: {selectedBook.title}</p>
          <p>Autor: {selectedBook.author}</p>
          <button onClick={createLoan}>Confirmar Empréstimo</button>
        </div>
      )}
    </div>
  );
};

export default Biblioteca;
