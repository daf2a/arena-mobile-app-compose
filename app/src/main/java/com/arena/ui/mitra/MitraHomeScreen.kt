package com.arena.ui.mitra

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.ui.theme.ArenaTheme
import com.arena.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MitraHomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hai, Admin") },
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LocationSection()
                Spacer(modifier = Modifier.height(16.dp))
                DashboardSection()
                Spacer(modifier = Modifier.height(16.dp))
                VenueSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                MonthlyRevenueSection()
            }
        }
    )
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(72.dp)
    ) {
        val items = listOf(
            BottomNavItem("Home", R.drawable.ic_home, "mitra_home_screen"),
            BottomNavItem("Chat", R.drawable.ic_chat, "mitra_chat_screen"),
            BottomNavItem("Booking", R.drawable.ic_booking, "mitra_booking_screen"),
            BottomNavItem("Profile", R.drawable.ic_profile, "mitra_profile_screen")
        )

        items.forEach { item ->
            val isSelected = navController.currentBackStackEntry?.destination?.route == item.screenRoute
            val iconColor = if (isSelected) Color.Blue else Color.Gray
            val textColor = if (isSelected) Color.Blue else Color.Gray

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
                    navController.navigate(item.screenRoute) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val icon: Int, val screenRoute: String)

@Composable
fun LocationSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(Icons.Default.LocationOn, contentDescription = "Location")
        Text(text = "Surabaya, Jawa Timur", fontSize = 16.sp)
    }
}

@Composable
fun DashboardSection() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "My Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DashboardButton(icon = Icons.Default.List, label = "Venue List")
            DashboardButton(icon = Icons.Default.Done, label = "Order History")
            DashboardButton(icon = Icons.Default.Done, label = "Chat")
        }
    }
}

@Composable
fun <ImageVector> DashboardButton(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = { /* Handle click */ }) {
            Icons.Default.Done
        }
        Text(text = label)
    }
}

@Composable
fun VenueSection(navController: NavHostController) {
    Column(horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "My Venue", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* Handle see all click */ }) {
                Text(text = "Lihat Semua")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(2) { index ->
                VenueCard(navController, index)
            }
        }
    }
}

@Composable
fun VenueCard(navController: NavHostController, index: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.iv_venue_2),
                contentDescription = "Venue Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (index == 0) "BBS Futsal Sport" else "MPN Arena",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Kepuith Gg 1D No.50, Surabaya",
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Rp ${if (index == 0) "30.000" else "40.000"} /jam",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navController.navigate("mitra_edit_field_screen") }) {
                    Text(text = "Edit")
                }
            }
        }
    }
}

@Composable
fun MonthlyRevenueSection() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Monthly Revenue", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Add content for Monthly Revenue Section
    }
}

@Preview(showBackground = true)
@Composable
fun MitraHomeScreenPreview() {
    ArenaTheme {
        MitraHomeScreen(navController = rememberNavController())
    }
}
