import React, { useState } from 'react';
import { FaBookOpen } from "react-icons/fa6";
import { PiStudent } from "react-icons/pi";
import './Biblioteca.css';

const Biblioteca = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [books, setBooks] = useState([
    { id: '1', title: 'Engenharia de Software', author: 'Ian Sommerville', quantity: 5 },
    { id: '2', title: 'Rapid Development', author: 'Steve McConnell', quantity: 3 },
    { id: '3', title: 'Requirements Engineering: A Good Practice Guide', author: 'Ian Sommerville', quantity: 4 },
  ]); // Dados simulados. TODO: buscar do db

  const [students, setStudents] = useState([
    { registration: 'R1', fullName: 'Student 1' },
    { registration: '200017414', fullName: 'Lucas Farias da Silva' },
    { registration: 'R3', fullName: 'Student 3' },
  ]); // Dados simulados. TODO: buscar do db

  const [selectedBookTitle, setSelectedBookTitle] = useState('');
  const [selectedBookAuthor, setSelectedBookAuthor] = useState('');
  const [studentRegistration, setStudentRegistration] = useState('');
  const [selectedStudentFullName, setSelectedStudentFullName] = useState('');

  const searchBook = (term) => {
    const foundBook = books.find(book =>
      book.id === term ||
      book.title.toLowerCase().includes(term.toLowerCase()) ||
      book.author.toLowerCase().includes(term.toLowerCase())
    );
    if (foundBook) {
      setSelectedBookTitle(foundBook.title);
      setSelectedBookAuthor(foundBook.author);
    } ;
  };

  const searchStudent = (registration) => {
    const foundStudent = students.find(student => student.registration === registration);
    if (foundStudent) {
      setSelectedStudentFullName(foundStudent.fullName);
      setStudentRegistration(foundStudent.registration);
    } 
  };

  const handleLendBook = () => {
    // TODO: Adicionar lógica de empréstimo relacionando ao db
    const updatedBooks = books.map(book => {
      if (book.title === selectedBookTitle && book.quantity > 0) {
        return { ...book, quantity: book.quantity - 1 };
      }
      return book;
    });
    setBooks(updatedBooks);

    alert(`Emprestando o livro "${selectedBookTitle}" para o(a) estudante: ${selectedStudentFullName}`);
  };

  const handleReturnBook = () => {
    // Verifica se o livro e o estudante selecionados são válidos
    if (!selectedBookTitle || !selectedStudentFullName) {
      alert("Por favor, selecione um livro e um estudante antes de devolver.");
      return;
    }
  
    // Encontra o livro a ser devolvido
    const returnedBook = books.find(book => book.title === selectedBookTitle);
    
    // Atualiza a quantidade do livro devolvido
    const updatedBooks = books.map(book => {
      if (book.title === selectedBookTitle) {
        return { ...book, quantity: book.quantity + 1 };
      }
      return book;
    });
  
    // Atualiza o estado dos livros
    setBooks(updatedBooks);
  
    // Limpa os estados dos livros e estudantes selecionados
    setSelectedBookTitle('');
    setSelectedBookAuthor('');
    setSelectedStudentFullName('');
    setStudentRegistration('');
  
    // Alerta que o livro foi devolvido com sucesso
    alert(`Livro "${returnedBook.title}" devolvido com sucesso.`);
  };

  return (
    <div className='library-div'>
      <h1>Biblioteca</h1>
      <div className='book-input'>
        <FaBookOpen className='book-icon' />
        <input
          type="text"
          list="book-list"
          placeholder="Pesquise por título, autor ou ISBN"
          value={searchTerm}
          onChange={(e) => {
            setSearchTerm(e.target.value);
            searchBook(e.target.value);
          }}
        />
        <datalist id="book-list">
          {books
            .filter(book =>
              book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
              book.author.toLowerCase().includes(searchTerm.toLowerCase()) ||
              book.id.includes(searchTerm)
            )
            .map((book) => (
              <option key={book.id} value={book.title}>{`${book.author} - (Disponível: ${book.quantity})`}</option>
            ))}
        </datalist>
      </div>
      <div className='student-input'>
        <PiStudent className='student-icon' />
        <input
          type="text"
          placeholder="Insira a matrícula do(a) estudante"
          value={studentRegistration}
          onChange={(e) => {
            setStudentRegistration(e.target.value);
            searchStudent(e.target.value);
          }}
        />
      </div>
      <p>Confira a relação do empréstimo:</p>
      <p className='p-answer'>
        Estudante: {selectedStudentFullName} - {studentRegistration} <br />
        Livro: {selectedBookTitle} - {selectedBookAuthor}
      </p>
      <button onClick={handleLendBook}>Emprestar</button>
      <button onClick={handleReturnBook}>Devolver</button>
    </div>
  );
}

export default Biblioteca;