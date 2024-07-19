package com.arena.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.ui.components.UserBottomNavigation
import com.arena.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("user_profile_screen") }

    Scaffold(
        topBar = {
            ProfileTopBar()
        },
        content = { paddingValues ->
            ProfileContent(paddingValues)
        },
        bottomBar = {
            UserBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    navController.navigate(it) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    )
}

@Composable
fun ProfileTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile",
            tint = Color.Black
        )
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Composable
fun ProfileContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFF7F7F9))
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItemGroup(
            items = listOf(
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_edit_profile),
                    title = "Edit profile information"
                ),
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_notification_line),
                    title = "Notifications",
                    trailingText = "ON"
                ),
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_language),
                    title = "Language",
                    trailingText = "English"
                )
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItemGroup(
            items = listOf(
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_security),
                    title = "Security"
                ),
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_theme),
                    title = "Theme",
                    trailingText = "Light mode"
                )
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItemGroup(
            items = listOf(
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_help),
                    title = "Help & Support"
                ),
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_contact),
                    title = "Contact us"
                ),
                ProfileItemData(
                    icon = painterResource(id = R.drawable.ic_privacy_policy),
                    title = "Privacy policy"
                )
            )
        )
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Transparent)
                .clip(CircleShape)
        ) {
            Image(
                painter = rememberImagePainter(R.drawable.iv_category_5),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Puerto Rico",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Text(
            text = "youremail@domain.com | +01 234 567 89",
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

data class ProfileItemData(
    val icon: Painter,
    val title: String,
    val trailingText: String? = null
)

@Composable
fun ProfileItemGroup(items: List<ProfileItemData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        items.forEachIndexed { index, item ->
            ProfileItem(
                icon = item.icon,
                title = item.title,
                trailingText = item.trailingText,
                onClick = { /* Handle click */ }
            )
            if (index < items.size - 1) {
                Divider(color = Color(0xFFEAEAEA))
            }
        }
    }
}

@Composable
fun ProfileItem(icon: Painter, title: String, trailingText: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        trailingText?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF808089)
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}