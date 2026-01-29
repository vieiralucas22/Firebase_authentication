package com.example.firebase_authentication.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.firebase_authentication.ui.theme.White

@Composable
fun HomeView(email: String?, isVerifiedEmail: Boolean?, uid: String?)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    )
    {
        Text(text = "Email: " + email.toString())
        Text(text = "IsVerifiedEmail: " + isVerifiedEmail.toString())
        Text(text = "UID: " + uid.toString())
    }
}