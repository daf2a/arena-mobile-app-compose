package com.arena.ui.user.home.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arena.R
import com.arena.ui.theme.Orange

@Composable
fun HomeTopBar(navController: NavController) {
    var hasNotifications by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Orange, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Lokasi saat ini",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Surabaya, Jawa Timur",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
            IconButton(onClick = { hasNotifications = !hasNotifications }) {
                val icon: Painter = if (hasNotifications) {
                    painterResource(id = R.drawable.ic_notifications_on)
                } else {
                    painterResource(id = R.drawable.ic_notifications_off)
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = CircleShape)
                        .size(40.dp)
                        .border(width = 1.dp, color = Color.White, shape = CircleShape)
                ) {
                    Icon(painter = icon, contentDescription = "Notifications", tint = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        SearchBar(navController)
    }
}
