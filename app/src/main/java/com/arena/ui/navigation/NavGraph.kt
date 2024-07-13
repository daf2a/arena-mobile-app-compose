package com.arena.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arena.ui.auth.LoginScreen
import com.arena.ui.auth.RegisterScreen
import com.arena.ui.auth.OnboardingScreen1
import com.arena.ui.auth.OnboardingScreen2
import com.arena.ui.auth.OnboardingGate1
import com.arena.ui.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("onboarding_screen_1") {
            OnboardingScreen1(navController = navController)
        }
        composable("onboarding_screen_2") {
            OnboardingScreen2(navController = navController)
        }
        composable("onboarding_gate_1") {
            OnboardingGate1(navController = navController)
        }
        composable("register_screen") {
            RegisterScreen(navController = navController)
        }
        composable("login_screen") {
            LoginScreen(navController = navController)
        }
    }
}
