package com.arena.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.arena.R
import com.arena.ui.components.UserBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("user_chat_screen") }
    val chats = remember {
        listOf(
            Chat("Aurora Futsal", "Aurora Futsal is typing...", R.drawable.iv_category_5, "14:28", 1),
            Chat("BCM Keputih", "Are you ready?", R.drawable.iv_category_4, "15:18", 0),
            Chat("Gor Pertamina", "I'm ready!", R.drawable.iv_category_3, "15:20", 0),
            Chat("Stadion ITS", "I'm ready too!", R.drawable.iv_category_2, "15:21", 0),
            Chat("Aurora Futsal", "Are you ready?", R.drawable.iv_category_5, "14:28", 0),
            Chat("BCM Keputih", "Are you ready?", R.drawable.iv_category_4, "15:18", 0),
            Chat("Gor Pertamina", "I'm ready!", R.drawable.iv_category_3, "15:20", 0),
            Chat("Stadion ITS", "I'm ready too!", R.drawable.iv_category_2, "15:21", 0),
            Chat("Aurora Futsal", "Are you ready?", R.drawable.iv_category_5, "14:28", 0),
            Chat("BCM Keputih", "Are you ready?", R.drawable.iv_category_4, "15:18", 0),
            Chat("Gor Pertamina", "I'm ready!", R.drawable.iv_category_3, "15:20", 0),
            Chat("Stadion ITS", "I'm ready too!", R.drawable.iv_category_2, "15:21", 0),
            Chat("Aurora Futsal", "Are you ready?", R.drawable.iv_category_5, "14:28", 0),
            Chat("BCM Keputih", "Are you ready?", R.drawable.iv_category_4, "15:18", 0),
            Chat("Gor Pertamina", "I'm ready!", R.drawable.iv_category_3, "15:20", 0),
            Chat("Stadion ITS", "I'm ready too!", R.drawable.iv_category_2, "15:21", 0),
        )
    }

    Scaffold(
        topBar = {
            ChatHeader()
        },
        content = { paddingValues ->
            ChatContent(paddingValues, chats, navController)
        },
        bottomBar = {
            UserBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    navController.navigate(it) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    )
}

@Composable
fun ChatContent(paddingValues: PaddingValues, chats: List<Chat>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFF7F7F9))
    ) {
        items(chats) { chat ->
            ChatItem(chat, navController)
            Divider(color = Color(0xFFEAEAEA), thickness = 1.dp)
        }
    }
}

@Composable
fun ChatHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_inbox),
            contentDescription = "Inbox",
            tint = Color.Black
        )
        Text(
            text = "Inbox",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Composable
fun ChatItem(chat: Chat, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("user_chat_room/${chat.name}")
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(chat.image),
            contentDescription = chat.name,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = chat.message,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = chat.time,
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (chat.unreadCount > 0) {
                Text(
                    text = chat.unreadCount.toString(),
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color(0xFFFB6D3A), shape = CircleShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(navController: NavController, chatName: String) {
    val messages = remember {
        listOf(
            Message("Sam", "Are you ready?", "15:18", false),
            Message("Aurora Futsal", "Actually yes, let me see...", "15:19", true),
            Message("Aurora Futsal", "I'm ready!", "15:20", true),
            Message("Sam", "I'm ready too!", "15:21", false),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(chatName)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            ChatRoomContent(paddingValues, messages)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomContent(paddingValues: PaddingValues, messages: List<Message>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFF3F3F3))
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(messages) { message ->
                MessageItem(message)
            }
        }
        TextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Type a message") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun MessageItem(message: Message) {
    val backgroundColor = if (message.isSent) Color(0xFFFB6D3A) else Color.White
    val textColor = if (message.isSent) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = if (message.isSent) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Text(
                text = message.text,
                color = textColor
            )
            Text(
                text = message.time,
                fontSize = 12.sp,
                color = textColor.copy(alpha = 0.7f),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

data class Chat(
    val name: String,
    val message: String,
    val image: Int,
    val time: String,
    val unreadCount: Int
)

data class Message(
    val sender: String,
    val text: String,
    val time: String,
    val isSent: Boolean
)
