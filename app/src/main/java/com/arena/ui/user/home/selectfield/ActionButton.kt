package com.arena.ui.user.home.selectfield

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arena.ui.theme.Orange

@Composable
fun ActionButton(onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Orange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Lanjut ke Pembayaran", color = Color.White)
        }
    }
}
