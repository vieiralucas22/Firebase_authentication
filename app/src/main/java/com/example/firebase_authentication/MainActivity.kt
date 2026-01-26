package com.example.firebase_authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_authentication.ui.Routes
import com.example.firebase_authentication.ui.views.InitialView
import com.example.firebase_authentication.ui.views.LoginScreen
import com.example.firebase_authentication.ui.views.RegisterScreen
import com.example.firebase_authentication.viewmodels.LoginViewModel
import com.example.firebase_authentication.viewmodels.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val loginViewModel = ViewModelProvider.create(this)[LoginViewModel::class.java]
            val registerViewModel = ViewModelProvider.create(this)[RegisterViewModel::class.java]

            NavHost(navController = navController, startDestination = Routes.InitialView, builder = {

                composable (Routes.InitialView)
                {
                    InitialView(navController)
                }

                composable (Routes.LoginView)
                {
                    LoginScreen(navController, loginViewModel)
                }

                composable (Routes.RegisterView)
                {
                    RegisterScreen(navController, registerViewModel)
                }
            })
        }
    }
}
