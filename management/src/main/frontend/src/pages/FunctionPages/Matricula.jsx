import React, { useState } from 'react';
import './Matricula.css'

const Matricula = () => {
  const [courses] = useState([
    { id: '1', name: 'Engenharia da Computação' },
    { id: '2', name: 'Sistemas de Informação' },
    { id: '3', name: 'Ciência da Computação' },
  ]);

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
          <select name="course" required>
            <option value="">Selecione um curso</option>
            {courses.map(course => (
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