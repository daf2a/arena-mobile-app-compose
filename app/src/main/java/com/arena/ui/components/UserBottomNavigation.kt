package com.arena.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arena.R
import com.arena.ui.theme.Orange

data class BottomNavItem(val title: String, val icon: Int, val screenRoute: String)

@Composable
fun UserBottomNavigation(navController: NavController, selectedTab: String) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .height(72.dp)
            .padding(vertical = 0.dp)
    ) {
        val items = listOf(
            BottomNavItem("Home", R.drawable.ic_home, "user_home"),
            BottomNavItem("Chat", R.drawable.ic_chat, "user_chat_screen"),
            BottomNavItem("Booking", R.drawable.ic_booking, "user_booking_screen"),
            BottomNavItem("Profile", R.drawable.ic_profile, "user_profile_screen")
        )

        items.forEach { item ->
            val isSelected = item.screenRoute == selectedTab
            val iconColor = if (isSelected) Orange else Color(0xFFD2CFD6)
            val textColor = if (isSelected) Orange else Color(0xFFD2CFD6)

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = iconColor
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = textColor
                    )
                },
                selected = isSelected,
                onClick = {
                    if (item.screenRoute != selectedTab) {
                        navController.navigate(item.screenRoute) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Orange,
                    unselectedIconColor = Color(0xFFD2CFD6),
                    selectedTextColor = Orange,
                    unselectedTextColor = Color(0xFFD2CFD6),
                    indicatorColor = Color.White
                ),
                modifier = Modifier.padding(vertical = 0.dp)
            )
        }
    }
}
