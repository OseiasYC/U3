import React, { useState } from "react";
import { AiOutlineFileAdd } from "react-icons/ai";
import { BiSearchAlt } from "react-icons/bi";
import "./Atendimento.css";
import requestsFetch from "../../axios/RequestsFetch";

const Atendimento = () => {
  const [studentRm, setStudentRm] = useState("");
  const [serviceTitle, setServiceTitle] = useState("");
  const [requestDescription, setRequestDescription] = useState("");
  const [registrationNumber, setRegistrationNumber] = useState("");
  const [showDate] = useState(new Date().toLocaleString());
  const [sendingDate] = useState(new Date().toISOString());
  const [selectedFile, setSelectedFile] = useState(null);

  const createRequest = async () => {
    const requestBody = {
      studentRm: registrationNumber,
      title: serviceTitle,
      description: requestDescription,
      attachment: selectedFile,
      requestDate: sendingDate,
      status: "OPEN",
    };

    try {
      const response = await requestsFetch.post(`/request/create`, requestBody);

      console.log("Solicitação enviada com sucesso!", response.data);

      setServiceTitle("");
      setRequestDescription("");
      setRegistrationNumber("");
      setSelectedFile(null);
    } catch (error) {
      console.error(error);
    }
  };

  //FIXME Função para lidar com a seleção do arquivo
  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64String = reader.result.split(",")[1];
        setSelectedFile(base64String);
        console.log(base64String);
      };
      reader.readAsDataURL(file);
    }
  };

  // Algumas sugestões de Serviços
  const [services] = useState([
    { id: "1", title: "Solicitação de Trancamento de Matrícula" },
    { id: "2", title: "Emissão de Comprovante de Matrícula" },
    { id: "3", title: "Solicitação de Agendamento de Orientação Acadêmica" },
  ]);

  return (
    <div className="service-request-div">
      <h1>Atendimento</h1>
      <div className="input-group">
        {/* TODO: Ajustar posição de ícones */}
        {/* <BiSearchAlt className='icon' /> */}
        <input
          type="text"
          list="service-list"
          value={serviceTitle}
          onChange={(e) => setServiceTitle(e.target.value)}
          placeholder="Selecione o serviço"
        />
        <datalist id="service-list">
          {services.map((service) => (
            <option key={service.id} value={service.title} />
          ))}
        </datalist>
      </div>
      <div className="input-group">
        <textarea
          value={requestDescription}
          onChange={(e) => setRequestDescription(e.target.value)}
          placeholder="Descreva a solicitação"
          required
        />
      </div>
      <div className="input-group">
        <input
          type="text"
          value={registrationNumber}
          onChange={(e) => setRegistrationNumber(e.target.value)}
          placeholder="Insira a matrícula do(a) estudante"
          required
        />
      </div>
      <div className="input-group">
        {/* TODO: Ajustar posição de ícones */}
        {/* <AiOutlineFileAdd className='icon' /> */}
        <input type="file" onChange={handleFileChange} />
      </div>
      <div className="sending-date">
        <label>Data da solicitação: {showDate}</label>
      </div>
      <button onClick={createRequest}>Enviar solicitação</button>
    </div>
  );
};

export default Atendimento;
