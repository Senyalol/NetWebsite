import React, { useState } from 'react';
import { Link , useParams} from 'react-router-dom'; // Для перехода между страницами
import './connect.css'; // Подключение CSS

function ConPage() {
    // Состояние для хранения данных таблицы
    const [entries, setEntries] = useState([]);
    const {id} = useParams();

    // Функция для добавления новой строки
    const addEntry = () => {
        setEntries([
            ...entries,
            { id: Date.now(), name: '', subnet: '', gateway: '' }, // Уникальный ID
        ]);
    };

    // Функция для удаления строки
    const deleteEntry = (id) => {
        setEntries(entries.filter((entry) => entry.id !== id));
    };

    // Функция для обработки изменений в полях таблицы
    const handleInputChange = (id, field, value) => {
        setEntries(
            entries.map((entry) =>
                entry.id === id ? { ...entry, [field]: value } : entry
            )
        );
    };

    return (
        <div className="connect-page">
            <h1>Сети</h1>

            {/* Кнопка "Назад" */}
            <div className="back-button-container">
                <Link to={`/start/${id}`} className="back-button">
                    Назад
                </Link>
            </div>

            {/* Таблица */}
            <table className="connect-table">
                <thead>
                    <tr>
                        <th>Имя сети</th>
                        <th>Подсеть</th>
                        <th>Шлюз</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    {entries.map((entry) => (
                        <tr key={entry.id}>
                            <td>
                                <input
                                    type="text"
                                    value={entry.name}
                                    onChange={(e) =>
                                        handleInputChange(entry.id, 'name', e.target.value)
                                    }
                                    placeholder="Введите имя сети"
                                />
                            </td>
                            <td>
                                <input
                                    type="text"
                                    value={entry.subnet}
                                    onChange={(e) =>
                                        handleInputChange(entry.id, 'subnet', e.target.value)
                                    }
                                    placeholder="Введите подсеть"
                                />
                            </td>
                            <td>
                                <input
                                    type="text"
                                    value={entry.gateway}
                                    onChange={(e) =>
                                        handleInputChange(entry.id, 'gateway', e.target.value)
                                    }
                                    placeholder="Введите шлюз"
                                />
                            </td>
                            <td>
                                <button
                                    className="delete-button"
                                    onClick={() => deleteEntry(entry.id)}
                                >
                                    Удалить
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* Кнопка "Добавить" */}
            <div className="add-button-container">
                <button className="add-button" onClick={addEntry}>
                    Добавить
                </button>
            </div>
        </div>
    );
}

export default ConPage;