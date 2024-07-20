package com.arena.ui.user.home.selectfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arena.R

data class Court(
    val name: String,
    val type: String,
    val size: String,
    val image: Int
)

@Composable
fun FieldSelectionComponent() {
    var selectedFieldIndex by remember { mutableStateOf(0) }
    val fields = listOf(
        Court("Lapangan A", "Badminton", "10 x 20 meter", R.drawable.iv_venue_1),
        Court("Lapangan B", "Futsal", "20 x 30 meter", R.drawable.iv_venue_2)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 0.dp)
    ) {
        fields.forEachIndexed { index, field ->
            if (index == selectedFieldIndex) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically // Align content vertically centered
                ) {
                    Image(
                        painter = rememberImagePainter(field.image),
                        contentDescription = field.name,
                        modifier = Modifier
                            .width(200.dp)
                            .height(120.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start // Align text to start (left)
                    ) {
                        Text(
                            text = field.type,
                            color = Color(0xFFFB6D3A),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = field.size,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFEDEDED))
                .padding(8.dp)
        ) {
            IconButton(
                onClick = {
                    if (selectedFieldIndex > 0) {
                        selectedFieldIndex--
                    }
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Previous"
                )
            }
            Text(
                text = fields[selectedFieldIndex].name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    if (selectedFieldIndex < fields.size - 1) {
                        selectedFieldIndex++
                    }
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Next"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FieldSelectionComponentPreview() {
    FieldSelectionComponent()
}
