package com.arena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arena.ui.navigation.NavGraph
import com.arena.ui.theme.ArenaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArenaTheme {
                NavGraph()
            }
        }
    }
}
