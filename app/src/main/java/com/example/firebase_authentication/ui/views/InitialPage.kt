package com.example.firebase_authentication.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebase_authentication.R
import com.example.firebase_authentication.ui.theme.FirebaseColor
import com.example.firebase_authentication.ui.theme.FirebaseColor2
import com.example.firebase_authentication.ui.theme.FirebaseColor3
import com.example.firebase_authentication.ui.theme.Grey
import com.example.firebase_authentication.ui.theme.White
import com.example.firebase_authentication.ui.theme.White2

@Composable
fun InitialPage()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        FirebaseColor,
                        White,
                        FirebaseColor3
                    )
                )
            )
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(
            painter = painterResource(id = R.drawable.firebase),
            contentDescription = "Logo",
            Modifier.width(200.dp)
        )
        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = FirebaseColor
            )
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }

        OutlinedButton (
            onClick = {}, modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, FirebaseColor),colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,

            )
        ) {
            Text(text = "Create Account", fontSize = 18.sp, color = FirebaseColor)
        }
    }
}

@Preview
@Composable
fun InitialPagePreview()
{
    InitialPage()
}