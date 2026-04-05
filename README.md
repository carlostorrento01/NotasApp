# 📱 NotasApp

Aplicación Android desarrollada en Kotlin usando Jetpack Compose que permite el registro de usuarios, inicio de sesión y cálculo de promedio de notas con validaciones.

---

## 🚀 Funcionalidades

- Registro de usuario (correo y contraseña)
- Inicio de sesión validado
- Ingreso de 3 notas
- Cálculo automático del promedio
- Validación de datos:
  - Solo números
  - Rango permitido: 0 a 10
- Resultado del promedio:
  - Aprobado (≥ 6)
  - Reprobado (< 6)
- Mensajes de error claros para el usuario
- Navegación entre pantallas

---

## 🧠 Lógica de la Aplicación

La aplicación funciona mediante estados controlados en Jetpack Compose:

1. El usuario se registra con un correo válido (formato `@dominio.com`)
2. Luego inicia sesión con los datos registrados
3. Ingresa tres notas
4. El sistema valida:
   - Que sean números
   - Que estén en el rango de 0 a 10
5. Se calcula el promedio
6. Se muestra el resultado con estado:
   - ✅ Aprobado
   - ❌ Reprobado

---

## 🛠️ Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Android Studio
- Material Design 3

---

## 📸 Capturas de pantalla

> (Aquí puedes subir imágenes luego)

- Pantalla de registro
- Pantalla de login
- Pantalla de ingreso de notas
- Pantalla de resultados

---

## ⚠️ Validaciones implementadas

- Formato de correo válido
- Campos obligatorios
- Solo números en notas
- Rango permitido (0 - 10)
- Manejo de errores en tiempo real

---

## 🎯 Objetivo del proyecto

Desarrollar una aplicación móvil que implemente lógica de validación, navegación entre pantallas y manejo de estados utilizando Jetpack Compose.

---

## 👨‍💻 Alumno

- Carlos Torrento

---

## 📌 Notas

Este proyecto fue desarrollado con fines educativos para demostrar el uso de Jetpack Compose en aplicaciones Android modernas.
