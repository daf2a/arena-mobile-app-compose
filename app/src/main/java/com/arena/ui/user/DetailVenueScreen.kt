package com.arena.ui.venue

import android.annotation.SuppressLint
import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.domain.model.Venue
import com.arena.ui.theme.Orange
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import java.time.LocalDate

data class Court(
    val name: String,
    val type: String,
    val size: String,
    val price: String,
    val image: Int
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun VenueDetailScreen(navController: NavController, venue: Venue) {
    val selectedCourt = remember { mutableStateOf<Court?>(null) }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedStartTime = remember { mutableStateOf("") }
    val selectedEndTime = remember { mutableStateOf("") }
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

    val operationalHours = listOf(
        "Senin - Jumat" to "18.00 - 23.00",
        "Sabtu - Minggu" to "07.00 - 23.00"
    )

    var selectedImageIndex by remember { mutableStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    var isCourtSheetVisible by remember { mutableStateOf(false) }
    var isDateSheetVisible by remember { mutableStateOf(false) }
    var isSessionSheetVisible by remember { mutableStateOf(false) }

    val courts = listOf(
        Court("Lapangan 1", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_2),
        Court("Lapangan 2", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_1),
        Court("Lapangan 3", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_2),
        Court("Lapangan 4", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_1),
        Court("Lapangan 5", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_2),
        Court("Lapangan 6", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_1),
        Court("Lapangan 7", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_2),
        Court("Lapangan 8", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_1),
        Court("Lapangan 9", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_2),
        Court("Lapangan 10", "Badminton", "20x30 meter", "30.000", R.drawable.iv_venue_1)
    )

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val bottomNavController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Transparent,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        NavHost(navController = bottomNavController, startDestination = "main") {
            composable("main") {
                MainContent(
                    navController = navController,
                    venue = venue,
                    selectedImageIndex = selectedImageIndex,
                    onImageClick = { selectedImageIndex = it },
                    facilities = facilities,
                    operationalHours = operationalHours,
                    isFavorite = isFavorite,
                    onFavoriteClick = { isFavorite = !isFavorite },
                    isDescriptionExpanded = isDescriptionExpanded,
                    onDescriptionToggle = { isDescriptionExpanded = !isDescriptionExpanded },
                    onSelectCourtClick = { isCourtSheetVisible = true }
                )
            }
            bottomSheet("court_sheet") {
                BottomSheetContent(
                    courts = courts,
                    onCourtClick = { /* Handle court click */ },
                    onSelectClick = {
                        selectedCourt.value = it
                        isCourtSheetVisible = false
                        isDateSheetVisible = true
                    },
                    onDismiss = {
                        isCourtSheetVisible = false
                    }
                )
            }
            bottomSheet("date_sheet") {
                DateSelectionContent(
                    selectedCourt = selectedCourt.value!!,
                    selectedDate = selectedDate,
                    onConfirmDate = {
                        isDateSheetVisible = false
                        isSessionSheetVisible = true
                    },
                    onDismiss = {
                        isDateSheetVisible = false
                    }
                )
            }
            bottomSheet("session_sheet") {
                SessionSelectionContent(
                    selectedCourt = selectedCourt.value!!,
                    selectedDate = selectedDate.value,
                    selectedStartTime = selectedStartTime,
                    selectedEndTime = selectedEndTime,
                    onConfirmSession = {
                        // Navigate to payment screen
                    },
                    onDismiss = {
                        isSessionSheetVisible = false
                    }
                )
            }
        }
    }

    LaunchedEffect(isCourtSheetVisible) {
        if (isCourtSheetVisible) {
            bottomNavController.navigate("court_sheet")
        } else {
            bottomNavController.popBackStack("court_sheet", true)
        }
    }

    LaunchedEffect(isDateSheetVisible) {
        if (isDateSheetVisible) {
            bottomNavController.navigate("date_sheet")
        } else {
            bottomNavController.popBackStack("date_sheet", true)
        }
    }

    LaunchedEffect(isSessionSheetVisible) {
        if (isSessionSheetVisible) {
            bottomNavController.navigate("session_sheet")
        } else {
            bottomNavController.popBackStack("session_sheet", true)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavController,
    venue: Venue,
    selectedImageIndex: Int,
    onImageClick: (Int) -> Unit,
    facilities: List<Pair<String, Int>>,
    operationalHours: List<Pair<String, String>>,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    isDescriptionExpanded: Boolean,
    onDescriptionToggle: () -> Unit,
    onSelectCourtClick: () -> Unit
) {
    val images = listOf(
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1,
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1,
        R.drawable.iv_venue_2,
        R.drawable.iv_venue_1
    )

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
                    IconButton(onClick = onFavoriteClick) {
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
                        .padding(bottom = 50.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 20.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = rememberImagePainter(venue.image),
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
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
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
                                    .clickable { onImageClick(index) },
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
                        text = if (isDescriptionExpanded) longDescription else shortDescription,
                        minimizedMaxLines = 3,
                        modifier = Modifier.padding(horizontal = 20.dp),
                        isExpanded = isDescriptionExpanded,
                        onExpand = onDescriptionToggle
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
                Button(
                    onClick = onSelectCourtClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.BottomCenter),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    ),
                    shape = RoundedCornerShape(20),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "Pilih Lapangan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    courts: List<Court>,
    onCourtClick: (Court) -> Unit,
    onSelectClick: (Court) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .width(1 / 5f * LocalConfiguration.current.screenWidthDp.dp)
                .height(4.dp)
                .background(Color(0xFFF2F3F6), shape = RoundedCornerShape(2.dp))
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${courts.size} Lapangan Tersedia",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        courts.forEach { court ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onCourtClick(court) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(court.image),
                    contentDescription = court.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = court.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = court.type, fontSize = 14.sp, color = Color.Gray)
                    Text(text = court.size, fontSize = 14.sp, color = Color.Gray)
                    Text(
                        text = "Rp ${court.price}/jam",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Orange
                    )
                }
                Button(
                    onClick = { onSelectClick(court) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "Pilih", color = Color.White)
                }
            }
        }
        DisposableEffect(Unit) {
            onDispose {
                onDismiss()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionContent(
    selectedCourt: Court,
    selectedDate: MutableState<LocalDate>,
    onConfirmDate: () -> Unit,
    onDismiss: () -> Unit
) {
    val today = LocalDate.now()
    val calendarState = remember { mutableStateOf(today) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .width(1 / 5f * LocalConfiguration.current.screenWidthDp.dp)
                .height(4.dp)
                .background(Color(0xFFF2F3F6), shape = RoundedCornerShape(2.dp))
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pilih Tanggal",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(
                text = selectedCourt.name,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .background(Orange, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Text(
            text = selectedDate.value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Gray,
        )
        CalendarView(
            selectedDate = selectedDate.value,
            onDateSelected = { selectedDate.value = it },
            today = today
        )
        Button(
            onClick = onConfirmDate,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lanjut Pilih Sesi", color = Color.White)
        }
        DisposableEffect(Unit) {
            onDispose {
                onDismiss()
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    today: LocalDate
) {
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val firstDayOfMonth = selectedDate.withDayOfMonth(1)
    val lastDayOfMonth = selectedDate.withDayOfMonth(selectedDate.lengthOfMonth())
    val firstDayOfWeekOffset = (firstDayOfMonth.dayOfWeek.value % 7)
    val daysInMonth = (1..lastDayOfMonth.dayOfMonth).toList()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onDateSelected(selectedDate.minusMonths(1))
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month", tint = Color(0xFFFDB469))
            }
            Text(
                text = "${selectedDate.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${selectedDate.year}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            IconButton(onClick = {
                onDateSelected(selectedDate.plusMonths(1))
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next Month", tint = Color(0xFFFDB469))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp),
                content = {
                    items(daysOfWeek) { day ->
                        Text(
                            text = day,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(2.dp),
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                    items(firstDayOfWeekOffset) {
                        Box(modifier = Modifier.size(30.dp))
                    }
                    items(daysInMonth) { day ->
                        val date = firstDayOfMonth.plusDays((day - 1).toLong())
                        val isToday = date == today
                        val isSelected = date == selectedDate
                        val isPastDate = date.isBefore(today)

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isSelected -> Orange
                                        isToday -> Color.LightGray
                                        else -> Color.Transparent
                                    }
                                )
                                .clickable(enabled = !isPastDate) {
                                    onDateSelected(date)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.toString(),
                                color = when {
                                    isSelected || isToday -> Color.White
                                    isPastDate -> Color(0xFFC5C6CB)
                                    else -> Color(0xFF0D1634)
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionSelectionContent(
    selectedCourt: Court,
    selectedDate: LocalDate,
    selectedStartTime: MutableState<String>,
    selectedEndTime: MutableState<String>,
    onConfirmSession: () -> Unit,
    onDismiss: () -> Unit
) {
    val timeSlots = (0..47).map { String.format("%02d:%02d", it / 2, (it % 2) * 30) }
    var expandedStart by remember { mutableStateOf(false) }
    var expandedEnd by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .width(1 / 5f * LocalConfiguration.current.screenWidthDp.dp)
                .height(4.dp)
                .background(Color(0xFFF2F3F6), shape = RoundedCornerShape(2.dp))
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pilih Sesi",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(
                text = selectedCourt.name,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .background(Orange, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Text(
            text = selectedDate.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExposedDropdownMenuBox(
                expanded = expandedStart,
                onExpandedChange = { expandedStart = !expandedStart }
            ) {
                TextField(
                    value = selectedStartTime.value,
                    onValueChange = { selectedStartTime.value = it },
                    label = { Text("Start Time") },
                    trailingIcon = null,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .menuAnchor()
                        .width(150.dp)
                        .border(1.dp, Color(0xFF939393), RoundedCornerShape(12.dp)),
                    readOnly = true,
                    singleLine = true
                )
                ExposedDropdownMenu(
                    expanded = expandedStart,
                    onDismissRequest = { expandedStart = false }
                ) {
                    timeSlots.forEach { time ->
                        DropdownMenuItem(
                            text = { Text(text = time) },
                            onClick = {
                                selectedStartTime.value = time
                                expandedStart = false
                            }
                        )
                    }
                }
            }
            Text(text = "-", modifier = Modifier.padding(horizontal = 8.dp))
            ExposedDropdownMenuBox(
                expanded = expandedEnd,
                onExpandedChange = { expandedEnd = !expandedEnd }
            ) {
                TextField(
                    value = selectedEndTime.value,
                    onValueChange = { selectedEndTime.value = it },
                    label = { Text("End Time") },
                    trailingIcon = null,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .menuAnchor()
                        .width(150.dp)
                        .border(1.dp, Color(0xFF939393), RoundedCornerShape(12.dp)),
                    readOnly = true,
                    singleLine = true
                )
                ExposedDropdownMenu(
                    expanded = expandedEnd,
                    onDismissRequest = { expandedEnd = false }
                ) {
                    timeSlots.forEach { time ->
                        DropdownMenuItem(
                            text = { Text(text = time) },
                            onClick = {
                                selectedEndTime.value = time
                                expandedEnd = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onConfirmSession,
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lanjut ke Pembayaran", color = Color.White)
        }
        DisposableEffect(Unit) {
            onDispose {
                onDismiss()
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

@Composable
fun ExpandableText(
    text: String,
    minimizedMaxLines: Int = 1,
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onExpand: () -> Unit
) {
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
            modifier = Modifier.clickable { onExpand() }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun VenueDetailScreenPreview() {
    val venue = Venue(
        "BBS Futsal Sport",
        "Kepuh Gg 1D No.50, Surabaya",
        5.0,
        "30.000 - 50.000",
        R.drawable.iv_venue_1,
        listOf("Badminton", "Futsal")
    )
    VenueDetailScreen(navController = rememberNavController(), venue = venue)
}
