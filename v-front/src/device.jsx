import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './device.css'; // Подключаем CSS

function DevPage() {
  const [devices, setDevices] = useState([]);
  const { id } = useParams(); // Получаем id пользователя из URL

  const [newDevice, setNewDevice] = useState({
    device: '',
    name: '',
    ip: '',
    mac: '',
  });

  // Загрузка устройств при монтировании компонента
  useEffect(() => {
    const fetchDevices = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/devices?user_id=${id}`);
        if (!response.ok) {
          throw new Error('Ошибка при загрузке устройств.');
        }
        const data = await response.json();
        setDevices(data);
      } catch (error) {
        alert('Ошибка: ' + error.message);
      }
    };

    fetchDevices();
  }, [id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewDevice({ ...newDevice, [name]: value });
  };

  const addDevice = async () => {
    if (newDevice.device && newDevice.name && newDevice.ip && newDevice.mac) {
      const payload = {
        user_id: parseInt(id),
        deviceName: newDevice.name,
        deviceType: newDevice.device,
        ipAddress: newDevice.ip,
        macAddress: newDevice.mac,
        createdAt: new Date().toISOString(),
      };

      try {
        const response = await fetch('http://localhost:8080/api/devices', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(payload),
        });

        if (!response.ok) {
          throw new Error('Ошибка при добавлении устройства.');
        }

        const data = await response.json();
        console.log('Устройство добавлено:', data);

        // Обновляем состояние с новым устройством
        setDevices([...devices, payload]);
        setNewDevice({ device: '', name: '', ip: '', mac: '' }); // Сброс полей ввода
      } catch (error) {
        alert('Ошибка: ' + error.message);
      }
    } else {
      alert('Заполните все поля перед добавлением устройства.');
    }
  };

  const deleteDevice = async (index) => {
    const deviceToDelete = devices[index];

    try {
      const response = await fetch(`http://localhost:8080/api/devices/${deviceToDelete.device_id}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Ошибка при удалении устройства.');
      }

      const updatedDevices = devices.filter((_, i) => i !== index);
      setDevices(updatedDevices);
    } catch (error) {
      alert('Ошибка: ' + error.message);
    }
  };

  return (
    <div className="device-page">
      <h1>Список устройств</h1>

      <table className="device-table">
        <thead>
          <tr>
            <th>Устройство</th>
            <th>Имя устройства</th>
            <th>IP адрес</th>
            <th>MAC адрес</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {devices.length > 0 ? (
            devices.map((device, index) => (
              <tr key={index}>
                <td>{device.deviceType}</td>
                <td>{device.deviceName}</td>
                <td>{device.ipAddress}</td>
                <td>{device.macAddress}</td>
                <td>
                  <button onClick={() => deleteDevice(index)} className="delete-button">Удалить</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" style={{ textAlign: 'center' }}>Нет устройств. Добавьте новое устройство.</td>
            </tr>
          )}
        </tbody>
      </table>

      <div className="add-device-form">
        <h2>Добавить новое устройство</h2>
        <input
          type="text"
          name="device"
          placeholder="Тип устройства"
          value={newDevice.device}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="name"
          placeholder="Имя устройства"
          value={newDevice.name}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="ip"
          placeholder="IP адрес"
          value={newDevice.ip}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="mac"
          placeholder="MAC адрес"
          value={newDevice.mac}
          onChange={handleInputChange}
        />
        <button onClick={addDevice} className="add-button">Добавить</button>
      </div>

      <div className="back-button-container">
        <Link to={`/start/${id}`} className="back-button">Назад</Link>
      </div>
    </div>
  );
}

export default DevPage;