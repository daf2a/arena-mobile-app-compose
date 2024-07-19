package com.arena.ui.user

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.domain.model.SportCategory
import com.arena.domain.model.Venue
import com.arena.ui.theme.Orange
import com.google.gson.Gson
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arena.ui.components.UserBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("user_home") }

    Scaffold(
        content = { paddingValues ->
            HomeContent(navController, paddingValues)
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
fun HomeContent(navController: NavController, paddingValues: PaddingValues) {
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
            Venue("BBS Futsal Sport", "Kepuh Gg 1D No.50, Surabaya", 5.0, "30.000 - 50.000", R.drawable.iv_venue_1, listOf("Badminton", "Futsal")),
            Venue("MPN Arena", "Sukolilo Gg 2, Surabaya", 4.8, "30.000 - 50.000", R.drawable.iv_venue_2, listOf("Badminton"))
        )
    }

    val nearbyVenues = remember {
        listOf(
            Venue("Aurora Sport", "BMC Sukolilo, Surabaya", 5.0, "30.000 - 50.000", R.drawable.iv_venue_2, listOf("Badminton", "Futsal")),
            Venue("BBS Futsal Sport", "Kepuh Gg 1D No.50, Surabaya", 5.0, "30.000 - 50.000", R.drawable.iv_venue_1, listOf("Badminton", "Futsal")),
        )
    }

    LazyColumn {
        item {
            HomeTopBar()
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F9))
                    .padding(paddingValues)
                    .padding(start = 16.dp, top = 0.dp, bottom = 10.dp, end = 0.dp)
            ) {
                SportCategoriesRow(sportCategories)
                SectionHeader(title = "Venue Terpopuler")
                HorizontalVenueList(venues = popularVenues, navController = navController)
                InviteFriendBanner()
                SectionHeader(title = "Venue Terdekat")
                HorizontalVenueList(venues = nearbyVenues, navController = navController)
            }
        }
    }
}

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
fun InviteFriendBanner() {
    Image(
        painter = painterResource(id = R.drawable.iv_invite_friend),
        contentDescription = "Invite Friend",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 12.dp, bottom = 20.dp, end = 20.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun HomeTopBar() {
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
        SearchBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Handle text input */ },
        placeholder = { Text(text = "Search", color = Color.White) },
        leadingIcon = {
            Icon(painterResource(id = R.drawable.ic_search), contentDescription = "Search", tint = Color.White)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFA8F69), shape = RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFA8F69),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
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
        Text(text = "Lihat Semua", fontWeight = FontWeight.Medium, fontSize = 12.sp, color = Orange, modifier = Modifier.clickable { /* TODO: Handle see all click */ })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    UserHomeScreen(navController = rememberNavController())
}
