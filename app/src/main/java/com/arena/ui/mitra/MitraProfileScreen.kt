package com.arena.ui.mitra

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.ui.components.BottomNavItem
import com.arena.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MitraProfileScreen() {
    var username by remember { mutableStateOf("arena") }
    var name by remember { mutableStateOf("arena") }
    var gender by remember { mutableStateOf("Laki-laki") }
    var email by remember { mutableStateOf("arena@gmail.com") }
    var phone by remember { mutableStateOf("(+62) XXX-XXXX-XXXX") }
    var address by remember { mutableStateOf("Jl. Raya No. 1, Jakarta") }
    var bankAccount by remember { mutableStateOf("1234 5678 9101 1121") }
    var expandedAccountInfo by remember { mutableStateOf(true) }
    var expandedSettings by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            MitraHomeBottomNavigation(selectedTab = "profile_screen", onTabSelected = {})
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { Text(text = "Profile", color = Color.Black) },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile Icon",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.White),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "@$username",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 14.dp)
            ) {
                Button(
                    onClick = { /* Edit profile action */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Edit Profil", color = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                }
                Button(
                    onClick = { /* Share action */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Orange),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Share", color = Orange)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Share, contentDescription = "Share", tint = Orange)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MitraAccountInfoSection("Informasi Akun", name, gender, email, phone, address, bankAccount, expandedAccountInfo) { expandedAccountInfo = !expandedAccountInfo }

            Spacer(modifier = Modifier.height(8.dp))

            MitraSettingsSection("Pengaturan", expandedSettings) { expandedSettings = !expandedSettings }
        }
    }
}

@Composable
fun MitraAccountInfoSection(header: String, name: String, gender: String, email: String, phone: String, address: String, bankAccount: String, expanded: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
                RoundedCornerShape(4.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource(id = R.drawable.ic_profile), contentDescription = "Account Info", tint = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = header,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f),
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = if (expanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up),
                contentDescription = "Expand/Collapse",
                tint = Color.Gray
            )
        }

        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                MitraFieldWithLine("Nama", name)
                MitraFieldWithLine("Gender", gender)
                MitraFieldWithLine("Email", email)
                MitraFieldWithLine("No Telpon", phone)
                MitraFieldWithLine("Alamat", address)
                MitraFieldWithLine("Akun Bank", bankAccount)
            }
        }
    }
}

@Composable
fun MitraFieldWithLine(field: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 12.dp, end = 12.dp)
    ) {
        Text(field, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray.copy(alpha = 0.2f))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, color = Color.Black, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun MitraSettingsSection(header: String, expanded: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
                RoundedCornerShape(4.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource(id = R.drawable.ic_settings), contentDescription = "Settings", tint = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = header,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f),
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
                contentDescription = "Expand/Collapse",
                tint = Color.Gray
            )
        }

        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { /* Logout action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}

@Composable
fun MitraHomeBottomNavigation(selectedTab: String, onTabSelected: (String) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .height(72.dp)
            .padding(vertical = 0.dp)

    ) {
        val items = listOf(
            BottomNavItem("Home", R.drawable.ic_home, "home_screen"),
            BottomNavItem("Chat", R.drawable.ic_chat, "chat_screen"),
            BottomNavItem("Booking", R.drawable.ic_booking, "booking_screen"),
            BottomNavItem("Profile", R.drawable.ic_profile, "profile_screen")
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
                onClick = { onTabSelected(item.screenRoute) },
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

@Preview(showBackground = true)
@Composable
fun MitraProfileScreenPreview() {
    MitraProfileScreen()
}
