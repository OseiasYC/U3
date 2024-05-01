import React, { useState } from "react";
import "./Matricula.css";
import enrollmentFetch from "../../axios/EnrollmentFetch";
import coursesFetch from "../../axios/CoursesFetch";

const Matricula = () => {
  const [course, setCourse] = useState([]);
  const [rm, setRm] = useState(null);
  const [name, setName] = useState(null);
  const [cpf, setCpf] = useState(null);
  const [birth, setBirth] = useState(null);

  const searchCourses = async () => {
    try {
      const response = await coursesFetch.get(`/course/all`);
      const data = response.data;
      setCourse(data);
      console.log(data);
    } catch (error) {
      console.error("Erro ao buscar os cursos", error);
    }
  };

  // TODO: passar os parâmetros aqui dos dados recebidos pelos campos digitados (inserindo dados manuais consegui testar, tudo ok na conexão aqui)
  const createEnrollment = async (course) => {
    const username = generateUsername("Iago Roque");
    console.log(course);

    const formData = new FormData();
    formData.append("rm", rm);
    formData.append("name", name);
    formData.append("username", username);
    formData.append("cpf", cpf);
    formData.append("birth", birth);
    formData.append("coursesId", null); // Ta rolando algum problema com courses, eu preciso pegar o id dele e não consigo, resolver também

    try {
      const response = await enrollmentFetch.post(`/student/add`, formData);

      console.log("Solicitação enviada com sucesso!", response.data);
    } catch (error) {
      console.error(error);
    }
  };

  function generateUsername(name) {
    const nameParts = name.trim().split(" ");

    const firstName = nameParts[0].toLowerCase();

    let lastName = "";
    if (nameParts.length > 1) {
      lastName = nameParts[nameParts.length - 1].toLowerCase();
    }

    const username = lastName ? `${firstName}.${lastName}` : firstName;

    return username;
  }

  return (
    <form className="enrollment-div">
      <h1>Matrícula</h1>
      <div>
        <label className="input-group">
          Nome completo:
          <input
            type="text"
            name="fullName"
            placeholder="Digite o nome completo"
            required
          />
        </label>
      </div>
      <div>
        <label className="input-group">
          CPF:
          <input type="text" name="cpf" placeholder="Digite o CPF" required />
        </label>
      </div>
      <div>
        <label className="input-group">
          Data de nascimento:
          <input type="date" name="birth" required />
        </label>
      </div>
      <div>
        <label className="input-group">
          Curso:
          <select
            name="course"
            onChange={(e) => setCourse(e.target.value)}
            required
          >
            <option value="">Selecione um curso</option>
            {course.map((course) => ( // Talvez tenha que rever isso também
              <option key={course.id} value={course.id}>
                {course.name}
              </option>
            ))}
          </select>
        </label>
      </div>
      <button type="submit" onClick={createEnrollment}>
        Registrar estudante
      </button>
    </form>
  );
};

export default Matricula;
