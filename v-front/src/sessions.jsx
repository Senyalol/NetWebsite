import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom'; // Для перехода между страницами
import './sessions.css'; // Подключение CSS

function SessionsPage() {
    const { id } = useParams();
    const [sessions, setSessions] = useState([]);

    // Загружаем сессии при монтировании компонента
    useEffect(() => {
        const fetchSessions = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/sessions');
                if (!response.ok) {
                    throw new Error('Ошибка при загрузке сессий.');
                }
                const data = await response.json();
                setSessions(data); // Предполагаем, что сервер возвращает массив сессий
            } catch (error) {
                alert('Ошибка: ' + error.message);
            }
        };

        fetchSessions();
    }, []);

    return (
        <div className="sessions-page">
            <h1>Список сессий</h1>

            {/* Кнопка "Назад" */}
            <div className="back-button-container">
                <Link to={`/start/${id}`} className="back-button">
                    Назад
                </Link>
            </div>

            {/* Таблица */}
            <table className="sessions-table">
                <thead>
                    <tr>
                        <th>ID устройства</th>
                        <th>ID сессии</th>
                        <th>Время начала сессии</th>
                        <th>Время конца сессии</th>
                    </tr>
                </thead>
                <tbody>
                    {sessions.length > 0 ? (
                        sessions.map((session) => (
                            <tr key={session.session_id}>
                                <td>{session.device_id}</td>
                                <td>{session.session_id}</td>
                                <td>{new Date(session.startTime).toLocaleString()}</td>
                                <td>{new Date(session.endTime).toLocaleString()}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: 'center' }}>Нет сессий для отображения.</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default SessionsPage;