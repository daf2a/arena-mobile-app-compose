package com.arena.ui.user.home.homescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arena.R

@Composable
fun SearchBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFA8F69), shape = RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate("search_screen")
                Log.d("SearchBar", "Navigate to SearchScreen")
            }
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = "Search",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
