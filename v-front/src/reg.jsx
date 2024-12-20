import React, { useState } from 'react';
import './aut.css'; // Подключаем CSS
import { Link, useNavigate } from 'react-router-dom';

function RegPage() {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [errorMessage, setErrorMessage] = useState(''); // Для отображения ошибок

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Login:', login);
        console.log('Password:', password);
        console.log('Email:', email);

        // Подготовка данных для отправки
        const payload = {
            user_id: null, // Сервер может назначить user_id автоматически
            username: login,
            email: email,
            password: password,
            createdAt: new Date().toISOString(), // Текущая дата и время
        };

        try {
            const response = await fetch('http://localhost:8080/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error('Ошибка при регистрации. Проверьте введенные данные.');
            }

            const data = await response.json();
            console.log('Пользователь зарегистрирован:', data);
            navigate('/'); // Перенаправление после успешной регистрации
            alert("Вы успешно создали аккаунт!");
        } catch (error) {
            console.error('Ошибка при регистрации:', error);
            setErrorMessage('Ошибка при регистрации: ' + error.message); // Установка сообщения об ошибке
        }
    };

    return (
        <div className="auth-wrapper">
            <div className="auth-card">
                <h1 className="auth-title">Регистрация</h1>
                <p className="auth-subtitle">Создайте аккаунт для входа</p>
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
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            placeholder="Введите email"
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
                        Зарегистрироваться
                    </button>
                </form>
                <div className="auth-footer">
                    <p>
                        Уже есть аккаунт? <Link to="/">Войти</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default RegPage;