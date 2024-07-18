package com.arena.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arena.ui.auth.LoginScreen
import com.arena.ui.auth.RegisterScreen
import com.arena.ui.mitra.*

@Composable
fun NavGraph(startDestination: String = "login_screen") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login_screen") { LoginScreen(navController) }
        composable("register_screen") { RegisterScreen(navController) }
        composable("home_screen") { MitraHomeScreen(navController) }
        composable("list_venue_screen") { ListVenueScreen(navController) }
        composable("new_venue_screen") { NewVenueScreen(navController) }
        composable("new_field_screen") { NewFieldScreen(navController) }
        composable("detail_field_screen") { DetailFieldScreen(navController) }
        composable("edit_field_screen") { EditFieldScreen(navController) }
    }
}
