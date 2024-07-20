package com.arena.ui.user.home.detailvenue

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.arena.ui.theme.Orange

@Composable
fun VenueInfo(venue: Venue) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = venue.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF101010),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "Location",
                tint = Orange,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = venue.location,
                color = Color(0xFF9C9BA6),
                fontSize = 14.sp
            )
        }
    }
}
