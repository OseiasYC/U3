import React, { useState } from 'react';
import { AiOutlineFileAdd } from "react-icons/ai";
import { BiSearchAlt } from "react-icons/bi";
import './Atendimento.css';

// TODO: Criar a lógica de lançamento ao banco de dados 

const Atendimento = () => {
  const [serviceTitle, setServiceTitle] = useState('');
  const [requestDescription, setRequestDescription] = useState('');
  const [registrationNumber, setRegistrationNumber] = useState('');
  const [sendingDate] = useState(new Date().toLocaleString());
  // Criar lógica de armazenamento de arquivo no db:
  // const [file, setFile] = useState<File | null>(null);

  const [services, setServices] = useState([
    { id: '1', title: 'Solicitação de Trancamento de Matrícula' },
    { id: '2', title: 'Emissão de Comprovante de Matrícula' },
    { id: '3', title: 'Solicitação de Agendamento de Orientação Acadêmica' },
  ]); // Simulated data for search dropdown

  return (
    <div className='service-request-div'>
      <h1>Atendimento</h1>
      <div className='input-group'>
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
      <div className='input-group'>
        <textarea 
          placeholder="Descreva a solicitação"
          value={requestDescription}
          onChange={(e) => setRequestDescription(e.target.value)} 
          required
        />
      </div>
      <div className='input-group'>
        <input 
          type="text" 
          placeholder="Insira a matrícula do(a) estudante"
          value={registrationNumber}
          onChange={(e) => setRegistrationNumber(e.target.value)} 
          required
        />
      </div>
      <div className='input-group'>
        {/* TODO: Ajustar posição de ícones */}
        {/* <AiOutlineFileAdd className='icon' /> */}
        <input 
          type="file" 
          // Criar lógica de armazenamento de arquivo no db:
          // onChange={(e) => setFile(e.target.files ? e.target.files[0] : null)} 
        />
      </div>
      <div className='sending-date'>
        <label>Data da solicitação: {sendingDate}</label>
      </div>
      <button>Enviar solicitação</button>
    </div>
  );
}

export default Atendimento;