package com.example.firebase_authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_authentication.ui.Routes
import com.example.firebase_authentication.ui.views.HomeView
import com.example.firebase_authentication.ui.views.InitialView
import com.example.firebase_authentication.ui.views.LoginScreen
import com.example.firebase_authentication.ui.views.RegisterScreen
import com.example.firebase_authentication.viewmodels.LoginViewModel
import com.example.firebase_authentication.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val loginViewModel : LoginViewModel by viewModels()
            val registerViewModel : RegisterViewModel by viewModels()

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

                composable (Routes.HomeView + "/{email}/{isVerifiedEmail}/{uid}")
                {
                    val email = it.arguments?.getString("email")
                    val isVerifiedEmail = it.arguments?.getBoolean("isVerifiedEmail")
                    val uid = it.arguments?.getString("uid")

                    HomeView(email, isVerifiedEmail, uid)
                }
            })
        }
    }
}
