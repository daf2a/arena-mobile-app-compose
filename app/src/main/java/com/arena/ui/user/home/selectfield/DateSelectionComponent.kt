package com.arena.ui.user.home.selectfield

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arena.ui.theme.Orange
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectionComponent() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dates = (0..90).map { LocalDate.now().plusDays(it.toLong()) }

    Column(modifier = Modifier.padding(start = 20.dp, end = 0.dp, top = 16.dp, bottom = 8.dp)) {
        Text(text = "Tanggal", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dates.size) { index ->
                val date = dates[index]
                val isSelected = date == selectedDate
                val backgroundColor = if (isSelected) Orange else Color.Gray.copy(alpha = 0.2f)
                val textColor = if (isSelected) Color.White else Color.Black
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(70.dp)
                        .height(45.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                        .clickable { selectedDate = date }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${date.dayOfMonth} ${date.month.toString().take(3)}",
                            color = textColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = date.dayOfWeek.toString().take(3),
                            color = textColor,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DateSelectionComponentPreview() {
    DateSelectionComponent()
}
