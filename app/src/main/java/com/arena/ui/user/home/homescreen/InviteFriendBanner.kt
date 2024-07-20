package com.arena.ui.user.home.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arena.R

@Composable
fun InviteFriendBanner() {
    Image(
        painter = painterResource(id = R.drawable.iv_invite_friend),
        contentDescription = "Invite Friend",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 12.dp, bottom = 20.dp, end = 20.dp),
        contentScale = ContentScale.Fit
    )
}
