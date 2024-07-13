package com.arena.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun OnboardingScreen1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding 1")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("onboarding_screen_2") }) {
            Text(text = "Next")
        }
    }
}

@Composable
fun OnboardingScreen2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding 2")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("onboarding_gate_1") }) {
            Text(text = "Next")
        }
    }
}

@Composable
fun OnboardingGate1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login as User or Mitra")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("login_screen") }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("register_screen") }) {
            Text(text = "Register")
        }
    }
}
