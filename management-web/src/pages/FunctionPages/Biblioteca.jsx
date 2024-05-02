import React, { useState, useEffect } from "react";
import { FaBookOpen, FaUserGraduate  } from "react-icons/fa";
import libraryFetch from "../../axios/LibraryFetch";
import "./Biblioteca.css";

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [showResults, setShowResults] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const [confirmationVisible, setConfirmationVisible] = useState(false);
  const [studentRm, setStudentRm] = useState(""); // Novo estado para armazenar a matrícula do aluno

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

      if (!studentRm) {
        console.error("Por favor, digite a matrícula do(a) estudante.");
        return;
      }
  
      const currentDate = new Date();
      const loanDate = currentDate.toISOString();
  
      const returnDate = new Date(currentDate);
      returnDate.setDate(returnDate.getDate() + 7);
      const formattedReturnDate = returnDate.toISOString();
  
      const response = await libraryFetch.post(`/loans/save`, {
        studentRm: studentRm, // Usando o valor do estado studentRm
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
                <span className="title"><b>{book.title}</b></span>
                <br />
                <span className="author">{book.author}</span>
                <span className="amount"> - Disponível: {book.amount}</span>
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="student-input">
        <FaUserGraduate className="student-icon" />
        <input
          type="text"
          placeholder="Digite a matrícula do(a) estudante"
          value={studentRm}
          onChange={(e) => setStudentRm(e.target.value)}
        />
      </div>
      {confirmationVisible && selectedBook && (
        <div className="confirmation">
          <p className="confirmation-advise">Confirme o empréstimo do livro:</p>
          <p className="confirmation-title"><b>Título:</b> {selectedBook.title}</p>
          <p className="confirmation-author"><b>Autor(a):</b> {selectedBook.author}</p>
          <button onClick={createLoan}>Confirmar Empréstimo</button>
        </div>
      )}
    </div>
  );
};

export default Biblioteca;
