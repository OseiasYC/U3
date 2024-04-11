import React, { useState } from 'react';
import './LoginPage.css';
import { useNavigate } from 'react-router-dom';
import { FaUser, FaLock } from "react-icons/fa6";

const Login = ({ onLogin }) => {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        // ALTERAR PARA A LÓGICA DE AUTENTICAÇÃO
        // Verifica se o usuário e a senha estão corretos (preset)
        if (username === 'usuario' && password === 'senha') {
            setError(''); // Limpa qualquer erro anterior

            navigate('/home'); // Redireciona para a página "/home"

            // Chama a função onLogin passada como prop
            if (onLogin) {
                onLogin();
            }
        } else {
            setError('Usuário ou senha incorretos.');
        }
    };

    return (
        <div className='wrapper'>
            <p className='logo-place'>
                <img className='logo-login' src='./U3-logo.png' alt='U3 Logo' />
            </p>
            <form onSubmit={handleSubmit}> {/* Adicione onSubmit aqui */}
                <h1>Login</h1>
                <div className='input-box'>
                    <input type='text' placeholder='Usuário' value={username} onChange={(e) => setUsername(e.target.value)} required />
                    <FaUser className='icon' />
                </div>
                <div className='input-box'>
                    <input type='password' placeholder='Senha' value={password} onChange={(e) => setPassword(e.target.value)} required />
                    <FaLock className='icon' />
                </div>

                <div className='remember-forgot'>
                    <label><input type='checkbox' placeholder='Lembrar senha' />Lembrar senha</label>
                    <a href='#'>Esqueceu sua senha?</a>
                </div>

                <p className={'error-message' + (error ? ' show' : '')}>{error}</p>

                <button type='submit'>Entrar</button>
            </form>
        </div>
    )
}

export default Login;
