package com.example.proyecto25febrero

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.proyecto25febrero.Screens.MainViewModel
import com.example.proyecto25febrero.navigation.AppNavigation
import com.example.proyecto25febrero.ui.theme.Proyecto25FebreroTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : MainViewModel by viewModels()
            val isLoading by viewModel.isLoading().observeAsState(false)
            val enviar by viewModel.enviar().observeAsState(false)
            Proyecto25FebreroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(
                        isLoading = isLoading,
                        enviar= enviar,
                        onClick = { viewModel.LoginWithGoogle(this@MainActivity) },
                    )

                }//fin del surface
            }//fin del theme
        }//fin del content
    }//fin del onCreate
    override fun onActivityResult(requestCode: Int, resultCode:Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        val viewModel : MainViewModel by viewModels()

        if(requestCode == 1){

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.finishLogin(task)
        }
    }
}//fin de la activity
