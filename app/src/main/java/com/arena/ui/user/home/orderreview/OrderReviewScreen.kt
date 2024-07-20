package com.arena.ui.user.home.orderreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.ui.user.home.selectfield.SelectFieldScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderReviewScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pilih Lapangan") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Text(
                    text = "Pilih jenis lapangan yang ingin kamu mainkan",
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OrderReviewScreenPreview() {
    OrderReviewScreen(navController = rememberNavController())
}