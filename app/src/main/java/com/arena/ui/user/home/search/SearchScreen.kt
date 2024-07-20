package com.arena.ui.user.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arena.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val searchHistory = listOf(
        "Arena", "Badminton", "Soccer", "Sepak Bola", "Futsal",
        "Mini Soccer", "Tennis", "BBS Futsal Sport"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(text = "Search", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            },
            trailingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "Enter",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        navController.navigate("list_venue_screen/$searchQuery")
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pencarian Terakhir",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchHistory.size) { index ->
                val historyItem = searchHistory[index]
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF6F6F6), shape = RoundedCornerShape(16.dp))
                        .clickable {
                            searchQuery = historyItem
                            navController.navigate("list_venue_screen/$historyItem")
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = historyItem, color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}
