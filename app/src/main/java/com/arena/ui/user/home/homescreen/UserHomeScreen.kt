package com.arena.ui.user.home.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arena.R
import com.arena.domain.model.SportCategory
import com.arena.domain.model.Venue
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
