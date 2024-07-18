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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.ui.theme.ArenaTheme
import com.arena.R
import com.google.ai.client.generativeai.Chat

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
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* Handle home click */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Chat, contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = false,
                    onClick = { /* Handle chat click */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.AttachMoney, contentDescription = "Finance") },
                    label = { Text("Finance") },
                    selected = false,
                    onClick = { /* Handle finance click */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* Handle profile click */ }
                )
            }
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
fun BottomNavigation(content: @Composable () -> Unit) {
        TODO("Not yet implemented")
}

@Composable
fun BottomNavigationItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
) {
    TODO("Not yet implemented")
}

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
            DashboardButton(icon = Icons.Default.History, label = "Order History")
            DashboardButton(icon = Icons.Default.Chat, label = "Chat")
        }
    }
}

@Composable
fun <ImageVector> DashboardButton(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label)
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
                painter = painterResource(id = R.drawable.placeholder), // Replace with your image
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
                Button(onClick = { navController.navigate("edit_field_screen") }) {
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
