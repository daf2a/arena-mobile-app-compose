package com.arena.ui.user.home.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arena.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Handle text input */ },
        placeholder = { Text(text = "Search", color = Color.White) },
        leadingIcon = {
            Icon(painterResource(id = R.drawable.ic_search), contentDescription = "Search", tint = Color.White)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFA8F69), shape = RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFA8F69),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
