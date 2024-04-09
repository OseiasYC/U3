import React from 'react';
import './Login.css';
import { FaUser, FaLock } from "react-icons/fa6";

const Login = () => {
    return (
        <div className='wrapper'>
            <p className='logo-place'>
                <img className='logo' src='./U3-white-logo.png' alt='U3 White Logo' />
            </p>
            <form action=''>
                <h1>Login</h1>
                <div className='input-box'>
                    <input type='text' placeholder='Usuário' required />
                    <FaUser className='icon' />
                </div>
                <div className='input-box'>
                    <input type='password' placeholder='Senha' required />
                    <FaLock className='icon' />
                </div>

                <div className='remember-forgot'>
                    <label><input type='checkbox' placeholder='Username' required />Lembrar senha</label>
                    <a href='#'>Esqueceu sua senha?</a>
                </div>

                <button type='submit'>Entrar</button>
            </form>
        </div>
    )
}

export default Login
