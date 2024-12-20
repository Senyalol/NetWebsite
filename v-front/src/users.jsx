import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom'; // Для перехода между страницами
import './users.css'; // Подключение CSS

function UserPage() {
  const { id } = useParams();
  const [users, setUsers] = useState([]);

  // Загружаем пользователей при монтировании компонента
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/users');
        if (!response.ok) {
          throw new Error('Ошибка при загрузке пользователей.');
        }
        const data = await response.json();
        setUsers(data); // Предполагаем, что server возвращает массив пользователей
      } catch (error) {
        alert('Ошибка: ' + error.message);
      }
    };

    fetchUsers();
  }, []);

  return (
    <div className="user-page">
      <h1>Информация о пользователях</h1>
      <table className="user-table">
        <thead>
          <tr>
            <th>ID пользователя</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((user) => (
              <tr key={user.user_id}>
                <td>{user.user_id}</td>
                <td>{user.username}</td>
                <td>{user.password}</td>
                <td>{user.email}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" style={{ textAlign: 'center' }}>Нет пользователей для отображения.</td>
            </tr>
          )}
        </tbody>
      </table>
      {/* Кнопка для возврата на страницу /start */}
      <div className="back-button">
        <Link to={`/start/${id}`}>Назад</Link>
      </div>
    </div>
  );
}

export default UserPage;