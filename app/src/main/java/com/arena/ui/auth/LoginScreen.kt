package com.arena.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.data.network.RetrofitInstance
import com.arena.data.repository.AuthRepository
import com.arena.ui.theme.ArenaTheme
import com.arena.ui.theme.Orange
import com.arena.ui.theme.GreyInputBg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    val authRepository = AuthRepository(RetrofitInstance.api)
    var role by remember { mutableStateOf("3") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign In", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "It was popularised in the 1960s with the release of Letraset sheets containing",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Facebook")
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Google")
                }
            }
            Text(
                text = "Or",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email/Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = GreyInputBg,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painterResource(id = image), contentDescription = null)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = GreyInputBg,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Forget Password?",
                style = MaterialTheme.typography.bodySmall,
                color = Orange,
                modifier = Modifier.align(Alignment.End).clickable { /* Navigate to forget password screen */ }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val response = authRepository.login(email, password)
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful && response.body()?.status == "success") {
                                (response.body()?.role ?: "").also { role = it }
                                if (role == "2") {
                                    navController.navigate("mitra_home_screen")
                                } else {
                                    navController.navigate("user_home")
                                }
                            } else {
                                loginError = response.body()?.message ?: "Login Failed"
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Orange)
            ) {
                Text(text = "Log In")
            }
            loginError?.let {
                Text(text = it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don’t have an account? ", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Orange,
                    modifier = Modifier.clickable { navController.navigate("register_screen/$role") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ArenaTheme {
        LoginScreen(navController = rememberNavController())
    }
}
