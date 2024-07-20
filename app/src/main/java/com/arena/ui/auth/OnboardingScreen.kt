package com.arena.ui.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.ui.theme.ArenaTheme
import com.arena.ui.theme.Orange
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_onboarding_1),
            contentDescription = "Onboarding 1",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Onboarding",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus eleifend erat, ac egestas massa mattis id.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("onboarding_gate") }) {
            Text(text = "Next")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun OnboardingGate(navController: NavHostController) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val bottomNavController = rememberNavController(bottomSheetNavigator)
    var isRegisterSheetVisible by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Transparent,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        NavHost(navController = bottomNavController, startDestination = "main") {
            composable("main") {
                MainContent(
                    navController = navController,
                    onRegisterClick = { isRegisterSheetVisible = true }
                )
            }
            bottomSheet("register_sheet") {
                RegisterSheetContent(
                    onRegisterUserClick = {
                        isRegisterSheetVisible = false
                        navController.navigate("register_screen/3")
                    },
                    onRegisterMitraClick = {
                        isRegisterSheetVisible = false
                        navController.navigate("register_screen/2")
                    }
                )
            }
        }
    }

    LaunchedEffect(isRegisterSheetVisible) {
        if (isRegisterSheetVisible) {
            bottomNavController.navigate("register_sheet")
        } else {
            bottomNavController.popBackStack("register_sheet", true)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            isRegisterSheetVisible = false
        }
    }
}

@Composable
fun MainContent(navController: NavController, onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = R.drawable.iv_onboarding_2),
            contentDescription = "Onboarding Gate",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Get Started!",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus eleifend erat, ac egestas massa mattis id.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 64.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate("login_screen")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Color.White
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Masuk")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_login),
                        contentDescription = null
                    )
                }
            }
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Color.White
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Daftar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_register),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterSheetContent(
    onRegisterUserClick: () -> Unit,
    onRegisterMitraClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Daftar sebagai",
            style = MaterialTheme.typography.headlineMedium,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onRegisterUserClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "User")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onRegisterMitraClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Mitra")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    ArenaTheme {
        OnboardingScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingGate() {
    ArenaTheme {
        OnboardingGate(navController = rememberNavController())
    }
}

