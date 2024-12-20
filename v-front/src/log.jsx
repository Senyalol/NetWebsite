import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom'; // Импортируем Link для переходов
import './log.css'; // Подключаем CSS

function LogPage() {
    const { id } = useParams();
    const [logs, setLogs] = useState([]);

    // Загружаем логи при монтировании компонента
    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/logs');
                if (!response.ok) {
                    throw new Error('Ошибка при загрузке логов.');
                }
                const data = await response.json();
                setLogs(data); // Предполагая, что сервер возвращает массив логов
            } catch (error) {
                alert('Ошибка: ' + error.message);
            }
        };

        fetchLogs();
    }, []);

    return (
        <div className="log-page">
            <h1>Журнал действий</h1>

            {/* Кнопка "Назад" */}
            <div className="back-button-container">
                <Link to={`/start/${id}`} className="back-button">
                    Назад
                </Link>
            </div>

            {/* Таблица */}
            <table className="log-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Пользователь ID</th>
                        <th>Действие</th>
                        <th>Время</th>
                        <th>Описание</th>
                    </tr>
                </thead>
                <tbody>
                    {logs.length > 0 ? (
                        logs.map((log) => (
                            <tr key={log.logs_id}>
                                <td>{log.logs_id}</td>
                                <td>{log.user_id}</td>
                                <td>{log.action}</td>
                                <td>{new Date(log.timestamp).toLocaleString()}</td>
                                <td>{log.description}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="5" style={{ textAlign: 'center' }}>Нет логов для отображения.</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default LogPage;