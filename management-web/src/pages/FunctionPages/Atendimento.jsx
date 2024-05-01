import React, { useState } from "react";
import { AiOutlineFileAdd } from "react-icons/ai";
import { BiSearchAlt } from "react-icons/bi";
import "./Atendimento.css";
import requestsFetch from "../../axios/RequestsFetch";

const Atendimento = () => {
  const [serviceTitle, setServiceTitle] = useState("");
  const [requestDescription, setRequestDescription] = useState("");
  const [registrationNumber, setRegistrationNumber] = useState("");
  const [sendingDate] = useState(new Date().toLocaleString());
  const [selectedFile, setSelectedFile] = useState(null);

  const createRequest = async (registrationNumber, serviceTitle, requestDescription, selectedFile) => {

    // TODO: o front n ta capturando os dados e passando para aqui, todos os atributos estão nulos
    const formData = new FormData();
    formData.append("studentRm", registrationNumber);
    formData.append("title", serviceTitle);
    formData.append("description", requestDescription);
    formData.append("attachment", null);
    formData.append("status", "OPEN");

    try {
      const response = await requestsFetch.post(`/request/create`, formData);

      console.log("Solicitação enviada com sucesso!", response.data);

      setServiceTitle("");
      setRequestDescription("");
      setRegistrationNumber("");
      setSelectedFile(null);
    } catch (error) {
      console.error(error);
    }
  };

  // Função para lidar com a seleção do arquivo
  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const [services, setServices] = useState([
    { id: "1", title: "Solicitação de Trancamento de Matrícula" },
    { id: "2", title: "Emissão de Comprovante de Matrícula" },
    { id: "3", title: "Solicitação de Agendamento de Orientação Acadêmica" },
  ]); // Simulated data for search dropdown

  return (
    <div className="service-request-div">
      <h1>Atendimento</h1>
      <div className="input-group">
        {/* TODO: Ajustar posição de ícones */}
        {/* <BiSearchAlt className='icon' /> */}
        <input
          type="text"
          list="service-list"
          placeholder="Selecione o serviço"
          value={serviceTitle}
          onChange={(e) => setServiceTitle(e.target.value)}
        />
        <datalist id="service-list">
          {services.map((service) => (
            <option key={service.id} value={service.title} />
          ))}
        </datalist>
      </div>
      <div className="input-group">
        <textarea
          placeholder="Descreva a solicitação"
          value={requestDescription}
          onChange={(e) => setRequestDescription(e.target.value)}
          required
        />
      </div>
      <div className="input-group">
        <input
          type="text"
          placeholder="Insira a matrícula do(a) estudante"
          value={registrationNumber}
          onChange={(e) => setRegistrationNumber(e.target.value)}
          required
        />
      </div>
      <div className="input-group">
        {/* TODO: Ajustar posição de ícones */}
        {/* <AiOutlineFileAdd className='icon' /> */}
        <input
          type="file"
          onChange={(e) => setSelectedFile(e.target.files ? e.target.files[0] : null)}
        />
      </div>
      <div className="sending-date">
        <label>Data da solicitação: {sendingDate}</label>
      </div>
      <button onClick={createRequest}>Enviar solicitação</button>
    </div>
  );
};

export default Atendimento;
