import React, { useState } from 'react';
import './Matricula.css'
import enrollmentFetch from '../../axios/EnrollmentFetch';
import coursesFetch from '../../axios/CoursesFetch';

const Matricula = () => {
  const [course, setCourse] = useState([]);
  const [enrollment, setEnrollment] = useState(null);
  const [rm, setRm] = useState(null);
  const [cpf, setCpf] = useState(null);
  const [birth, setBirth] = useState(null);

  // const searchCourses = async () => {
  //   try {
  //     const response = await coursesFetch.get(`/course/all`);
  //     const data = response.data;
  //     setCourse(data);
  //     setShowResults(true);
  //   } catch (error) {
  //     console.error("Erro ao buscar livro:", error);
  //     setSearchResults([]);
  //     setShowResults(false);
  //   }
  // };

  const createEnrollment = async () => {

    const formData = new FormData();
    formData.append("rm", rm);
    formData.append("cpf", cpf);
    formData.append("birth", birth);
    formData.append("coursesId", course);

  }

  return (
    <form className='enrollment-div'>
      <h1>Matrícula</h1>
      <div>
        <label className='input-group'>
          Nome completo:
          <input type="text" name="fullName" placeholder="Digite o nome completo" required />
        </label>
      </div>
      <div>
        <label className='input-group'>
          CPF:
          <input type="text" name="cpf" placeholder="Digite o CPF" required />
        </label>
      </div>
      <div>
        <label className='input-group'>
          Data de nascimento:
          <input type="date" name="birth" required />
        </label>
      </div>
      <div>
        <label className='input-group'>
          Curso:
          <select name="course" onChange={(e) => setCourse(e.target.value)} required>
            <option value="">Selecione um curso</option>
            {course.map(course => (
              <option key={course.id} value={course.id}>{course.name}</option>
            ))}
          </select>
        </label>
      </div>
      <button type="submit">Registrar estudante</button>
    </form>
  );
};

export default Matricula;