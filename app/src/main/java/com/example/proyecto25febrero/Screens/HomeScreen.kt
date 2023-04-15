package com.example.proyecto25febrero.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.proyecto25febrero.R
import com.example.proyecto25febrero.navigation.AppScrens

@Composable
fun HomeScreen(navController: NavController){
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.Home_Screen),
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = {
            navController.navigate(route = AppScrens.LoginScreen.route)
        }) {
            Text(text = "Volver")
        }
    }
}
