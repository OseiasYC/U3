import React, { useState, useEffect } from "react";
import "./Cursos.css";
import coursesFetch from "../../axios/CoursesFetch";

const Cursos = () => {
  const [courses, setCourses] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [editCourseId, setEditCourseId] = useState(null);
  const [editSubjectId, setEditSubjectId] = useState(null);
  const [newCourseTitle, setNewCourseTitle] = useState("");
  const [newCourseDuration, setNewCourseDuration] = useState("");
  const [newSubjectName, setNewSubjectName] = useState("");
  const [newSubjectProfessor, setNewSubjectProfessor] = useState("");
  const [selectedSubject, setSelectedSubject] = useState(null);
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [isSubjectPopupOpen, setIsSubjectPopupOpen] = useState(false);
  const [isCoursePopupOpen, setIsCoursePopupOpen] = useState(false);
  const [nameNewCourse, setNameNewCourse] = useState("");
  const [idNewCourse, setIdNewCourse] = useState("");
  const [nameNewSubject, setNameNewSubject] = useState("");
  const [workloadNewSubject, setWorkloadNewSubject] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await coursesFetch.get(`/course/all`);
        const data = response.data;
        console.log(response.data);
        setCourses(data);
      } catch (error) {
        console.error("Erro ao buscar os cursos", error);
      }

      try {
        const response2 = await coursesFetch.get(`/subject/all`);
        const data2 = response2.data;
        console.log(response2.data);
        setSubjects(data2);
      } catch (error) {
        console.error("Erro ao buscar os cursos", error);
      }
    };

    const delay = setTimeout(() => {
      fetchData();
    }, 1000);

    return () => clearTimeout(delay);
  }, []);

  const handleAddCourse = () => {
    try {
      const requestBody = {
        id: idNewCourse,
        name: nameNewCourse,
        studentsRM: "200019213",
        subjects: [],
      };

      const response = coursesFetch.post(`/course/add`, requestBody);

      console.log("Solicitação enviada com sucesso!", response.data);
    } catch (error) {
      console.error("Erro ao criar curso", error);
    }
  };

  const handleAddSubject = () => {
    try {
      const requestBody = {
        name: nameNewSubject,
        workload: workloadNewSubject,
      };

      const response = coursesFetch.post(`/subject/add`, requestBody);

      console.log("Solicitação enviada com sucesso!", response.data);
    } catch (error) {
      console.error("Erro ao criar disciplina", error);
    }
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
    setCourses(courses.filter((course) => course.id !== id));
  };

  const handleDeleteSubject = (id) => {
    setSubjects(subjects.filter((subject) => subject.id !== id));
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
            {courses.map((course) => (
              <div
                className="content"
                key={course.id}
                onClick={() => handleCourseClick(course)}
              >
                {course.name} - {course.id}
              </div>
            ))}
          </div>
          <div className="view-edit-delete">
            <h2>Disciplinas existentes</h2>
            {subjects.map((subject) => (
              <div
                className="content"
                key={subject.id}
                onClick={() => handleSubjectClick(subject)}
              >
                {subject.name} - CH: {subject.workload} hrs
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
            <button
              className="edit-delete-button"
              onClick={() =>
                handleEditCourseChange(selectedCourse.id, {
                  title: newCourseTitle,
                  duration: newCourseDuration,
                })
              }
            >
              Salvar
            </button>
            <button
              className="edit-delete-button"
              onClick={() => handleDeleteCourse(selectedCourse.id)}
            >
              Deletar
            </button>
            <button
              className="edit-delete-button"
              onClick={handleCloseCoursePopup}
            >
              Cancelar/Fechar
            </button>
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
            <button
              className="edit-delete-button"
              onClick={() =>
                handleEditSubjectChange(selectedSubject.id, {
                  name: newSubjectName,
                  professor: newSubjectProfessor,
                })
              }
            >
              Salvar
            </button>
            <button
              className="edit-delete-button"
              onClick={() => handleDeleteSubject(selectedSubject.id)}
            >
              Deletar
            </button>
            <button
              className="edit-delete-button"
              onClick={handleCloseSubjectPopup}
            >
              Cancelar
            </button>
          </div>
        )}
        <div className="content-right">
          <div className="create">
            <h2>Criar novo curso</h2>
            <form onSubmit={handleAddCourse}>
              <input
                name="title"
                value={nameNewCourse}
                onChange={(e) => setNameNewCourse(e.target.value)}
                placeholder="Título"
                required
              />
              <input
                name="id"
                value={idNewCourse}
                onChange={(e) => setIdNewCourse(e.target.value)}
                placeholder="Sigla"
                required
              />
              <button className="create-button" type="submit">
                Adicionar curso
              </button>
            </form>
          </div>
          <div className="create">
            <h2>Criar nova disciplina</h2>
            <form onSubmit={handleAddSubject}>
              <input
                name="name"
                value={nameNewSubject}
                onChange={(e) => setNameNewSubject(e.target.value)}
                placeholder="Nome"
                required
              />
              <input
                name="ch"
                value={workloadNewSubject}
                onChange={(e) => setWorkloadNewSubject(e.target.value)}
                placeholder="Carga Horária"
                required
              />
              <button className="create-button" type="submit">
                Adicionar disciplina
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cursos;
