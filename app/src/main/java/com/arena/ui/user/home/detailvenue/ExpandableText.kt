package com.arena.ui.user.home.detailvenue

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.arena.ui.theme.Orange

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
