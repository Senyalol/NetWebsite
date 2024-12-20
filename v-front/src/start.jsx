import React from 'react';
import './start.css'; // Подключаем CSS
import { Link, useParams } from 'react-router-dom';

function StartPage() {
    const {id} = useParams();

    return (
        <div className="start-page">
            <h1 className="start-title">Добро пожаловать на главную страницу</h1>
            <div className="button-container">
                <Link to={`/devices/${id}`} className="nav-button">Устройства</Link> 
                <Link to={`/logs/${id}`} className="nav-button">Логи</Link>
                {/* <Link to={`/connections/${id}`} className="nav-button">Соединения</Link> */}
                <Link to={`/sessions/${id}`} className="nav-button">Сессии</Link>
                <Link to={`/users/${id}`} className="nav-button">Пользователи</Link>
                <Link to="/" className="nav-button">К авторизации</Link>
            </div>
        </div>
    );
}

export default StartPage;