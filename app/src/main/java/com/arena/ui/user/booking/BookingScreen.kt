package com.arena.ui.user.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arena.R
import com.arena.ui.components.UserBottomNavigation
import com.arena.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("user_booking_screen") }

    Scaffold(
        content = { paddingValues ->
            BookingContent(paddingValues, navController)
        },
        bottomBar = {
            UserBottomNavigation(
                navController = navController,
                selectedTab = selectedTab
            )
        }
    )
}

@Composable
fun BookingContent(paddingValues: PaddingValues, navController: NavController) {
    val bookings = remember {
        listOf(
            Booking("12/01/2022", "Menunggu Pembayaran", "Nama Venue 1", "Jenis Lapangan 1", "Rp 8.000.000", Color(0xFFCC8100), Color(0xFFFFF5C7)),
            Booking("12/01/2022", "Aktif", "Nama Venue 2", "Jenis Lapangan 2", "Rp 8.000.000", Color(0xFF007D3A), Color(0xFFD7FAE0)),
            Booking("11/01/2022", "Gagal", "Nama Venue 3", "Jenis Lapangan 3", "Rp 8.000.000", Color(0xFFFF424F), Color(0xFFFFF0F1)),
            Booking("11/01/2022", "Selesai", "Nama Venue 4", "Jenis Lapangan 4", "Rp 8.000.000", Color(0xFF808089), Color(0xFFEBEBF0)),
            Booking("10/01/2022", "Aktif", "Nama Venue 5", "Jenis Lapangan 5", "Rp 8.000.000", Color(0xFF007D3A), Color(0xFFD7FAE0)),
            Booking("10/01/2022", "Gagal", "Nama Venue 6", "Jenis Lapangan 6", "Rp 8.000.000", Color(0xFFFF424F), Color(0xFFFFF0F1)),
            Booking("09/01/2022", "Selesai", "Nama Venue 7", "Jenis Lapangan 7", "Rp 8.000.000", Color(0xFF808089), Color(0xFFEBEBF0)),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top=16.dp, start=16.dp, end=16.dp)
            .background(Color(0xFFF7F7F9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal=8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_repeat),
                contentDescription = "Booking",
                tint = Color.Black
            )
            Text(
                text = "History Booking",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(bookings) { booking ->
                BookingItem(booking)
            }
        }
    }
}

@Composable
fun BookingItem(booking: Booking) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = booking.status,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = booking.statusColor,
                modifier = Modifier
                    .background(booking.statusBgColor, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = booking.date,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = booking.venueName,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = booking.fieldType,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFFEAEAEA))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = booking.fieldType,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = booking.price,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Orange
            )
        }
    }
}

data class Booking(
    val date: String,
    val status: String,
    val venueName: String,
    val fieldType: String,
    val price: String,
    val statusColor: Color,
    val statusBgColor: Color
)
