package com.example.firebase_authentication.ui.views

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebase_authentication.R
import com.example.firebase_authentication.ui.theme.FirebaseColor
import com.example.firebase_authentication.ui.theme.FirebaseColor2
import com.example.firebase_authentication.ui.theme.FirebaseColor3
import com.example.firebase_authentication.ui.theme.Grey
import com.example.firebase_authentication.ui.theme.White
import com.example.firebase_authentication.viewmodels.LoginViewModel

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_firebase),
                contentDescription = "Logo image",
                Modifier.width(150.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Login in your account",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey
            )
        }

        Spacer(Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(), value = viewModel.mEmail, onValueChange = {
                viewModel.mEmail = it
                viewModel.mIsMissingEmail = false
            }, label = {
                Text(text = "Email")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                )
            }, colors = TextFieldDefaults.colors(
                focusedLabelColor = FirebaseColor2,
                focusedIndicatorColor = FirebaseColor2,
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedLeadingIconColor = FirebaseColor2,
                cursorColor = FirebaseColor2,
                errorLabelColor = FirebaseColor3,
                errorIndicatorColor = FirebaseColor3,
                errorContainerColor = White,
                errorLeadingIconColor = FirebaseColor3,
            ),
            isError = viewModel.mIsMissingEmail
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.mPassword,
            onValueChange = {
                viewModel.mPassword = it
                viewModel.mIsMissingPassword = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {
                Text(text = "Password")
            }, visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            }, colors = TextFieldDefaults.colors(
                focusedLabelColor = FirebaseColor2,
                focusedIndicatorColor = FirebaseColor2,
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedLeadingIconColor = FirebaseColor2,
                cursorColor = FirebaseColor2,
                errorLabelColor = FirebaseColor3,
                errorIndicatorColor = FirebaseColor3,
                errorContainerColor = White,
                errorLeadingIconColor = FirebaseColor3,
            ),
            isError = viewModel.mIsMissingPassword
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.login(navController)
            },
            modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = FirebaseColor
            )
        ) {
            Text(text = "Login")
        }

    }
}
