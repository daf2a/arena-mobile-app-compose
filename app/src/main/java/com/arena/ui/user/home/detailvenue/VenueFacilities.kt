package com.arena.ui.user.home.detailvenue

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arena.R
import com.arena.domain.model.Venue

@Composable
fun VenueFacilities(venue: Venue) {
    val facilities = listOf(
        "Free Wifi" to R.drawable.ic_wifi,
        "Parkir Mobil" to R.drawable.ic_parking,
        "Toilet" to R.drawable.ic_wifi,
        "Sauna" to R.drawable.ic_wifi,
        "Parkir Motor" to R.drawable.ic_wifi,
        "Kantin" to R.drawable.ic_wifi,
        "Free Wifi" to R.drawable.ic_wifi,
        "Parkir Mobil" to R.drawable.ic_parking,
        "Toilet" to R.drawable.ic_wifi,
        "Sauna" to R.drawable.ic_wifi,
        "Parkir Motor" to R.drawable.ic_wifi,
        "Kantin" to R.drawable.ic_wifi
    )

    LazyRow(
        modifier = Modifier.padding(start = 20.dp, end = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(facilities.size) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color(0xFFF6F6FD), shape = CircleShape)
                        .border(1.dp, Color(0xFFE8EAED), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = facilities[index].second),
                        contentDescription = facilities[index].first,
                        tint = Color(0xFF3E4152)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = facilities[index].first,
                    color = Color(0xFF3E4152),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun FacilityItem(name: String, icon: Int) {
    Row(
        modifier = Modifier
            .background(Color(0xFFF6F6FD), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painterResource(id = icon), contentDescription = name, tint = Color.Black)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = name, fontSize = 14.sp, color = Color.Black)
    }
}

@Composable
fun RatingItem(rating: Double) {
    Row(
        modifier = Modifier
            .background(Color(0xFFF6F6FD), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painterResource(id = R.drawable.ic_star), contentDescription = "Rating", tint = Color(0xFFFFD700))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = rating.toString(), fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}
