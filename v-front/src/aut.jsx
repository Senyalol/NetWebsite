// authorization.jsx
import React, { useState } from 'react';
import './aut.css'; // Подключаем CSS
import { Link, useNavigate } from 'react-router-dom';

function AutPage() {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState(''); // Для отображения ошибок

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Login:', login);
        console.log('Password:', password);

        try {
            const response = await fetch('http://localhost:8080/api/users'); // GET запрос для получения всех пользователей

            if (!response.ok) {
                throw new Error('Ошибка получения данных с сервера.');
            }

            const users = await response.json();

            // Поиск пользователя с соответствующим логином и паролем
            const user = users.find(user => user.username === login && user.password === password);

            if (user) {
                navigate(`/start/${user.user_id}`); // Перенаправление с id пользователя
            } else {
                setErrorMessage('Неверные учетные данные'); // Установка сообщения об ошибке
            }
        } catch (error) {
            console.error('Ошибка при авторизации:', error);
            setErrorMessage('Ошибка при авторизации: ' + error.message); // Установка сообщения об ошибке
        }
    };

    return (
        <div className="auth-wrapper">
            <div className="auth-card">
                <h1 className="auth-title">Добро пожаловать</h1>
                <p className="auth-subtitle">Введите свои данные для входа</p>
                <form onSubmit={handleSubmit} className="auth-form">
                    <div className="form-group">
                        <label htmlFor="login">Логин</label>
                        <input
                            type="text"
                            id="login"
                            value={login}
                            onChange={(e) => setLogin(e.target.value)}
                            required
                            placeholder="Введите логин"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Пароль</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            placeholder="Введите пароль"
                        />
                    </div>
                    {errorMessage && <p className="error-message">{errorMessage}</p>} {/* Отображение сообщения об ошибке */}
                    <button type="submit" className="auth-button">
                        Войти
                    </button>
                </form>
                <div className="auth-footer">
                    <p>
                        Нет аккаунта? <Link to="/register">Создать</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default AutPage;