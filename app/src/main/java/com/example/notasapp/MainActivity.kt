package com.example.notasapp

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    var screen by remember { mutableStateOf("register") }
    var userEmail by remember { mutableStateOf("") }
    var promedio by remember { mutableStateOf(0.0) }

    when (screen) {
        "register" -> RegisterScreen {
            userEmail = it
            screen = "login"
        }

        "login" -> LoginScreen(userEmail) {
            screen = "welcome"
        }

        "welcome" -> WelcomeScreen(userEmail) {
            screen = "notas"
        }

        "notas" -> NotasScreen {
            promedio = it
            screen = "resultado"
        }

        "resultado" -> ResultadoScreen(promedio) {
            screen = "login"
        }
    }
}

@Composable
fun CenteredCard(content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card {
            Column(modifier = Modifier.padding(20.dp)) {
                content()
            }
        }
    }
}

@Composable
fun RegisterScreen(onRegister: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    CenteredCard {

        Text("Registro", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") })

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                error = "Correo inválido (ej: usuario@gmail.com)"
            } else if (password.length < 4) {
                error = "Contraseña mínimo 4 caracteres"
            } else {
                onRegister(email)
            }
        }) {
            Text("Registrarse")
        }

        Text(error, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun LoginScreen(savedEmail: String, onLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    CenteredCard {

        Text("Inicio de Sesión", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") })

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if (email.isBlank() || password.isBlank()) {
                error = "Complete todos los campos"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                error = "Formato de correo inválido"
            } else if (email != savedEmail) {
                error = "Usuario no registrado"
            } else {
                onLogin()
            }
        }) {
            Text("Ingresar")
        }

        Text(error, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun WelcomeScreen(email: String, onContinue: () -> Unit) {
    CenteredCard {

        Text("Bienvenido ${email.substringBefore("@")}")

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onContinue) {
            Text("Continuar")
        }
    }
}

@Composable
fun NotasScreen(onCalculate: (Double) -> Unit) {
    var n1 by remember { mutableStateOf("") }
    var n2 by remember { mutableStateOf("") }
    var n3 by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    CenteredCard {

        Text("Ingreso de Notas", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = n1, onValueChange = { n1 = it }, label = { Text("Nota 1") })
        OutlinedTextField(value = n2, onValueChange = { n2 = it }, label = { Text("Nota 2") })
        OutlinedTextField(value = n3, onValueChange = { n3 = it }, label = { Text("Nota 3") })

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            val notas = listOf(n1, n2, n3)

            if (notas.any { it.isBlank() }) {
                error = "Complete todas las notas"
                return@Button
            }

            try {
                val valores = notas.map { it.toDouble() }

                if (valores.any { it < 0 || it > 10 }) {
                    error = "Notas deben estar entre 0 y 10"
                    return@Button
                }

                val promedio = valores.average()
                onCalculate(promedio)

            } catch (e: Exception) {
                error = "Solo números válidos"
            }
        }) {
            Text("Calcular Promedio")
        }

        Text(error, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun ResultadoScreen(promedio: Double, onRestart: () -> Unit) {
    CenteredCard {

        Text("Resultado", style = MaterialTheme.typography.titleLarge)
        Text("Promedio: %.2f".format(promedio))

        if (promedio >= 6) {
            Text("Aprobado", color = MaterialTheme.colorScheme.primary)
        } else {
            Text("Reprobado", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onRestart) {
            Text("Volver")
        }
    }
}