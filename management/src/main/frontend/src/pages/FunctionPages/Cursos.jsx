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

  const handleEditCourseChange = (editCourseId, values) => {
    const updatedCourses = courses.map(course => {
      if (course.id === editCourseId) {
        return { ...course, ...values };
      }
      return course;
    });
    setCourses(updatedCourses);
  };

  const handleEditSubjectChange = (editSubjectId, values) => {
    const updatedSubjects = subjects.map(subject => {
      if (subject.id === editSubjectId) {
        return { ...subject, ...values };
      }
      return subject;
    });
    setSubjects(updatedSubjects);
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

  return (
    <div className="crud-container">
      <h1>Cursos</h1>
      <div className="view-edit-delete">
        <h2>Cursos existentes</h2>
        {courses.map(course => (
          <div key={course.id}>
            {editCourseId === course.id ? (
              <>
                <input
                  value={newCourseTitle}
                  onChange={(e) => setNewCourseTitle(e.target.value)}
                  placeholder="Título"
                />
                <input
                  value={newCourseDuration}
                  onChange={(e) => setNewCourseDuration(e.target.value)}
                  placeholder="Duração (semestres)"
                />
                <button className='edit-delete-button' onClick={() => handleEditCourseChange(course.id, { title: newCourseTitle, duration: newCourseDuration })}>Save</button>
                <button className='edit-delete-button' onClick={handleFinishEditCourse}>Cancel</button>
              </>
            ) : (
              <>
                {course.title} - {course.duration}
                <button className='edit-delete-button' onClick={() => setEditCourseId(course.id)}>Edit</button>
                <button className='edit-delete-button' onClick={() => handleDeleteCourse(course.id)}>Delete</button>
              </>
            )}
          </div>
        ))}
      </div>
      <div className="view-edit-delete">
        <h2>Disciplinas existentes</h2>
        {subjects.map(subject => (
          <div key={subject.id}>
            {editSubjectId === subject.id ? (
              <>
                <input
                  value={newSubjectName}
                  onChange={(e) => setNewSubjectName(e.target.value)}
                  placeholder="Nome"
                />
                <input
                  value={newSubjectProfessor}
                  onChange={(e) => setNewSubjectProfessor(e.target.value)}
                  placeholder="Professor"
                />
                <button className='edit-delete-button' onClick={() => handleEditSubjectChange(subject.id, { name: newSubjectName, professor: newSubjectProfessor })}>Save</button>
                <button className='edit-delete-button' onClick={handleFinishEditSubject}>Cancel</button>
              </>
            ) : (
              <>
                {subject.name} - {subject.professor}
                <button className='edit-delete-button' onClick={() => setEditSubjectId(subject.id)}>Edit</button>
                <button className='edit-delete-button' onClick={() => handleDeleteSubject(subject.id)}>Delete</button>
              </>
            )}
          </div>
        ))}
      </div>
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
  );
};

export default Cursos;