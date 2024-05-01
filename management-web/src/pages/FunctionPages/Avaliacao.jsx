import React, { useState } from "react";
import "./Avaliacao.css";
import gradesFetch from "../../axios/Grades";

const Avaliacao = () => {
  const [enrollmentNumber, setEnrollmentNumber] = useState("");
  const [studentData, setStudentData] = useState(null);
  const [studentSummary, setStudentSummary] = useState(null);
  const [studentSubjectGrades, setStudentSubjectGrades] = useState(null);

  //TODO: Quase todo feito, ajeitar a busca de ids só
  const searchGrades = async () => {
    try {
      const response1 = await gradesFetch.get(
        `/grades/studentsummary/student/61}`
      );
      const data1 = response1.data;
      setStudentSummary(data1);
      console.log(data1);
    } catch (error) {
      console.error("Erro ao buscar o resumo do aluno", error);
    }

    try {
      const response2 = await gradesFetch.get(
        `/grades/studentsubjectgrades/student/61`
      );
      const data2 = response2.data;
      setStudentSubjectGrades(data2);
      console.log(data2);
    } catch (error) {
      console.error("Erro ao buscar o resumo por disciplina do aluno", error);
    }
  };

  //Remover isso aqui futuramente
  const students = [
    {
      enrollmentNumber: "123456",
      name: "John Doe",
      course: "Engenharia de Software",
      subjects: 2,
      completedCourseHours: "80%",
      globalAverage: 9.4,
      courseStatus: "Cursando",
      courseShift: "Matutino",
      courseEntryDate: "01-01-2021",
      subjectsInformation: [
        {
          subject: "Tópicos Avançados em Banco de Dados",
          situation: "Cursando",
          shift: "Matutino",
          totalWorkload: "60h",
          grades1stAssessment: "9,5",
          grades2ndAssessment: "",
          numberOfAbsences: 2,
          dateEntry: "22-02-2024",
        },
        {
          subject: "Compiladores",
          situation: "Cursando",
          shift: "Matutino",
          totalWorkload: "60h",
          grades1stAssessment: "7",
          grades2ndAssessment: "",
          numberOfAbsences: 1,
          dateEntry: "23-02-2024",
        },
      ],
    },
  ];

  const handleEnrollmentNumberChange = (event) => {
    setEnrollmentNumber(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Search for student data based on enrollment number
    const student = students.find(
      (student) => student.enrollmentNumber === enrollmentNumber
    );
    setStudentData(student);
  };

  const renderStudentData = () => {
    if (!studentData) {
      return <p>Não foram encontrados dados para a matrícula inserida.</p>;
    }
    return (
      <>
        <div className="block">
          <h2>Sumário</h2>
          <p>Matrícula: {studentData.enrollmentNumber}</p>
          <p>Nome: {studentData.name}</p>
          <p>Curso: {studentData.course}</p>
          <p>Disciplinas: {studentData.subjects}</p>
          <p>Horas Concluídas do Curso: {studentData.completedCourseHours}</p>
          <p>Média Global: {studentData.globalAverage}</p>
          <p>Situação: {studentData.courseStatus}</p>
          <p>Turno: {studentData.courseShift}</p>
          <p>Data de Entrada: {studentData.courseEntryDate}</p>
        </div>
        <div className="block">
          <h2>Disciplinas</h2>
          {studentData.subjectsInformation.map((subject, index) => (
            <div key={index}>
              <h3>{subject.subject}</h3>
              <p>Situação: {subject.situation}</p>
              <p>Turno: {subject.shift}</p>
              <p>Carga Horária: {subject.totalWorkload}</p>
              <p>Notas (1ª Unidade): {subject.grades1stAssessment}</p>
              <p>Notas (2ª Unidade): {subject.grades2ndAssessment}</p>
              <p>Ausências: {subject.numberOfAbsences}</p>
              <p>Data de Entrada: {subject.dateEntry}</p>
              <br></br>
            </div>
          ))}
        </div>
      </>
    );
  };

  return (
    <div className="container">
      <h1>Avaliação</h1>
      <form onSubmit={handleSubmit}>
        <div className="enrollment-input">
          <label htmlFor="enrollmentNumber">Matrícula:</label>
          <input
            type="text"
            id="enrollmentNumber"
            value={enrollmentNumber}
            onChange={handleEnrollmentNumberChange}
            placeholder="Digite a matrícula do(a) estudante"
          />
        </div>
        <div className="flex-container">{renderStudentData()}</div>
        <button type="submit" onClick={searchGrades}>Buscar</button>
      </form>
    </div>
  );
};

export default Avaliacao;
