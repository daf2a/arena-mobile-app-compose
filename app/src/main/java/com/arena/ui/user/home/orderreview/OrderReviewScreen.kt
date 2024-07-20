package com.arena.ui.user.orderreview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.ui.theme.Orange

data class Venue(
    val name: String,
    val location: String,
    val image: Int,
    val sessions: List<String>,
    val sessionPrices: List<Int>
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderReviewScreen(navController: NavController, venue: Venue) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Order") },
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
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp)
            ) {
                VenueDetailSection(venue = venue)
                Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
                DiscountSection()
                Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
                PriceReviewSection(venue = venue)
            }
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate("payment_success_screen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange
                ),
                shape = RoundedCornerShape(20)
            ) {
                Text(text = "Bayar", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
    )
}

@Composable
fun VenueDetailSection(venue: Venue) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(venue.image),
            contentDescription = venue.name,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = venue.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = venue.location, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Senin, DD/MM/YYYY", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                venue.sessions.forEach { session ->
                    Text(
                        text = session,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(Orange, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}

@Composable
fun DiscountSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Punya Kode Diskon?", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "Add Discount")
    }
}

@Composable
fun PriceReviewSection(venue: Venue) {
    Column(modifier = Modifier.fillMaxWidth()) {
        venue.sessions.forEachIndexed { index, session ->
            PriceItem(session = session, price = venue.sessionPrices[index])
        }
        PriceItem(session = "Biaya Admin", price = 0, isAdminFee = true)
        Spacer(modifier = Modifier.height(8.dp))
        val totalPrice = venue.sessionPrices.sum()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Tagihan",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Rp $totalPrice.000",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun PriceItem(session: String, price: Int, isAdminFee: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = if (isAdminFee) session else "Biaya Sesi $session", fontSize = 14.sp, color = Color.Gray)
        Text(
            text = if (price == 0) "Free" else "Rp $price.000",
            fontSize = 14.sp,
            fontWeight = if (isAdminFee) FontWeight.Bold else FontWeight.Normal,
            color = if (isAdminFee) Color.Black else Color.Gray
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun OrderReviewScreenPreview() {
    val venue = Venue(
        name = "Aurora Futsal",
        location = "Kepulih Gg.ID No.15",
        image = R.drawable.iv_venue_1,
        sessions = listOf("17:00", "18:00", "19:00"),
        sessionPrices = listOf(100, 100, 100)
    )
    OrderReviewScreen(navController = rememberNavController(), venue = venue)
}
