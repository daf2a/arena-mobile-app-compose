package com.arena.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arena.R
import com.arena.domain.model.Venue
import com.arena.ui.auth.LoginScreen
import com.arena.ui.auth.OnboardingGate
import com.arena.ui.auth.OnboardingScreen
import com.arena.ui.auth.RegisterScreen
import com.arena.ui.mitra.*
import com.arena.ui.user.booking.BookingScreen
import com.arena.ui.user.chat.ChatRoomScreen
import com.arena.ui.splash.SplashScreen
import com.arena.ui.user.chat.ChatScreen
import com.arena.ui.user.home.detailvenue.VenueDetailScreen
import com.arena.ui.user.home.homescreen.UserHomeScreen
import com.arena.ui.user.home.listvenue.UserListVenueScreen
import com.arena.ui.user.home.search.SearchScreen
import com.arena.ui.user.home.selectfield.SelectFieldScreen
import com.arena.ui.user.orderreview.OrderReviewScreen
import com.arena.ui.user.payment.PaymentSuccessScreen
import com.arena.ui.user.profile.ProfileScreen
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(startDestination: String = "user_home") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        // Splash
        composable("splash_screen") { SplashScreen(navController = navController) }

        // Auth
        composable("onboarding_screen") { OnboardingScreen(navController = navController) }
        composable("onboarding_gate") { backStackEntry ->
            OnboardingGate(navController = navController)
        }
        composable("register_screen/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role")?.toInt() ?: 0
            RegisterScreen(navController = navController, role = role)
        }
        composable("login_screen") { backStackEntry ->
            LoginScreen(navController = navController)
        }

        // User
        composable("user_home") { UserHomeScreen(navController = navController) }
        composable("user_venue_detail/{venue}") { backStackEntry ->
            val venueJson = backStackEntry.arguments?.getString("venue")
            val venue = Gson().fromJson(venueJson, Venue::class.java)
            VenueDetailScreen(navController = navController, venue = venue)
        }
        composable(
            "list_venue_screen/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "List Venue"
            UserListVenueScreen(navController, title)
        }
        composable("search_screen") { SearchScreen(navController = navController) }
        composable("select_field_screen") {
            SelectFieldScreen(navController = navController)
        }
        composable("order_review_screen") {
            val venue = com.arena.ui.user.orderreview.Venue(
                name = "Aurora Futsal",
                location = "Kepulih Gg.ID No.15",
                image = R.drawable.iv_venue_2,
                sessions = listOf("17:00", "18:00", "19:00"),
                sessionPrices = listOf(50, 50, 75)
            )
            OrderReviewScreen(navController = navController, venue = venue)
        }
        composable("payment_success_screen") {
            PaymentSuccessScreen(navController = navController)
        }
        composable("user_chat_screen") { ChatScreen(navController = navController) }
        composable("user_chat_room/{chatName}") { backStackEntry ->
            val chatName = backStackEntry.arguments?.getString("chatName") ?: ""
            ChatRoomScreen(navController = navController, chatName = chatName)
        }
        composable("user_booking_screen") { BookingScreen(navController = navController) }
        composable("user_profile_screen") { ProfileScreen(navController = navController) }

        // Mitra
        composable("mitra_home_screen") { MitraHomeScreen(navController) }
        composable("mitra_list_venue_screen") { ListVenueScreen(navController) }
        composable("mitra_new_venue_screen") { NewVenueScreen(navController) }
        composable("mitra_new_field_screen") { NewFieldScreen(navController) }
        composable("mitra_detail_field_screen") { DetailFieldScreen(navController) }
        composable("mitra_edit_field_screen") { EditFieldScreen(navController) }
    }
}
