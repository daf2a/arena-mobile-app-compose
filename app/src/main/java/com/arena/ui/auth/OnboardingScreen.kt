package com.arena.ui.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arena.R
import com.arena.ui.theme.ArenaTheme
import com.arena.ui.theme.Orange
import com.arena.ui.theme.OrangeBg
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun OnboardingScreen1(navController: NavHostController) {
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
            text = "Onboarding 1",
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
        Button(onClick = { navController.navigate("onboarding_screen_2") }) {
            Text(text = "Next")
        }
    }
}

@Composable
fun OnboardingScreen2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_onboarding_2),
            contentDescription = "Onboarding 2",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Onboarding 2",
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
        Button(onClick = { navController.navigate("onboarding_gate_1") }) {
            Text(text = "Next")
        }
    }
}

@Composable
fun OnboardingGate1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_onboarding_1),
            contentDescription = "Onboarding Gate 1",
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
                    val role = 3
                    navController.navigate("onboarding_gate_2/$role")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeBg,
                    contentColor = Orange
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "User")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_login),
                        contentDescription = null
                    )
                }
            }
            Button(
                onClick = {
                    val role = 2
                    navController.navigate("onboarding_gate_2/$role")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = OrangeBg
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Mitra")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_register),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Composable
fun OnboardingGate2(navController: NavHostController, role: Int) {
    Log.d("Role", role.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_onboarding_2),
            contentDescription = "Onboarding Gate 2",
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
                    navController.navigate("login_screen/$role")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeBg,
                    contentColor = Orange
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Masuk")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_login),
                        contentDescription = null
                    )
                }
            }
            Button(
                onClick = {
                    navController.navigate("register_screen/$role")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = OrangeBg
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Daftar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_register),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen1() {
    ArenaTheme {
        OnboardingScreen1(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen2() {
    ArenaTheme {
        OnboardingScreen2(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingGate1() {
    ArenaTheme {
        OnboardingGate1(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingGate2() {
    ArenaTheme {
        OnboardingGate2(navController = rememberNavController(), role = 2)
    }
}

