package com.arena.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.domain.model.SportCategory
import com.arena.domain.model.Venue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val sportCategories = remember {
        listOf(
            SportCategory("Futsal", R.drawable.iv_category_1),
            SportCategory("Badminton", R.drawable.iv_category_2),
            SportCategory("Basketball", R.drawable.iv_category_3),
            SportCategory("Volleyball", R.drawable.iv_category_4),
            SportCategory("Tennis", R.drawable.iv_category_5),
            SportCategory("Futsal", R.drawable.iv_category_1),
            SportCategory("Badminton", R.drawable.iv_category_2),
            SportCategory("Basketball", R.drawable.iv_category_3),
        )
    }

    val popularVenues = remember {
        listOf(
            Venue("BBS Futsal Sport", "Kepuh Gg 1D No.50, Surabaya", 5.0, "Rp 30.000 - 50.000/jam", R.drawable.iv_venue_1, listOf("Badminton", "Futsal")),
            Venue("MPN Arena", "Sukolilo Gg 2, Surabaya", 4.8, "Rp 30.000 - 50.000/jam", R.drawable.iv_venue_2, listOf("Badminton"))
            // Tambahkan venue lainnya...
        )
    }

    val nearbyVenues = remember {
        listOf(
            Venue("Aurora Sport", "BMC Sukolilo, Surabaya", 5.0, "Rp 30.000 - 50.000/jam", R.drawable.iv_venue_2, listOf("Badminton", "Futsal")),
            Venue("BBS Futsal Sport", "Kepuh Gg 1D No.50, Surabaya", 5.0, "Rp 30.000 - 50.000/jam", R.drawable.iv_venue_1, listOf("Badminton", "Futsal")),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lokasi saat ini", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF6D3A)),
                actions = {
                    IconButton(onClick = { /* TODO: Open Notifications */ }) {
                        Icon(painterResource(id = R.drawable.ic_notifications), contentDescription = "Notifications", tint = Color.White)
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                item { LocationHeader() }
                item { SearchBar() }
                item { SportCategoriesRow(sportCategories) }
                item { SectionHeader(title = "Venue Terpopuler") }
                items(popularVenues) { venue -> VenueItem(venue) }
                item { SectionHeader(title = "Venue Terdekat") }
                items(nearbyVenues) { venue -> VenueItem(venue) }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* TODO: Handle click */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_chat), contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = false,
                    onClick = { /* TODO: Handle click */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_booking), contentDescription = "Booking") },
                    label = { Text("Booking") },
                    selected = false,
                    onClick = { /* TODO: Handle click */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_profile), contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* TODO: Handle click */ }
                )
            }
        }
    )
}

@Composable
fun LocationHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF6D3A), shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(16.dp)
    ) {
        Text(text = "Surabaya, Jawa Timur", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Handle text input */ },
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(24.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SportCategoriesRow(categories: List<SportCategory>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = "Lihat Semua", color = Color(0xFFFF6D3A), modifier = Modifier.clickable { /* TODO: Handle see all click */ })
    }
}

@Composable
fun VenueItem(venue: Venue) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* TODO: Handle venue click */ },
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Image(
                painter = rememberImagePainter(venue.image),
                contentDescription = venue.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = venue.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))
            Text(text = venue.location, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 8.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                venue.tags.forEach { tag ->
                    Text(
                        text = tag,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color(0xFFFF6D3A), shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rp ${venue.priceRange}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6D3A),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
