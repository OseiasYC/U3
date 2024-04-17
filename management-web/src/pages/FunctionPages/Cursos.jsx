import React, { useState } from 'react';
import './Cursos.css';

const Cursos = () => {
  const [courses, setCourses] = useState([
    { id: 1, title: 'BES - Engenharia de Software', duration: '8 semestres' },
    { id: 2, title: 'LMA - Matemática', duration: '10 semestres' },
  ]);
  const [subjects, setSubjects] = useState([
    { id: 1, name: 'Cálculo I', professor: 'John Doe' },
    { id: 2, name: 'Sistema Operacionais', professor: 'Jane Smith' },
  ]);
  const [editCourseId, setEditCourseId] = useState(null);
  const [editSubjectId, setEditSubjectId] = useState(null);
  const [newCourseTitle, setNewCourseTitle] = useState('');
  const [newCourseDuration, setNewCourseDuration] = useState('');
  const [newSubjectName, setNewSubjectName] = useState('');
  const [newSubjectProfessor, setNewSubjectProfessor] = useState('');
  const [selectedSubject, setSelectedSubject] = useState(null);
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [isSubjectPopupOpen, setIsSubjectPopupOpen] = useState(false);
  const [isCoursePopupOpen, setIsCoursePopupOpen] = useState(false);

  const handleAddCourse = (e) => {
    e.preventDefault();
    const title = e.target.title.value;
    const duration = e.target.duration.value;
    const newCourse = { id: Date.now(), title, duration };
    setCourses([...courses, newCourse]);
  };

  const handleAddSubject = (e) => {
    e.preventDefault();
    const name = e.target.name.value;
    const professor = e.target.professor.value;
    const newSubject = { id: Date.now(), name, professor };
    setSubjects([...subjects, newSubject]);
  };

  const handleEditCourseChange = () => {
    // Implementar a lógica para editar o curso
  };

  const handleEditSubjectChange = () => {
    // Implementar a lógica para editar a disciplina
  };

  const handleFinishEditCourse = () => {
    setEditCourseId(null);
    setNewCourseTitle("");
    setNewCourseDuration("");
  };

  const handleFinishEditSubject = () => {
    setEditSubjectId(null);
    setNewSubjectName("");
    setNewSubjectProfessor("");
  };

  const handleDeleteCourse = (id) => {
    setCourses(courses.filter(course => course.id !== id));
  };

  const handleDeleteSubject = (id) => {
    setSubjects(subjects.filter(subject => subject.id !== id));
  };

  const handleSubjectClick = (subject) => {
    setSelectedSubject(subject);
    setIsSubjectPopupOpen(true);
  };

  const handleCourseClick = (course) => {
    setSelectedCourse(course);
    setIsCoursePopupOpen(true);
  };

  const handleCloseSubjectPopup = () => {
    setIsSubjectPopupOpen(false);
  };

  const handleCloseCoursePopup = () => {
    setIsCoursePopupOpen(false);
  };

  return (
    <div className="crud-container">
      <h1>Cursos</h1>
      <div className="crud-content">
        <div className="content-left">
          <div className="view-edit-delete">
            <h2>Cursos existentes</h2>
            {courses.map(course => (
              <div className='content' key={course.id} onClick={() => handleCourseClick(course)}>
                {course.title} - {course.duration}
              </div>
            ))}
          </div>
          <div className="view-edit-delete">
            <h2>Disciplinas existentes</h2>
            {subjects.map(subject => (
              <div className='content' key={subject.id} onClick={() => handleSubjectClick(subject)}>
                {subject.name} - {subject.professor}
              </div>
            ))}
          </div>
        </div>
        {isCoursePopupOpen && (
          <div className="popup">
            <h2>{selectedCourse.title}</h2>
            <input
              value={newCourseTitle}
              onChange={(e) => setNewCourseTitle(e.target.value)}
              placeholder="Novo título"
            />
            <input
              value={newCourseDuration}
              onChange={(e) => setNewCourseDuration(e.target.value)}
              placeholder="Nova duração"
            />
            <button className='edit-delete-button' onClick={() => handleEditCourseChange(selectedCourse.id, { title: newCourseTitle, duration: newCourseDuration })}>Salvar</button>
            <button className='edit-delete-button' onClick={() => handleDeleteCourse(selectedCourse.id)}>Deletar</button>
            <button className='edit-delete-button' onClick={handleCloseCoursePopup}>Cancelar/Fechar</button>
          </div>
        )}
        {isSubjectPopupOpen && (
          <div className="popup">
            <h2>{selectedSubject.name}</h2>
            <input
              value={newSubjectName}
              onChange={(e) => setNewSubjectName(e.target.value)}
              placeholder="Novo nome"
            />
            <input
              value={newSubjectProfessor}
              onChange={(e) => setNewSubjectProfessor(e.target.value)}
              placeholder="Novo professor"
            />
            <button className='edit-delete-button' onClick={() => handleEditSubjectChange(selectedSubject.id, { name: newSubjectName, professor: newSubjectProfessor })}>Salvar</button>
            <button className='edit-delete-button' onClick={() => handleDeleteSubject(selectedSubject.id)}>Deletar</button>
            <button className='edit-delete-button' onClick={handleCloseSubjectPopup}>Cancelar</button>
          </div>
        )}
        <div className="content-right">
          <div className="create">
            <h2>Criar nova disciplina</h2>
            <form onSubmit={handleAddSubject}>
              <input name="name" placeholder="Nome" required />
              <input name="professor" placeholder="Professor" required />
              <button className='create-button' type="submit">Adicionar disciplina</button>
            </form>
          </div>
          <div className="create">
            <h2>Criar novo curso</h2>
            <form onSubmit={handleAddCourse}>
              <input name="title" placeholder="Título" required />
              <input name="duration" placeholder="Duração (semestres)" required />
              <button className='create-button' type="submit">Adicionar curso</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cursos;
