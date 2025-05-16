
# 📄 API de Microservicio `usuarios` – Documentación de Endpoints

Este documento contiene todos los endpoints disponibles del microservicio de usuarios, con ejemplos de objetos JSON para usar en herramientas como Postman.

---

## 🔹 POST `/usuarios/registrar`
**Descripción:** Registrar un nuevo usuario.  
**Tipo:** `POST`

```json
{
  "nombre": "juan123",
  "correo_electronico": "juan@example.com",
  "direccion": "Calle Falsa 123",
  "contrasena": "password123"
}
```

---

## 🔹 PUT `/usuarios/registrar`
**Descripción:** Actualizar un usuario existente.  
**Tipo:** `PUT`

```json
{
  "u_id": 1,
  "u_nombre": "juan123",
  "u_correo_electronico": "nuevo_correo@example.com",
  "u_direccion": "Calle Nueva 456",
  "u_contrasena": "nuevaPassword123"
}
```

---

## 🔹 DELETE `/usuarios`
**Descripción:** Eliminar un usuario por nombre y contraseña.  
**Tipo:** `DELETE`

```json
{
  "nombre": "juan123",
  "contrasena": "nuevaPassword123"
}
```

---

## 🔹 POST `/usuarios/validar`
**Descripción:** Validar un usuario por nombre y contraseña.  
**Tipo:** `POST`

```json
{
  "nombre": "juan123",
  "contrasena": "nuevaPassword123"
}
```

---

## 🔹 GET `/usuarios/todos`
**Descripción:** Obtener todos los usuarios.  
**Tipo:** `GET`  
**Cuerpo:** No requerido

**URL completa:**  
```
http://localhost:8502/usuarios/todos
```

---

## 🔹 GET `/usuarios/info/id/{id}`
**Descripción:** Obtener nombre del usuario por ID.  
**Tipo:** `GET`

**Ejemplo URL:**  
```
http://localhost:8502/usuarios/info/id/1
```

---

## 🔹 GET `/usuarios/info/nombre/{nombre}`
**Descripción:** Obtener info del usuario por nombre.  
**Tipo:** `GET`

**Ejemplo URL:**  
```
http://localhost:8502/usuarios/info/nombre/juan123
```

---

## 🔹 GET `/usuarios/checkIfExist/{id}`
**Descripción:** Comprobar si existe un usuario con ID.  
**Tipo:** `GET`

**Ejemplo URL:**  
```
http://localhost:8502/usuarios/checkIfExist/1
```

---

## 🔹 GET `/usuarios/test`
**Descripción:** Comprobación del funcionamiento de la app.  
**Tipo:** `GET`

**URL completa:**  
```
http://localhost:8502/usuarios/test
```

---

### ⚠️ Notas

- Asegúrate de que el microservicio `usuarios` está corriendo en el puerto `8502`.
- Usa `Content-Type: application/json` en headers para peticiones `POST`, `PUT` y `DELETE`.
- Si Postman lanza `ECONNREFUSED`, verifica que la aplicación esté levantada correctamente.

---

**Autor:** Sistema de Microservicios – Proyecto Java Spring Boot  
**Formato compatible con:** GitHub, VS Code, Markdown Preview, y herramientas Markdown online.
