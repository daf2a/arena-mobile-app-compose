package com.arena.ui.user.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import coil.compose.rememberImagePainter

data class SessionDetail(val courtName: String, val date: String, val time: String, val price: String)

data class Venue(
    val name: String,
    val location: String,
    val image: Int,
    val activity: String,
    val sessions: List<SessionDetail>
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(navController: NavController) {
    val venue = Venue(
        name = "Zuper Badminton Hall (KEJAWAN)",
        location = "Kepulih Gg.ID No.15",
        image = R.drawable.iv_venue_1,
        activity = "Badminton",
        sessions = listOf(
            SessionDetail("Lapangan 6", "20 Jul 2024", "10:00 - 11:00", "Rp 70.000"),
            SessionDetail("Lapangan 6", "20 Jul 2024", "11:00 - 12:00", "Rp 70.000"),
            SessionDetail("Lapangan 6", "20 Jul 2024", "12:00 - 13:00", "Rp 70.000")
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Transaksi") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                StatusBar(status = "Berhasil", color = Color(0xFF007D3A))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    TransactionDetailsSection()
                    Divider(
                        color = Color.Gray.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    SessionDetailsSection(sessions = venue.sessions)
                    Divider(
                        color = Color.Gray.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    OrderSummarySection(totalPrice = "210.000")
                }
            }
        },
    )
}

@Composable
fun StatusBar(status: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = status,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TransactionDetailsSection() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "Transaction ID #INV-240717-9UV7EPSXA--", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Rabu, 17 Juli 2024, 07:47", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Tempat", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text(text = "Zuper Badminton Hall (KEJAWAN)", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Aktivitas", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text(text = "Badminton", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Save Invoice Action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .border(1.dp, Color.Blue, RoundedCornerShape(12.dp))
                .fillMaxWidth()
        ) {
            Text(text = "Lihat Invoice", fontSize = 14.sp, color = Color.Blue)
        }
    }
}

@Composable
fun SessionDetailsSection(sessions: List<SessionDetail>) {
    LazyColumn {
        items(sessions) { session ->
            SessionItem(session = session)
        }
    }
}

@Composable
fun SessionItem(session: SessionDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = session.courtName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = session.price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = session.date, fontSize = 14.sp, color = Color.Gray)
        Text(text = session.time, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun OrderSummarySection(totalPrice: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Order Summary", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        SummaryItem(label = "Metode Pembayaran", value = "QRIS (Free Service Charge)")
        SummaryItem(label = "Status Pemesanan", value = "LUNAS")
        SummaryItem(label = "Total Harga", value = "Rp 210.000")
        SummaryItem(label = "Promo", value = "0")
        SummaryItem(label = "Service Charge", value = "0")
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        SummaryItem(label = "Total Pembayaran", value = "Rp $totalPrice", isTotal = true)
    }
}

@Composable
fun SummaryItem(label: String, value: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 16.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if (isTotal) Color.Black else Color.Gray
        )
        Text(
            text = value,
            fontSize = if (isTotal) 16.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if (isTotal) Color.Black else Color.Gray
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview() {
    OrderDetailScreen(navController = rememberNavController())
}
