package com.example.firebase_authentication.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.firebase_authentication.enums.ErrorCode
import com.example.firebase_authentication.services.interfaces.IAuthenticationService
import com.example.firebase_authentication.ui.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val authenticationService: IAuthenticationService
) : BaseViewModel(application) {

    var mEmail by mutableStateOf("")
    var mPassword by mutableStateOf("")

    var mIsMissingEmail by mutableStateOf(false)
    var mIsMissingPassword by mutableStateOf(false)

    fun login(navController: NavController) {
        if (!canLogin()) return

        mIsLoading = true

        viewModelScope.launch {
            authenticationService.signInWithEmailAndPassword(mEmail, mPassword).onSuccess { user ->
                navController.navigate(Routes.HomeView + "/" + user.email + "/" + user.isVerifiedEmail + "/" + user.uid)

            }.onFailure { exception ->
                Toast.makeText(application, exception.message, Toast.LENGTH_LONG).show()
                mIsLoading = false

            }
        }
    }

    fun showIsMissingTextAlert(error: ErrorCode) {
        when (error) {
            ErrorCode.EmailIsMissing -> mIsMissingEmail = true
            ErrorCode.PasswordIsMissing -> mIsMissingPassword = true
            else -> {}
        }
    }

    fun canLogin(): Boolean {
        if (mEmail.isEmpty())
            showIsMissingTextAlert(ErrorCode.EmailIsMissing)

        if (mPassword.isEmpty())
            showIsMissingTextAlert(ErrorCode.PasswordIsMissing)

        return mEmail.isNotEmpty() && mPassword.isNotEmpty()
    }

}