package com.arena.ui.user.home.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arena.R
import com.arena.domain.model.SportCategory
import com.arena.domain.model.Venue
import com.google.gson.Gson

@Composable
fun HorizontalVenueList(venues: List<Venue>, navController: NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(venues) { venue ->
            VenueItem(navController, venue)
        }
    }
}

@Composable
fun VenueItem(navController: NavController, venue: Venue) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(vertical = 8.dp)
            .clickable {
                val venueJson = Gson().toJson(venue)
                navController.navigate("user_venue_detail/$venueJson")
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Image(
                painter = painterResource(id = venue.image),
                contentDescription = venue.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(0.dp)
            ) {
                val iconRes = if (isFavorite) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_non_favorite
                }
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(horizontal = 14.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = venue.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${venue.rating}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFB6D3A))
                }
            }
            Text(
                text = venue.location,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                venue.tags.forEachIndexed { index, tag ->
                    val tagColor = when (index % 3) {
                        0 -> Color(0xFF00BAA1)
                        1 -> Color(0xFFFB6D3A)
                        else -> Color(0xFF8873FB)
                    }
                    val backgroundColor = tagColor.copy(alpha = 0.15f)
                    Text(
                        text = tag,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = tagColor,
                        modifier = Modifier
                            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFB6D3A))) {
                        append("Rp ${venue.priceRange}")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontSize = 14.sp, color = Color.Gray)) {
                        append("/jam")
                    }
                },
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 20.dp, top = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Text(text = "Lihat Semua", fontWeight = FontWeight.Medium, fontSize = 12.sp, color = Color(0xFFFB6D3A), modifier = Modifier.clickable { /* TODO: Handle see all click */ })
    }
}

@Composable
fun SportCategoriesRow(categories: List<SportCategory>) {
    LazyRow(
        modifier = Modifier
            .padding(start = 0.dp, top = 20.dp, bottom = 20.dp, end = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { /* TODO: Handle category click */ },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = category.name,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = category.name, fontSize = 12.sp, textAlign = TextAlign.Center)
            }
        }
    }
}
