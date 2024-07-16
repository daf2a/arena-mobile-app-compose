package com.arena.ui.venue

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.domain.model.Venue
import com.arena.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueDetailScreen(navController: NavController, venue: Venue) {
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

    val images = listOf(
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1,
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1,
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1
    )

    val operationalHours = listOf(
        "Senin - Jumat" to "18.00 - 23.00",
        "Sabtu - Minggu" to "07.00 - 23.00"
    )

    var selectedImageIndex by remember { mutableStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    val shortDescription = "BBS Futsal Sport adalah tempat yang bisa digunakan untuk keperluan pertandingan futsal yang"
    val longDescription = "BBS Futsal Sport adalah tempat yang bisa digunakan untuk keperluan pertandingan futsal yang sudah dilengkapi dengan rumput sintetis berkualitas tinggi. Fasilitas ini dirancang untuk memberikan pengalaman terbaik bagi para pemain futsal..."

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(venue.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        val iconRes = if (isFavorite) {
                            R.drawable.ic_favorite
                        } else {
                            R.drawable.ic_non_favorite
                        }
                        Icon(painterResource(id = iconRes), contentDescription = "Favorite")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFEFEFE))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 75.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 20.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = rememberImagePainter(images[selectedImageIndex]),
                            contentDescription = "Venue Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "${selectedImageIndex + 1}/${images.size}",
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .background(Color(0xFFFEFEFE).copy(alpha = 0.7f), shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    LazyRow(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(images.size) { index ->
                            Image(
                                painter = rememberImagePainter(images[index]),
                                contentDescription = "Thumbnail Image",
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { selectedImageIndex = index },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            facilities.take(2).forEach { (name, icon) ->
                                FacilityItem(name = name, icon = icon)
                            }
                        }
                        RatingItem(rating = venue.rating)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = venue.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF101010),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
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
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Deskripsi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    ExpandableText(
                        text = longDescription,
                        minimizedMaxLines = 3,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Jam Operasional",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    operationalHours.forEach { (days, hours) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = days, fontSize = 14.sp, color = Color(0xFF9C9BA6))
                            Text(text = hours, fontSize = 14.sp, color = Color(0xFF9C9BA6))
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Fasilitas Lainnya",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        modifier = Modifier.padding(horizontal = 20.dp)
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
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Lokasi Maps",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.iv_maps),
                        contentDescription = "Maps",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp)
                ) {
                    FloatingActionButton(
                        onClick = { /* TODO: Handle click */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.BottomCenter),
                        containerColor = Color(0xFFFB6D3A),
                        shape = RoundedCornerShape(20),
                    ) {
                        Text(text = "Pilih Lapangan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                }
            }
        }
    )
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

@Composable
fun ExpandableText(text: String, minimizedMaxLines: Int = 1, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            fontSize = 14.sp,
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF9C9BA6)
        )
        Text(
            fontSize = 14.sp,
            text = if (isExpanded) "Tampilkan sedikit" else "Baca Selengkapnya...",
            color = Orange,
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VenueDetailScreenPreview() {
    val venue = Venue("BBS Futsal Sport", "Kepuh Gg 1D No.50, Surabaya", 5.0, "30.000 - 50.000", R.drawable.iv_venue_1, listOf("Badminton", "Futsal"))
    VenueDetailScreen(navController = rememberNavController(), venue = venue)
}
