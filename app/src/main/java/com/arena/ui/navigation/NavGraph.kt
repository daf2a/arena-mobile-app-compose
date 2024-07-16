package com.arena.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arena.domain.model.Venue
import com.arena.ui.auth.*
import com.arena.ui.splash.SplashScreen
import com.arena.ui.user.HomeScreen
import com.arena.ui.venue.VenueDetailScreen
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController = navController) }
        composable("onboarding_screen_1") { OnboardingScreen1(navController = navController) }
        composable("onboarding_screen_2") { OnboardingScreen2(navController = navController) }
        composable("onboarding_gate_1") { OnboardingGate1(navController = navController) }
        composable("onboarding_gate_2") { OnboardingGate2(navController = navController) }
        composable("register_screen") { RegisterScreen(navController = navController) }
        composable("login_screen") { LoginScreen(navController = navController) }
        composable("user_home") { HomeScreen(navController = navController) }
        composable("venue_detail/{venue}") { backStackEntry ->
            val venueJson = backStackEntry.arguments?.getString("venue")
            val venue = Gson().fromJson(venueJson, Venue::class.java)
            VenueDetailScreen(navController = navController, venue = venue)
        }
    }
}
