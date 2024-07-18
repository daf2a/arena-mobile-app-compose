package com.arena.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arena.domain.model.Venue
import com.arena.ui.auth.*
import com.arena.ui.splash.SplashScreen
import com.arena.ui.user.HomeScreen
import com.arena.ui.user.BookingScreen
import com.arena.ui.user.ChatRoomScreen
import com.arena.ui.user.ChatScreen
import com.arena.ui.user.ProfileScreen
import com.arena.ui.venue.VenueDetailScreen
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "user_home") {
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
        composable("chat_screen") { ChatScreen(navController = navController) }
        composable("chat_room/{chatName}") { backStackEntry ->
            val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
            ChatRoomScreen(navController = navController, chatName = chatName)
        }
        composable("booking_screen") { BookingScreen(navController = navController) }
        composable("profile_screen") { ProfileScreen(navController = navController) }
    }
}

