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
class RegisterViewModel @Inject constructor(
    application: Application,
    private val authenticationService: IAuthenticationService
) : BaseViewModel(application) {

    var mEmail by mutableStateOf("")
    var mPassword by mutableStateOf("")
    var mFullName by mutableStateOf("")

    var mIsMissingEmail by mutableStateOf(false)
    var mIsMissingPassword by mutableStateOf(false)
    var mIsMissingFullName by mutableStateOf(false)

    fun createAccount(navController: NavController) {
        if (!canCreateAccount()) return

        mIsLoading = true

        viewModelScope.launch {
            authenticationService.createUserWithEmailAndPassword(mEmail, mPassword).onSuccess {
                Toast.makeText(application, "User created!", Toast.LENGTH_LONG).show()
                navController.navigate(Routes.LoginView)
            }.onFailure { exception ->

                mIsLoading = false
                Toast.makeText(application, exception.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showIsMissingTextAlert(error: ErrorCode) {
        when (error) {
            ErrorCode.EmailIsMissing -> mIsMissingEmail = true
            ErrorCode.PasswordIsMissing -> mIsMissingPassword = true
            ErrorCode.FullNameIsMissing -> mIsMissingFullName = true
        }
    }

    fun canCreateAccount(): Boolean {
        if (mEmail.isEmpty())
            showIsMissingTextAlert(ErrorCode.EmailIsMissing)

        if (mPassword.isEmpty())
            showIsMissingTextAlert(ErrorCode.PasswordIsMissing)

        if (mFullName.isEmpty())
            showIsMissingTextAlert(ErrorCode.FullNameIsMissing)

        return mEmail.isNotEmpty() && mPassword.isNotEmpty() && mFullName.isNotEmpty()
    }
}