package com.arena.ui.user.home.selectfield

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.ui.theme.Orange

data class TimeSlot(val time: String, val price: Int, val available: Boolean)

@Composable
fun TimeSlotSelectionComponent(onClick: () -> Unit) {
    val timeSlots = listOf(
        TimeSlot("01:00", 50, false),
        TimeSlot("02:00", 50, true),
        TimeSlot("03:00", 50, true),
        TimeSlot("04:00", 50, true),
        TimeSlot("05:00", 50, false),
        TimeSlot("06:00", 50, true),
        TimeSlot("07:00", 50, true),
        TimeSlot("08:00", 50, true),
        TimeSlot("09:00", 50, true),
        TimeSlot("10:00", 50, true),
        TimeSlot("11:00", 50, true),
        TimeSlot("12:00", 50, true),
        TimeSlot("13:00", 50, true),
        TimeSlot("14:00", 50, true),
        TimeSlot("15:00", 50, true),
        TimeSlot("16:00", 50, true),
        TimeSlot("17:00", 50, true),
        TimeSlot("18:00", 50, true),
        TimeSlot("19:00", 75, true),
        TimeSlot("20:00", 75, true),
        TimeSlot("21:00", 75, true),
        TimeSlot("22:00", 75, false),
        TimeSlot("23:00", 75, false),
        TimeSlot("24:00", 75, false)
    )
    var selectedSlots by remember { mutableStateOf(setOf<TimeSlot>()) }

    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)) {
        Text(text = "Jam", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(timeSlots) { slot ->
                val isSelected = selectedSlots.contains(slot)
                val backgroundColor = when {
                    isSelected -> Orange
                    slot.available -> Color(0xFFE2E2E2)
                    else -> Color(0xFFE2E2E2).copy(alpha = 0.4f)
                }
                val textColor = when {
                    isSelected -> Color.White
                    slot.available -> Color.Black
                    else -> Color.Black.copy(alpha = 0.4f)
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                        .clickable {
                            if (slot.available) {
                                if (isSelected) {
                                    selectedSlots = selectedSlots - slot
                                } else {
                                    selectedSlots = selectedSlots + slot
                                }
                            }
                        }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = slot.time, color = textColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text(text = "${slot.price}K", color = textColor, fontSize = 12.sp)
                    }
                }
            }
        }
        val totalPrice = selectedSlots.sumOf { it.price }
        Text(
            text = "Total Harga: ${totalPrice}K",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, bottom = 8.dp, end = 8.dp)
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Orange),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
        ) {
            Text(text = "Lanjut ke Pembayaran", color = Color.White)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TimeSlotSelectionComponentPreview() {
    TimeSlotSelectionComponent(onClick = {})
}