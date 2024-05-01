import React, { useState, useEffect } from "react";
import "./Matricula.css";
import enrollmentFetch from "../../axios/EnrollmentFetch";
import coursesFetch from "../../axios/CoursesFetch";

const Matricula = () => {
  const [courses, setCourse] = useState([]);
  const [selectedCourseId, setSelectedCourseId] = useState("");
  const [name, setName] = useState("");
  const [cpf, setCpf] = useState("");
  const [birth, setBirth] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await coursesFetch.get(`/course/all`);
        const data = response.data;
        console.log(response.data);
        setCourse(data);
      } catch (error) {
        console.error("Erro ao buscar os cursos", error);
      }
    };

    const delay = setTimeout(() => {
      fetchData();
    }, 1000);

    return () => clearTimeout(delay);
  }, []);

  const createEnrollment = async () => {
    const username = generateUsername(name);
    const randomRM = generateRandomRM();

    const requestBody = {
      rm: randomRM,
      name: name,
      username: username,
      cpf: cpf,
      birth: birth,
      coursesId: [selectedCourseId],
    };

    try {
      const response = await enrollmentFetch.post(`/student/add`, requestBody);

      alert(`Matrícula efetuada com sucesso! RM do estudante: ${randomRM}`); //TODO: fazer esse alert funcionar
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

  function generateRandomRM() {
    const randomNumber = Math.floor(Math.random() * 100000);
    const formattedRM = `2000${randomNumber.toString().padStart(5, "0")}`;
    return formattedRM;
  }

  return (
    <form className="enrollment-div">
      <h1>Matrícula</h1>
      <div>
        <label className="input-group">
          Nome completo:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Digite o nome completo"
            required
          />
        </label>
      </div>
      <div>
        <label className="input-group">
          CPF:
          <input
            type="text"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
            placeholder="Digite o CPF"
            required
          />
        </label>
      </div>
      <div>
        <label className="input-group">
          Data de nascimento:
          <input
            type="date"
            value={birth}
            onChange={(e) => setBirth(e.target.value)}
            required
          />
        </label>
      </div>
      <div>
        <label className="input-group">
          Curso:
          <select
            name="course"
            value={selectedCourseId}
            onChange={(e) => setSelectedCourseId(e.target.value)}
            required
          >
            <option value="">Selecione um curso</option>
            {courses.map((course) => (
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
